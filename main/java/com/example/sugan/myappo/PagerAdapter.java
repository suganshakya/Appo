package com.example.sugan.myappo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by sugan on 22/07/16.
 */
public class PagerAdapter extends FragmentStatePagerAdapter {
    static final int NUM_ITEMS = 3;
    FragmentManager fm;

    public PagerAdapter(FragmentManager fm) {
        super(fm);
        this.fm = fm;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        FragmentTransaction ft= fm.beginTransaction();
        switch (position){
            case 0:
                fragment = new CreateFragment();
                break;
            case 1:
                fragment = new ViewFragment();
                break;
            case 2:
                fragment = new EditFragment();
                break;
        }
        ft.detach(fragment).attach(fragment).commit();
        return fragment;
    }

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }


    public CharSequence getPageTitle(int position){
        String title = "";
        switch (position){
            case 0:
                title = "Create";
                break;
            case 1:
                title = "View";
                break;
            case 2:
                title = "Edit";
                break;
        }
        return title;
    }
}
