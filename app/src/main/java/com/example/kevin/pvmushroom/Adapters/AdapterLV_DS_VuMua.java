package com.example.kevin.pvmushroom.Adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.kevin.pvmushroom.Object.Vu_Mua;
import com.example.kevin.pvmushroom.R;

import java.util.Calendar;
import java.util.Date;
import java.util.ArrayList;
import java.util.TimeZone;

/**
 * Created by kevin on 1/31/2016.
 */
public class AdapterLV_DS_VuMua extends BaseAdapter {
    ArrayList<Vu_Mua> ds_vumua;
    Context context;
    Typeface tf;

    public AdapterLV_DS_VuMua(ArrayList<Vu_Mua> ds_vumua, Context context, Typeface tf) {
        this.ds_vumua = ds_vumua;
        this.context = context;
        this.tf = tf;
    }

    @Override
    public int getCount() {
        return ds_vumua.size();
    }

    @Override
    public Vu_Mua getItem(int position) {
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
            convertView = inflater.inflate(R.layout.item_harvest_in_dsvumua,parent,false);

            Vu_Mua vu_mua = (Vu_Mua)ds_vumua.get(position);
            if(vu_mua!=null)
            {
                TextView tvVuMua = (TextView)convertView.findViewById(R.id.tv_vumua);
                tvVuMua.setText(vu_mua.getParseObject().getString("TEN_VU"));
                tvVuMua.setTypeface(tf);

               /* Calendar c = shiftTimeZone(vu_mua.getParseObject().getDate("NGAY_BAT_DAU"), TimeZone.getTimeZone("EST"), TimeZone.getTimeZone("UTC"));
*/
                Calendar cal = Calendar.getInstance();
                cal.setTime(vu_mua.getParseObject().getDate("NGAY_BAT_DAU"));
                cal.setTimeZone(TimeZone.getTimeZone("UTC"));
                cal.add(Calendar.HOUR_OF_DAY, 7);

                String AMorPM = "";
                if(cal.get(cal.AM_PM) == 0)
                {
                    AMorPM = " SÁNG";
                }else
                {
                    AMorPM = " CHIỀU";
                }
                TextView tvStart = (TextView)convertView.findViewById(R.id.tv_vumua_time_bat_dau);
                tvStart.setText(cal.get(Calendar.DAY_OF_MONTH) + "/"+ (cal.get(Calendar.MONTH) + 1)+ "/"  + cal.get(Calendar.YEAR));
               // tvStart.setText(cal.getTime().toString() );
                tvStart.setTypeface(tf);

                /*cal = shiftTimeZone(vu_mua.getParseObject().getDate("NGAY_KET_THUC"),TimeZone.getTimeZone("EST"),TimeZone.getTimeZone("UTC"));
*/
                cal.setTime(vu_mua.getParseObject().getDate("NGAY_KET_THUC"));
                cal.setTimeZone(TimeZone.getTimeZone("UTC"));
                cal.add(Calendar.HOUR_OF_DAY, 7);

                AMorPM = "";
                if(cal.get(cal.AM_PM) == 0)
                {
                    AMorPM = " SÁNG";
                }else
                {
                    AMorPM = " CHIỀU";
                }
                TextView tvEnd = (TextView)convertView.findViewById(R.id.tv_vumua_time_ket_thuc);
                tvEnd.setText(cal.get(Calendar.DAY_OF_MONTH) + "/"+ (cal.get(Calendar.MONTH) + 1)+ "/"  + cal.get(Calendar.YEAR)+ "---- "+ cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE) + AMorPM);

                tvEnd.setTypeface(tf);

                TextView stage = (TextView)convertView.findViewById(R.id.tv_vumua_time_stage);
                if(vu_mua.getParseObject().getBoolean("TINH_TRANG"))
                {
                    stage.setText(context.getResources().getString(R.string.tinh_trang_dang_dien_ra));
                }
                else
                {
                    stage.setText(context.getResources().getString(R.string.tinh_trang_ngung_dien_ra));
                    stage.setTextColor(context.getResources().getColor(R.color.navigation1));
                }
                stage.setTypeface(tf);
            }
        }



        return convertView;
    }
    private Calendar shiftTimeZone(Date date, TimeZone sourceTimeZone, TimeZone targetTimeZone) {
        Calendar sourceCalendar = Calendar.getInstance();
        sourceCalendar.setTime(date);
        sourceCalendar.setTimeZone(sourceTimeZone);

        Calendar targetCalendar = Calendar.getInstance();
        for (int field : new int[]
                {Calendar.YEAR, Calendar.MONTH, Calendar.DATE, Calendar.HOUR, Calendar.MINUTE, Calendar.SECOND, Calendar.MILLISECOND})
        {
            targetCalendar.set(field, sourceCalendar.get(field));
        }
        targetCalendar.setTimeZone(targetTimeZone);

        return targetCalendar;
    }
}
