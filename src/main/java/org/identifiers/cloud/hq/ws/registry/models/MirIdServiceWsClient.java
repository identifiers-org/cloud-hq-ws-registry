package org.identifiers.cloud.hq.ws.registry.models;

import org.springframework.stereotype.Component;

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

    // Helpers
    // END - Helpers
    @Override
    public String mintId() throws MirIdServiceException {
        return null;
    }

    @Override
    public void keepAlive(String mirId) throws MirIdServiceException {

    }
}
