package org.identifiers.cloud.hq.ws.registry.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

/**
 * Project: registry
 * Package: org.identifiers.cloud.hq.ws.registry.configuration
 * Timestamp: 2019-04-25 08:07
 *
 * @author Manuel Bernal Llinares <mbdebian@gmail.com>
 * ---
 */
@Configuration
@Profile("authenabled")
@Slf4j
@EnableWebSecurity
public class AuthSecurityConfiguration extends WebSecurityConfigurerAdapter {
    // TODO - Maybe configurable in the future?
    // Connection parameters
    private static final int WS_REQUEST_CONNECT_TIMEOUT = 2000; // 2 seconds
    private static final int WS_REQUEST_READ_TIMEOUT = 2000; // 2 seconds

    // OAuth2 stuff
    static final String JWT_SCOPE_RESOURCE_ACCESS = "resource_access";

    @Value("${spring.security.oauth2.client.registration.keycloak.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.keycloak.client-secret}")
    private String clientSecret;

    @Value("${spring.security.oauth2.client.registration.keycloak.accessTokenUri}")
    private String accessTokenUri;

    @Value("${spring.security.oauth2.client.registration.keycloak.grantType}")
    private String grantType;

    @PostConstruct
    private void postConstruct() {
        log.info("[CONFIG] (AAA) ENABLED");
    }

    @Bean
    public RestTemplate restTemplate() {
        ClientCredentialsResourceDetails clientCredentialsResourceDetails = new ClientCredentialsResourceDetails();
        clientCredentialsResourceDetails.setClientId(clientId);
        clientCredentialsResourceDetails.setClientSecret(clientSecret);
        clientCredentialsResourceDetails.setAccessTokenUri(accessTokenUri);
        clientCredentialsResourceDetails.setGrantType(grantType);
        clientCredentialsResourceDetails.setId("1");
        OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(clientCredentialsResourceDetails, new DefaultOAuth2ClientContext());
        SimpleClientHttpRequestFactory simpleClientHttpRequestFactory = new SimpleClientHttpRequestFactory();
        // Configure requests time outs
        simpleClientHttpRequestFactory.setConnectTimeout(WS_REQUEST_CONNECT_TIMEOUT);
        simpleClientHttpRequestFactory.setReadTimeout(WS_REQUEST_READ_TIMEOUT);
        restTemplate.setRequestFactory(simpleClientHttpRequestFactory);
        return restTemplate;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // TODO - I may need this information to disble CSRF on a particular URL, https://stackoverflow.com/questions/22524470/spring-security-3-2-csrf-disable-for-specific-urls
        // TODO - Look into writing a role mapper, the use of @PreAuthorize on the repositories and the injection of "noauth" role everywhere as a way to disable authentication
        // TODO - Update ACLs with the roles for the Resource Management API
        http
                .authorizeRequests()
                    .antMatchers("/healthApi/**").permitAll()
                // REST Repository - Institutions
                    .antMatchers(HttpMethod.GET, "/restApi/institutions/**").permitAll()
                    .antMatchers(HttpMethod.HEAD, "/restApi/institutions/**").permitAll()
                    .antMatchers(HttpMethod.POST, "/restApi/institutions/**").access(String.format("isAuthenticated() and (principal?.claims.get('%s') != null) and (principal?.claims['%s'].get('%s') != null) and (principal?.claims['%s']['%s']['roles'].contains('restApiInstitutionPost'))", JWT_SCOPE_RESOURCE_ACCESS, JWT_SCOPE_RESOURCE_ACCESS, clientId, JWT_SCOPE_RESOURCE_ACCESS, clientId))
                    .antMatchers(HttpMethod.PUT, "/restApi/institutions/**").access(String.format("isAuthenticated() and (principal?.claims.get('%s') != null) and (principal?.claims['%s'].get('%s') != null) and (principal?.claims['%s']['%s']['roles'].contains('restApiInstitutionPut'))", JWT_SCOPE_RESOURCE_ACCESS, JWT_SCOPE_RESOURCE_ACCESS, clientId, JWT_SCOPE_RESOURCE_ACCESS, clientId))
                    .antMatchers(HttpMethod.PATCH, "/restApi/institutions/**").access(String.format("isAuthenticated() and (principal?.claims.get('%s') != null) and (principal?.claims['%s'].get('%s') != null) and (principal?.claims['%s']['%s']['roles'].contains('restApiInstitutionPatch'))", JWT_SCOPE_RESOURCE_ACCESS, JWT_SCOPE_RESOURCE_ACCESS, clientId, JWT_SCOPE_RESOURCE_ACCESS, clientId))
                    .antMatchers(HttpMethod.DELETE, "/restApi/institutions/**").denyAll()
                // REST Repository - Locations
                    .antMatchers(HttpMethod.GET, "/restApi/locations/**").permitAll()
                    .antMatchers(HttpMethod.HEAD, "/restApi/locations/**").permitAll()
                    .antMatchers(HttpMethod.POST, "/restApi/locations/**").access(String.format("isAuthenticated() and (principal?.claims.get('%s') != null) and (principal?.claims['%s'].get('%s') != null) and (principal?.claims['%s']['%s']['roles'].contains('restApiLocationPost'))", JWT_SCOPE_RESOURCE_ACCESS, JWT_SCOPE_RESOURCE_ACCESS, clientId, JWT_SCOPE_RESOURCE_ACCESS, clientId))
                    .antMatchers(HttpMethod.PUT, "/restApi/locations/**").access(String.format("isAuthenticated() and (principal?.claims.get('%s') != null) and (principal?.claims['%s'].get('%s') != null) and (principal?.claims['%s']['%s']['roles'].contains('restApiLocationPut'))", JWT_SCOPE_RESOURCE_ACCESS, JWT_SCOPE_RESOURCE_ACCESS, clientId, JWT_SCOPE_RESOURCE_ACCESS, clientId))
                    .antMatchers(HttpMethod.PATCH, "/restApi/locations/**").access(String.format("isAuthenticated() and (principal?.claims.get('%s') != null) and (principal?.claims['%s'].get('%s') != null) and (principal?.claims['%s']['%s']['roles'].contains('restApiLocationPatch'))", JWT_SCOPE_RESOURCE_ACCESS, JWT_SCOPE_RESOURCE_ACCESS, clientId, JWT_SCOPE_RESOURCE_ACCESS, clientId))
                    .antMatchers(HttpMethod.DELETE, "/restApi/locations/**").denyAll()
                // REST Repository - Namespaces
                    .antMatchers(HttpMethod.GET, "/restApi/namespaces/**/contactPerson/**").access(String.format("isAuthenticated() and (principal?.claims.get('%s') != null) and (principal?.claims['%s'].get('%s') != null) and (principal?.claims['%s']['%s']['roles'].contains('restApiPersonGet'))", JWT_SCOPE_RESOURCE_ACCESS, JWT_SCOPE_RESOURCE_ACCESS, clientId, JWT_SCOPE_RESOURCE_ACCESS, clientId))
                    .antMatchers(HttpMethod.GET, "/restApi/namespaces/**").permitAll()
                    .antMatchers(HttpMethod.HEAD, "/restApi/namespaces/**").permitAll()
                    .antMatchers(HttpMethod.POST, "/restApi/namespaces/**").access(String.format("isAuthenticated() and (principal?.claims.get('%s') != null) and (principal?.claims['%s'].get('%s') != null) and (principal?.claims['%s']['%s']['roles'].contains('restApiNamespacePost'))", JWT_SCOPE_RESOURCE_ACCESS, JWT_SCOPE_RESOURCE_ACCESS, clientId, JWT_SCOPE_RESOURCE_ACCESS, clientId))
                    .antMatchers(HttpMethod.PUT, "/restApi/namespaces/**").access(String.format("isAuthenticated() and (principal?.claims.get('%s') != null) and (principal?.claims['%s'].get('%s') != null) and (principal?.claims['%s']['%s']['roles'].contains('restApiNamespacePut'))", JWT_SCOPE_RESOURCE_ACCESS, JWT_SCOPE_RESOURCE_ACCESS, clientId, JWT_SCOPE_RESOURCE_ACCESS, clientId))
                    .antMatchers(HttpMethod.PATCH, "/restApi/namespaces/**").access(String.format("isAuthenticated() and (principal?.claims.get('%s') != null) and (principal?.claims['%s'].get('%s') != null) and (principal?.claims['%s']['%s']['roles'].contains('restApiNamespacePatch'))", JWT_SCOPE_RESOURCE_ACCESS, JWT_SCOPE_RESOURCE_ACCESS, clientId, JWT_SCOPE_RESOURCE_ACCESS, clientId))
                    .antMatchers(HttpMethod.DELETE, "/restApi/namespaces/**").denyAll()
                // REST Repository - Namespace Synonyms
                    .antMatchers(HttpMethod.GET, "/restApi/namespaceSynonyms/**").permitAll()
                    .antMatchers(HttpMethod.HEAD, "/restApi/namespaceSynonyms/**").permitAll()
                    .antMatchers(HttpMethod.POST, "/restApi/namespaceSynonyms/**").access(String.format("isAuthenticated() and (principal?.claims.get('%s') != null) and (principal?.claims['%s'].get('%s') != null) and (principal?.claims['%s']['%s']['roles'].contains('restApiNamespaceSynonymPost'))", JWT_SCOPE_RESOURCE_ACCESS, JWT_SCOPE_RESOURCE_ACCESS, clientId, JWT_SCOPE_RESOURCE_ACCESS, clientId))
                    .antMatchers(HttpMethod.PUT, "/restApi/namespaceSynonyms/**").access(String.format("isAuthenticated() and (principal?.claims.get('%s') != null) and (principal?.claims['%s'].get('%s') != null) and (principal?.claims['%s']['%s']['roles'].contains('restApiNamespaceSynonymPut'))", JWT_SCOPE_RESOURCE_ACCESS, JWT_SCOPE_RESOURCE_ACCESS, clientId, JWT_SCOPE_RESOURCE_ACCESS, clientId))
                    .antMatchers(HttpMethod.PATCH, "/restApi/namespaceSynonyms/**").access(String.format("isAuthenticated() and (principal?.claims.get('%s') != null) and (principal?.claims['%s'].get('%s') != null) and (principal?.claims['%s']['%s']['roles'].contains('restApiNamespaceSynonymPatch'))", JWT_SCOPE_RESOURCE_ACCESS, JWT_SCOPE_RESOURCE_ACCESS, clientId, JWT_SCOPE_RESOURCE_ACCESS, clientId))
                    .antMatchers(HttpMethod.DELETE, "/restApi/namespaceSynonyms/**").denyAll()
                // REST Repository - Resources
                    .antMatchers(HttpMethod.GET, "/restApi/resources/**/contactPerson/**").access(String.format("isAuthenticated() and (principal?.claims.get('%s') != null) and (principal?.claims['%s'].get('%s') != null) and (principal?.claims['%s']['%s']['roles'].contains('restApiPersonGet'))", JWT_SCOPE_RESOURCE_ACCESS, JWT_SCOPE_RESOURCE_ACCESS, clientId, JWT_SCOPE_RESOURCE_ACCESS, clientId))
                    .antMatchers(HttpMethod.GET, "/restApi/resources/**").permitAll()
                    .antMatchers(HttpMethod.HEAD, "/restApi/resources/**").permitAll()
                    .antMatchers(HttpMethod.POST, "/restApi/resources/**").access(String.format("isAuthenticated() and (principal?.claims.get('%s') != null) and (principal?.claims['%s'].get('%s') != null) and (principal?.claims['%s']['%s']['roles'].contains('restApiResourcePost'))", JWT_SCOPE_RESOURCE_ACCESS, JWT_SCOPE_RESOURCE_ACCESS, clientId, JWT_SCOPE_RESOURCE_ACCESS, clientId))
                    .antMatchers(HttpMethod.PUT, "/restApi/resources/**").access(String.format("isAuthenticated() and (principal?.claims.get('%s') != null) and (principal?.claims['%s'].get('%s') != null) and (principal?.claims['%s']['%s']['roles'].contains('restApiResourcePut'))", JWT_SCOPE_RESOURCE_ACCESS, JWT_SCOPE_RESOURCE_ACCESS, clientId, JWT_SCOPE_RESOURCE_ACCESS, clientId))
                    .antMatchers(HttpMethod.PATCH, "/restApi/resources/**").access(String.format("isAuthenticated() and (principal?.claims.get('%s') != null) and (principal?.claims['%s'].get('%s') != null) and (principal?.claims['%s']['%s']['roles'].contains('restApiResourcePatch'))", JWT_SCOPE_RESOURCE_ACCESS, JWT_SCOPE_RESOURCE_ACCESS, clientId, JWT_SCOPE_RESOURCE_ACCESS, clientId))
                    .antMatchers(HttpMethod.DELETE, "/restApi/resources/**").denyAll()
                // REST Repository - Persons
                    .antMatchers(HttpMethod.GET, "/restApi/persons/**").access(String.format("isAuthenticated() and (principal?.claims.get('%s') != null) and (principal?.claims['%s'].get('%s') != null) and (principal?.claims['%s']['%s']['roles'].contains('restApiPersonGet'))", JWT_SCOPE_RESOURCE_ACCESS, JWT_SCOPE_RESOURCE_ACCESS, clientId, JWT_SCOPE_RESOURCE_ACCESS, clientId))
                    .antMatchers(HttpMethod.HEAD, "/restApi/persons/**").access(String.format("isAuthenticated() and (principal?.claims.get('%s') != null) and (principal?.claims['%s'].get('%s') != null) and (principal?.claims['%s']['%s']['roles'].contains('restApiPersonHead'))", JWT_SCOPE_RESOURCE_ACCESS, JWT_SCOPE_RESOURCE_ACCESS, clientId, JWT_SCOPE_RESOURCE_ACCESS, clientId))
                    .antMatchers(HttpMethod.POST, "/restApi/persons/**").access(String.format("isAuthenticated() and (principal?.claims.get('%s') != null) and (principal?.claims['%s'].get('%s') != null) and (principal?.claims['%s']['%s']['roles'].contains('restApiPersonPost'))", JWT_SCOPE_RESOURCE_ACCESS, JWT_SCOPE_RESOURCE_ACCESS, clientId, JWT_SCOPE_RESOURCE_ACCESS, clientId))
                    .antMatchers(HttpMethod.PUT, "/restApi/persons/**").access(String.format("isAuthenticated() and (principal?.claims.get('%s') != null) and (principal?.claims['%s'].get('%s') != null) and (principal?.claims['%s']['%s']['roles'].contains('restApiPersonPut'))", JWT_SCOPE_RESOURCE_ACCESS, JWT_SCOPE_RESOURCE_ACCESS, clientId, JWT_SCOPE_RESOURCE_ACCESS, clientId))
                    .antMatchers(HttpMethod.PATCH, "/restApi/persons/**").access(String.format("isAuthenticated() and (principal?.claims.get('%s') != null) and (principal?.claims['%s'].get('%s') != null) and (principal?.claims['%s']['%s']['roles'].contains('restApiPersonPatch'))", JWT_SCOPE_RESOURCE_ACCESS, JWT_SCOPE_RESOURCE_ACCESS, clientId, JWT_SCOPE_RESOURCE_ACCESS, clientId))
                    .antMatchers(HttpMethod.DELETE, "/restApi/persons/**").denyAll()
                // REST Repository - Prefix Registration Requests
                    .antMatchers(HttpMethod.GET, "/restApi/prefixRegistrationRequests/**").access(String.format("isAuthenticated() and (principal?.claims.get('%s') != null) and (principal?.claims['%s'].get('%s') != null) and (principal?.claims['%s']['%s']['roles'].contains('restApiPrefixRegistrationRequestGet'))", JWT_SCOPE_RESOURCE_ACCESS, JWT_SCOPE_RESOURCE_ACCESS, clientId, JWT_SCOPE_RESOURCE_ACCESS, clientId))
                    .antMatchers(HttpMethod.HEAD, "/restApi/prefixRegistrationRequests/**").access(String.format("isAuthenticated() and (principal?.claims.get('%s') != null) and (principal?.claims['%s'].get('%s') != null) and (principal?.claims['%s']['%s']['roles'].contains('restApiPrefixRegistrationRequestHead'))", JWT_SCOPE_RESOURCE_ACCESS, JWT_SCOPE_RESOURCE_ACCESS, clientId, JWT_SCOPE_RESOURCE_ACCESS, clientId))
                    .antMatchers(HttpMethod.POST, "/restApi/prefixRegistrationRequests/**").access(String.format("isAuthenticated() and (principal?.claims.get('%s') != null) and (principal?.claims['%s'].get('%s') != null) and (principal?.claims['%s']['%s']['roles'].contains('restApiPrefixRegistrationRequestPost'))", JWT_SCOPE_RESOURCE_ACCESS, JWT_SCOPE_RESOURCE_ACCESS, clientId, JWT_SCOPE_RESOURCE_ACCESS, clientId))
                    .antMatchers(HttpMethod.PUT, "/restApi/prefixRegistrationRequests/**").access(String.format("isAuthenticated() and (principal?.claims.get('%s') != null) and (principal?.claims['%s'].get('%s') != null) and (principal?.claims['%s']['%s']['roles'].contains('restApiPrefixRegistrationRequestPut'))", JWT_SCOPE_RESOURCE_ACCESS, JWT_SCOPE_RESOURCE_ACCESS, clientId, JWT_SCOPE_RESOURCE_ACCESS, clientId))
                    .antMatchers(HttpMethod.PATCH, "/restApi/prefixRegistrationRequests/**").access(String.format("isAuthenticated() and (principal?.claims.get('%s') != null) and (principal?.claims['%s'].get('%s') != null) and (principal?.claims['%s']['%s']['roles'].contains('restApiPrefixRegistrationRequestPatch'))", JWT_SCOPE_RESOURCE_ACCESS, JWT_SCOPE_RESOURCE_ACCESS, clientId, JWT_SCOPE_RESOURCE_ACCESS, clientId))
                    .antMatchers(HttpMethod.DELETE, "/restApi/prefixRegistrationRequests/**").denyAll()
                // REST Repository - Prefix Registration Sessions
                    .antMatchers(HttpMethod.GET, "/restApi/prefixRegistrationSessions/**").access(String.format("isAuthenticated() and (principal?.claims.get('%s') != null) and (principal?.claims['%s'].get('%s') != null) and (principal?.claims['%s']['%s']['roles'].contains('restApiPrefixRegistrationSessionGet'))", JWT_SCOPE_RESOURCE_ACCESS, JWT_SCOPE_RESOURCE_ACCESS, clientId, JWT_SCOPE_RESOURCE_ACCESS, clientId))
                    .antMatchers(HttpMethod.HEAD, "/restApi/prefixRegistrationSessions/**").access(String.format("isAuthenticated() and (principal?.claims.get('%s') != null) and (principal?.claims['%s'].get('%s') != null) and (principal?.claims['%s']['%s']['roles'].contains('restApiPrefixRegistrationSessionHead'))", JWT_SCOPE_RESOURCE_ACCESS, JWT_SCOPE_RESOURCE_ACCESS, clientId, JWT_SCOPE_RESOURCE_ACCESS, clientId))
                    .antMatchers(HttpMethod.POST, "/restApi/prefixRegistrationSessions/**").access(String.format("isAuthenticated() and (principal?.claims.get('%s') != null) and (principal?.claims['%s'].get('%s') != null) and (principal?.claims['%s']['%s']['roles'].contains('restApiPrefixRegistrationSessionPost'))", JWT_SCOPE_RESOURCE_ACCESS, JWT_SCOPE_RESOURCE_ACCESS, clientId, JWT_SCOPE_RESOURCE_ACCESS, clientId))
                    .antMatchers(HttpMethod.PUT, "/restApi/prefixRegistrationSessions/**").access(String.format("isAuthenticated() and (principal?.claims.get('%s') != null) and (principal?.claims['%s'].get('%s') != null) and (principal?.claims['%s']['%s']['roles'].contains('restApiPrefixRegistrationSessionPut'))", JWT_SCOPE_RESOURCE_ACCESS, JWT_SCOPE_RESOURCE_ACCESS, clientId, JWT_SCOPE_RESOURCE_ACCESS, clientId))
                    .antMatchers(HttpMethod.PATCH, "/restApi/prefixRegistrationSessions/**").access(String.format("isAuthenticated() and (principal?.claims.get('%s') != null) and (principal?.claims['%s'].get('%s') != null) and (principal?.claims['%s']['%s']['roles'].contains('restApiPrefixRegistrationSessionPatch'))", JWT_SCOPE_RESOURCE_ACCESS, JWT_SCOPE_RESOURCE_ACCESS, clientId, JWT_SCOPE_RESOURCE_ACCESS, clientId))
                    .antMatchers(HttpMethod.DELETE, "/restApi/prefixRegistrationSessions/**").denyAll()
                // REST Repository - Prefix Registration Session Events
                    .antMatchers(HttpMethod.GET, "/restApi/prefixRegistrationSessionEvents/**").access(String.format("isAuthenticated() and (principal?.claims.get('%s') != null) and (principal?.claims['%s'].get('%s') != null) and (principal?.claims['%s']['%s']['roles'].contains('restApiPrefixRegistrationSessionEventGet'))", JWT_SCOPE_RESOURCE_ACCESS, JWT_SCOPE_RESOURCE_ACCESS, clientId, JWT_SCOPE_RESOURCE_ACCESS, clientId))
                    .antMatchers(HttpMethod.HEAD, "/restApi/prefixRegistrationSessionEvents/**").access(String.format("isAuthenticated() and (principal?.claims.get('%s') != null) and (principal?.claims['%s'].get('%s') != null) and (principal?.claims['%s']['%s']['roles'].contains('restApiPrefixRegistrationSessionEventHead'))", JWT_SCOPE_RESOURCE_ACCESS, JWT_SCOPE_RESOURCE_ACCESS, clientId, JWT_SCOPE_RESOURCE_ACCESS, clientId))
                    .antMatchers(HttpMethod.POST, "/restApi/prefixRegistrationSessionEvents/**").access(String.format("isAuthenticated() and (principal?.claims.get('%s') != null) and (principal?.claims['%s'].get('%s') != null) and (principal?.claims['%s']['%s']['roles'].contains('restApiPrefixRegistrationSessionEventPost'))", JWT_SCOPE_RESOURCE_ACCESS, JWT_SCOPE_RESOURCE_ACCESS, clientId, JWT_SCOPE_RESOURCE_ACCESS, clientId))
                    .antMatchers(HttpMethod.PUT, "/restApi/prefixRegistrationSessionEvents/**").access(String.format("isAuthenticated() and (principal?.claims.get('%s') != null) and (principal?.claims['%s'].get('%s') != null) and (principal?.claims['%s']['%s']['roles'].contains('restApiPrefixRegistrationSessionEventPut'))", JWT_SCOPE_RESOURCE_ACCESS, JWT_SCOPE_RESOURCE_ACCESS, clientId, JWT_SCOPE_RESOURCE_ACCESS, clientId))
                    .antMatchers(HttpMethod.PATCH, "/restApi/prefixRegistrationSessionEvents/**").access(String.format("isAuthenticated() and (principal?.claims.get('%s') != null) and (principal?.claims['%s'].get('%s') != null) and (principal?.claims['%s']['%s']['roles'].contains('restApiPrefixRegistrationSessionEventPatch'))", JWT_SCOPE_RESOURCE_ACCESS, JWT_SCOPE_RESOURCE_ACCESS, clientId, JWT_SCOPE_RESOURCE_ACCESS, clientId))
                    .antMatchers(HttpMethod.DELETE, "/restApi/prefixRegistrationSessionEvents/**").denyAll()
                // REST Repository - Prefix Registration Session Events - Accept
                    .antMatchers(HttpMethod.GET, "/restApi/prefixRegistrationSessionEventAccepts/**").access(String.format("isAuthenticated() and (principal?.claims.get('%s') != null) and (principal?.claims['%s'].get('%s') != null) and (principal?.claims['%s']['%s']['roles'].contains('restApiPrefixRegistrationSessionEventAcceptGet'))", JWT_SCOPE_RESOURCE_ACCESS, JWT_SCOPE_RESOURCE_ACCESS, clientId, JWT_SCOPE_RESOURCE_ACCESS, clientId))
                    .antMatchers(HttpMethod.HEAD, "/restApi/prefixRegistrationSessionEventAccepts/**").access(String.format("isAuthenticated() and (principal?.claims.get('%s') != null) and (principal?.claims['%s'].get('%s') != null) and (principal?.claims['%s']['%s']['roles'].contains('restApiPrefixRegistrationSessionEventAcceptHead'))", JWT_SCOPE_RESOURCE_ACCESS, JWT_SCOPE_RESOURCE_ACCESS, clientId, JWT_SCOPE_RESOURCE_ACCESS, clientId))
                    .antMatchers(HttpMethod.POST, "/restApi/prefixRegistrationSessionEventAccepts/**").access(String.format("isAuthenticated() and (principal?.claims.get('%s') != null) and (principal?.claims['%s'].get('%s') != null) and (principal?.claims['%s']['%s']['roles'].contains('restApiPrefixRegistrationSessionEventAcceptPost'))", JWT_SCOPE_RESOURCE_ACCESS, JWT_SCOPE_RESOURCE_ACCESS, clientId, JWT_SCOPE_RESOURCE_ACCESS, clientId))
                    .antMatchers(HttpMethod.PUT, "/restApi/prefixRegistrationSessionEventAccepts/**").access(String.format("isAuthenticated() and (principal?.claims.get('%s') != null) and (principal?.claims['%s'].get('%s') != null) and (principal?.claims['%s']['%s']['roles'].contains('restApiPrefixRegistrationSessionEventAcceptPut'))", JWT_SCOPE_RESOURCE_ACCESS, JWT_SCOPE_RESOURCE_ACCESS, clientId, JWT_SCOPE_RESOURCE_ACCESS, clientId))
                    .antMatchers(HttpMethod.PATCH, "/restApi/prefixRegistrationSessionEventAccepts/**").access(String.format("isAuthenticated() and (principal?.claims.get('%s') != null) and (principal?.claims['%s'].get('%s') != null) and (principal?.claims['%s']['%s']['roles'].contains('restApiPrefixRegistrationSessionEventAcceptPatch'))", JWT_SCOPE_RESOURCE_ACCESS, JWT_SCOPE_RESOURCE_ACCESS, clientId, JWT_SCOPE_RESOURCE_ACCESS, clientId))
                    .antMatchers(HttpMethod.DELETE, "/restApi/prefixRegistrationSessionEventAccepts/**").denyAll()
                // REST Repository - Prefix Registration Session Events - Amend
                    .antMatchers(HttpMethod.GET, "/restApi/prefixRegistrationSessionEventAmends/**").access(String.format("isAuthenticated() and (principal?.claims.get('%s') != null) and (principal?.claims['%s'].get('%s') != null) and (principal?.claims['%s']['%s']['roles'].contains('restApiPrefixRegistrationSessionEventAmendGet'))", JWT_SCOPE_RESOURCE_ACCESS, JWT_SCOPE_RESOURCE_ACCESS, clientId, JWT_SCOPE_RESOURCE_ACCESS, clientId))
                    .antMatchers(HttpMethod.HEAD, "/restApi/prefixRegistrationSessionEventAmends/**").access(String.format("isAuthenticated() and (principal?.claims.get('%s') != null) and (principal?.claims['%s'].get('%s') != null) and (principal?.claims['%s']['%s']['roles'].contains('restApiPrefixRegistrationSessionEventAmendHead'))", JWT_SCOPE_RESOURCE_ACCESS, JWT_SCOPE_RESOURCE_ACCESS, clientId, JWT_SCOPE_RESOURCE_ACCESS, clientId))
                    .antMatchers(HttpMethod.POST, "/restApi/prefixRegistrationSessionEventAmends/**").access(String.format("isAuthenticated() and (principal?.claims.get('%s') != null) and (principal?.claims['%s'].get('%s') != null) and (principal?.claims['%s']['%s']['roles'].contains('restApiPrefixRegistrationSessionEventAmendPost'))", JWT_SCOPE_RESOURCE_ACCESS, JWT_SCOPE_RESOURCE_ACCESS, clientId, JWT_SCOPE_RESOURCE_ACCESS, clientId))
                    .antMatchers(HttpMethod.PUT, "/restApi/prefixRegistrationSessionEventAmends/**").access(String.format("isAuthenticated() and (principal?.claims.get('%s') != null) and (principal?.claims['%s'].get('%s') != null) and (principal?.claims['%s']['%s']['roles'].contains('restApiPrefixRegistrationSessionEventAmendPut'))", JWT_SCOPE_RESOURCE_ACCESS, JWT_SCOPE_RESOURCE_ACCESS, clientId, JWT_SCOPE_RESOURCE_ACCESS, clientId))
                    .antMatchers(HttpMethod.PATCH, "/restApi/prefixRegistrationSessionEventAmends/**").access(String.format("isAuthenticated() and (principal?.claims.get('%s') != null) and (principal?.claims['%s'].get('%s') != null) and (principal?.claims['%s']['%s']['roles'].contains('restApiPrefixRegistrationSessionEventAmendPatch'))", JWT_SCOPE_RESOURCE_ACCESS, JWT_SCOPE_RESOURCE_ACCESS, clientId, JWT_SCOPE_RESOURCE_ACCESS, clientId))
                    .antMatchers(HttpMethod.DELETE, "/restApi/prefixRegistrationSessionEventAmends/**").denyAll()
                // REST Repository - Prefix Registration Session Event - Comment
                    .antMatchers(HttpMethod.GET, "/restApi/prefixRegistrationSessionEventComments/**").access(String.format("isAuthenticated() and (principal?.claims.get('%s') != null) and (principal?.claims['%s'].get('%s') != null) and (principal?.claims['%s']['%s']['roles'].contains('restApiPrefixRegistrationSessionEventCommentGet'))", JWT_SCOPE_RESOURCE_ACCESS, JWT_SCOPE_RESOURCE_ACCESS, clientId, JWT_SCOPE_RESOURCE_ACCESS, clientId))
                    .antMatchers(HttpMethod.HEAD, "/restApi/prefixRegistrationSessionEventComments/**").access(String.format("isAuthenticated() and (principal?.claims.get('%s') != null) and (principal?.claims['%s'].get('%s') != null) and (principal?.claims['%s']['%s']['roles'].contains('restApiPrefixRegistrationSessionEventCommentHead'))", JWT_SCOPE_RESOURCE_ACCESS, JWT_SCOPE_RESOURCE_ACCESS, clientId, JWT_SCOPE_RESOURCE_ACCESS, clientId))
                    .antMatchers(HttpMethod.POST, "/restApi/prefixRegistrationSessionEventComments/**").access(String.format("isAuthenticated() and (principal?.claims.get('%s') != null) and (principal?.claims['%s'].get('%s') != null) and (principal?.claims['%s']['%s']['roles'].contains('restApiPrefixRegistrationSessionEventCommentPost'))", JWT_SCOPE_RESOURCE_ACCESS, JWT_SCOPE_RESOURCE_ACCESS, clientId, JWT_SCOPE_RESOURCE_ACCESS, clientId))
                    .antMatchers(HttpMethod.PUT, "/restApi/prefixRegistrationSessionEventComments/**").access(String.format("isAuthenticated() and (principal?.claims.get('%s') != null) and (principal?.claims['%s'].get('%s') != null) and (principal?.claims['%s']['%s']['roles'].contains('restApiPrefixRegistrationSessionEventCommentPut'))", JWT_SCOPE_RESOURCE_ACCESS, JWT_SCOPE_RESOURCE_ACCESS, clientId, JWT_SCOPE_RESOURCE_ACCESS, clientId))
                    .antMatchers(HttpMethod.PATCH, "/restApi/prefixRegistrationSessionEventComments/**").access(String.format("isAuthenticated() and (principal?.claims.get('%s') != null) and (principal?.claims['%s'].get('%s') != null) and (principal?.claims['%s']['%s']['roles'].contains('restApiPrefixRegistrationSessionEventCommentPatch'))", JWT_SCOPE_RESOURCE_ACCESS, JWT_SCOPE_RESOURCE_ACCESS, clientId, JWT_SCOPE_RESOURCE_ACCESS, clientId))
                    .antMatchers(HttpMethod.DELETE, "/restApi/prefixRegistrationSessionEventComments/**").denyAll()
                // REST Repository - Prefix Registration Session Event - Reject
                    .antMatchers(HttpMethod.GET, "/restApi/prefixRegistrationSessionEventRejects/**").access(String.format("isAuthenticated() and (principal?.claims.get('%s') != null) and (principal?.claims['%s'].get('%s') != null) and (principal?.claims['%s']['%s']['roles'].contains('restApiPrefixRegistrationSessionEventRejectGet'))", JWT_SCOPE_RESOURCE_ACCESS, JWT_SCOPE_RESOURCE_ACCESS, clientId, JWT_SCOPE_RESOURCE_ACCESS, clientId))
                    .antMatchers(HttpMethod.HEAD, "/restApi/prefixRegistrationSessionEventRejects/**").access(String.format("isAuthenticated() and (principal?.claims.get('%s') != null) and (principal?.claims['%s'].get('%s') != null) and (principal?.claims['%s']['%s']['roles'].contains('restApiPrefixRegistrationSessionEventRejectHead'))", JWT_SCOPE_RESOURCE_ACCESS, JWT_SCOPE_RESOURCE_ACCESS, clientId, JWT_SCOPE_RESOURCE_ACCESS, clientId))
                    .antMatchers(HttpMethod.POST, "/restApi/prefixRegistrationSessionEventRejects/**").access(String.format("isAuthenticated() and (principal?.claims.get('%s') != null) and (principal?.claims['%s'].get('%s') != null) and (principal?.claims['%s']['%s']['roles'].contains('restApiPrefixRegistrationSessionEventRejectPost'))", JWT_SCOPE_RESOURCE_ACCESS, JWT_SCOPE_RESOURCE_ACCESS, clientId, JWT_SCOPE_RESOURCE_ACCESS, clientId))
                    .antMatchers(HttpMethod.PUT, "/restApi/prefixRegistrationSessionEventRejects/**").access(String.format("isAuthenticated() and (principal?.claims.get('%s') != null) and (principal?.claims['%s'].get('%s') != null) and (principal?.claims['%s']['%s']['roles'].contains('restApiPrefixRegistrationSessionEventRejectPut'))", JWT_SCOPE_RESOURCE_ACCESS, JWT_SCOPE_RESOURCE_ACCESS, clientId, JWT_SCOPE_RESOURCE_ACCESS, clientId))
                    .antMatchers(HttpMethod.PATCH, "/restApi/prefixRegistrationSessionEventRejects/**").access(String.format("isAuthenticated() and (principal?.claims.get('%s') != null) and (principal?.claims['%s'].get('%s') != null) and (principal?.claims['%s']['%s']['roles'].contains('restApiPrefixRegistrationSessionEventRejectPatch'))", JWT_SCOPE_RESOURCE_ACCESS, JWT_SCOPE_RESOURCE_ACCESS, clientId, JWT_SCOPE_RESOURCE_ACCESS, clientId))
                    .antMatchers(HttpMethod.DELETE, "/restApi/prefixRegistrationSessionEventRejects/**").denyAll()
                // REST Repository - Prefix Registration Session Event - Start
                    .antMatchers(HttpMethod.GET, "/restApi/prefixRegistrationSessionEventStarts/**").access(String.format("isAuthenticated() and (principal?.claims.get('%s') != null) and (principal?.claims['%s'].get('%s') != null) and (principal?.claims['%s']['%s']['roles'].contains('restApiPrefixRegistrationSessionEventStartGet'))", JWT_SCOPE_RESOURCE_ACCESS, JWT_SCOPE_RESOURCE_ACCESS, clientId, JWT_SCOPE_RESOURCE_ACCESS, clientId))
                    .antMatchers(HttpMethod.HEAD, "/restApi/prefixRegistrationSessionEventStarts/**").access(String.format("isAuthenticated() and (principal?.claims.get('%s') != null) and (principal?.claims['%s'].get('%s') != null) and (principal?.claims['%s']['%s']['roles'].contains('restApiPrefixRegistrationSessionEventStartHead'))", JWT_SCOPE_RESOURCE_ACCESS, JWT_SCOPE_RESOURCE_ACCESS, clientId, JWT_SCOPE_RESOURCE_ACCESS, clientId))
                    .antMatchers(HttpMethod.POST, "/restApi/prefixRegistrationSessionEventStarts/**").access(String.format("isAuthenticated() and (principal?.claims.get('%s') != null) and (principal?.claims['%s'].get('%s') != null) and (principal?.claims['%s']['%s']['roles'].contains('restApiPrefixRegistrationSessionEventStartPost'))", JWT_SCOPE_RESOURCE_ACCESS, JWT_SCOPE_RESOURCE_ACCESS, clientId, JWT_SCOPE_RESOURCE_ACCESS, clientId))
                    .antMatchers(HttpMethod.PUT, "/restApi/prefixRegistrationSessionEventStarts/**").access(String.format("isAuthenticated() and (principal?.claims.get('%s') != null) and (principal?.claims['%s'].get('%s') != null) and (principal?.claims['%s']['%s']['roles'].contains('restApiPrefixRegistrationSessionEventStartPut'))", JWT_SCOPE_RESOURCE_ACCESS, JWT_SCOPE_RESOURCE_ACCESS, clientId, JWT_SCOPE_RESOURCE_ACCESS, clientId))
                    .antMatchers(HttpMethod.PATCH, "/restApi/prefixRegistrationSessionEventStarts/**").access(String.format("isAuthenticated() and (principal?.claims.get('%s') != null) and (principal?.claims['%s'].get('%s') != null) and (principal?.claims['%s']['%s']['roles'].contains('restApiPrefixRegistrationSessionEventStartPatch'))", JWT_SCOPE_RESOURCE_ACCESS, JWT_SCOPE_RESOURCE_ACCESS, clientId, JWT_SCOPE_RESOURCE_ACCESS, clientId))
                    .antMatchers(HttpMethod.DELETE, "/restApi/prefixRegistrationSessionEventStarts/**").denyAll()
                // REST Repository - Resolution API
                    .antMatchers("/resolutionApi/**").permitAll()
                // REST Repository - Semantic API
                    .antMatchers("/semanticApi/**").permitAll()
                // REST Repository - Prefix Registration API
                    .antMatchers(HttpMethod.POST, "/prefixRegistrationApi/registerPrefix").permitAll()
                    .antMatchers("/prefixRegistrationApi/amendPrefixRegistrationRequest/**").access(String.format("isAuthenticated() and (principal?.claims.get('%s') != null) and (principal?.claims['%s'].get('%s') != null) and (principal?.claims['%s']['%s']['roles'].contains('ApiPrefixRegistrationAmendPrefixRegistrationRequest'))", JWT_SCOPE_RESOURCE_ACCESS, JWT_SCOPE_RESOURCE_ACCESS, clientId, JWT_SCOPE_RESOURCE_ACCESS, clientId))
                    .antMatchers("/prefixRegistrationApi/commentPrefixRegistrationRequest/**").access(String.format("isAuthenticated() and (principal?.claims.get('%s') != null) and (principal?.claims['%s'].get('%s') != null) and (principal?.claims['%s']['%s']['roles'].contains('ApiPrefixRegistrationCommentPrefixRegistrationRequest'))", JWT_SCOPE_RESOURCE_ACCESS, JWT_SCOPE_RESOURCE_ACCESS, clientId, JWT_SCOPE_RESOURCE_ACCESS, clientId))
                    .antMatchers("/prefixRegistrationApi/rejectPrefixRegistrationRequest/**").access(String.format("isAuthenticated() and (principal?.claims.get('%s') != null) and (principal?.claims['%s'].get('%s') != null) and (principal?.claims['%s']['%s']['roles'].contains('ApiPrefixRegistrationRejectPrefixRegistrationRequest'))", JWT_SCOPE_RESOURCE_ACCESS, JWT_SCOPE_RESOURCE_ACCESS, clientId, JWT_SCOPE_RESOURCE_ACCESS, clientId))
                    .antMatchers("/prefixRegistrationApi/acceptPrefixRegistrationRequest/**").access(String.format("isAuthenticated() and (principal?.claims.get('%s') != null) and (principal?.claims['%s'].get('%s') != null) and (principal?.claims['%s']['%s']['roles'].contains('ApiPrefixRegistrationAcceptPrefixRegistrationRequest'))", JWT_SCOPE_RESOURCE_ACCESS, JWT_SCOPE_RESOURCE_ACCESS, clientId, JWT_SCOPE_RESOURCE_ACCESS, clientId))
                    .antMatchers(HttpMethod.POST, "/prefixRegistrationApi/validate*").permitAll()
                    .anyRequest().denyAll()
                .and()
                .csrf()
                    .ignoringAntMatchers("/prefixRegistrationApi/registerPrefix")
                    .ignoringAntMatchers("/prefixRegistrationApi/validate*")
                .and()
                .cors()
                .and()
                .oauth2ResourceServer().jwt();
    }
    // TODO
}
