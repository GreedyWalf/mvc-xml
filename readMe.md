spring + springmc + mybatis + shiro + freemarker

springMvc遇到的坑：
    1、ajax请求返回json串时报406错；
    
    解决：
        将spring版本改为4.1.5+goole gson，测试时4.0.0时会报406；
        测试时尝试用阿里的fastjson，也会出现406；
       
demo包含知识点：
    
    1、用户密码加密；
    2、用户登录校验、用户锁定；
    3、用户权限校验；
    
