package com.gamecodeschool.mockspeech;

/**
 * Created by nan.liu on 2017/07/06.
 */

public class SpeechItem {

    private int speechId;
    private String speechName;
    private String speechDate;
    private String speechPlace;
    private String speechUri;
    private int speechCategory;
    private int speecher;
    private String speech;
    private String translation;

    public SpeechItem() {

    }

    public SpeechItem(String speechName, String speechDate, String speechPlace, String speechUri, int speechCategory, int speecher, String speech, String translation) {
        this.speechName = speechName;
        this.speechDate = speechDate;
        this.speechPlace = speechPlace;
        this.speechUri = speechUri;
        this.speechCategory = speechCategory;
        this.speecher = speecher;
        this.speech = speech;
        this.translation = translation;
    }

    public int getSpeechId() {
        return speechId;
    }

    public void setSpeechId(int speechId) {
        this.speechId = speechId;
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

    public String getSpeechUri() {
        return speechUri;
    }

    public void setSpeechUri(String speechUri) {
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
