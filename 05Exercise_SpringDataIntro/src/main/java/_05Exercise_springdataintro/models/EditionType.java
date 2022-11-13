package _05Exercise_springdataintro.models;

public enum EditionType {
    NORMAL("NORMAL"),
    PROMO("PROMO"),
    GOLD("GOLD");

    public final String type;

    EditionType(String type) {
        this.type = type;
    }
}
