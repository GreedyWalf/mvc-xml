package com.qs.base.common;

import com.qs.util.WebConstant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Component
public class SessionAccessor {

    @Value("${login.sessionMaxTime}")
    private String sessionMaxTime;

    public String getSessionMaxTime() {
        return sessionMaxTime;
    }

    public SessionAccessor(){

    }

    public void store(HttpServletRequest request, HttpServletResponse response, Map<String,String> contextMap){
        String sessionId = contextMap.get(WebConstant.SESSION_ID);
        if(StringUtils.isBlank(sessionId)){
            return;
        }

        Cookie cookie = new Cookie(WebConstant.SESSION_ID, sessionId);
        int maxAge = Integer.parseInt(sessionMaxTime);
        cookie.setMaxAge(maxAge);
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}
