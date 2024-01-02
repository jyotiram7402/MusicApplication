package org.gfg.musicapplication.response;

import lombok.Getter;

@Getter
public enum GenericMessages {
    FAILURE("failure"),
    SUCCESS("Success"),
    SIGNUPSUCCESS("Signup Successful."),
    SIGNUPFAILURE("Signup Failure."),
    ENTRYALREADYPRESENT("Entry Already present"),
    SONGADDITIONSUCCESS("song addition success"),
    SONGADDITIONFAILURE("song addition failure"),
    USERNOTPRESENTTOCREATESONG("user not present to create song");

    private String message;

    GenericMessages(String message){
        this.message = message;

    }

}
