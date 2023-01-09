package com.stock.util.sign;

import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;

public class SignUtil {

    public static final String PRIVATE_KEY = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBALF7TjSytAbpyheMD17VPH4nn5vNih9IodfUY0IJs97s+FEm3rccOMCoLp5ihLT298zIqyF4yk/QyW6dmG++nifPN4aUilujAnrmjT/J0Pj0q7wbrrzYRaZ8U7l+3QnWTf0A3Bzoz02iQGrj9yYZLqGvZ9Ppzx9JDz4g2UHO6K25AgMBAAECgYEAjr3yLzYOzoSXcgrGjeoIRhpOJzvPO8vpxrFdx04QaVnDeVk+xJyhFGnvXJk1zGnY8M3vvPLuG/V7HEkJwc3YWis74j/Xoxqq2Bb3mU6SGtwsj8qoW8GhmbKZRu8VI9EUs0d7BkRnpANmvMG7PkPFMS/wKZFoHnzBVARc/x9/ioECQQDaDSldHiJ2A9gd9/xtkC3waVr7UW4EFYan6cHLDPgnxGz0ZVOpYh/Pfc1a8vWJpI2AgRkfyJzYOvC/D3I+K/LpAkEA0F6kFsy/rhcTpebaI/++Oiib7WQcQSUbfdmusUC6q46+QsMeIQIpOAHnf3y6JobuirzlJJ303m4UxaAzI2MCUQJARHZ3c04/4bAJTjeLoVbwpZhewaPqmAuuMNB7UWasD8hjsUSTRQ6TEFHehVzdbS8mYp+mJM2XOWkgJ80QwKaJ6QJBAL8lkrG3sj8pvER8ubA8c202w5PKOoEoYUfZ4X+JObUXhUIJbCIrsVRHqu4wpTOipEFxd2NX48IIidWhWEgmE4ECQAgkNX04Ud7UCFkgEv4SguXW+TTl6N09dOPtsZ1vHWK9whc261RJkMS5PBKwfVS2A9MP/Qyd4Rnr7twqbPMvvAA=";
    public static final String PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCxe040srQG6coXjA9e1Tx+J5+bzYofSKHX1GNCCbPe7PhRJt63HDjAqC6eYoS09vfMyKsheMpP0MlunZhvvp4nzzeGlIpbowJ65o0/ydD49Ku8G6682EWmfFO5ft0J1k39ANwc6M9NokBq4/cmGS6hr2fT6c8fSQ8+INlBzuituQIDAQAB";

    public static void main(String[] args) {
        /*RSA rsa = new RSA();
        // 获得私钥
//        System.out.println(rsa.getPrivateKey());
        System.out.println(rsa.getPrivateKeyBase64());
        // 获得公钥
//        System.out.println(rsa.getPublicKey());
        System.out.println(rsa.getPublicKeyBase64());
        //公钥加密，私钥解密
        byte[] encrypt = rsa.encrypt(StrUtil.bytes("我是一段测试aaaa", CharsetUtil.CHARSET_UTF_8), KeyType.PublicKey);
        byte[] decrypt = rsa.decrypt(encrypt, KeyType.PrivateKey);

        //Junit单元测试
        Assert.assertEquals("我是一段测试aaaa", StrUtil.str(decrypt, CharsetUtil.CHARSET_UTF_8));

        //私钥加密，公钥解密
        byte[] encrypt2 = rsa.encrypt(StrUtil.bytes("我是一段测试aaaa", CharsetUtil.CHARSET_UTF_8), KeyType.PrivateKey);
        byte[] decrypt2 = rsa.decrypt(encrypt2, KeyType.PublicKey);*/

        //Junit单元测试
        //Assert.assertEquals("我是一段测试aaaa", StrUtil.str(decrypt2, CharsetUtil.CHARSET_UTF_8));
        /*KeyPair pair = SecureUtil.generateKeyPair("RSA");
        PrivateKey aPrivate = pair.getPrivate();
        PublicKey aPublic = pair.getPublic();
        System.out.println(aPrivate.getEncoded());
        System.out.println(aPublic.getEncoded());*/


        System.out.println(decrypt(encrypt()));
    }

    // 公钥加密
    public static String encrypt() {
        RSA rsa = new RSA(null,PUBLIC_KEY);
        return rsa.encryptBase64("这是一条加密信息", KeyType.PublicKey);
    }

    // 私钥解密
    public static String decrypt(String token) {
        RSA rsa = new RSA(PRIVATE_KEY, null);
        return rsa.decryptStr(token, KeyType.PrivateKey);
    }
}
