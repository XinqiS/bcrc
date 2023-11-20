package com.sooka.common.utils;

import java.io.InputStream;
import java.util.ArrayList;

public class FtpQueue {

    public interface OnComplete {
        void onComplete(JSONResult jsonResult);
    }

    private static ArrayList<Data> datas;
    private static FtpHelper ftpHelper;
    static {
        datas = new ArrayList<>();
    }

    public static void addUpload(InputStream inputStream, String ftpDirName,
                                 String ftpFileName, OnComplete onComplete){

        datas.add(new Data(inputStream, ftpFileName, onComplete));

        System.err.println("-----FtpQueue count-----"+datas.size());

        upload();

    }

    private static void upload(){

        if (datas.size() == 0){
            return;
        }

        if (ftpHelper != null){
            return;
        }

        final Data data = datas.remove(0);

        ftpHelper = new FtpHelper() {
            @Override
            public void complete(JSONResult jsonResult) {
                ftpHelper = null;
                data.onComplete.onComplete(jsonResult);
                upload();
            }
        };

        ftpHelper.uploadFile(data.inputStream, data.ftpFileName);

    }

    private static class Data{

    	InputStream inputStream;
        String ftpFileName;
        OnComplete onComplete;

        Data(InputStream inputStream,
             String ftpFileName, OnComplete onComplete){

            this.inputStream = inputStream;
            this.ftpFileName = ftpFileName;
            this.onComplete = onComplete;

        }

    }

    public static int getCount() {
        return datas.size();
    }

    public static void clean() {
        if (ftpHelper != null) {
            ftpHelper.close();
            datas.clear();
            ftpHelper = null;
        }
    }

}
