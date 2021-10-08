package se.citerus.java17.eventsourcing;

import java.time.Instant;

public interface DomainEvent<T> {
    T id();

    Version version();

    Instant timestamp();
}
