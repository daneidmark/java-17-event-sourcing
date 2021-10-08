package se.citerus.java17.eventsourcing;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public abstract class AggregateRoot<T> {
    private Version version;
    private Instant timestamp;
    private T id;
    private List<DomainEvent<T>> uncommittedEvents = new ArrayList<>();

    public void loadFromHistory(List<DomainEvent<T>> history) {
        history.forEach(this::applyWrapper);
    }

    public void commit() {
        this.uncommittedEvents = new ArrayList<>();
    }

    protected void applyNew(DomainEvent<T> event) {
        uncommittedEvents.add(event);
        applyWrapper(event);
    }

    private void applyWrapper(DomainEvent<T> event) {
        version = event.version();
        timestamp = event.timestamp();
        id = event.id();

        apply(event);
    }

    protected abstract void apply(DomainEvent<T> domainEvent);


    protected Instant now() {
        return Instant.now();
    }

    protected Version initialVersion() {
        return new Version(0);
    }

    public Version getVersion() {
        return version;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public T getId() {
        return id;
    }

    public List<DomainEvent<T>> getUncommittedEvents() {
        return uncommittedEvents;
    }
}
