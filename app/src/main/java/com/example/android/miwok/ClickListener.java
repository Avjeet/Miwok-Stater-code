package com.example.android.miwok;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * Created by AVJEET on 18-01-2017.
 */

public class ClickListener implements AdapterView.OnItemClickListener {
    private FragmentActivity mActivity;
    private Context mContext;
    private ArrayList<Word> mWord;
    private Word word;
    private ImageView mImageView = null;
    private MediaPlayer mediaPlayer;
    private AudioManager audioManager;
    private int result;

    AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            switch (focusChange) {
                case AudioManager.AUDIOFOCUS_GAIN:
                    mediaPlayer.start();
                    break;

                case AudioManager.AUDIOFOCUS_LOSS:
                    mediaPlayer.stop();
                    releaseMediaPlayer();
            }
        }
    };

    ClickListener(FragmentActivity activity, ArrayList<Word> word) {
        mContext = activity.getBaseContext();
        mActivity= activity;
        mWord = word;
        audioManager = (AudioManager) mActivity.getSystemService(Context.AUDIO_SERVICE);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        releaseMediaPlayer();
        word = mWord.get(position);

        Toast.makeText(mContext, word.getMiwokTranslation(), Toast.LENGTH_SHORT).show();

        mImageView = (ImageView) view.findViewById(R.id.play_button);
        setPauseImage();

        result = audioManager.requestAudioFocus(mOnAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

        if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
            // We have audio focus now.

            mediaPlayer = MediaPlayer.create(mContext, word.getAudioResourceId());

            mediaPlayer.start();

            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {

                    mediaPlayer.release();
                    setPlayImage();
                }
            });
        }
    }

    public void setPauseImage() {
        if(mImageView!=null)
            mImageView.setImageResource(android.R.drawable.ic_media_pause);
    }

    public void setPlayImage() {
        if(mImageView!=null)
            mImageView.setImageResource(android.R.drawable.ic_media_play);
    }

    public void releaseMediaPlayer() {
        if (mediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mediaPlayer.release();

        }

        // Regardless of whether or not we were granted audio focus, abandon it. This also
        // unregisters the AudioFocusChangeListener so we don't get anymore callbacks.
        audioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
    }
}
