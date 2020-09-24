package com.example.finalcriminalintent.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class User implements Serializable {

    private String mUserName;
    private String mUserPassword;
    private UUID mUserID;


    public User() {
        mUserID = UUID.randomUUID();
    }

    public User(String userName, String userPassword) {
        mUserName = userName;
        mUserPassword = userPassword;
    }

    public User(UUID id, String userName, String userPassword) {
        mUserID = id;
        mUserName = userName;
        mUserPassword = userPassword;
    }

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String userName) {
        mUserName = userName;
    }

    public String getUserPassword() {
        return mUserPassword;
    }

    public void setUserPassword(String userPassword) {
        mUserPassword = userPassword;
    }

    public UUID getUserID() {
        return mUserID;
    }


}
