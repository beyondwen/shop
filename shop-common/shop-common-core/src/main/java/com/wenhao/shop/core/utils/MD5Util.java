package com.wenhao.shop.core.utils;

import java.security.MessageDigest;

public class MD5Util {
    /**加盐方式
     * 生成32位md5码
     * @param password
     * @return
     */
    public static String md5Password(String password){
        try{
            //得到一个信息摘要器
            MessageDigest digest=MessageDigest.getInstance("md5");
            byte[] result=digest.digest(password.getBytes());
            StringBuffer buffer=new StringBuffer();
            //把给一个byte做一个与运算
            for(byte b:result){
                //与运算
                int number=b&0xff;//加盐
                String str=Integer.toHexString(number);
                if (str.length() == 1) {
                    buffer.append("0");
                }
                buffer.append(str);
            }
            //标准的md5加密后结果
            return buffer.toString();
        }catch (Exception e){
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 普通方式
     * @param key
     * @return
     */
    public static String MD5(String key){
        char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        try {
            byte[] btInput=key.getBytes();
            //获得MD5摘要算法的MessageDigest对象
            MessageDigest mdInst=MessageDigest.getInstance("MD5");
            //使用指定的字节更新摘要
            mdInst.update(btInput);
            //获得密文
            byte[] md=mdInst.digest();
            int j=md.length;
            char str[]=new char[j*2];
            int k=0;
            for (int i=0;i<j;i++){
                byte byte0=md[i];
                str[k++]=hexDigits[byte0>>>4&0xf];
                str[k++]=hexDigits[byte0&0xf];
            }
            return new String(str);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
