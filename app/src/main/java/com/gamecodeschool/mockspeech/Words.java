package com.gamecodeschool.mockspeech;


class Words {

    private String words;
    private String type;
    private String ipa;
    private String mean;
    private int speech;

    public Words() {

    }

    public Words(String words, String type, String ipa, String mean, int speech) {
        this.words = words;
        this.type = type;
        this.ipa = ipa;
        this.mean = mean;
        this.speech = speech;
    }

    String getWords() {
        return words;
    }

    public void setWords(String words) {
        this.words = words;
    }

    String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    String getIpa() {
        return ipa;
    }

    public void setIpa(String ipa) {
        this.ipa = ipa;
    }

    String getMean() {
        return mean;
    }

    public void setMean(String mean) {
        this.mean = mean;
    }

    public int getSpeech() {
        return speech;
    }

    public void setSpeech(int speech) {
        this.speech = speech;
    }
}
