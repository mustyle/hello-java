package org.fool.hellojava.component;

public interface Component<T> {
    T start();

    T stop();
}
