package se.citerus.java17.eventsourcing;

public record Version(int value) {
    public static Version of(int version) {
        return new Version(version);
    }

    public Version increment() {
        return new Version(value + 1);
    }
}
