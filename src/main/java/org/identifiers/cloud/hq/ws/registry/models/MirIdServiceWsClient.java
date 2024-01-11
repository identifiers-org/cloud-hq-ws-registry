package org.identifiers.cloud.hq.ws.registry.models;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.http.HttpStatusCode;
import reactor.core.publisher.Mono;

import static org.springframework.util.StringUtils.hasText;

/**
 * Project: registry
 * Package: org.identifiers.cloud.hq.ws.registry.models
 * Timestamp: 2019-03-26 14:22
 *
 * @author Manuel Bernal Llinares <mbdebian@gmail.com>
 * ---
 *
 * This implementation of MIR ID Service delegates the operations on identifiers.org MIR ID Controller API.
 */
@Component
@Slf4j
public class MirIdServiceWsClient implements MirIdService {
    private static final int WS_REQUEST_RETRY_MAX_ATTEMPTS = 12;
    private static final int WS_REQUEST_RETRY_BACK_OFF_PERIOD = 1500; // 1.5 seconds

    @Value("${org.identifiers.cloud.hq.ws.registry.backend.service.miridcontroller.host}")
    private String wsMirIdControllerHost;
    @Value("${org.identifiers.cloud.hq.ws.registry.backend.service.miridcontroller.port}")
    private String wsMirIdControllerPort;

    private final WebClient webClient;
    public MirIdServiceWsClient(WebClient webClient) {
        this.webClient = webClient;
    }

    // Helpers
    private String getMirIdServiceBaseUrl() {
        // We should allow HTTP / HTTPS configurability in case we want the cluster internal traffic to be encrypted,
        // otherwise this is fine, as all HQ services are deployed within the same cluster
        return String.format("http://%s:%s/mirIdApi", wsMirIdControllerHost, wsMirIdControllerPort);
    }

    private String getWsMirIdMintingUrl() {
        return String.format("%s/mintId", getMirIdServiceBaseUrl());
    }

    @Retryable(maxAttempts = WS_REQUEST_RETRY_MAX_ATTEMPTS,
            backoff = @Backoff(delay = WS_REQUEST_RETRY_BACK_OFF_PERIOD))
    @Override
    public String mintId() throws MirIdServiceException {
        log.info("Requesting MIR ID MINTING");
        var response = webClient.get().uri(getWsMirIdMintingUrl()).retrieve()
                .bodyToMono(String.class)
                .doOnError(error -> {
                    throw new MirIdServiceException(String.format("MIR ID minting FAILED, %s", error));
                });
        String mirId = response.block();
        if (!hasText(mirId)) {
            throw new MirIdServiceException(String.format("MIR ID minting FAILED, NO BODY IN THE RESPONSE, response -> '%s'", response));
        }
        log.info(String.format("MIR ID MINTING, newly minted ID '%s'", mirId));
        return mirId;
    }

    @Retryable(maxAttempts = WS_REQUEST_RETRY_MAX_ATTEMPTS,
            backoff = @Backoff(delay = WS_REQUEST_RETRY_BACK_OFF_PERIOD))
    @Override
    public void keepAlive(String mirId) throws MirIdServiceException {
        log.info(String.format("Requesting '%s' MIR ID to be kept alive", mirId));
        String requestUrl = String.format("%s/keepAlive/%s", getMirIdServiceBaseUrl(), mirId);
        webClient.get().uri(requestUrl).retrieve()
                .onStatus(HttpStatusCode::is5xxServerError, response ->
                    Mono.error(new MirIdServiceException(String.format(
                            "MIR ID '%s' keepAlive FAILED, status code '%d', something went WRONG on the other side!",
                                mirId, response.statusCode().value()))
                    )
                )
                .onStatus(HttpStatusCode::is4xxClientError, response ->
                    Mono.error(new MirIdServiceException(String.format(
                            "MIR ID '%s' keepAlive FAILED, status code '%d', WE did something WRONG",
                                mirId, response.statusCode().value())))
                )
                .onStatus(HttpStatusCode::is3xxRedirection, response ->
                    Mono.error(new MirIdServiceException(String.format(
                            "MIR ID '%s' keepAlive FAILED, status code '%d', " +
                             "CONGRATULATIONS! YOU FOUND THE UNICORN! Something is deeply wrong because " +
                             "this iteration of the platform development has no redirections for the " +
                             "MIR ID Controller API Service",
                                mirId, response.statusCode().value())))
                );
                log.info(String.format("SUCCESS, Request for '%s' MIR ID to be kept alive", mirId));
    }
}
