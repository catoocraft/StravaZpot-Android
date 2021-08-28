package com.sweetzpot.stravazpot.authenticaton.api;

public enum AccessScope {
    READ("read"),
    ACTIVITY_READ("activity:read"),
    ACTIVITY_WRITE("activity:write"),
    PROFILE_WRITE("profile:write"),
    READ_ALL("read_all"),
    ACTIVITY_READ_ALL("activity:read_all"),
    PROFILE_READ_ALL("profile:read_all");
    /*
    VIEW_PRIVATE("view_private"),
    VIEW_PRIVATE_WRITE("view_private,write");
    */
    private String rawValue;

    AccessScope(String rawValue) {
        this.rawValue = rawValue;
    }
    
    @Override
    public String toString() {
        return rawValue;
    }
}
