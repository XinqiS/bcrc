package com.sooka.common.utils.encrypt;

public  class Encrypt {

    /**
     * 随机秘钥长度
     */
	private static final int RANDOM_KEY_LENGTH =8;

    /**
     * 共有秘钥
     */
    private static final String PUBLIC_KEY ="123123";

    /**
     * 加密
     * @param encode 要加密的字符串
     * @param privateKey 私有秘钥
     * @return 返回加密后的字符串
     * @throws Exception 异常
     */
	public static String encode(String encode,String privateKey) throws Exception{
		
		//获取随机秘钥
//		String randomKeyChars  = getRandomKey(RANDOM_KEY_LENGTH );
		String randomKeyChars  = "12345678";
		String publicKeyChars  = bufferWithString(PUBLIC_KEY );
		String privateKeyChars = bufferWithString(privateKey);
		StringBuilder contentChars = new StringBuilder(bufferWithString(encode));

		// 第一步先用随机密钥 从前往后 编码
		int keyIndex = 0;
		for(int i = 0;i<contentChars.length();i++){

			int c = contentChars.charAt(i);
			char randomChar = randomKeyChars.charAt(keyIndex);
			 // 如果密钥循环一遍后 从0开始重新循环
            if (++keyIndex >= randomKeyChars.length()){
                keyIndex = 0;
            }

            c += randomChar - 32;
            c = c > 126 ? c - 94 : c;
            contentChars.setCharAt(i, (char)c);
		}

		// 第二步先用随机密钥 从后往前 编码
		keyIndex = 0;
	    for (int i  = contentChars.length() - 1; i >= 0; i--) {
	    	int c = contentChars.charAt(i);
			char randomChar = randomKeyChars.charAt(keyIndex);
			// 如果密钥循环一遍后 从0开始重新循环
            if (++keyIndex >= randomKeyChars.length()){
                keyIndex = 0;
            }


	        c += randomChar - 32;
	        c = c > 126 ? c - 94 : c;
            contentChars.setCharAt(i, (char)c);
	    }

		 // 将随机密钥附加到内容顶部
	     contentChars.insert(0, randomKeyChars);
	    
	     // 在顶部附加随机密钥长度
        for (int i = 0; i < 32; i += 4) {
            int num = (RANDOM_KEY_LENGTH  >> i) & 0xF;
            char numChar = (char)(num + (num > 9 ? 55 : 48));
            contentChars.insert(0, numChar);
        }
		
        // 第三步再用私有密钥 从后往前 编码
        keyIndex = 0;
        for (int i = contentChars.length() - 1; i >=0 ; i--)
        {
            int c = contentChars.charAt(i);
			char privateChar = privateKeyChars.charAt(keyIndex);

            if (++keyIndex >= privateKeyChars.length()){
                keyIndex = 0;
            }


            c += privateChar - 32;
            c = c > 126 ? c - 94 : c;
            contentChars.setCharAt(i, (char)c);
        }
        
     // 第四步最后用公有密钥 从前往后 编码
        keyIndex = 0;
        for (int i = 0; i < contentChars.length(); i++)
        {
        	
            int c = contentChars.charAt(i);
			char publicChar = publicKeyChars.charAt(keyIndex);

            // 如果密钥循环一遍后 从0开始重新循环
            if (++keyIndex >= publicKeyChars.length()){
                keyIndex = 0;
            }


            c += publicChar - 32;
            c = c > 126 ? c - 94 : c;
            contentChars.setCharAt(i, (char)c);
        }
        
		return contentChars.toString();
	}

    /**
     * 解密
     * @param target 要解密的字符串
     * @param privateKey 私有秘钥
     * @return 返回解密后的字符串
     * @throws Exception 异常
     */
	public static String decode(String target,String privateKey) throws Exception{

        //共有秘钥
		String publicKeyChars  = bufferWithString(PUBLIC_KEY );
        //共有秘钥
		String privateKeyChars  = bufferWithString(privateKey);
		StringBuilder contentChars = new StringBuilder(bufferWithString(target));
		
		
		// 解密共有密钥 从前往后 减编码
        int keyIndex = 0;
        for (int i = 0;i<contentChars.length();i++)
        {
        	
    	    int t = contentChars.charAt(i);
  			char publicChar = publicKeyChars.charAt(keyIndex);

            if (++keyIndex >= publicKeyChars.length()){
                keyIndex = 0;
            }


            t -= publicChar - 32;
            t = t < 32 ? t + 94 : t;
            contentChars.setCharAt(i, (char)t);
        }
        // 解密私有密钥 从后往前 减编码
        keyIndex = 0;
        for (int i = contentChars.length()-1; i >= 0; i--) {
        	
    	    int t = contentChars.charAt(i);
   			char privateChar = privateKeyChars.charAt(keyIndex);
   			
            // 如果密钥循环一遍后 从0开始重新循环
            if (++keyIndex >= privateKeyChars.length()) { 
            		keyIndex = 0;
            	}
            t -= privateChar - 32;
            t = t < 32 ? t + 94 : t;
            contentChars.setCharAt(i, (char)(t & 0x7F));
        }
		
        // 解密随机密钥
        int length = 0;
        for (int i = 0; i < 8; i++) {
    		int t = contentChars.charAt(i) - 48;
            t = t < 10 ? t : t - 7;
            length += t << (28 - i * 4);
        }
        
        StringBuffer randomKeyChars = new StringBuffer(contentChars.substring(8, length+8));
        
        contentChars.delete(0, length+8);
        
        // 第三步先用随机密钥 从后往前 减编码
        keyIndex = 0;
        for (int i  = contentChars.length() - 1; i >= 0; i--) {
        	
        	int c = contentChars.charAt(i);
   			char randomChar = randomKeyChars.charAt(keyIndex);
        	
            
            // 如果密钥循环一遍后 从0开始重新循环
            if (++keyIndex >= randomKeyChars.length()) {
        		keyIndex = 0; 
			}
            c -= randomChar - 32;
            c = c < 32 ? c + 94 : c;
            contentChars.setCharAt(i, (char)(c & 0x7F));
        }
        
     // 第四步再用随机密钥 从前往后 减编码
        keyIndex = 0;
        for (int i = 0; i < contentChars.length(); i++) {
        	int c = contentChars.charAt(i);
   			char randomChar = randomKeyChars.charAt(keyIndex);
        	
            
            // 如果密钥循环一遍后 从0开始重新循环
            if (++keyIndex >= randomKeyChars.length()) {
        		keyIndex = 0; 
			}
            c -= randomChar - 32;
            c = c < 32 ? c + 94 : c;
            contentChars.setCharAt(i, (char)(c & 0x7F));
        }
		return contentChars.toString();
	}
	
	//- 给中文 编码
   public static String bufferWithString(String string){
       
	   StringBuffer buffer = new StringBuffer();
	   for(int i = 0;i<string.length();i++){
			int chr = string.codePointAt(i);
			char chr1 = string.charAt(i);
			
			if (chr > 127) {
				buffer.append("\\u");           // 代表\
                // 2字节 16位 (1字节8位)  转16进制后为4字节字符串
                // 0xA5F8  A5F8
                // char >> 0   15
                
                for (int j = 0; j < 16; j+=4) {
                    int k = chr >> (12 - j);
                    int c = (k & 0xF);
                    buffer.append(Integer.toHexString(c).toUpperCase());
                }
                
			} else {
				buffer.append(chr1);
			}
		}
	   
	   return buffer.toString();
    }
   
   // 生成随机数
	public static String getRandomKey(int length){
		StringBuilder strb = new StringBuilder();
		for(int i=0;i<length;i++ ) {	
			strb.append((int)(10*(Math.random())));
		}
		return strb.toString();
	}

}
