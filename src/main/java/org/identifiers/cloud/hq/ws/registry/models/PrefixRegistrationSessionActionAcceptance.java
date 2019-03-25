package org.identifiers.cloud.hq.ws.registry.models;

import org.identifiers.cloud.hq.ws.registry.data.models.PrefixRegistrationSession;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Project: registry
 * Package: org.identifiers.cloud.hq.ws.registry.models
 * Timestamp: 2019-03-25 12:00
 *
 * @author Manuel Bernal Llinares <mbdebian@gmail.com>
 * ---
 *
 * Composite action to perform upon prefix registration session completion
 */
@Component
@Qualifier("PrefixRegistrationSessionActionAcceptance")
public class PrefixRegistrationSessionActionAcceptance implements PrefixRegistrationSessionAction {


    private List<PrefixRegistrationSessionAction> buildActionSequence() {
        // TODO
    }

    @Override
    public PrefixRegistrationSessionActionReport performAction(PrefixRegistrationSession session) throws PrefixRegistrationSessionActionException {
        return null;
    }
}
