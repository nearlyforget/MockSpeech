package com.gamecodeschool.mockspeech;


import android.os.Parcel;
import android.os.Parcelable;

class Words implements Parcelable {
    private int id;
    private String words;
    private String type;
    private String ipa;
    private String mean;
    private int speech;

    public Words() {

    }

    protected Words(Parcel in) {
        id = in.readInt();
        words = in.readString();
        type = in.readString();
        ipa = in.readString();
        mean = in.readString();
        speech = in.readInt();
    }

    public static final Creator<Words> CREATOR = new Creator<Words>() {
        @Override
        public Words createFromParcel(Parcel in) {
            return new Words(in);
        }

        @Override
        public Words[] newArray(int size) {
            return new Words[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(words);
        dest.writeString(type);
        dest.writeString(ipa);
        dest.writeString(mean);
        dest.writeInt(speech);
    }
}
