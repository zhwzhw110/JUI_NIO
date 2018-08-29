package com.zhw.sign;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;

/**
 * @author <a href='mailto:zhw@xxlc360.com'>ZhangHaiWen</a>
 * @create 2018/8/8 0008
 * @desc 验签
 **/
public class SignTest {
    public static final String  SIGN_ALGORITHMS = "SHA1withDSA";
    public static final String  CHAR_SET = "UTF-8";

    public static void main(String[] args) {
        try {
            getPrivateKey("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDPg0O4rPQJL1O+jqJ4rBjFVNRAuDmBSoii9pYfPQBaescCVY0irkWWoLyfTT65TjvnPpOx+IfNzBTlB13qCEFm7algREoeUHjFgFNHiXJ2LK/R0+VWgXe5+EDFfbrFCPnmLKG3OcKDGQszP0VOf6VVTM1t56CpgaRMm1/+Tzd2TQIDAQAB");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static PrivateKey getPrivateKey(String key) throws Exception {
        byte[] decodeKey =key.getBytes();
        PKCS8EncodedKeySpec x509= new PKCS8EncodedKeySpec(decodeKey);//创建x509证书封装类
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");//指定RSA
        PrivateKey  privateKey = keyFactory.generatePrivate(x509);//生成私钥
        return privateKey;
    }


}
