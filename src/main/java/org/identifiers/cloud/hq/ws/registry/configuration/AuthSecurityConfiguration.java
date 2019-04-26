package org.identifiers.cloud.hq.ws.registry.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

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
@Profile("authprofile")
@Slf4j
@EnableWebSecurity
public class AuthSecurityConfiguration extends WebSecurityConfigurerAdapter {
    static final String JWT_SCOPE_RESOURCE_ACCESS = "resource_access";

    @Value("${spring.security.oauth2.client.registration.keycloak.client-id}")
    private String clientId;

    @PostConstruct
    private void postConstruct() {
        log.info("[CONFIG] (AAA) ENABLED");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // TODO - ACLs for prefix registration session event amend
        // TODO - ACLs for prefix registration session event comment
        // TODO - ACLs for prefix registration session event reject
        // TODO - ACLs for prefix registration session event start
        // TODO - ACLs for prefix registration API (Request and Validation controllers)
        // TODO - ACLs for the resolution API
        // TODO - ACLs for the semantic API
        http
                .authorizeRequests()
                    .antMatchers("/healthApi/**").permitAll()
                    .antMatchers(HttpMethod.GET, "restApi/institutions/**").permitAll()
                    .antMatchers(HttpMethod.HEAD, "restApi/institutions/**").permitAll()
                    .antMatchers(HttpMethod.POST, "restApi/institutions/**").access(String.format("isAuthenticated() and (principal?.claims.get('%s') != null) and (principal?.claims['%s'].get('%s') != null) and (principal?.claims['%s']['%s']['roles'].contains('restApiInstitutionPost'))", JWT_SCOPE_RESOURCE_ACCESS, JWT_SCOPE_RESOURCE_ACCESS, clientId, JWT_SCOPE_RESOURCE_ACCESS, clientId))
                    .antMatchers(HttpMethod.PUT, "restApi/institutions/**").access(String.format("isAuthenticated() and (principal?.claims.get('%s') != null) and (principal?.claims['%s'].get('%s') != null) and (principal?.claims['%s']['%s']['roles'].contains('restApiInstitutionPut'))", JWT_SCOPE_RESOURCE_ACCESS, JWT_SCOPE_RESOURCE_ACCESS, clientId, JWT_SCOPE_RESOURCE_ACCESS, clientId))
                    .antMatchers(HttpMethod.PATCH, "restApi/institutions/**").access(String.format("isAuthenticated() and (principal?.claims.get('%s') != null) and (principal?.claims['%s'].get('%s') != null) and (principal?.claims['%s']['%s']['roles'].contains('restApiInstitutionPatch'))", JWT_SCOPE_RESOURCE_ACCESS, JWT_SCOPE_RESOURCE_ACCESS, clientId, JWT_SCOPE_RESOURCE_ACCESS, clientId))
                    .antMatchers(HttpMethod.DELETE, "restApi/institutions/**").denyAll()
                    .antMatchers(HttpMethod.GET, "restApi/locations/**").permitAll()
                    .antMatchers(HttpMethod.HEAD, "restApi/locations/**").permitAll()
                    .antMatchers(HttpMethod.POST, "restApi/locations/**").access(String.format("isAuthenticated() and (principal?.claims.get('%s') != null) and (principal?.claims['%s'].get('%s') != null) and (principal?.claims['%s']['%s']['roles'].contains('restApiLocationPost'))", JWT_SCOPE_RESOURCE_ACCESS, JWT_SCOPE_RESOURCE_ACCESS, clientId, JWT_SCOPE_RESOURCE_ACCESS, clientId))
                    .antMatchers(HttpMethod.PUT, "restApi/locations/**").access(String.format("isAuthenticated() and (principal?.claims.get('%s') != null) and (principal?.claims['%s'].get('%s') != null) and (principal?.claims['%s']['%s']['roles'].contains('restApiLocationPut'))", JWT_SCOPE_RESOURCE_ACCESS, JWT_SCOPE_RESOURCE_ACCESS, clientId, JWT_SCOPE_RESOURCE_ACCESS, clientId))
                    .antMatchers(HttpMethod.PATCH, "restApi/locations/**").access(String.format("isAuthenticated() and (principal?.claims.get('%s') != null) and (principal?.claims['%s'].get('%s') != null) and (principal?.claims['%s']['%s']['roles'].contains('restApiLocationPatch'))", JWT_SCOPE_RESOURCE_ACCESS, JWT_SCOPE_RESOURCE_ACCESS, clientId, JWT_SCOPE_RESOURCE_ACCESS, clientId))
                    .antMatchers(HttpMethod.DELETE, "restApi/locations/**").denyAll()
                    .antMatchers(HttpMethod.GET, "restApi/namespaces/**").permitAll()
                    .antMatchers(HttpMethod.HEAD, "restApi/namespaces/**").permitAll()
                    .antMatchers(HttpMethod.POST, "restApi/namespaces/**").access(String.format("isAuthenticated() and (principal?.claims.get('%s') != null) and (principal?.claims['%s'].get('%s') != null) and (principal?.claims['%s']['%s']['roles'].contains('restApiNamespacePost'))", JWT_SCOPE_RESOURCE_ACCESS, JWT_SCOPE_RESOURCE_ACCESS, clientId, JWT_SCOPE_RESOURCE_ACCESS, clientId))
                    .antMatchers(HttpMethod.PUT, "restApi/namespaces/**").access(String.format("isAuthenticated() and (principal?.claims.get('%s') != null) and (principal?.claims['%s'].get('%s') != null) and (principal?.claims['%s']['%s']['roles'].contains('restApiNamespacePut'))", JWT_SCOPE_RESOURCE_ACCESS, JWT_SCOPE_RESOURCE_ACCESS, clientId, JWT_SCOPE_RESOURCE_ACCESS, clientId))
                    .antMatchers(HttpMethod.PATCH, "restApi/namespaces/**").access(String.format("isAuthenticated() and (principal?.claims.get('%s') != null) and (principal?.claims['%s'].get('%s') != null) and (principal?.claims['%s']['%s']['roles'].contains('restApiNamespacePatch'))", JWT_SCOPE_RESOURCE_ACCESS, JWT_SCOPE_RESOURCE_ACCESS, clientId, JWT_SCOPE_RESOURCE_ACCESS, clientId))
                    .antMatchers(HttpMethod.DELETE, "restApi/namespaces/**").denyAll()
                    .antMatchers(HttpMethod.GET, "restApi/namespaceSynonyms/**").permitAll()
                    .antMatchers(HttpMethod.HEAD, "restApi/namespaceSynonyms/**").permitAll()
                    .antMatchers(HttpMethod.POST, "restApi/namespaceSynonyms/**").access(String.format("isAuthenticated() and (principal?.claims.get('%s') != null) and (principal?.claims['%s'].get('%s') != null) and (principal?.claims['%s']['%s']['roles'].contains('restApiNamespaceSynonymPost'))", JWT_SCOPE_RESOURCE_ACCESS, JWT_SCOPE_RESOURCE_ACCESS, clientId, JWT_SCOPE_RESOURCE_ACCESS, clientId))
                    .antMatchers(HttpMethod.PUT, "restApi/namespaceSynonyms/**").access(String.format("isAuthenticated() and (principal?.claims.get('%s') != null) and (principal?.claims['%s'].get('%s') != null) and (principal?.claims['%s']['%s']['roles'].contains('restApiNamespaceSynonymPut'))", JWT_SCOPE_RESOURCE_ACCESS, JWT_SCOPE_RESOURCE_ACCESS, clientId, JWT_SCOPE_RESOURCE_ACCESS, clientId))
                    .antMatchers(HttpMethod.PATCH, "restApi/namespaceSynonyms/**").access(String.format("isAuthenticated() and (principal?.claims.get('%s') != null) and (principal?.claims['%s'].get('%s') != null) and (principal?.claims['%s']['%s']['roles'].contains('restApiNamespaceSynonymPatch'))", JWT_SCOPE_RESOURCE_ACCESS, JWT_SCOPE_RESOURCE_ACCESS, clientId, JWT_SCOPE_RESOURCE_ACCESS, clientId))
                    .antMatchers(HttpMethod.DELETE, "restApi/namespaceSynonyms/**").denyAll()
                    .antMatchers(HttpMethod.GET, "restApi/resources/**").permitAll()
                    .antMatchers(HttpMethod.HEAD, "restApi/resources/**").permitAll()
                    .antMatchers(HttpMethod.POST, "restApi/resources/**").access(String.format("isAuthenticated() and (principal?.claims.get('%s') != null) and (principal?.claims['%s'].get('%s') != null) and (principal?.claims['%s']['%s']['roles'].contains('restApiResourcePost'))", JWT_SCOPE_RESOURCE_ACCESS, JWT_SCOPE_RESOURCE_ACCESS, clientId, JWT_SCOPE_RESOURCE_ACCESS, clientId))
                    .antMatchers(HttpMethod.PUT, "restApi/resources/**").access(String.format("isAuthenticated() and (principal?.claims.get('%s') != null) and (principal?.claims['%s'].get('%s') != null) and (principal?.claims['%s']['%s']['roles'].contains('restApiResourcePut'))", JWT_SCOPE_RESOURCE_ACCESS, JWT_SCOPE_RESOURCE_ACCESS, clientId, JWT_SCOPE_RESOURCE_ACCESS, clientId))
                    .antMatchers(HttpMethod.PATCH, "restApi/resources/**").access(String.format("isAuthenticated() and (principal?.claims.get('%s') != null) and (principal?.claims['%s'].get('%s') != null) and (principal?.claims['%s']['%s']['roles'].contains('restApiResourcePatch'))", JWT_SCOPE_RESOURCE_ACCESS, JWT_SCOPE_RESOURCE_ACCESS, clientId, JWT_SCOPE_RESOURCE_ACCESS, clientId))
                    .antMatchers(HttpMethod.DELETE, "restApi/resources/**").denyAll()
                    .antMatchers(HttpMethod.GET, "restApi/persons/**").access(String.format("isAuthenticated() and (principal?.claims.get('%s') != null) and (principal?.claims['%s'].get('%s') != null) and (principal?.claims['%s']['%s']['roles'].contains('restApiPersonGet'))", JWT_SCOPE_RESOURCE_ACCESS, JWT_SCOPE_RESOURCE_ACCESS, clientId, JWT_SCOPE_RESOURCE_ACCESS, clientId))
                    .antMatchers(HttpMethod.HEAD, "restApi/persons/**").access(String.format("isAuthenticated() and (principal?.claims.get('%s') != null) and (principal?.claims['%s'].get('%s') != null) and (principal?.claims['%s']['%s']['roles'].contains('restApiPersonHead'))", JWT_SCOPE_RESOURCE_ACCESS, JWT_SCOPE_RESOURCE_ACCESS, clientId, JWT_SCOPE_RESOURCE_ACCESS, clientId))
                    .antMatchers(HttpMethod.POST, "restApi/persons/**").access(String.format("isAuthenticated() and (principal?.claims.get('%s') != null) and (principal?.claims['%s'].get('%s') != null) and (principal?.claims['%s']['%s']['roles'].contains('restApiPersonPost'))", JWT_SCOPE_RESOURCE_ACCESS, JWT_SCOPE_RESOURCE_ACCESS, clientId, JWT_SCOPE_RESOURCE_ACCESS, clientId))
                    .antMatchers(HttpMethod.PUT, "restApi/persons/**").access(String.format("isAuthenticated() and (principal?.claims.get('%s') != null) and (principal?.claims['%s'].get('%s') != null) and (principal?.claims['%s']['%s']['roles'].contains('restApiPersonPut'))", JWT_SCOPE_RESOURCE_ACCESS, JWT_SCOPE_RESOURCE_ACCESS, clientId, JWT_SCOPE_RESOURCE_ACCESS, clientId))
                    .antMatchers(HttpMethod.PATCH, "restApi/persons/**").access(String.format("isAuthenticated() and (principal?.claims.get('%s') != null) and (principal?.claims['%s'].get('%s') != null) and (principal?.claims['%s']['%s']['roles'].contains('restApiPersonPatch'))", JWT_SCOPE_RESOURCE_ACCESS, JWT_SCOPE_RESOURCE_ACCESS, clientId, JWT_SCOPE_RESOURCE_ACCESS, clientId))
                    .antMatchers(HttpMethod.DELETE, "restApi/persons/**").denyAll()
                    .antMatchers(HttpMethod.GET, "restApi/prefixRegistrationRequests/**").access(String.format("isAuthenticated() and (principal?.claims.get('%s') != null) and (principal?.claims['%s'].get('%s') != null) and (principal?.claims['%s']['%s']['roles'].contains('restApiPrefixRegistrationRequestGet'))", JWT_SCOPE_RESOURCE_ACCESS, JWT_SCOPE_RESOURCE_ACCESS, clientId, JWT_SCOPE_RESOURCE_ACCESS, clientId))
                    .antMatchers(HttpMethod.HEAD, "restApi/prefixRegistrationRequests/**").access(String.format("isAuthenticated() and (principal?.claims.get('%s') != null) and (principal?.claims['%s'].get('%s') != null) and (principal?.claims['%s']['%s']['roles'].contains('restApiPrefixRegistrationRequestHead'))", JWT_SCOPE_RESOURCE_ACCESS, JWT_SCOPE_RESOURCE_ACCESS, clientId, JWT_SCOPE_RESOURCE_ACCESS, clientId))
                    .antMatchers(HttpMethod.POST, "restApi/prefixRegistrationRequests/**").access(String.format("isAuthenticated() and (principal?.claims.get('%s') != null) and (principal?.claims['%s'].get('%s') != null) and (principal?.claims['%s']['%s']['roles'].contains('restApiPrefixRegistrationRequestPost'))", JWT_SCOPE_RESOURCE_ACCESS, JWT_SCOPE_RESOURCE_ACCESS, clientId, JWT_SCOPE_RESOURCE_ACCESS, clientId))
                    .antMatchers(HttpMethod.PUT, "restApi/prefixRegistrationRequests/**").access(String.format("isAuthenticated() and (principal?.claims.get('%s') != null) and (principal?.claims['%s'].get('%s') != null) and (principal?.claims['%s']['%s']['roles'].contains('restApiPrefixRegistrationRequestPut'))", JWT_SCOPE_RESOURCE_ACCESS, JWT_SCOPE_RESOURCE_ACCESS, clientId, JWT_SCOPE_RESOURCE_ACCESS, clientId))
                    .antMatchers(HttpMethod.PATCH, "restApi/prefixRegistrationRequests/**").access(String.format("isAuthenticated() and (principal?.claims.get('%s') != null) and (principal?.claims['%s'].get('%s') != null) and (principal?.claims['%s']['%s']['roles'].contains('restApiPrefixRegistrationRequestPatch'))", JWT_SCOPE_RESOURCE_ACCESS, JWT_SCOPE_RESOURCE_ACCESS, clientId, JWT_SCOPE_RESOURCE_ACCESS, clientId))
                    .antMatchers(HttpMethod.DELETE, "restApi/prefixRegistrationRequests/**").denyAll()
                    .antMatchers(HttpMethod.GET, "restApi/prefixRegistrationSessions/**").access(String.format("isAuthenticated() and (principal?.claims.get('%s') != null) and (principal?.claims['%s'].get('%s') != null) and (principal?.claims['%s']['%s']['roles'].contains('restApiPrefixRegistrationSessionGet'))", JWT_SCOPE_RESOURCE_ACCESS, JWT_SCOPE_RESOURCE_ACCESS, clientId, JWT_SCOPE_RESOURCE_ACCESS, clientId))
                    .antMatchers(HttpMethod.HEAD, "restApi/prefixRegistrationSessions/**").access(String.format("isAuthenticated() and (principal?.claims.get('%s') != null) and (principal?.claims['%s'].get('%s') != null) and (principal?.claims['%s']['%s']['roles'].contains('restApiPrefixRegistrationSessionHead'))", JWT_SCOPE_RESOURCE_ACCESS, JWT_SCOPE_RESOURCE_ACCESS, clientId, JWT_SCOPE_RESOURCE_ACCESS, clientId))
                    .antMatchers(HttpMethod.POST, "restApi/prefixRegistrationSessions/**").access(String.format("isAuthenticated() and (principal?.claims.get('%s') != null) and (principal?.claims['%s'].get('%s') != null) and (principal?.claims['%s']['%s']['roles'].contains('restApiPrefixRegistrationSessionPost'))", JWT_SCOPE_RESOURCE_ACCESS, JWT_SCOPE_RESOURCE_ACCESS, clientId, JWT_SCOPE_RESOURCE_ACCESS, clientId))
                    .antMatchers(HttpMethod.PUT, "restApi/prefixRegistrationSessions/**").access(String.format("isAuthenticated() and (principal?.claims.get('%s') != null) and (principal?.claims['%s'].get('%s') != null) and (principal?.claims['%s']['%s']['roles'].contains('restApiPrefixRegistrationSessionPut'))", JWT_SCOPE_RESOURCE_ACCESS, JWT_SCOPE_RESOURCE_ACCESS, clientId, JWT_SCOPE_RESOURCE_ACCESS, clientId))
                    .antMatchers(HttpMethod.PATCH, "restApi/prefixRegistrationSessions/**").access(String.format("isAuthenticated() and (principal?.claims.get('%s') != null) and (principal?.claims['%s'].get('%s') != null) and (principal?.claims['%s']['%s']['roles'].contains('restApiPrefixRegistrationSessionPatch'))", JWT_SCOPE_RESOURCE_ACCESS, JWT_SCOPE_RESOURCE_ACCESS, clientId, JWT_SCOPE_RESOURCE_ACCESS, clientId))
                    .antMatchers(HttpMethod.DELETE, "restApi/prefixRegistrationSessions/**").denyAll()
                    .antMatchers(HttpMethod.GET, "restApi/prefixRegistrationSessionEvents/**").access(String.format("isAuthenticated() and (principal?.claims.get('%s') != null) and (principal?.claims['%s'].get('%s') != null) and (principal?.claims['%s']['%s']['roles'].contains('restApiPrefixRegistrationSessionEventGet'))", JWT_SCOPE_RESOURCE_ACCESS, JWT_SCOPE_RESOURCE_ACCESS, clientId, JWT_SCOPE_RESOURCE_ACCESS, clientId))
                    .antMatchers(HttpMethod.HEAD, "restApi/prefixRegistrationSessionEvents/**").access(String.format("isAuthenticated() and (principal?.claims.get('%s') != null) and (principal?.claims['%s'].get('%s') != null) and (principal?.claims['%s']['%s']['roles'].contains('restApiPrefixRegistrationSessionEventHead'))", JWT_SCOPE_RESOURCE_ACCESS, JWT_SCOPE_RESOURCE_ACCESS, clientId, JWT_SCOPE_RESOURCE_ACCESS, clientId))
                    .antMatchers(HttpMethod.POST, "restApi/prefixRegistrationSessionEvents/**").access(String.format("isAuthenticated() and (principal?.claims.get('%s') != null) and (principal?.claims['%s'].get('%s') != null) and (principal?.claims['%s']['%s']['roles'].contains('restApiPrefixRegistrationSessionEventPost'))", JWT_SCOPE_RESOURCE_ACCESS, JWT_SCOPE_RESOURCE_ACCESS, clientId, JWT_SCOPE_RESOURCE_ACCESS, clientId))
                    .antMatchers(HttpMethod.PUT, "restApi/prefixRegistrationSessionEvents/**").access(String.format("isAuthenticated() and (principal?.claims.get('%s') != null) and (principal?.claims['%s'].get('%s') != null) and (principal?.claims['%s']['%s']['roles'].contains('restApiPrefixRegistrationSessionEventPut'))", JWT_SCOPE_RESOURCE_ACCESS, JWT_SCOPE_RESOURCE_ACCESS, clientId, JWT_SCOPE_RESOURCE_ACCESS, clientId))
                    .antMatchers(HttpMethod.PATCH, "restApi/prefixRegistrationSessionEvents/**").access(String.format("isAuthenticated() and (principal?.claims.get('%s') != null) and (principal?.claims['%s'].get('%s') != null) and (principal?.claims['%s']['%s']['roles'].contains('restApiPrefixRegistrationSessionEventPatch'))", JWT_SCOPE_RESOURCE_ACCESS, JWT_SCOPE_RESOURCE_ACCESS, clientId, JWT_SCOPE_RESOURCE_ACCESS, clientId))
                    .antMatchers(HttpMethod.DELETE, "restApi/prefixRegistrationSessionEvents/**").denyAll()
                    .antMatchers(HttpMethod.GET, "restApi/prefixRegistrationSessionEventAccepts/**").access(String.format("isAuthenticated() and (principal?.claims.get('%s') != null) and (principal?.claims['%s'].get('%s') != null) and (principal?.claims['%s']['%s']['roles'].contains('restApiPrefixRegistrationSessionEventAcceptGet'))", JWT_SCOPE_RESOURCE_ACCESS, JWT_SCOPE_RESOURCE_ACCESS, clientId, JWT_SCOPE_RESOURCE_ACCESS, clientId))
                    .antMatchers(HttpMethod.HEAD, "restApi/prefixRegistrationSessionEventAccepts/**").access(String.format("isAuthenticated() and (principal?.claims.get('%s') != null) and (principal?.claims['%s'].get('%s') != null) and (principal?.claims['%s']['%s']['roles'].contains('restApiPrefixRegistrationSessionEventAcceptHead'))", JWT_SCOPE_RESOURCE_ACCESS, JWT_SCOPE_RESOURCE_ACCESS, clientId, JWT_SCOPE_RESOURCE_ACCESS, clientId))
                    .antMatchers(HttpMethod.POST, "restApi/prefixRegistrationSessionEventAccepts/**").access(String.format("isAuthenticated() and (principal?.claims.get('%s') != null) and (principal?.claims['%s'].get('%s') != null) and (principal?.claims['%s']['%s']['roles'].contains('restApiPrefixRegistrationSessionEventAcceptPost'))", JWT_SCOPE_RESOURCE_ACCESS, JWT_SCOPE_RESOURCE_ACCESS, clientId, JWT_SCOPE_RESOURCE_ACCESS, clientId))
                    .antMatchers(HttpMethod.PUT, "restApi/prefixRegistrationSessionEventAccepts/**").access(String.format("isAuthenticated() and (principal?.claims.get('%s') != null) and (principal?.claims['%s'].get('%s') != null) and (principal?.claims['%s']['%s']['roles'].contains('restApiPrefixRegistrationSessionEventAcceptPut'))", JWT_SCOPE_RESOURCE_ACCESS, JWT_SCOPE_RESOURCE_ACCESS, clientId, JWT_SCOPE_RESOURCE_ACCESS, clientId))
                    .antMatchers(HttpMethod.PATCH, "restApi/prefixRegistrationSessionEventAccepts/**").access(String.format("isAuthenticated() and (principal?.claims.get('%s') != null) and (principal?.claims['%s'].get('%s') != null) and (principal?.claims['%s']['%s']['roles'].contains('restApiPrefixRegistrationSessionEventAcceptPatch'))", JWT_SCOPE_RESOURCE_ACCESS, JWT_SCOPE_RESOURCE_ACCESS, clientId, JWT_SCOPE_RESOURCE_ACCESS, clientId))
                    .antMatchers(HttpMethod.DELETE, "restApi/prefixRegistrationSessionEventAccepts/**").denyAll()
                    .antMatchers(HttpMethod.GET, "restApi/prefixRegistrationSessionEventAmends/**").access(String.format("isAuthenticated() and (principal?.claims.get('%s') != null) and (principal?.claims['%s'].get('%s') != null) and (principal?.claims['%s']['%s']['roles'].contains('restApiPrefixRegistrationSessionEventAmendGet'))", JWT_SCOPE_RESOURCE_ACCESS, JWT_SCOPE_RESOURCE_ACCESS, clientId, JWT_SCOPE_RESOURCE_ACCESS, clientId))
                    .antMatchers(HttpMethod.HEAD, "restApi/prefixRegistrationSessionEventAmends/**").access(String.format("isAuthenticated() and (principal?.claims.get('%s') != null) and (principal?.claims['%s'].get('%s') != null) and (principal?.claims['%s']['%s']['roles'].contains('restApiPrefixRegistrationSessionEventAmendHead'))", JWT_SCOPE_RESOURCE_ACCESS, JWT_SCOPE_RESOURCE_ACCESS, clientId, JWT_SCOPE_RESOURCE_ACCESS, clientId))
                    .antMatchers(HttpMethod.POST, "restApi/prefixRegistrationSessionEventAmends/**").access(String.format("isAuthenticated() and (principal?.claims.get('%s') != null) and (principal?.claims['%s'].get('%s') != null) and (principal?.claims['%s']['%s']['roles'].contains('restApiPrefixRegistrationSessionEventAmendPost'))", JWT_SCOPE_RESOURCE_ACCESS, JWT_SCOPE_RESOURCE_ACCESS, clientId, JWT_SCOPE_RESOURCE_ACCESS, clientId))
                    .antMatchers(HttpMethod.PUT, "restApi/prefixRegistrationSessionEventAmends/**").access(String.format("isAuthenticated() and (principal?.claims.get('%s') != null) and (principal?.claims['%s'].get('%s') != null) and (principal?.claims['%s']['%s']['roles'].contains('restApiPrefixRegistrationSessionEventAmendPut'))", JWT_SCOPE_RESOURCE_ACCESS, JWT_SCOPE_RESOURCE_ACCESS, clientId, JWT_SCOPE_RESOURCE_ACCESS, clientId))
                    .antMatchers(HttpMethod.PATCH, "restApi/prefixRegistrationSessionEventAmends/**").access(String.format("isAuthenticated() and (principal?.claims.get('%s') != null) and (principal?.claims['%s'].get('%s') != null) and (principal?.claims['%s']['%s']['roles'].contains('restApiPrefixRegistrationSessionEventAmendPatch'))", JWT_SCOPE_RESOURCE_ACCESS, JWT_SCOPE_RESOURCE_ACCESS, clientId, JWT_SCOPE_RESOURCE_ACCESS, clientId))
                    .antMatchers(HttpMethod.DELETE, "restApi/prefixRegistrationSessionEventAmends/**").denyAll()
                    .antMatchers(HttpMethod.GET, "restApi/prefixRegistrationSessionEventComments/**").access(String.format("isAuthenticated() and (principal?.claims.get('%s') != null) and (principal?.claims['%s'].get('%s') != null) and (principal?.claims['%s']['%s']['roles'].contains('restApiPrefixRegistrationSessionEventCommentGet'))", JWT_SCOPE_RESOURCE_ACCESS, JWT_SCOPE_RESOURCE_ACCESS, clientId, JWT_SCOPE_RESOURCE_ACCESS, clientId))
                    .antMatchers(HttpMethod.HEAD, "restApi/prefixRegistrationSessionEventComments/**").access(String.format("isAuthenticated() and (principal?.claims.get('%s') != null) and (principal?.claims['%s'].get('%s') != null) and (principal?.claims['%s']['%s']['roles'].contains('restApiPrefixRegistrationSessionEventCommentHead'))", JWT_SCOPE_RESOURCE_ACCESS, JWT_SCOPE_RESOURCE_ACCESS, clientId, JWT_SCOPE_RESOURCE_ACCESS, clientId))
                    .antMatchers(HttpMethod.POST, "restApi/prefixRegistrationSessionEventComments/**").access(String.format("isAuthenticated() and (principal?.claims.get('%s') != null) and (principal?.claims['%s'].get('%s') != null) and (principal?.claims['%s']['%s']['roles'].contains('restApiPrefixRegistrationSessionEventCommentPost'))", JWT_SCOPE_RESOURCE_ACCESS, JWT_SCOPE_RESOURCE_ACCESS, clientId, JWT_SCOPE_RESOURCE_ACCESS, clientId))
                    .antMatchers(HttpMethod.PUT, "restApi/prefixRegistrationSessionEventComments/**").access(String.format("isAuthenticated() and (principal?.claims.get('%s') != null) and (principal?.claims['%s'].get('%s') != null) and (principal?.claims['%s']['%s']['roles'].contains('restApiPrefixRegistrationSessionEventCommentPut'))", JWT_SCOPE_RESOURCE_ACCESS, JWT_SCOPE_RESOURCE_ACCESS, clientId, JWT_SCOPE_RESOURCE_ACCESS, clientId))
                    .antMatchers(HttpMethod.PATCH, "restApi/prefixRegistrationSessionEventComments/**").access(String.format("isAuthenticated() and (principal?.claims.get('%s') != null) and (principal?.claims['%s'].get('%s') != null) and (principal?.claims['%s']['%s']['roles'].contains('restApiPrefixRegistrationSessionEventCommentPatch'))", JWT_SCOPE_RESOURCE_ACCESS, JWT_SCOPE_RESOURCE_ACCESS, clientId, JWT_SCOPE_RESOURCE_ACCESS, clientId))
                    .antMatchers(HttpMethod.DELETE, "restApi/prefixRegistrationSessionEventComments/**").denyAll()
                    .anyRequest().denyAll()
                .and()
                .oauth2ResourceServer().jwt();
    }
    // TODO
}
