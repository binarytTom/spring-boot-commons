package cn.binarytom.spring.commons.exception.error;

public enum BaseErrors implements Errors {
    /**
     * success
     */
    SUCCESS(0, "success"),
    /**
     * unknown exception
     */
    UNKNOWN_ERROR(1, "unknown error"),
    ;

    private int code;

    private String msg;

    BaseErrors(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public int code() {
        return code;
    }

    @Override
    public String msg() {
        return msg;
    }
}
