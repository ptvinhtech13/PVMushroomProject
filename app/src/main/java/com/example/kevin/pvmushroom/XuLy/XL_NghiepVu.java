package com.example.kevin.pvmushroom.XuLy;

import android.content.Context;

import com.example.kevin.pvmushroom.Object.Vu_Mua;

/**
 * Created by kevin on 1/26/2016.
 */
public class XL_NghiepVu {


    Context context;

    public XL_NghiepVu(Context context) {
        this.context = context;
    }

    public void KhoiDongDuLieu()
    {
        XL_LuuTru xl_luuTru = new XL_LuuTru(context);
        xl_luuTru.GetListCustomer();

    }
    public void KhoiDongDulieuDataYear()
    {
        XL_LuuTru xl_luuTru = new XL_LuuTru(context);
        xl_luuTru.GetDataYear();

    }
    public boolean AddCustomer(String NameCustomer)
    {
        return new XL_LuuTru(context).SaveCustomer(NameCustomer);
    }
    public boolean SaveHavrest(Vu_Mua vu_mua)
    {
        return new XL_LuuTru(context).SaveVuMua(vu_mua);
    }
}
