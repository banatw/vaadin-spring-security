package com.example.application.securityhelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.savedrequest.HttpSessionRequestCache;

public class CustomRequestCache extends HttpSessionRequestCache {

    @Override
    public void saveRequest(HttpServletRequest arg0, HttpServletResponse arg1) {
        // TODO Auto-generated method stub
        if (!SecurityUtils.isFrameworkInternalRequest(arg0))
            super.saveRequest(arg0, arg1);
    }

}
