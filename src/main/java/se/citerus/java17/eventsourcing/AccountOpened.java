package se.citerus.java17.eventsourcing;

import java.time.Instant;

public record AccountOpened(Version version, Instant timestamp, AccountId id,
                            Balance balance) implements DomainEvent<AccountId> {

}
