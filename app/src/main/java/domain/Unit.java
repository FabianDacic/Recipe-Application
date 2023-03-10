package domain;

public enum Unit {
    Litre, Piece, Gram,No_unit;

    public static Unit fromString(String text) {
        if (text == null || text.trim().equals("")) {
            return No_unit;
        }
        for (Unit unit : Unit.values()) {
            if (unit.name().equalsIgnoreCase(text)) {
                return unit;
            }
        }
        throw new IllegalStateException("Unknown unit, please add unit to enum or correct ingredient : "+text);
    }

    @Override
    public String toString() {
        return this.name();
    }
}