package com.example.kevin.pvmushroom.DanhSachStatic;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.example.kevin.pvmushroom.Object.ObjectFragmentCustom;
import com.example.kevin.pvmushroom.R;

import java.util.ArrayList;

/**
 * Created by kevin on 1/31/2016.
 */
public class Ds_Fragment {

    public static FragmentManager getFm() {
        return fm;
    }

    public static void setFm(FragmentManager zfm) {
        fm = zfm;
    }

    static FragmentManager fm;




    public static int getFrag_current() {
        return frag_current;
    }

    public static void setFrag_current(int frag_current) {
        Ds_Fragment.frag_current = frag_current;
    }

    static int frag_current;
    public static ArrayList<ObjectFragmentCustom> getListFragment() {
        return listFragment;
    }
    public static void AddItem(ObjectFragmentCustom obj)
    {
        listFragment.add(obj);
    }
    public static void setListFragment(ArrayList<ObjectFragmentCustom> listFragment) {
        listFragment = listFragment;
    }
    public static ObjectFragmentCustom getItemList(int position)
    {
        return listFragment.get(position);
    }

    static ArrayList<ObjectFragmentCustom> listFragment = new ArrayList<>();



}
