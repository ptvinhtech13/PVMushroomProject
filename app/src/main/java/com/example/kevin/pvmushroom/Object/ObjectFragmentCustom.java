package com.example.kevin.pvmushroom.Object;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.kevin.pvmushroom.DanhSachStatic.Ds_Fragment;
import com.example.kevin.pvmushroom.R;

/**
 * Created by kevin on 1/31/2016.
 */
public class ObjectFragmentCustom {

    public ObjectFragmentCustom(Fragment fragment, int ID) {
        this.fragment = fragment;
        this.ID = ID;
    }

   // Swap transcation custom animation khi OnBackPressed
    public void setSwapFragmentTransaction() {
        fragmentTransaction.setCustomAnimations(R.anim.img_anim_off, R.anim.img_anim_come);
    }

    android.support.v4.app.FragmentTransaction fragmentTransaction;

    public Fragment getFragment() {
        return fragment;
    }


    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void CallFrag(int position, boolean come_off) // bien come_off de bao hieu cho biet OnBackPressed
    {
        // position 0:year  1:harvest
        FragmentManager fragmentManager = Ds_Fragment.getFm();
        fragmentTransaction = fragmentManager.beginTransaction();
        if(come_off)
            fragmentTransaction.setCustomAnimations(R.anim.img_anim_come,R.anim.img_anim_come_2, R.anim.img_anim_off,R.anim.img_anim_come_2);
        else
            fragmentTransaction.setCustomAnimations(R.anim.img_anim_off_back_pressed, R.anim.img_anim_come);


        fragmentTransaction.replace(R.id.frag_container, Ds_Fragment.getItemList(position).getFragment());

        if(position != 0){
            fragmentTransaction.addToBackStack(position + "");
        }
        fragmentTransaction.commit();

        Ds_Fragment.setFrag_current(position); // setup frag dag tren man hinh
    }
    Fragment fragment;
    int ID;
}
