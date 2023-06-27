package com.stock.controller;

import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author makuo
 * @Date 2023/3/3 14:04
 **/
@Component
public class Oauth2Util {

    /**
     * 登录
     *
     * @param url          登录地址
     * @param clientId     客户端id
     * @param clientSecret 客户端密钥
     * @param username     用户名
     * @param password     密码
     * @return 登录结果
     */
    public static OAuth2AccessToken login(String url, String clientId, String clientSecret, String username, String password) {
        ResourceOwnerPasswordResourceDetails resourceDetails = new ResourceOwnerPasswordResourceDetails();
        resourceDetails.setAccessTokenUri(url);
        resourceDetails.setClientId(clientId);
        resourceDetails.setClientSecret(clientSecret);
        resourceDetails.setUsername(username);
        resourceDetails.setPassword(password);
        OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(resourceDetails);
        restTemplate.setAccessTokenProvider(new ResourceOwnerPasswordAccessTokenProvider());
        return restTemplate.getAccessToken();
    }

}
