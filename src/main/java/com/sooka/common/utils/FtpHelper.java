package com.sooka.common.utils;


import jodd.datetime.JDateTime;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Component
public abstract class FtpHelper implements Closeable {

    private FTPClient ftpClient = null;
    boolean _isLogin = false;

    /**
     * ftp 匿名登录
     */
    public JSONResult anonymity(){
        //如果没有设置ftp用户可将username设为anonymous，密码为任意字符串

        return initFtpClient(loadProperties().get("ftp_hostname"), Integer.parseInt(loadProperties().get("ftp_port")), "anonymous", "");
    }

    /**
     * ftp登录
     */
    public JSONResult login() {
        return initFtpClient(loadProperties().get("ftp_hostname"), Integer.parseInt(loadProperties().get("ftp_port")), loadProperties().get("ftp_username"), loadProperties().get("ftp_password"));
    }

    /**
     * 初始化ftp服务器
     */
    public JSONResult initFtpClient(String hostname,int port, String username, String password) {

        ftpClient = new FTPClient();
        ftpClient.setControlEncoding("utf-8");
        try {
            System.out.println("connecting...ftp服务器:"+hostname+":"+port);
            //连接ftp服务器
            ftpClient.connect(hostname, port);
            //登录ftp服务器
            ftpClient.login(username, password);
            //是否成功登录服务器
            int replyCode = ftpClient.getReplyCode();
            if(!FTPReply.isPositiveCompletion(replyCode)){
                return JSONResult.errorMsg("connect failed...ftp服务器:"+hostname+":"+port);
            }
            System.out.println("connect successful...ftp服务器:"+hostname+":"+port);
            return JSONResult.ok();
        } catch (IOException e) {
            e.printStackTrace();
            return JSONResult.errorMsg("初始化ftp失败：" + e.getMessage());
        }
    }

    /**
     *
     * ftp上传文件 (使用inputstream)
     * @param ftpDirName ftp 目录名
     * @param ftpFileName ftp目标文件
     * @return true||false
     */

    public void uploadFile(InputStream uploadInputStream
            , String ftpFileName) {

       /* Debug.printFormat("准备上传 [流] 到 ftp://{0}/{1}"
                ,loadProperties().get("ftp_DirName")
                ,ftpFileName);*/
        Debug.printFormat("准备上传 [流] 到 ftp://{0}/{1}"
                ,"upload"
                ,ftpFileName);
//		if(StringExtend.isNullOrEmpty(ftpDirName))
//			ftpDirName="/";
        if(StringExtend.isNullOrEmpty(ftpFileName)){
            complete(JSONResult.errorMsg("上传文件必须填写文件名！"));
            return;
        }

        try {

            if (!login().isOK()){
                complete(login());
                return;
            }

            // 设置上传目录(没有则创建)
            String newName=this.getNewFileName();
            /*if (!createDir(loadProperties().get("ftp_DirName")).isOK()){
                complete(createDir(loadProperties().get("ftp_DirName")));
                return;
            }*/
           if (!createDir(newName).isOK()){
                complete(createDir(newName));
                return;
            }

//            if(createDir(ftpDirName)){
            ftpClient.setBufferSize(1024);
            //解决上传中文 txt 文件乱码
            ftpClient.setControlEncoding("utf-8");
            FTPClientConfig conf = new FTPClientConfig(FTPClientConfig.SYST_NT);
            conf.setServerLanguageCode("zh");

            // 设置文件类型（二进制）
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            // 上传
            String fileName = new String(ftpFileName.getBytes("utf-8"),"utf-8");
            if(!ftpClient.storeFile(fileName, uploadInputStream)){
                complete(JSONResult.errorMsg("文件上传失败：{0}/{1}"));
                return;
            }

            uploadInputStream.close();
            Debug.printFormat("文件上传成功：{0}/{1}"
                    ,loadProperties().get("ftp_DirName")
                    ,ftpFileName);

            complete(JSONResult.ok("文件上传成功：{0}/{1}"));
//            }else {
//                complete(JSONResult.errorMsg("切入FTP目录失败："+ftpDirName));
//            }

        } catch (Exception e) {
            e.printStackTrace();
            complete(JSONResult.errorMsg("文件上传失败：{0}/{1}：" + e.getMessage()));
        } finally {
            closeFtpConnection();
            if (uploadInputStream != null) {
                try {
                    uploadInputStream.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
    public  String getNewFileName(){
        JDateTime jt = new JDateTime();
        return File.separator + "upload" + File.separator + jt.getYear()
                + File.separator + jt.getMonth() + File.separator + jt.getDay() + File.separator ;
    }
    /**
     * 创建目录(有则切换目录，没有则创建目录)
     * @param dir
     * @return
     */
    private JSONResult createDir(String dir){
        if(StringExtend.isNullOrEmpty(dir)){
            return JSONResult.errorMsg("目录为空，无法创建或切入");
        }
        String d;
        try {
            //目录编码，解决中文路径问题
            d = new String(dir.getBytes("utf-8"),"utf-8");
            //尝试切入目录
            if(ftpClient.changeWorkingDirectory(d)){
                return JSONResult.ok("切入目录成功");
            }
            dir = StringExtend.trimStart(dir, "/");
            dir = StringExtend.trimEnd(dir, "/");
            String[] arr =  dir.split("/");
            StringBuilder sbfDir=new StringBuilder();
            //循环生成子目录
            for(String s : arr){
                sbfDir.append("/");
                sbfDir.append(s);
                //目录编码，解决中文路径问题
                d = new String(sbfDir.toString().getBytes("utf-8"),"utf-8");
                //尝试切入目录
                if(ftpClient.changeWorkingDirectory(d)){
                    continue;
                }
                if(!ftpClient.makeDirectory(d)){
                    return JSONResult.errorMsg("[失败]ftp创建目录："+sbfDir.toString());
                }
                System.out.println("[成功]创建ftp目录："+sbfDir.toString());
            }
            //将目录切换至指定路径
            if(!ftpClient.changeWorkingDirectory(d)){
                return JSONResult.errorMsg("切换目录失败："+d);
            }
            return JSONResult.ok("切换目录成功："+d);
        } catch (Exception e) {
            e.printStackTrace();
            return JSONResult.errorMsg("[失败]："+e.getMessage());
        }
    }

    /**
     * 销毁ftp连接
     */
    @Override
    public void close() {
        closeFtpConnection();
    }

    /**
     * 销毁ftp连接
     */
    private void closeFtpConnection() {
        _isLogin = false;
        if (ftpClient != null) {
            if (ftpClient.isConnected()) {
                try {
                    ftpClient.logout();
                    ftpClient.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    /**
     * 加载配置文件
     * */
    /*@Value("${ftp_hostname}")
    private  String ftp_hostname ;
    @Value("${ftp_port}")
    private  String ftp_port ;
    @Value("${ftp_username}")
    private  String ftp_username ;
    @Value("${ftp_password}")
    private  String ftp_password ;
    @Value("${ftp_DirName}")
    private  String ftp_DirName ;

    ftp_hostname=127.0.0.1
ftp_port=21
ftp_username=bcrc
ftp_password=bcrc123!
ftp_DirName=/UploadFiles/
     */

    public Map<String,String> loadProperties() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd/");
        Date d = new Date();
        String dateNowStr = sdf.format(d);
        Map<String,String> conf=new HashMap<>();
        Properties prop = new Properties();
        InputStream in;
        try {
			in = this.getClass().getResourceAsStream("/application.properties");
			prop.load(in);     ///加载属性列表
			conf.put("ftp_hostname", prop.getProperty("ftp_hostname"));//ftp地址
			conf.put("ftp_port", prop.getProperty("ftp_port"));//ftp端口
			conf.put("ftp_username", prop.getProperty("ftp_username"));//ftp用户名
			conf.put("ftp_password", prop.getProperty("ftp_password"));//ftp密码
			conf.put("ftp_DirName", prop.getProperty("ftp_DirName")+dateNowStr);//ftp保存路径
			in.close();
            //in = this.getClass().getResourceAsStream("/app.properties");
            //prop.load(in);     ///加载属性列表
           /* conf.put("ftp_hostname", ftp_hostname);//ftp地址
            conf.put("ftp_port", ftp_port);//ftp端口
            conf.put("ftp_username", ftp_username);//ftp用户名
            conf.put("ftp_password", ftp_password);//ftp密码
            conf.put("ftp_DirName", ftp_DirName+dateNowStr);//ftp保存路径*/
            //in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conf;
    }
    /**
     * 完成后回调
     * @param jsonResult 参数
     */
    public abstract void complete(JSONResult jsonResult);

}

