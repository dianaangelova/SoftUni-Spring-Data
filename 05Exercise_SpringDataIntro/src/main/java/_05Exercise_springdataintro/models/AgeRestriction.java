package _05Exercise_springdataintro.models;

public enum AgeRestriction {
    MINOR("MINOR"),
    TEEN("TEEN"),
    ADULT("ADULT");

    public final String label;

    AgeRestriction(String label) {
        this.label = label;
    }
}
