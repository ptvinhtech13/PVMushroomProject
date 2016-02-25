package com.example.kevin.pvmushroom.DanhSachStatic;

import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kevin.pvmushroom.Adapters.AdapterLV_DS_Year;
import com.example.kevin.pvmushroom.Fragment.frag_of_PhieuThu.Android_Tree_View;
import com.example.kevin.pvmushroom.Fragment.frag_of_PhieuThu.Tree_Node;
import com.example.kevin.pvmushroom.Object.Khach_Hang;
import com.example.kevin.pvmushroom.Object.Nam;
import com.example.kevin.pvmushroom.Object.Thang;
import com.example.kevin.pvmushroom.Object.Vu_Mua;
import com.example.kevin.pvmushroom.R;
import com.parse.ParseObject;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import java.util.Date;

/**
 * Created by kevin on 1/29/2016.
 */
public class DS_NAM {

    static ArrayList<Nam> Ds_Nam;
    static boolean  fristStartDefaultItemClick;
    static Typeface tfApple, tfIcon;
    static HorizontalScrollView hsv_Timeline;
    static Animation tvAnimation;
    static LinearLayout ly_ContainerHSV;
    static Tree_Node NODEROOT;



    static int IDLastPhieuThu = -1;
    static int IDLastNgay = -1;

    public static int getIDLastNgay() {
        return IDLastNgay;
    }

    public static int generateIDLastNgay() {
        return ++IDLastNgay;
    }

    public static void setIDLastNgay(int IDLastNgay) {
        DS_NAM.IDLastNgay = IDLastNgay;
    }


    public static int getIDLastPhieuThu() {
        return IDLastPhieuThu;
    }

    public static int generateIDLastPhieuThu() {
        return ++IDLastPhieuThu;
    }

    public static void setIDLastPhieuThu(int IDLastPhieuThu) {
        DS_NAM.IDLastPhieuThu = IDLastPhieuThu;
    }


    public static Android_Tree_View gettView() {
        return tView;
    }

    public static void settView(Android_Tree_View tView) {
        DS_NAM.tView = tView;
    }

    static Android_Tree_View tView;

    public static Thang getThangDcChon() {
        return thangDcChon;
    }

    public static void setThangDcChon(Thang thangDcChon) {
        DS_NAM.thangDcChon = thangDcChon;
    }

    static Thang thangDcChon;

    public static Tree_Node getNODEROOT() {
        return NODEROOT;
    }

    public static void setNODEROOT(Tree_Node NODEROOT) {
        DS_NAM.NODEROOT = NODEROOT;
    }



    public static FragmentManager getFm() {
        return fm;
    }

    public static void setFm(FragmentManager fm) {
        DS_NAM.fm = fm;
    }

    static FragmentManager fm;
    public static Context getContext() {
        return context;
    }

    public static void setContext(Context context) {
        DS_NAM.context = context;
    }

    static Context context;

    public static AdapterLV_DS_Year getListAdapter_PT() {
        return listAdapter_PT;
    }

    public static void setListAdapter_PT(AdapterLV_DS_Year listAdapter_PTz) {
        listAdapter_PT = listAdapter_PTz;
    }

    static AdapterLV_DS_Year listAdapter_PT;

    public static boolean isFristStartDefaultItemClick() {
        return fristStartDefaultItemClick;
    }

    public static void setFristStartDefaultItemClick(boolean fristStartDefaultItemClick) {
        DS_NAM.fristStartDefaultItemClick = fristStartDefaultItemClick;
    }

    public static HorizontalScrollView getHsv_Timeline() {
        return hsv_Timeline;
    }

    public static void setHsv_Timeline(HorizontalScrollView hsv_Timelinez) {
        hsv_Timeline = hsv_Timelinez;
    }

    public static Typeface getTfApple() {
        return tfApple;
    }

    public static void setTfApple(Typeface tfApple) {
        DS_NAM.tfApple = tfApple;
    }

    public static Typeface getTfIcon() {
        return tfIcon;
    }

    public static void setTfIcon(Typeface tfIcon) {
        DS_NAM.tfIcon = tfIcon;
    }

    public static Animation getTvAnimation() {
        return tvAnimation;
    }

    public static void setTvAnimation(Animation tvAnimation) {
        DS_NAM.tvAnimation = tvAnimation;
    }
    public static  LinearLayout getLy_ContainerHSV() {
        return ly_ContainerHSV;
    }
    public static  View getItemViewInLinearLayout(int position) {
        return ly_ContainerHSV.getChildAt(position);
    }

    public static void setLy_ContainerHSV(LinearLayout ly_ContainerHSVz) {
        ly_ContainerHSV = ly_ContainerHSVz;
    }



    public static void AddViewToHSVTimeline(View v)
    {
        ly_ContainerHSV.addView(v);
        hsv_Timeline.removeAllViews();
        hsv_Timeline.addView(ly_ContainerHSV);
    }
    public static void RemoveViewToHSVTimeline()
    {
        ly_ContainerHSV.removeViewAt(ly_ContainerHSV.getChildCount() - 1);
        hsv_Timeline.removeAllViews();
        hsv_Timeline.addView(ly_ContainerHSV);
    }
    public static void createTextView(Context context, String text, boolean String_or_Icon)
    {
        TextView v = new TextView(context);
        if(String_or_Icon)
        {
            v.setText(text);
            v.setTypeface(tfApple);
            v.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
            v.setTextColor(context.getResources().getColor(R.color.colorAccent));
            AddViewToHSVTimeline(v);
            v.startAnimation(tvAnimation);
            return;
        }else{


            v.setText(context.getResources().getString(R.string.ic_keyboard_arrow_right));
            v.setTypeface(tfIcon);
            v.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
            AddViewToHSVTimeline(v);

            LinearLayout.LayoutParams lllp=(LinearLayout.LayoutParams)v.getLayoutParams();
            lllp.gravity= Gravity.CENTER_VERTICAL;
            v.setLayoutParams(lllp);
        }


    }


    public static ArrayList<Nam> getDs_Nam() {
        return Ds_Nam;
    }

    public static void setDs_Nam(ArrayList<Nam> ds_Nam) {
        Ds_Nam = ds_Nam;
    }

    private static boolean checkList(int year)
    {
        boolean kq = true;
        for(int i = 0; i < Ds_Nam.size();i++)
        {
            if((Ds_Nam.get(i)).getIDNam() == year)
            {
                return false;
            }
        }
        return true;
    }
    private static void FindYearLike(int year, List<ParseObject> list, ArrayList<Vu_Mua> dsVuMua)
    {
        boolean kq = true;
        for(int i = 0; i < list.size();i++)
        {
            if(list.get(i).getInt("NAM_BD") == year)
            {
                dsVuMua.add(new Vu_Mua(context,null,list.get(i)));
            }
        }

    }
    public static int KhoiTaoDanhSachYEAR(List<ParseObject> list)
    {
        Ds_Nam = new ArrayList<>();
        for(int i = 0; i < list.size(); i++)
        {
            int Year = list.get(i).getInt("NAM_BD");
            if(checkList(Year))
            {
                Nam year = new Nam(Year,null);
                ArrayList<Vu_Mua> dsVuMua = new ArrayList<>();
                FindYearLike(Year,list,dsVuMua);
                year.setDs_vumua(dsVuMua);
                Ds_Nam.add(year);
            }
        }
        return Ds_Nam.size();
    }
}
