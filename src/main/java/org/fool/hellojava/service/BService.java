package org.fool.hellojava.service;

import org.fool.hellojava.component.AbstractComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BService extends AbstractComponent<BService> {
    private static final Logger logger = LoggerFactory.getLogger(BService.class);

    public BService() {
        // initialize sth.
    }

    @Override
    protected void doStart() {
        logger.info("BService start");
    }

    @Override
    protected void doStop() {
        logger.info("BService stop");
    }
}
