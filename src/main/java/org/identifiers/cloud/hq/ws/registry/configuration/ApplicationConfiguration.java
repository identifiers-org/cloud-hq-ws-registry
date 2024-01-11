package org.identifiers.cloud.hq.ws.registry.configuration;

import java.time.Duration;
import java.util.Properties;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.security.oauth2.client.web.server.ServerOAuth2AuthorizedClientRepository;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.netty.http.client.HttpClient;

/**
 * Project: registry
 * Package: org.identifiers.cloud.hq.ws.registry.configuration
 * Timestamp: 2018-10-11 20:58
 *
 * @author Manuel Bernal Llinares <mbdebian@gmail.com>
 * ---
 */
@Configuration
@EnableJpaAuditing
@EnableRetry
public class ApplicationConfiguration {
    private static final Duration WS_REQUEST_TIMEOUT = Duration.ofSeconds (2);

    HttpClient httpClient() {
        return HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, (int)WS_REQUEST_TIMEOUT.toMillis())
                .responseTimeout(WS_REQUEST_TIMEOUT)
                .doOnConnected(conn ->
                        conn.addHandlerLast(new ReadTimeoutHandler((int)WS_REQUEST_TIMEOUT.toSeconds()))
                            .addHandlerLast(new WriteTimeoutHandler((int)WS_REQUEST_TIMEOUT.toSeconds())));
    }


    @Bean
    @Profile("authenabled")
    WebClient webClient(ReactiveClientRegistrationRepository clientRegistrations,
                        ServerOAuth2AuthorizedClientRepository authorizedClients) {
        var oauth = new ServerOAuth2AuthorizedClientExchangeFilterFunction(
                clientRegistrations, authorizedClients);
        oauth.setDefaultOAuth2AuthorizedClient(true);

        return WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient()))
                .filter(oauth)
                .build();
    }

    @Bean
    @Profile("!authenabled")
    public WebClient webClientNoAuth() {
        var clientConnector = new ReactorClientHttpConnector(httpClient());
        return WebClient.builder()
                .clientConnector(clientConnector)
                .build();
    }

    @Bean
    public JavaMailSender getJavaMailSender(
            @Value("${spring.mail.host}") String host,
            @Value("${spring.mail.port}") int port,
            @Value("${spring.mail.username}") String username,
            @Value("${spring.mail.password}") String password,
            @Value("${spring.mail.protocol}") String protocol,
            @Value("${spring.mail.properties.mail.smtp.auth") String auth,
            @Value("${spring.mail.properties.mail.smtp.starttls.enable") String tlsEnabled,
            @Value("${spring.mail.properties.mail.smtp.starttls.required") String tlsRequired,
            @Value("${spring.mail.properties.mail.debug") String debug
    ) {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(host);
        mailSender.setPort(port);

        mailSender.setUsername(username);
        mailSender.setPassword(password);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", protocol);
        props.put("mail.smtp.auth", auth);
        props.put("mail.smtp.starttls.enable", tlsEnabled);
        props.put("mail.smtp.starttls.required", tlsRequired);
        props.put("mail.debug", debug);

        return mailSender;
    }

}
