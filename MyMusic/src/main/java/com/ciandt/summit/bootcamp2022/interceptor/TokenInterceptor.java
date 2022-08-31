package com.ciandt.summit.bootcamp2022.interceptor;

import com.ciandt.summit.bootcamp2022.dto.security.CreateAuthorizerRequest;
import com.ciandt.summit.bootcamp2022.dto.security.CreateAuthorizerRequestData;
import com.ciandt.summit.bootcamp2022.exception.UserUnauthorizedException;
import com.ciandt.summit.bootcamp2022.proxy.TokenProviderProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class TokenInterceptor implements HandlerInterceptor {

    @Autowired
    private TokenProviderProxy tokenProviderProxy;

    @Override
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) {

        //Replace with getSession after authentication implementation
        String name = request.getHeader("name");
        String token = request.getHeader("token");

        try {
            tokenProviderProxy.tokenAuthorizer(new CreateAuthorizerRequest(new CreateAuthorizerRequestData(name, token)))
                    .getStatusCode();

            return true;

        } catch (Exception e) {
            e.printStackTrace();

            throw new UserUnauthorizedException("We are sorry but we are not able to authenticate you. You have to subscribe " +
                    "to access this resource. If you are already subscribed, check if you gave the proper credential in the login step");
        }
    }
}
