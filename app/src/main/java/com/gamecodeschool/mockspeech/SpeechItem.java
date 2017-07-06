package com.gamecodeschool.mockspeech;

import android.net.Uri;

import java.sql.Date;

/**
 * Created by nan.liu on 2017/07/06.
 */

public class SpeechItem {

    private String speechName;
    private Date speechDate;
    private String speechPlace;
    private Uri speechUri;
    private String speechCategory;
    private String speecher;
    private String speech;

    public String getSpeech() {
        return speech;
    }

    public void setSpeech(String speech) {
        this.speech = speech;
    }

    public SpeechItem(String speechName, Date speechDate, String speechPlace, Uri speechUri, String speechCategory, String speecher) {
        this.speechName = speechName;
        this.speechDate = speechDate;
        this.speechPlace = speechPlace;
        this.speechUri = speechUri;
        this.speechCategory = speechCategory;
        this.speecher = speecher;
    }

    public String getSpeechName() {
        return speechName;
    }

    public void setSpeechName(String speechName) {
        this.speechName = speechName;
    }

    public Date getSpeechDate() {
        return speechDate;
    }

    public void setSpeechDate(Date speechDate) {
        this.speechDate = speechDate;
    }

    public String getSpeechPlace() {
        return speechPlace;
    }

    public void setSpeechPlace(String speechPlace) {
        this.speechPlace = speechPlace;
    }

    public Uri getSpeechUri() {
        return speechUri;
    }

    public void setSpeechUri(Uri speechUri) {
        this.speechUri = speechUri;
    }

    public String getSpeechCategory() {
        return speechCategory;
    }

    public void setSpeechCategory(String speechCategory) {
        this.speechCategory = speechCategory;
    }

    public String getSpeecher() {
        return speecher;
    }

    public void setSpeecher(String speecher) {
        this.speecher = speecher;
    }
}
