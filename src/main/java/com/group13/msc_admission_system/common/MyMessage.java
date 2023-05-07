package com.group13.msc_admission_system.common;

/**
 * This MyMessage class is created to handle all messages to be sent as a response to Request.
 * Instead of hardcoding the responses in the form of strings in each method, we instead use this class to standardize all returned strings
 */
public class MyMessage {

    public static final String created= "CREATED";
    public static final String updated= "UPDATED";
    public static final String deleted= "DELETED";
    public static final String added= "ADDED";
    public static final String removed= "REMOVED";
    public static final String invalidInput= "Invalid Input Error";

    public static String resourceNotFound(ResourceType resourceType, Long id){
        return resourceType +": " + id+ " NOT FOUND";
    }
    public static String resourceNotFound(ResourceType resourceType){
        return resourceType +": NOT FOUND";
    }

    public static String resourceAlreadyExist(ResourceType resourceType){
        return resourceType + ": ALREADY EXIST";
    }

    public static String isEmpty(String field){ return field.toUpperCase() + " IS EMPTY"; }

    public static String updated(ResourceType resourceType){ return resourceType +" " + updated;}

    public static String deleted(ResourceType resourceType){ return resourceType +" " + deleted;}

    public static String added(ResourceType resourceType){ return resourceType +" " + added;}

    public static String removed(ResourceType resourceType){ return resourceType +" " + removed;}
}
