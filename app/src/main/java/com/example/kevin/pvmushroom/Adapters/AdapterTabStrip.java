package com.example.kevin.pvmushroom.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.kevin.pvmushroom.Fragment.frag_of_PhieuThu.Frag_Phieu_Thu;
import com.example.kevin.pvmushroom.Fragment.Frag_Phieu_Chi;

import java.util.Locale;

/**
 * Created by kevin on 1/22/2016.
 */
public class AdapterTabStrip extends FragmentPagerAdapter {


    public AdapterTabStrip(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                Frag_Phieu_Thu pt = new Frag_Phieu_Thu();
                return pt;
            case 1:
                Frag_Phieu_Chi pc = new Frag_Phieu_Chi();
                return pc;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "PHIẾU THU";
            case 1:

                return "PHIẾU CHI";

        }
        return null;
    }
}
