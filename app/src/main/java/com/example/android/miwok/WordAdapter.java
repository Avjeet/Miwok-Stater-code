package com.example.android.miwok;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * Created by AVJEET on 13-01-2017.
 */

public class WordAdapter extends ArrayAdapter<Word> {
    private int backColorID;
    ViewHolder viewHolder=null;
    WordAdapter(Activity context , ArrayList<Word> words,Integer colorID){
        super(context,0, words);
        backColorID=ContextCompat.getColor(getContext(),colorID);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView=convertView;

        if(listItemView==null){
            viewHolder = null;
            listItemView= LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
            viewHolder=new ViewHolder((TextView)listItemView.findViewById(R.id.miwok_words),(TextView)listItemView.findViewById(R.id.english_words),(ImageView)listItemView.findViewById(R.id.icon_pic),listItemView.findViewById(R.id.text_layout),(ImageView) listItemView.findViewById(R.id.play_button));
            listItemView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)listItemView.getTag();
        }

        //For changing the background color
        viewHolder.textContainer.setBackgroundColor(backColorID);
        viewHolder.imageContainer.setBackgroundColor(backColorID);

        Word currentWord = getItem(position);

        //For selecting the corresponsing views in the word and attaching their source to them.
        viewHolder.miwokTextView.setText(currentWord.getMiwokTranslation());

        viewHolder.defaultTextView.setText(currentWord.getDefaultTranslation());

        if(currentWord.getImageResourceId()==0){
            viewHolder.imageView.setVisibility(View.GONE);
        }else {
            viewHolder.imageView.setImageResource(currentWord.getImageResourceId());
        }


        return listItemView;
    }

}
class ViewHolder{
        TextView miwokTextView;
        TextView defaultTextView;
        ImageView imageView;
        View textContainer;
        ImageView imageContainer;
    ViewHolder(TextView miwokTextView,TextView defaultTextView,ImageView imageView,View textContainer,ImageView imageContainer){
        this.miwokTextView=miwokTextView;
        this.defaultTextView=defaultTextView;
        this.imageView=imageView;
        this.textContainer=textContainer;
        this.imageContainer=imageContainer;
    }
        }