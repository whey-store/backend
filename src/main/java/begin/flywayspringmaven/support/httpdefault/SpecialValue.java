package begin.flywayspringmaven.support.httpdefault;

/**
 * Special Value.
 *
 * @author thopv
 */
public enum SpecialValue {
    NONE(""),
    CURRENT_USER_ID("CURRENT_USER_ID");

    private String code;

    SpecialValue(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
