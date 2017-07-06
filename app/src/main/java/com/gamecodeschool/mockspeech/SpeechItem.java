package com.gamecodeschool.mockspeech;

import android.net.Uri;

/**
 * Created by nan.liu on 2017/07/06.
 */

public class SpeechItem {

    private String speechName;
    private String speechDate;
    private String speechPlace;
    private Uri speechUri;
    private int speechCategory;
    private int speecher;
    private String speech;
    private String translation;

    public SpeechItem(String speechName, String speechDate, String speechPlace, Uri speechUri, int speechCategory, int speecher, String speech, String translation) {
        this.speechName = speechName;
        this.speechDate = speechDate;
        this.speechPlace = speechPlace;
        this.speechUri = speechUri;
        this.speechCategory = speechCategory;
        this.speecher = speecher;
        this.speech = speech;
        this.translation = translation;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public String getSpeech() {
        return speech;
    }

    public void setSpeech(String speech) {
        this.speech = speech;
    }

    public String getSpeechName() {
        return speechName;
    }

    public void setSpeechName(String speechName) {
        this.speechName = speechName;
    }

    public String getSpeechDate() {
        return speechDate;
    }

    public void setSpeechDate(String speechDate) {
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

    public int getSpeechCategory() {
        return speechCategory;
    }

    public void setSpeechCategory(int speechCategory) {
        this.speechCategory = speechCategory;
    }

    public int getSpeecher() {
        return speecher;
    }

    public void setSpeecher(int speecher) {
        this.speecher = speecher;
    }
}
