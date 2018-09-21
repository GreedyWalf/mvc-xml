package salt;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.SimpleByteSource;
import org.junit.Test;

import java.io.UnsupportedEncodingException;

public class TestSalt {

    //164283dc28896b5beb43fdb07572edc6
    public static void main(String[] args) throws UnsupportedEncodingException {
        String password = "123456";
        String md5Pwd = DigestUtils.md5Hex(password);
        String salt = "abc";

        //加密次数
        int hashIterations = 2;
        //使用md5进行2次加密
        Md5Hash mh = new Md5Hash(md5Pwd, salt, hashIterations);
        System.out.println(mh.toString()); //164283dc28896b5beb43fdb07572edc6

        SimpleHash sh = new SimpleHash("md5", md5Pwd, salt, hashIterations);
        System.out.println(sh.toString()); //164283dc28896b5beb43fdb07572edc6


        //将salt字符串使用utf-8转化为字节，再进行base64编码，生成字符串
        ByteSource byteSalt = ByteSource.Util.bytes("abc");
        byte[] bytes = "abc".getBytes("utf-8");
        SimpleByteSource simpleByteSource = new SimpleByteSource(bytes);
        System.out.println(bytes);
        System.out.println(Base64.encodeToString(bytes)); //YWJj

    }
}
