package org.identifiers.cloud.hq.ws.registry.models;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

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
public class MirIdServiceWsClient implements MirIdService {
    private static final int WS_REQUEST_RETRY_MAX_ATTEMPTS = 12;
    private static final int WS_REQUEST_RETRY_BACK_OFF_PERIOD = 1500; // 1.5 seconds
    private static final int WS_REQUEST_CONNECT_TIMEOUT = 1000; // 1 second
    private static final int WS_REQUEST_READ_TIMEOUT = 1000; // 1 second

    @Value("${org.identifiers.cloud.hq.ws.registry.backend.service.miridcontroller.host}")
    private String wsMirIdControllerHost;
    @Value("${org.identifiers.cloud.hq.ws.registry.backend.service.miridcontroller.port}")
    private String wsMirIdControllerPort;

    // Helpers
    private String getMirIdServiceBaseUrl() {
        // We should allow HTTP / HTTPS configurability in case we want the cluster internal traffic to be encrypted,
        // otherwise this is fine, as all HQ services are deployed within the same cluster
        return String.format("http://%s:%s/mirIdApi", wsMirIdControllerHost, wsMirIdControllerPort);
    }
    // END - Helpers

    @Retryable(maxAttempts = WS_REQUEST_RETRY_MAX_ATTEMPTS,
            backoff = @Backoff(delay = WS_REQUEST_RETRY_BACK_OFF_PERIOD))
    @Override
    public String mintId() throws MirIdServiceException {
        // TODO
        int status = 0;
        String mirId = null;
        HttpURLConnection connection = null;
        try {
            URL requestUrl = new URL(String.format("%s/mintId", getMirIdServiceBaseUrl()));
            connection = (HttpURLConnection) requestUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.setInstanceFollowRedirects(false);
            status = connection.getResponseCode();
            if (status == 200) {
                // Whaaaaat!?!? Thank you Baeldung! https://www.baeldung.com/java-http-request
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder content = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                in.close();
                mirId = content.toString();
            }
        } catch (RuntimeException | IOException e) {
            throw new MirIdServiceException(e.getMessage());
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        if (status != 200) {
            throw new MirIdServiceException(String.format("MIR ID minting FAILED, status code '%d'", status));
        }
        return mirId;
    }

    @Retryable(maxAttempts = WS_REQUEST_RETRY_MAX_ATTEMPTS,
            backoff = @Backoff(delay = WS_REQUEST_RETRY_BACK_OFF_PERIOD))
    @Override
    public void keepAlive(String mirId) throws MirIdServiceException {
        // TODO
    }
}
