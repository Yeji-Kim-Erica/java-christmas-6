package christmas.model.domain;

public enum EventBadge {
    STAR("별", 5000),
    TREE("트리", 10000),
    SANTA("산타", 20000);

    private final String name;
    private final int standard;

    EventBadge(String name, int standard) {
        this.name = name;
        this.standard = standard;
    }
}
