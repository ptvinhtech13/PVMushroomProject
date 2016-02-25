package com.example.kevin.pvmushroom.XuLy;

import android.content.Context;
import android.widget.Toast;

import com.example.kevin.pvmushroom.DanhSachStatic.DS_KHACH_HANG;
import com.example.kevin.pvmushroom.DanhSachStatic.DS_NAM;
import com.example.kevin.pvmushroom.Object.Phieu_Thu;
import com.example.kevin.pvmushroom.Object.Vu_Mua;
import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.util.Date;
import java.util.List;

/**
 * Created by kevin on 1/26/2016.
 */
public class XL_LuuTru {

    Context context;

    public XL_LuuTru(Context context) {
        this.context = context;
    }

    public boolean SaveCustomer(String NameCustomer)
    {
        return true;
    }
    public boolean SaveVuMua(Vu_Mua vu_mua)
    {
        return true;
    }
    public boolean SavePhieuThu(Phieu_Thu ptx)
    {
        ParseObject pox = new ParseObject("PHIEU_THU");
        pox.put("NGAY_MUA", new Date());
        pox.put("ID", String.valueOf(ptx.getIDPhieuThu()));
        pox.put("ID_KH", String.valueOf(ptx.getIDKH()));
        pox.put("ID_NGAY_MUA", ptx.getIDNgayCuaServer());
        pox.put("DON_GIA", ptx.getDonGia());
        pox.put("SO_LUONG", ptx.getSoLuong());
        pox.put("TinhTrang", ptx.isTinhTrang());
        pox.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if(e == null)
                    Toast.makeText(context,"Saving Object Done",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(context,"Error Saving Object",Toast.LENGTH_SHORT).show();
            }
        });
        return true;

    }
    public void GetListCustomer() {
        Parse.enableLocalDatastore(context);
        Parse.initialize(context);



        ParseQuery<ParseObject> query = ParseQuery.getQuery("KHACH_HANG");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, com.parse.ParseException e) {
                if (e == null) {
                    //Toast.makeText(context, list.size() + " Customer", Toast.LENGTH_SHORT).show();
                    DS_KHACH_HANG.KhoiTaoDanhSachKH(list);

                } else
                    Toast.makeText(context, "Failed Customer", Toast.LENGTH_SHORT).show();
            }
        });

    }
    public void GetDataYear()
    {


        ParseQuery<ParseObject> query2 = ParseQuery.getQuery("VU_MUA");
        query2.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, com.parse.ParseException e) {
                if (e == null) {
                    DS_NAM.KhoiTaoDanhSachYEAR(list);
                    Toast.makeText(context, DS_NAM.getDs_Nam().size() + " VU_MUA", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(context, "Failed Customer", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
