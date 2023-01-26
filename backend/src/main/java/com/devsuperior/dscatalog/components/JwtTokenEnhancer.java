package com.devsuperior.dscatalog.components;

import com.devsuperior.dscatalog.entities.User;
import com.devsuperior.dscatalog.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenEnhancer implements TokenEnhancer {

    @Autowired
    private UserRepository userRepository;

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken auth2AccessToken, OAuth2Authentication auth2Authentication) {

        User user = userRepository.findByEmail(auth2Authentication.getName());
        Map<String, Object> map = new HashMap<>();
        map.put("userFirestName", user.getFirstName());
        map.put("userId", user.getId());

        //Foi feito o downcast por que a classe DefaultOAuth2AccessToken tem oacesso ao metodo setAdditionalInformation
        DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken) auth2AccessToken;
        token.setAdditionalInformation(map);

        //Retornando referenecia original, após ser adicionado mas dados para o token
        return auth2AccessToken;
    }
}
