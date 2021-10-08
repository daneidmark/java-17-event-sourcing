package se.citerus.java17.eventsourcing;

public record Version(int value) {
    public Version increment() {
        return new Version(value + 1);
    }
}
