package se.citerus.java17.eventsourcing;

public record Balance(long value) {
    public Balance subtract(Amount amount) {
        return new Balance(value - amount.amount());
    }

    public Balance add(Amount amount) {
        return new Balance(value + amount.amount());
    }
}
