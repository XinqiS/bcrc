package com.sooka.common.upload;


import com.sooka.common.upload.bean.UploadBean;
import com.sooka.common.utils.CmsUtil;
import com.sooka.common.utils.FFmpegUtils;
import com.sooka.common.utils.JsonUtil;
import com.sooka.common.utils.TranscodeConfig;
import com.sooka.module.web.system.service.AttachmentService;
import com.sooka.mybatis.model.TSysAttachment;
import com.sooka.common.exception.CmsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.HttpStatus;


@Controller
public class UploadController {

    @Autowired
    private UploadComponent uploadComponent;

    @Autowired
    private AttachmentService attachmentService;

    @RequestMapping("/uploads")
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile multipartFile,
                         HttpServletRequest request){
        UploadBean result = uploadComponent.uploadFile(multipartFile,request);

        return JsonUtil.toUploadSUCCESS("上传成功！",result.getFileUrl());
    }

    @RequestMapping("/ftpuploads")
    @ResponseBody
    public String ftpupload(@RequestParam("file") MultipartFile multipartFile,
                         HttpServletRequest request){
        UploadBean result = uploadComponent.ftpuploadFile(multipartFile,request);

        return JsonUtil.toUploadSUCCESS("上传成功！",result.getFileUrl());
    }


    @RequestMapping("/uploads/wangEditorUpload")
    @ResponseBody
    public String WangEditorUpload(@RequestParam("file") MultipartFile multipartFile,
                         HttpServletRequest request) {
        UploadBean result = uploadComponent.uploadFile(multipartFile,request);
        return result.getFileUrl();

    }

    @RequestMapping("/uploads/CKEditorUpload")
    @ResponseBody
    public String CKEditorUpload(@RequestParam("upload") MultipartFile multipartFile,
                                 HttpServletRequest request) {
        StringBuffer sb=new StringBuffer();
        UploadBean result = uploadComponent.uploadFile(multipartFile,request);
        sb.append("<script type=\"text/javascript\">");
        sb.append("window.parent.CKEDITOR.tools.callFunction("+ request.getParameter("CKEditorFuncNum") + ",'" +result.getFileUrl()+"','')");
        sb.append("</script>");
        return sb.toString();

    }

    @RequestMapping(value = "/res/{key}.{resType}")
    public void showAttr(@PathVariable(value = "key",required = false) String key,
                         @PathVariable(value = "resType",required = false) String resType,
                         HttpServletRequest request,HttpServletResponse response) {
        if(CmsUtil.isNullOrEmpty(key)) {
            return;
        }
        TSysAttachment attachment = attachmentService.findByKey(key);
        if(CmsUtil.isNullOrEmpty(attachment)||!attachment.getFileName().contains(resType)) {
            throw new CmsException("文件不存在！");
        }
        try {
            response.reset();
            /* 判断浏览器类型，设置文件下载名 */
            String userAgent = request.getHeader("user-agent").toLowerCase();
            if (userAgent.contains("msie") || userAgent.contains("like gecko")||userAgent.contains("trident")||userAgent.contains("edge")) {
                attachment.setOriginalFilename(URLEncoder.encode(attachment.getOriginalFilename(), "UTF-8"));
            } else {
                attachment.setOriginalFilename(new String(attachment.getOriginalFilename().getBytes("utf-8"), "ISO8859-1"));
            }
            response.setHeader("Content-disposition", "attachment;filename="+attachment.getOriginalFilename());
            response.setContentType(attachment.getFileExtname());
            FileCopyUtils.copy(new FileInputStream(uploadComponent.getUploadPath()+attachment.getFilePath()), response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }
    @Value("${app.video-folder}")
    private String videoFolder;

    private Path tempDir = Paths.get(System.getProperty("java.io.tmpdir"));

    /**
     * 上传视频进行切片处理，返回访问路径
     * @param video
     * @param transcodeConfig
     * @return
     * @throws IOException
     */

    @RequestMapping("/qpupload")
    @ResponseBody
    public String qpupload(@RequestParam("file") MultipartFile multipartFile,
                            HttpServletRequest request){
        UploadBean result = uploadComponent.qpuploadFile(multipartFile,request,tempDir,videoFolder);

        return JsonUtil.toUploadSUCCESS("上传成功！",result.getFileUrl());
    }

}
