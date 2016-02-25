package com.example.kevin.pvmushroom.Fragment.frag_of_PhieuThu;

import android.support.v4.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import com.example.kevin.pvmushroom.DanhSachStatic.DS_NAM;
import com.example.kevin.pvmushroom.DanhSachStatic.Ds_Fragment;
import com.example.kevin.pvmushroom.Fragment.frag_of_PhieuThu.Frag__Harvest;
import com.example.kevin.pvmushroom.Fragment.frag_of_PhieuThu.Frag__Year;
import com.example.kevin.pvmushroom.Object.ObjectFragmentCustom;
import com.example.kevin.pvmushroom.R;

/**
 * Created by kevin on 1/22/2016.
 */
public class Frag_Phieu_Thu extends Fragment {

    Frag__Year frag_year;
    Frag__Harvest frag_harvest;
    Frag__Info frag_info;
    Frag_Infor_Month frag_infor_month;
    Animation animationTextview;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.phieu_thu,container,false);


        Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), "SanFranciscoDisplay-Light.otf");
        Typeface tf2 = Typeface.createFromAsset(getActivity().getAssets(), "material-icon-font.ttf");
        animationTextview = AnimationUtils.loadAnimation(getContext(),R.anim.amin_button_left);

        if(Ds_Fragment.getListFragment().isEmpty()) {
            frag_harvest = new Frag__Harvest();
            frag_year = new Frag__Year();
            frag_info = new Frag__Info();
            frag_infor_month = new Frag_Infor_Month();

            Ds_Fragment.setFm(getActivity().getSupportFragmentManager());
            Ds_Fragment.AddItem(new ObjectFragmentCustom(frag_year, 0));
            Ds_Fragment.AddItem(new ObjectFragmentCustom(frag_harvest, 1));
            Ds_Fragment.AddItem(new ObjectFragmentCustom(frag_info, 2));
            Ds_Fragment.AddItem(new ObjectFragmentCustom(frag_infor_month, 3));

        }

        if(DS_NAM.getLy_ContainerHSV() == null) {
            HorizontalScrollView hsv_timeline = (HorizontalScrollView) view.findViewById(R.id.hsv_Timeline);
            LinearLayout lycontainer = (LinearLayout) view.findViewById(R.id.ly_container);

            DS_NAM.setHsv_Timeline(hsv_timeline);
            DS_NAM.setLy_ContainerHSV(lycontainer);
            DS_NAM.setTfApple(tf);
            DS_NAM.setTfIcon(tf2);
            DS_NAM.setTvAnimation(animationTextview);
            DS_NAM.setFristStartDefaultItemClick(true);
            DS_NAM.createTextView(getContext(), "YEARS", true);
            DS_NAM.setContext(getContext());
        }

        Ds_Fragment.getItemList(0).CallFrag(0, true);


        return  view;
    }
    // To animate view slide out from bottom to top
    public void slideToTop(View view){
        TranslateAnimation animate = new TranslateAnimation(0,0,0,-view.getHeight());
        animate.setDuration(500);
        animate.setFillAfter(true);
        view.startAnimation(animate);
        view.setVisibility(View.GONE);
    }
    // To animate view slide out from right to left
    public void slideToLeft(View view){
        TranslateAnimation animate = new TranslateAnimation(0,-view.getWidth(),0,0);
        animate.setDuration(500);
        animate.setFillAfter(true);
        view.startAnimation(animate);
        view.setVisibility(View.GONE);
    }
    public void slideToRight(View view){
        TranslateAnimation animate = new TranslateAnimation(0,view.getWidth(),0,0);
        animate.setDuration(500);
        animate.setFillAfter(true);
        view.startAnimation(animate);
        view.setVisibility(View.GONE);
    }






}
