package com.java14.constants;

public class EndPoints {
    private static final String VERSION = "/v1";
    private static final String API = "/api";
    private static final String TEST = "/test";


    private static final String ROOT = API + VERSION;

    public static final String AUTH = ROOT + "/auth";

    public static final String LOGIN = "/login";
    public static final String REGISTER_MANAGER = "/register-manager";
    public static final String REGISTER_EMPLOYEE = "/register-employee";
    public static final String REGISTER_ADMIN = "/register-admin";
    public static final String CHANGE_PASSWORD = "/change-password";

}
