package com.sooka.common.utils.encrypt;

/******************************
 * Created by yanhongliang
 * 2019-06-04 11:00
 ******************************/

public class EMain {

    public static void  main(String[] args) throws Exception {

        String privateKey = "privateKey";

        Encrypt encrypt = new Encrypt();

        String str = "yhliang";

        String encodeJSON = Encrypt.encode(str, privateKey);

        System.out.println("------原始------"+str);
        System.out.println("------加密------"+encodeJSON);
        System.out.println("------加密------"+encodeJSON);
        System.out.println("===============================");
        System.out.println("------解密------"+Encrypt.decode(encodeJSON, privateKey));

        str = "123456";

        encodeJSON = Encrypt.encode(str, privateKey);

        System.out.println("------原始------"+str);
        System.out.println("------加密------"+encodeJSON);
        System.out.println("------加密------"+encodeJSON);
        System.out.println("===============================");
        System.out.println("------解密------"+Encrypt.decode(encodeJSON, privateKey));


    }


}
