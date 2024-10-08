package com.komanrudden.multiauthapi.config;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * Configuration properties for the application.
 * <p>
 * This class binds the properties prefixed with "app" to the fields in this class.
 * It contains nested classes for authentication and OAuth2 properties.
 * </p>
 *
 * <p><b>Auth</b>:
 * <ul>
 *   <li><b>tokenSecret</b>: The secret key used for token generation.</li>
 *   <li><b>tokenExpirationMsec</b>: The expiration time for tokens in milliseconds.</li>
 * </ul>
 * </p>
 *
 * <p><b>OAuth2</b>:
 * <ul>
 *   <li><b>authorizedRedirectUris</b>: List of authorized redirect URIs for OAuth2 authentication.</li>
 * </ul>
 * </p>
 */
@Data
@ConfigurationProperties(prefix = "app")
public class AppProperties {

    private final Auth auth = new Auth();
    private final OAuth2 oAuth2 = new OAuth2();

    @Getter
    @Setter
    public static class Auth {
        private String tokenSecret;
        private long tokenExpirationMsec;
    }

    @Getter
    public static final class OAuth2 {
        private List<String> authorizedRedirectUris = List.of("http://localhost:4200/oauth2/redirect");

        public OAuth2 authorizedRedirectUris(List<String> authorizedRedirectUris) {
            this.authorizedRedirectUris = authorizedRedirectUris;
            return this;
        }
    }
}
