package com.komanrudden.multiauthapi.security.oauth2.user;

import com.komanrudden.multiauthapi.model.enums.AuthProvider;
import com.komanrudden.multiauthapi.model.error.OAuth2AuthenticationProcessingException;

import java.util.Map;

/**
 * Factory class for creating OAuth2UserInfo instances based on the registration ID.
 * <p>
 * This class provides a static method to return the appropriate OAuth2UserInfo implementation
 * based on the given registration ID and user attributes.
 * </p>
 */
public class OAuth2UserInfoFactory {

    public static OAuth2UserInfo getOAuth2UserInfo(String registrationId, Map<String, Object> attributes) throws OAuth2AuthenticationProcessingException {
        if (registrationId.equalsIgnoreCase(AuthProvider.GOOGLE.toString())) {
            return new GoogleOAuth2UserInfo(attributes);
        } else if (registrationId.equalsIgnoreCase(AuthProvider.FACEBOOK.toString())) {
            return new FacebookOAuth2UserInfo(attributes);
        } else if (registrationId.equalsIgnoreCase(AuthProvider.GITHUB.toString())) {
            return new GithubOAuth2UserInfo(attributes);
        } else if (registrationId.equalsIgnoreCase(AuthProvider.LINKEDIN.toString())) {
            return new LinkedinOAuth2UserInfo(attributes);
        } else if (registrationId.equalsIgnoreCase(AuthProvider.X.toString())) {
            return new XOAuth2UserInfo(attributes);
        } else {
            throw new OAuth2AuthenticationProcessingException(String.format("Login with %s is not supported.", registrationId));
        }
    }
}
