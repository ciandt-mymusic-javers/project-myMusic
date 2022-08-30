package com.ciandt.summit.bootcamp2022.interceptor;

import com.ciandt.summit.bootcamp2022.dto.security.CreateAuthorizerRequest;
import com.ciandt.summit.bootcamp2022.dto.security.CreateAuthorizerRequestData;
import com.ciandt.summit.bootcamp2022.proxy.TokenProviderProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class TokenInterceptor implements HandlerInterceptor {

    @Autowired
    private TokenProviderProxy tokenProviderProxy;

    @Override
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) throws Exception {
        System.out.println("-------------> Token Interceptor");

        //Replace with getSession after authentication implementation
        String name = request.getHeader("name");
        String token = request.getHeader("token");

        HttpStatus statusCode = tokenProviderProxy.tokenAuthorizer(new CreateAuthorizerRequest(new CreateAuthorizerRequestData(name, token))).getStatusCode();
        System.out.println("-------------> Status Code: " + statusCode);

        return true;
    }
}
