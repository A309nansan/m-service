package site.nansan.BASA_M.domain;

public enum Operator {
    PLUS, MIN, MULT, DIV;

    public static Operator from(String operator) {
        for (Operator op : Operator.values()) {
            if (op.name().equalsIgnoreCase(operator)) {
                return op;
            }
        }
        throw new IllegalArgumentException("유효하지 않은 연산자입니다: " + operator);
    }
}
