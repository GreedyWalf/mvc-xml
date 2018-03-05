package com.qs.base.common;

import org.apache.commons.lang3.StringUtils;

import java.util.HashSet;
import java.util.Set;

public class OpenUri {

    private static Set<String> sessionNotRequiredSet = new HashSet<String>();

    static{
        sessionNotRequiredSet.add("/login/login");
        sessionNotRequiredSet.add("/login/register");
        sessionNotRequiredSet.add("/login/ajaxLogin");
        sessionNotRequiredSet.add("/login/ajaxRegister");

        sessionNotRequiredSet.add("/user/listUser");
    }


    /**
     * 判断uri是否不需要session就可以访问
     */
    public static boolean isSessionNotRequired(String uri) {
        if(StringUtils.isBlank(uri)){
            return false;
        }

        if(uri.contains("mvc")){
            uri = uri.substring(4, uri.length());
        }

        if(uri.contains(".")){
            uri = uri.substring(0, uri.lastIndexOf("."));
        }

        return StringUtils.isNotBlank(uri) && sessionNotRequiredSet.contains(uri);
    }
}
