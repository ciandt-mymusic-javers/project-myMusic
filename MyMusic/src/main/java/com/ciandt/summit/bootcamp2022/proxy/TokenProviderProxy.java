package com.ciandt.summit.bootcamp2022.proxy;

import com.ciandt.summit.bootcamp2022.dto.security.CreateAuthorizerRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "token-provider", url = "localhost:8081")
public interface TokenProviderProxy {

    @PostMapping("/api/v1/token/authorizer")
    public ResponseEntity<String> tokenAuthorizer(CreateAuthorizerRequest createAuthorizerRequest);
}
