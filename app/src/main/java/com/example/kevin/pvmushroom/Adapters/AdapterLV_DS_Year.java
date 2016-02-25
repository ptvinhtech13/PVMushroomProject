package com.example.kevin.pvmushroom.Adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kevin.pvmushroom.Object.Khach_Hang;
import com.example.kevin.pvmushroom.Object.Nam;
import com.example.kevin.pvmushroom.R;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by kevin on 1/30/2016.
 */
public class AdapterLV_DS_Year extends BaseAdapter {

    public ArrayList<Nam> Ds_Nam;
    Context Context;
    int idItem;
    Typeface tf;

    public AdapterLV_DS_Year(Context context, ArrayList<Nam> ds_Nam, int idItem, Typeface tf) {

        this.Ds_Nam = ds_Nam;
        this.Context = context;
        this.idItem = idItem;
        this.tf = tf;
    }

    @Override
    public int getCount() {
        if(Ds_Nam == null)
            return 0;
        return Ds_Nam.size();
    }

    @Override
    public Object getItem(int position) {
        return Ds_Nam.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater)this.Context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_year_in_dsnam,null);

            Nam nam = Ds_Nam.get(position);

            if(nam != null)
            {
                TextView txHoTen = (TextView) convertView.findViewById(R.id.tv_year);
                txHoTen.setTypeface(tf);
                txHoTen.setText(nam.getIDNam() + "");

            }
        }



        return convertView;
    }

}
