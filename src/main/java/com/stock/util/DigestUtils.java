package com.stock.util;

/**
 * @Description
 * @Author makuo
 * @Date 2023/3/14 19:02
 **/
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.binary.StringUtils;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DigestUtils {


    public static void main(String[] args) {
        /**
         * 1、先字符串进行md5加密
         * 2、再对md5进行sha-1加密
         */
        String s = md51("123456");
        System.out.println(s);
    }

    private static final int STREAM_BUFFER_LENGTH = 1024;
    private final MessageDigest messageDigest;

    public static byte[] digest(MessageDigest messageDigest, InputStream data) throws IOException {
        return updateDigest(messageDigest, data).digest();
    }

    public static MessageDigest getDigest(String algorithm) {
        try {
            return MessageDigest.getInstance(algorithm);
        } catch (NoSuchAlgorithmException var2) {
            throw new IllegalArgumentException(var2);
        }
    }

    public static MessageDigest getDigest(String algorithm, MessageDigest defaultMessageDigest) {
        try {
            return MessageDigest.getInstance(algorithm);
        } catch (Exception var3) {
            return defaultMessageDigest;
        }
    }

    public static MessageDigest getMd2Digest() {
        return getDigest("MD2");
    }

    public static MessageDigest getMd5Digest() {
        return getDigest("MD5");
    }

    public static MessageDigest getSha1Digest() {
        return getDigest("SHA-1");
    }

    public static MessageDigest getSha256Digest() {
        return getDigest("SHA-256");
    }

    public static MessageDigest getSha384Digest() {
        return getDigest("SHA-384");
    }

    public static MessageDigest getSha512Digest() {
        return getDigest("SHA-512");
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static MessageDigest getShaDigest() {
        return getSha1Digest();
    }

    public static byte[] md2(byte[] data) {
        return getMd2Digest().digest(data);
    }

    public static byte[] md2(InputStream data) throws IOException {
        return digest(getMd2Digest(), data);
    }

    public static byte[] md2(String data) {
        return md2(StringUtils.getBytesUtf8(data));
    }

    public static String md2Hex(byte[] data) {
        return Hex.encodeHexString(md2(data));
    }

    public static String md2Hex(InputStream data) throws IOException {
        return Hex.encodeHexString(md2(data));
    }

    public static String md2Hex(String data) {
        return Hex.encodeHexString(md2(data));
    }

    public static byte[] md5(byte[] data) {
        return getMd5Digest().digest(data);
    }

    public static byte[] md5(InputStream data) throws IOException {
        return digest(getMd5Digest(), data);
    }

    public static byte[] md5(String data) {
        return md5(StringUtils.getBytesUtf8(data));
    }

    public static String md5Hex(byte[] data) {
        return Hex.encodeHexString(md5(data));
    }

    public static String md5Hex(InputStream data) throws IOException {
        return Hex.encodeHexString(md5(data));
    }

    public static String md5Hex(String data) {
        return Hex.encodeHexString(md5(data));
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static byte[] sha(byte[] data) {
        return sha1(data);
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static byte[] sha(InputStream data) throws IOException {
        return sha1(data);
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static byte[] sha(String data) {
        return sha1(data);
    }

    public static byte[] sha1(byte[] data) {
        return getSha1Digest().digest(data);
    }

    public static byte[] sha1(InputStream data) throws IOException {
        return digest(getSha1Digest(), data);
    }

    public static byte[] sha1(String data) {
        return sha1(StringUtils.getBytesUtf8(data));
    }

    public static String sha1Hex(byte[] data) {
        return Hex.encodeHexString(sha1(data));
    }

    public static String sha1Hex(InputStream data) throws IOException {
        return Hex.encodeHexString(sha1(data));
    }

    public static String sha1Hex(String data) {
        return Hex.encodeHexString(sha1(data));
    }

    public static byte[] sha256(byte[] data) {
        return getSha256Digest().digest(data);
    }

    public static byte[] sha256(InputStream data) throws IOException {
        return digest(getSha256Digest(), data);
    }

    public static byte[] sha256(String data) {
        return sha256(StringUtils.getBytesUtf8(data));
    }

    public static String sha256Hex(byte[] data) {
        return Hex.encodeHexString(sha256(data));
    }

    public static String sha256Hex(InputStream data) throws IOException {
        return Hex.encodeHexString(sha256(data));
    }

    public static String sha256Hex(String data) {
        return Hex.encodeHexString(sha256(data));
    }

    public static byte[] sha384(byte[] data) {
        return getSha384Digest().digest(data);
    }

    public static byte[] sha384(InputStream data) throws IOException {
        return digest(getSha384Digest(), data);
    }

    public static byte[] sha384(String data) {
        return sha384(StringUtils.getBytesUtf8(data));
    }

    public static String sha384Hex(byte[] data) {
        return Hex.encodeHexString(sha384(data));
    }

    public static String sha384Hex(InputStream data) throws IOException {
        return Hex.encodeHexString(sha384(data));
    }

    public static String sha384Hex(String data) {
        return Hex.encodeHexString(sha384(data));
    }

    public static byte[] sha512(byte[] data) {
        return getSha512Digest().digest(data);
    }

    public static byte[] sha512(InputStream data) throws IOException {
        return digest(getSha512Digest(), data);
    }

    public static byte[] sha512(String data) {
        return sha512(StringUtils.getBytesUtf8(data));
    }

    public static String sha512Hex(byte[] data) {
        return Hex.encodeHexString(sha512(data));
    }

    public static String sha512Hex(InputStream data) throws IOException {
        return Hex.encodeHexString(sha512(data));
    }

    public static String sha512Hex(String data) {
        return Hex.encodeHexString(sha512(data));
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static String shaHex(byte[] data) {
        return sha1Hex(data);
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static String shaHex(InputStream data) throws IOException {
        return sha1Hex(data);
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static String shaHex(String data) {
        return sha1Hex(data);
    }

    public static MessageDigest updateDigest(MessageDigest messageDigest, byte[] valueToDigest) {
        messageDigest.update(valueToDigest);
        return messageDigest;
    }

    public static MessageDigest updateDigest(MessageDigest messageDigest, ByteBuffer valueToDigest) {
        messageDigest.update(valueToDigest);
        return messageDigest;
    }

    public static MessageDigest updateDigest(MessageDigest digest, File data) throws IOException {
        BufferedInputStream stream = new BufferedInputStream(new FileInputStream(data));

        MessageDigest var3;
        try {
            var3 = updateDigest(digest, (InputStream) stream);
        } finally {
            stream.close();
        }

        return var3;
    }

    public static MessageDigest updateDigest(MessageDigest digest, InputStream data) throws IOException {
        byte[] buffer = new byte[1024];

        for (int read = data.read(buffer, 0, 1024); read > -1; read = data.read(buffer, 0, 1024)) {
            digest.update(buffer, 0, read);
        }

        return digest;
    }

    public static MessageDigest updateDigest(MessageDigest messageDigest, String valueToDigest) {
        messageDigest.update(StringUtils.getBytesUtf8(valueToDigest));
        return messageDigest;
    }

    public static boolean isAvailable(String messageDigestAlgorithm) {
        return getDigest(messageDigestAlgorithm, (MessageDigest) null) != null;
    }

    /**
     * @deprecated
     */
    @Deprecated
    public DigestUtils() {
        this.messageDigest = null;
    }

    public DigestUtils(MessageDigest digest) {
        this.messageDigest = digest;
    }

    public DigestUtils(String name) {
        this(getDigest(name));
    }

    public MessageDigest getMessageDigest() {
        return this.messageDigest;
    }

    public byte[] digest(byte[] data) {
        return updateDigest(this.messageDigest, data).digest();
    }

    public byte[] digest(String data) {
        return updateDigest(this.messageDigest, data).digest();
    }

    public byte[] digest(ByteBuffer data) {
        return updateDigest(this.messageDigest, data).digest();
    }

    public byte[] digest(File data) throws IOException {
        return updateDigest(this.messageDigest, data).digest();
    }

    public byte[] digest(InputStream data) throws IOException {
        return updateDigest(this.messageDigest, data).digest();
    }

    public String digestAsHex(byte[] data) {
        return Hex.encodeHexString(this.digest(data));
    }

    public String digestAsHex(String data) {
        return Hex.encodeHexString(this.digest(data));
    }

    public String digestAsHex(ByteBuffer data) {
        return Hex.encodeHexString(this.digest(data));
    }

    public String digestAsHex(File data) throws IOException {
        return Hex.encodeHexString(this.digest(data));
    }

    public String digestAsHex(InputStream data) throws IOException {
        return Hex.encodeHexString(this.digest(data));
    }


    // 获取md5加密算法对象
    private final static MessageDigest md;
    private final static char[] cs = {'0', '1', '2', '3', '4', '5', '6', '7',
            '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    static {
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static String md51(String text) {
        try {
            byte[] bs = md.digest(text.getBytes("utf-8"));

            System.out.println(encodeHexMd5(bs));
            String s = new String(encodeHexMd5(bs));

            MessageDigest sha1 = MessageDigest.getInstance("SHA-1");
            //sha1.digest(md5);
            return encodeHex(sha1.digest(s.getBytes(StandardCharsets.UTF_8)));
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private static String encodeHex(byte[] bs) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bs) {
            sb.append(cs[(b >> 4) & 15]);
            sb.append(cs[b & 15]);
        }
        return sb.toString();
    }

    private static char[] encodeHexMd5(byte[] bytes) {
        char[] chars = new char[32];

        for(int i = 0; i < chars.length; i += 2) {
            byte b = bytes[i / 2];
            chars[i] = cs[b >>> 4 & 15];
            chars[i + 1] = cs[b & 15];
        }

        return chars;
    }

}


