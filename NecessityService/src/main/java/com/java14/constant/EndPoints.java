package com.java14.constant;

public class EndPoints {
    public static final String VERSION="/v1";
    //profiller:
    public static final String API="/api";
    public static final String DEV="/dev";
    public static final String TEST="/test";

    public static final String ROOT=API+VERSION;


    //entities:
    public static final String LEAVE=ROOT+"/leave";
    public static final String SHIFT=ROOT+"/shift";
    public static final String EQUIPMENT=ROOT+"/equipment";
    public static final String EXPENSES=ROOT+"/expenses";



    //methods:
    public static final String REGISTER="/register";
    public static final String REGISTERWITHRABBIT="/registerwithrabbit";
    public static final String SAVE="/save";
    public static final String UPDATE="/update";
    public static final String DELETE="/delete";
    public static final String FINDALL="/findall";
    public static final String FINDBYID="/findbyid";

    public static final String LOGIN = "/login";
    public static final String ACTIVATECODE = "/activatecode";
    public static final String ACTIVATEUSER = "/activateuser";
    public static final String UPDATEPASSWORD = "/updatepassword";
    public static final String SIFREMIUNUTTUM = "/sifremiunuttum";


}
