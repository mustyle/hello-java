package org.fool.hellojava.service;

import org.fool.hellojava.component.AbstractComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AService extends AbstractComponent<AService> {
    private static final Logger logger = LoggerFactory.getLogger(AService.class);

    public AService() {
        // initialize sth.
    }

    @Override
    protected void doStart() {
        logger.info("AService start");
    }

    @Override
    protected void doStop() {
        logger.info("AService stop");
    }
}
