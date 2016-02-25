package com.example.kevin.pvmushroom.Object;

import android.content.Context;

import com.example.kevin.pvmushroom.DanhSachStatic.DS_NAM;
import com.parse.ParseObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by kevin on 1/29/2016.
 */
public class Vu_Mua {

    public ArrayList<Thang> getDs_Thang() {
        return Ds_Thang;
    }

    public void setDs_Thang(ArrayList<Thang> ds_Thang) {
        Ds_Thang = ds_Thang;
    }



    public String getTenVu() {
        return TenVu;
    }

    public void setTenVu(String tenVu) {
        TenVu = tenVu;
    }

    String TenVu;

    public int getIDVuServer() {
        return IDVuServer;
    }

    int IDVuServer;
    ParseObject parseObject;
    Context context;
    ArrayList<Thang> Ds_Thang;

    public ParseObject getParseObject() {
        return parseObject;
    }
    public Vu_Mua(Context context, ArrayList<Thang> ds_Thang, ParseObject parseObject) {
        Ds_Thang = ds_Thang;
        this.context = context;
        this.parseObject = parseObject;
        this.TenVu = parseObject.getString("TEN_VU");
        this.IDVuServer = parseObject.getInt("ID");
    }
    public void KhoiTaoDSThangChoVuMua(List<ParseObject> lis)
    {
        Ds_Thang = new ArrayList<>();
        for(int i = 0; i < lis.size(); i++)
        {
            Ds_Thang.add(new Thang(lis.get(i).getInt("ID"),context,lis.get(i).getString("TEN_THANG"),null,lis.get(i).getInt("TONG_TIEN"),lis.get(i)));
        }
    }
}
