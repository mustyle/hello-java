package org.fool.hellojava.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractComponent<T> implements Component<T> {
    protected final Logger logger;

    public AbstractComponent() {
        this.logger = LoggerFactory.getLogger(getClass());
    }

    @Override
    public T start() {
        doStart();

        return (T) this;
    }

    @Override
    public T stop() {
        doStop();

        return (T) this;
    }

    protected abstract void doStart();

    protected abstract void doStop();
}
