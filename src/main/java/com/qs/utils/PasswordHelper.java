package com.qs.utils;

import com.qs.entity.User;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * 自定义密码生成类
 *
 * @author QinYupeng
 * @since 2018年09月21日14:27:22
 */
public class PasswordHelper {
    //生成随机数/随机字节，在未自定义salt时，可以使用该类实例生成动态色salt
    private RandomNumberGenerator randomNumberGenerator;
    //加密算法：一般为散列算法 md5、sha
    private String algorithmName;
    //加密次数
    private int hashIterations;
    //自定义盐，可以注入一个常量字符串，也可以动态生成，当paramSalt参数为空，则使用randomNumberGenerator生成一个salt
    private String salt;

    public void setRandomNumberGenerator(RandomNumberGenerator randomNumberGenerator) {
        this.randomNumberGenerator = randomNumberGenerator;
    }

    public void setAlgorithmName(String algorithmName) {
        this.algorithmName = algorithmName;
    }

    public void setHashIterations(int hashIterations) {
        this.hashIterations = hashIterations;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public void encryptPassword(User user) {
        if (salt == null && randomNumberGenerator == null) {
            throw new RuntimeException("salt or randomNumberGenerator can not all be null");
        }

        if (salt == null) {
            user.setSalt(randomNumberGenerator.nextBytes().toHex());
        } else {
            user.setSalt(salt);
        }

        String newPassword = new SimpleHash(
                algorithmName,
                user.getPassword(),
                ByteSource.Util.bytes(user.getCredentialsSalt()),
                hashIterations).toHex();

        user.setPassword(newPassword);
    }
}
