package com.example.android.miwok;

/**
 * Created by AVJEET on 13-01-2017.
 */

class Word {
    private String mEnglish_trans;

    private String mMiwok_trans;

    private Integer mImgResourceId;

    private Integer mAudioId;

    Word(String en_tr, String mi_tr , Integer id,Integer aud_id) {
        mEnglish_trans = en_tr;
        mMiwok_trans = mi_tr;
        mImgResourceId=id;
        mAudioId=aud_id;
    }
    Word(String en_tr, String mi_tr,Integer aud_id ) {
        mEnglish_trans = en_tr;
        mMiwok_trans = mi_tr;
        mImgResourceId=0;
        mAudioId=aud_id;
    }

    public String getDefaultTranslation() {
        return mEnglish_trans;
    }

    public String getMiwokTranslation() {
        return mMiwok_trans;
    }

    public Integer getImageResourceId(){ return mImgResourceId; }

    public Integer getAudioResourceId(){return mAudioId; }
}
