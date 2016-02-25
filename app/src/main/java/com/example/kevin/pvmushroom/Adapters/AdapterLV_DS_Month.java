package com.example.kevin.pvmushroom.Adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.kevin.pvmushroom.Object.Thang;
import com.example.kevin.pvmushroom.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

/**
 * Created by kevin on 2/5/2016.
 */
public class AdapterLV_DS_Month extends BaseAdapter {

    ArrayList<Thang> ds_vumua;
    Context context;
    Typeface tf;

    public AdapterLV_DS_Month(ArrayList<Thang> ds_vumua, Context context, Typeface tf) {
        if(ds_vumua == null)
            this.ds_vumua = new ArrayList<>();
        this.ds_vumua = ds_vumua;
        this.context = context;
        this.tf = tf;
    }

    @Override
    public int getCount() {
        return ds_vumua.size();
    }

    @Override
    public Thang getItem(int position) {
        return ds_vumua.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.frag_item_thang_ds_vu_mua,parent,false);

            Thang thang = ds_vumua.get(position);
            if(thang!=null) {
                TextView tv_Month = (TextView)convertView.findViewById(R.id.tv_Month);
                tv_Month.setText(thang.getTenThang());
                TextView tv_Tien = (TextView)convertView.findViewById(R.id.tv_Money);
                tv_Tien.setText("" + thang.getTongTien());

                Calendar cal = Calendar.getInstance();
                cal.setTime(thang.getNgayBD());
                cal.setTimeZone(TimeZone.getTimeZone("UTC"));
                cal.add(Calendar.HOUR_OF_DAY, 7);
                TextView tv_day_start = (TextView)convertView.findViewById(R.id.tv_day_Started);
                tv_day_start.setText("" + thang.getTongTien());
                tv_day_start.setText("Start: " + cal.get(Calendar.DAY_OF_MONTH) + "/" + (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.YEAR));


                cal.add(Calendar.MONTH, 1);
                cal.add(Calendar.DAY_OF_MONTH,-cal.get(Calendar.DAY_OF_MONTH));
                TextView tv_day_end = (TextView)convertView.findViewById(R.id.tv_day_End);
                tv_day_end.setText(""+ thang.getTongTien());
                tv_day_end.setText("End: " + cal.get(Calendar.DAY_OF_MONTH) + "/"+ (cal.get(Calendar.MONTH) + 1)+ "/"  + cal.get(Calendar.YEAR));



                tv_Month.setTypeface(tf);
                tv_Tien.setTypeface(tf);
                tv_day_start.setTypeface(tf);
                tv_day_end.setTypeface(tf);
            }
        }



        return convertView;
    }

}
