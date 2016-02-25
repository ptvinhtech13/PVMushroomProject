package com.example.kevin.pvmushroom.Object;

import android.content.Context;

import com.example.kevin.pvmushroom.Fragment.frag_of_PhieuThu.Tree_Node;
import com.example.kevin.pvmushroom.Fragment.hover.IconTreeItemHolder;
import com.example.kevin.pvmushroom.Fragment.hover.ProfileHolder;
import com.example.kevin.pvmushroom.R;
import com.parse.ParseObject;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kevin on 1/29/2016.
 */
public class Thang {
    int IDThangServer;

    public Thang(int IDThangServer, Context context, String tenThang, ArrayList<Tuan> dsTuan, int tongTien, ParseObject data) {
        this.IDThangServer = IDThangServer;
        this.context = context;
        TenThang = tenThang;
        this.dsTuan = dsTuan;
        TongTien = tongTien;
        if(data == null)
        {
            ngayBD = new Date();
        }
        else
            ngayBD = data.getDate("NGAY_BAT_DAU");
        this.data = data;
    }

    Context context;
    String TenThang;
    ArrayList<Tuan> dsTuan;
    int TongTien;
    Date ngayBD;


    public Date getNgayBD() {
        return ngayBD;
    }

    public void setNgayBD(Date ngayBD) {
        this.ngayBD = ngayBD;
    }


    public ParseObject getData() {
        return data;
    }

    public void setData(ParseObject data) {
        this.data = data;
    }

    ParseObject data;



    public String getTenThang() {
        return TenThang;
    }

    public void setTenThang(String tenThang) {
        TenThang = tenThang;
    }



    public ArrayList<Tuan> getDsTuan() {
        return dsTuan;
    }

    public void setDsTuan(ArrayList<Tuan> dsTuan) {
        this.dsTuan = dsTuan;
    }




    public void AddMoney(int Tien)
    {
        TongTien+=Tien;
    }

    public int getTongTien() {
        return TongTien;
    }

    public void setTongTien(int tongTien) {
        TongTien = tongTien;
    }

    public int getIDThangServer() {
        return IDThangServer;
    }

    public void setIDThangServer(int IDThangServer) {
        this.IDThangServer = IDThangServer;
    }

    public void KhoiTaoDSTuanCuaThang(List<ParseObject> list, Tree_Node root)
    {
        dsTuan = new ArrayList<>();
        for(int i = 0; i < list.size(); i++)
        {
            ParseObject pox = list.get(i);
            Tuan tuan = new Tuan(pox.getInt("ID"),pox.getInt("TONG_TIEN"),null,pox.getDate("NGAY_BAT_DAU"),pox.getDate("NGAY_KET_THUC"));
            tuan.setIDinListThang(i);
            tuan.setTenTuan(pox.getString("TEN_TUAN"));
            Tree_Node tuanx = new Tree_Node(new IconTreeItemHolder.IconTreeItem(R.string.ic_sd_storage, pox.getString("TEN_TUAN"), pox.getDate("NGAY_BAT_DAU"), pox.getDate("NGAY_KET_THUC"),tuan)).setViewHolder(new ProfileHolder(context.getApplicationContext()));
            tuan.setNodeTuan(tuanx);
            root.addChild(tuanx);
            dsTuan.add(tuan);
        }
    }


}
