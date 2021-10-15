package se.citerus.java17.eventsourcing;

import java.util.UUID;

public record AccountId(UUID value) {
    public static AccountId of(UUID id) {
        return new AccountId(id);
    }
}
