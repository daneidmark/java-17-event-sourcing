package se.citerus.java17.eventsourcing;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;
import java.util.UUID;

public class AccountDeposited implements DomainEvent<AccountId> {
    @JsonProperty
    private final int version;
    @JsonProperty
    private final Instant timestamp;
    @JsonProperty
    private final UUID id;
    @JsonProperty
    private final long balance;

    public AccountDeposited(Version version,
                            Instant timestamp,
                            AccountId id,
                            Balance balance) {
        this.version = version.value();
        this.timestamp = timestamp;
        this.id = id.value();
        this.balance = balance.value();
    }

    @JsonCreator
    public AccountDeposited(
            @JsonProperty("version") int version,
            @JsonProperty("timestamp") Instant timestamp,
            @JsonProperty("id") UUID id,
            @JsonProperty("balance") long balance) {
        this.version = version;
        this.timestamp = timestamp;
        this.id = id;
        this.balance = balance;
    }

    @Override
    public AccountId id() {
        return AccountId.of(id);
    }

    @Override
    public Version version() {
        return Version.of(version);
    }

    @Override
    public Instant timestamp() {
        return timestamp;
    }

    public Balance balance() {
        return Balance.of(balance);
    }

    @Override
    public String toString() {
        return "AccountDeposited{" +
                "version=" + version +
                ", timestamp=" + timestamp +
                ", id=" + id +
                ", balance=" + balance +
                '}';
    }
}
