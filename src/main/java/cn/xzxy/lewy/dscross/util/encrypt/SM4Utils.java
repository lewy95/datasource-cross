package cn.xzxy.lewy.dscross.util.encrypt;

import cn.hutool.core.codec.Base64;
import cn.xzxy.lewy.dscross.util.BitByteUtil;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SM4Utils {
    private String secretKey = "";

    private String iv = "";

    private boolean hexString = false;

    public SM4Utils() {
    }

    private byte[] getkey(String key) {
        try {
            byte[] keyBytes = new byte[16];
            byte[] t = new byte[0];

            t = key.getBytes("UTF-8");

            if (t.length < 16) {
                for (int i = 0; i < t.length; i++) {
                    keyBytes[i] = t[i];
                }
            } else {
                keyBytes = t;
            }
            return keyBytes;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String encryptData_ECB(String plainText) {
        try {
            SM4Context ctx = new SM4Context();
            ctx.isPadding = true;
            ctx.mode = SM4.SM4_ENCRYPT;

            byte[] keyBytes;
            if (hexString) {
                keyBytes = BitByteUtil.hexStringToBytes(secretKey);
            } else {
                keyBytes = getkey(secretKey);
            }

            SM4 sm4 = new SM4();
            sm4.sm4_setkey_enc(ctx, keyBytes);
            byte[] encrypted = sm4.sm4_crypt_ecb(ctx, plainText.getBytes("UTF-8"));
            String cipherText = new BASE64Encoder().encode(encrypted);
            if (cipherText != null && cipherText.trim().length() > 0) {
                Pattern p = Pattern.compile("\\s*|\t|\r|\n");
                Matcher m = p.matcher(cipherText);
                cipherText = m.replaceAll("");
            }
            return cipherText;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String decryptData_ECB(String cipherText) {
        try {
            SM4Context ctx = new SM4Context();
            ctx.isPadding = true;
            ctx.mode = SM4.SM4_DECRYPT;

            byte[] keyBytes;
            if (hexString) {
                keyBytes = BitByteUtil.hexStringToBytes(secretKey);
            } else {
                keyBytes = getkey(secretKey);
            }

            SM4 sm4 = new SM4();
            sm4.sm4_setkey_dec(ctx, keyBytes);
            byte[] decrypted = sm4.sm4_crypt_ecb(ctx, new BASE64Decoder().decodeBuffer(cipherText));
            return new String(decrypted, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String encryptData_CBC(String plainText) {
        try {
            SM4Context ctx = new SM4Context();
            ctx.isPadding = true;
            ctx.mode = SM4.SM4_ENCRYPT;

            byte[] keyBytes;
            byte[] ivBytes;
            if (hexString) {
                keyBytes = BitByteUtil.hexStringToBytes(secretKey);
                ivBytes = BitByteUtil.hexStringToBytes(iv);
            } else {
                keyBytes = getkey(secretKey);
                ivBytes = iv.getBytes();
            }

            SM4 sm4 = new SM4();
            sm4.sm4_setkey_enc(ctx, keyBytes);
            byte[] encrypted = sm4.sm4_crypt_cbc(ctx, ivBytes, plainText.getBytes("UTF-8"));
            String cipherText = new BASE64Encoder().encode(encrypted);
            if (cipherText != null && cipherText.trim().length() > 0) {
                Pattern p = Pattern.compile("\\s*|\t|\r|\n");
                Matcher m = p.matcher(cipherText);
                cipherText = m.replaceAll("");
            }
            return cipherText;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String decryptData_CBC(String cipherText) {
        try {
            SM4Context ctx = new SM4Context();
            ctx.isPadding = true;
            ctx.mode = SM4.SM4_DECRYPT;

            byte[] keyBytes;
            byte[] ivBytes;
            if (hexString) {
                keyBytes = BitByteUtil.hexStringToBytes(secretKey);
                ivBytes = BitByteUtil.hexStringToBytes(iv);
            } else {
                keyBytes = getkey(secretKey);
                ivBytes = iv.getBytes();
            }

            SM4 sm4 = new SM4();
            sm4.sm4_setkey_dec(ctx, keyBytes);
            byte[] decrypted = sm4.sm4_crypt_cbc(ctx, ivBytes, new BASE64Decoder().decodeBuffer(cipherText));
            return new String(decrypted, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String strEncode(String value, String key) throws IOException {
        SM4Utils sm4Utils = new SM4Utils();
        sm4Utils.secretKey = key;
        sm4Utils.hexString = false;
        String s = sm4Utils.encryptData_ECB(value);
        s = s.trim().replaceAll("\r|\n", "");
        s = s.replace("+", "%2B");
        s = s.replace("/", "%2F");
        return s;
    }

    public static String strDecode(String value, String key) throws IOException {
        value = value.replace("%2B", "+");
        value = value.replace("%2F", "/");
        SM4Utils sm4Utils = new SM4Utils();
        sm4Utils.secretKey = key;
        sm4Utils.hexString = false;
        String s = sm4Utils.decryptData_ECB(value);
        return s;
    }

    /**
     * 进行base64编码
     */
    public static String custBeforeEncryptHandler(String secretKey, String pwd) {
        return Base64.encode(secretKey + pwd, "utf-8");
    }

    /**
     * 进行base64解码
     */
    public static String[] custParseEncryptHandler(String dbPwd) {
        String[] resArr = new String[2];
        byte[] decode = Base64.decode(dbPwd, "utf-8");
        String result = new String(decode);
        resArr[0] = result.substring(16);
        resArr[1] = result.substring(0, 16);
        return resArr;
    }

//    public static void main(String[] args) throws IOException {
//        String plainText = "中国你好";
//
//        SM4Utils sm4 = new SM4Utils();
//        sm4.secretKey = "admin#$%^";
//        sm4.hexString = false;
//
//        System.out.println("ECB模式");
//        String cipherText = sm4.encryptData_ECB(plainText);
//        System.out.println("密文: " + cipherText);
//        System.out.println("");
//
//        plainText = sm4.decryptData_ECB(cipherText);
//        System.out.println("明文: " + plainText);
//        System.out.println("");
//
//        System.out.println("CBC模式");
//        sm4.iv = "UISwD9fW6cFh9SNS";
//        cipherText = sm4.encryptData_CBC(plainText);
//        System.out.println("密文: " + cipherText);
//        System.out.println("");
//
//        plainText = sm4.decryptData_CBC(cipherText);
//        System.out.println("明文: " + plainText);
//    }
}
