package com.example.finalcriminalintent.model;

import com.example.finalcriminalintent.utils.DateUtils;

import java.util.Date;
import java.util.UUID;

public class Crime {

    private UUID mId;
    private String mTitle;
    private Date mDate;
    private boolean mSolved;

    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public boolean isSolved() {
        return mSolved;
    }

    public void setSolved(boolean solved) {
        mSolved = solved;
    }

    public Crime() {
        this(UUID.randomUUID());
//        mDate = DateUtils.randomDate();
    }

    public Crime(UUID id) {
        mId = id;
        mDate = new Date();
    }

    public Crime(UUID id, String title, Date date, boolean solved) {
        mId = id;
        mTitle = title;
        mDate = date;
        mSolved = solved;
    }
}

    /*public static class Builder {

        private Crime mCrime;

        public Builder() {
            mCrime = new Crime();
        }

        public Builder setTitle(String title) {
            mCrime.setTitle(title);
            return this;
        }

        public Builder setDate(Date date) {
            mCrime.setDate(date);
            return this;
        }

        public Crime create() {
            return mCrime;
        }
    }*/

//public class Crime {
//
//    private UUID mId;
//    private String mTitle;
//    private Date mDate;
//    private boolean mSolved;
//
//    public UUID getId() {
//        return mId;
//    }
//
//    public String getTitle() {
//        return mTitle;
//    }
//
//    public void setTitle(String title) {
//        mTitle = title;
//    }
//
//    public Date getDate() {
//        return mDate;
//    }
//
//    public Crime() {
//        mId = UUID.randomUUID();
//        mDate = DateUtils.randomDate();
//    }
//
//
////    public String getWithoutTime(){
////        String str = mDate.toString();
////        String[] output = str.split(" ");
////        String finalStr = output[0] + " " + output[1] + " " + output[2] + " " + output[5];
////        return finalStr;
////    }
//
////    public String getTime(){
////        String str = mDate.toString();
////        String[] output = str.split(" ");
////        String finalStr = output[3] + " " + output[4];
////        return finalStr;
////    }
//
//    public void setDate(Date date) {
//        mDate = date;
//    }
//
//    public boolean isSolved() {
//        return mSolved;
//    }
//
//    public void setSolved(boolean solved) {
//        mSolved = solved;
//    }
//

//}
