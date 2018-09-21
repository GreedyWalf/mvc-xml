package shiro;

import com.qs.entity.User;
import com.qs.service.UserService;
import com.qs.service.mapper.UserMapper;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import sun.misc.Cache;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-config/applicationContext.xml"})
@TransactionConfiguration(defaultRollback = false)
public class ShiroTest {
    @Autowired
    private UserService userService;

    @Autowired
    private CacheManager cacheManager;

    @Value("${shiro.password.algorithmName}")
    private String algorithmName;

    @Value("${shiro.password.hashIterations}")
    private int hashIterations;

    @Value("${shiro.password.salt}")
    private String salt;

    @Test
    public void createUserAndComparePwd() {
        String username = "zhangsan";
        String password = "123";

        User user2 = new User(username, password);
        int cnt = userService.saveUser(user2);
        String pwd = userService.findByUsername(username).getPassword();
        System.out.println("===>>>pwd=" + pwd);

        SimpleHash sh = new SimpleHash(algorithmName, password, username + salt, hashIterations);
        String newPwd = sh.toString();
        System.out.println("===>>>newPwd=" + newPwd); //164283dc28896b5beb43fdb07572edc6
        System.out.println("===>>>生成的密码是否相同：" + pwd.equals(newPwd));
    }


    @Test
    public void updatePwd() {
        User user = userService.findByUsername("zhang");
        System.out.println("====>>>修改前密码：" + user.getPassword());

        user.setPassword("123");
        int cnt = userService.updateUser(user);
        System.out.println("====>>>修改后密码：" + user.getPassword());

    }


    //清除用户登录次数存储在ehcache中的缓存
    @Test
    public void clearCache() {
        Object remove = cacheManager.getCache("shirocache").remove("passwordRetryCache");
        System.out.println("==>>remove=" + remove);
    }
}
