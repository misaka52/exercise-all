
public enum EnumSingleton {
    INSTANCE(1, "123");

    private int code;
    private String desc;
    EnumSingleton(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}