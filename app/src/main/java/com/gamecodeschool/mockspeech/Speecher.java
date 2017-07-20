package com.gamecodeschool.mockspeech;

import java.io.Serializable;

/**
 * Created by nan.liu on 2017/07/06.
 */

public class Speecher implements Serializable {
    private int speecherId;
    private String speecherName;
    private String speecherBirthday;
    private String speecharDisc;
    private String speecherCountry;
    private String speecherSocial;
    private String speecher_photo;

    public Speecher() {

    }

    public Speecher(String speecherName, String speecherBirthday, String speecharDisc, String speecherCountry, String speecherSocial, String speecher_photo) {
        this.speecherName = speecherName;
        this.speecherBirthday = speecherBirthday;
        this.speecharDisc = speecharDisc;
        this.speecherCountry = speecherCountry;
        this.speecherSocial = speecherSocial;
        this.speecher_photo = speecher_photo;
    }

    public int getSpeecherId() {
        return speecherId;
    }

    public void setSpeecherId(int speecherId) {
        this.speecherId = speecherId;
    }

    public String getSpeecherName() {
        return speecherName;
    }

    public void setSpeecherName(String speecherName) {
        this.speecherName = speecherName;
    }

    public String getSpeecherBirthday() {
        return speecherBirthday;
    }

    public void setSpeecherBirthday(String speecherBirthday) {
        this.speecherBirthday = speecherBirthday;
    }

    public String getSpeecharDisc() {
        return speecharDisc;
    }

    public void setSpeecharDisc(String speecharDisc) {
        this.speecharDisc = speecharDisc;
    }

    public String getSpeecherCountry() {
        return speecherCountry;
    }

    public void setSpeecherCountry(String speecherCountry) {
        this.speecherCountry = speecherCountry;
    }

    public String getSpeecherSocial() {
        return speecherSocial;
    }

    public void setSpeecherSocial(String speecherSocial) {
        this.speecherSocial = speecherSocial;
    }

    public String getSpeecher_photo() {
        return speecher_photo;
    }

    public void setSpeecher_photo(String speecher_photo) {
        this.speecher_photo = speecher_photo;
    }
}
