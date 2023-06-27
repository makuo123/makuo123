package com.stock.util.sign;

import org.springframework.stereotype.Component;

import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.X509EncodedKeySpec;
/**
 * @Description
 * @Author makuo
 * @Date 2023/3/20 13:50
 **/
@Component
public class SignUtil {

    private static final String KEY_ALGORITHM = "RSA";
    private static final String SIGNATURE_ALGORITHM = "SHA256withRSA";
    private static final String PUBLIC_KEY_STRING = ""; // 公钥字符串

    public boolean verify(String signature, String data) {
        try {
            PublicKey publicKey = getPublicKey();
            Signature sig = Signature.getInstance(SIGNATURE_ALGORITHM);
            sig.initVerify(publicKey);
            sig.update(data.getBytes(StandardCharsets.UTF_8));
            return sig.verify(DatatypeConverter.parseBase64Binary(signature));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public PublicKey getPublicKey() throws Exception {
        byte[] keyBytes = DatatypeConverter.parseBase64Binary(PUBLIC_KEY_STRING);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance(KEY_ALGORITHM);
        return kf.generatePublic(spec);
    }

    public String generateSignature(String data) {
        try {
            PrivateKey privateKey = getPrivateKey();
            Signature sig = Signature.getInstance(SIGNATURE_ALGORITHM);
            sig.initSign(privateKey);
            sig.update(data.getBytes(StandardCharsets.UTF_8));
            byte[] signatureBytes = sig.sign();
            return DatatypeConverter.printBase64Binary(signatureBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private PrivateKey getPrivateKey() {
        return null;
    }
}

