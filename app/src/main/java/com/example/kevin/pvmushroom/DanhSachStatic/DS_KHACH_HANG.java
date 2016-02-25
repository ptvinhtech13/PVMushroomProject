package com.example.kevin.pvmushroom.DanhSachStatic;

import com.example.kevin.pvmushroom.Object.Khach_Hang;
import com.parse.ParseObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kevin on 1/26/2016.
 */
public class DS_KHACH_HANG {
    public static ArrayList<Khach_Hang> getDs_KhachHang() {
        return Ds_KhachHang;
    }

    public static void setDs_KhachHang(ArrayList<Khach_Hang> ds_KhachHang) {
        Ds_KhachHang = ds_KhachHang;
    }

    static ArrayList<Khach_Hang> Ds_KhachHang;


    public static int KhoiTaoDanhSachKH(List<ParseObject> list)
    {
        Ds_KhachHang = new ArrayList<>();
        for(int i = 0; i < list.size(); i++)
        {
            Khach_Hang kh = new Khach_Hang(Integer.parseInt((list.get(i)).getString("ID")),(list.get(i)).getString("HoTen"));
            Ds_KhachHang.add(kh);
        }
        return Ds_KhachHang.size();
    }

}
