package com.example.kevin.pvmushroom.Adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.kevin.pvmushroom.Object.Khach_Hang;
import com.example.kevin.pvmushroom.R;

import java.util.ArrayList;

/**
 * Created by kevin on 1/27/2016.
 */
public class AdapterLV_DS_KH extends ArrayAdapter<Khach_Hang> {

    Context context;
    ArrayList dsKhachHang;
    Typeface tf;
    int resource; // id của item_xml
    public AdapterLV_DS_KH(Context context, int resource, ArrayList<Khach_Hang> objects, Typeface tf) {
        super(context, resource, objects);
        this.context = context;
        this.dsKhachHang = objects;
        this.resource = resource;
        this.tf = tf;
    }
    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        // TODO Auto-generated method stub
        return getView(position, convertView, parent);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(resource,null);
        }

        Khach_Hang kh = (Khach_Hang)dsKhachHang.get(position);
        if(kh != null)
        {
            TextView txHoTen = (TextView) convertView.findViewById(R.id.tv_hoten_kh);
            txHoTen.setTypeface(tf);
            txHoTen.setText(kh.getHoTen());
        }

        return convertView;

    }
}
