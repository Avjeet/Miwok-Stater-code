package com.example.android.miwok;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by AVJEET on 30-01-2017.
 */

public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {
    private Context mContext;
    public SimpleFragmentPagerAdapter(Context context , FragmentManager fm) {
        super(fm);
        mContext=context;

    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public Fragment getItem(int position) {

        switch(position){
            case 0: return new NumbersFragment();

            case 1: return new ColorsFragment();

            case 2: return new PhrasesFragment();

            case 3: return new FamilyFragment();

            default:return null;
        }

    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch(position){
            case 0: return mContext.getString(R.string.category_numbers);

            case 1: return mContext.getString(R.string.category_colors);

            case 2: return mContext.getString(R.string.category_phrases);

            case 3: return "Family";

        default: return null;
        }

    }
}
