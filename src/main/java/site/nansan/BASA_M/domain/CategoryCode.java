package site.nansan.BASA_M.domain;

public record CategoryCode(int value) {

    public static CategoryCode of(int group, int child) {
        return new CategoryCode(group * 100 + child);
    }

    @Override public String toString() {
        return Integer.toString(value);
    }
}
