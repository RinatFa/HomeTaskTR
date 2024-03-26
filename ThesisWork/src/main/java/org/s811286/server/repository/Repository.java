package org.s811286.server.repository;

public interface Repository<T> {
    void save(T text);

    void saveEmpty(T text);

    T load();
}
