package com.android.location.response;


public enum ResponseCode {
    SUCCESS(1, "SUCCESS"),
    ERROR(0, "ERROR"),
    NEED_LOGIN(10, "NEED_LOGIN"),
    ILLEGAL_ARGUMENT(300, "ILLEGAL_ARGUMENT"),
	UNKNOWN_ERROR(305, "UNKNOWN_ERROR"),
	LIMITED_AUTHORITY(310, "LIMITED_AUTHORITY"),
	SERVER_ERROR(500, "SERVER_ERROR");
	
    private final int code;
    private final String desc;

    ResponseCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

}
