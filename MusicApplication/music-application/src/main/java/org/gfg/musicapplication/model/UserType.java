package org.gfg.musicapplication.model;

import lombok.Getter;

@Getter
public enum UserType {
    ADMIN(0, "admin"),
    SUBSCRIBER(1,"subscriber"),
    NONSUBSCRIBER(2,"non-subscriber"),
    GUEST(3,"guest");

    Integer id;
    String userType;

    UserType(Integer id,String userType){
        this.id =id ;
        this.userType = userType;
    }


}
