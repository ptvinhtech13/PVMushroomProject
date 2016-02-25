package com.example.kevin.pvmushroom.Object;

import com.example.kevin.pvmushroom.DanhSachStatic.DS_NAM;
import com.example.kevin.pvmushroom.Fragment.frag_of_PhieuThu.Tree_Node;
import com.example.kevin.pvmushroom.XuLy.XL_LuuTru;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by kevin on 2/6/2016.
 */
public class Phieu_Thu implements Serializable {

    public String getIDCodeServer() {
        return IDCodeServer;
    }

    public void setIDCodeServer(String IDCodeServer) {
        this.IDCodeServer = IDCodeServer;
    }


    int IDKH;
    String IDCodeServer = null;
    int IDNgayCuaServer; // ID ngay tren server cua phieu thu
    double SoLuong;
    double ThanhTien;
    Tree_Node node;
    int IDTuanofListThang;
    int IDNgayListNgayofTuan;
    int positioninListPTinDay;
    int IDPhieuThu;
    int DonGia;
    boolean TinhTrang;
    ParseObject pox;

    public int getIDNgayCuaServer() {
        return IDNgayCuaServer;
    }

    public void setIDNgayCuaServer(int IDNgayCuaServer) {
        this.IDNgayCuaServer = IDNgayCuaServer;
    }

    public ParseObject getPox() {
        return pox;
    }

    public void setPox(ParseObject pox) {
        this.pox = pox;
    }


    public boolean isTinhTrang() {
        return TinhTrang;
    }

    public boolean CapNhatLenServer()
    {
        boolean isthanhCong = false;
        if(IDCodeServer != null)
        {
            ParseQuery<ParseObject> query = ParseQuery.getQuery("PHIEU_THU");
            query.getInBackground(IDCodeServer, new GetCallback<ParseObject>() {
                public void done(ParseObject pox, ParseException e) {
                    if (e == null) {
                        // Now let's update it with some new data. In this case, only cheatMode and score
                        // will get sent to the Parse Cloud. playerName hasn't changed.
                        pox.put("SO_LUONG", SoLuong);
                        pox.put("DON_GIA", DonGia);
                        pox.put("TinhTrang", TinhTrang);
                        pox.saveInBackground();
                    }
                }
            });
        }
        else
        {
          //  Date date = new Date();
            XL_LuuTru xl_luuTru = new XL_LuuTru(DS_NAM.getContext());

            xl_luuTru.SavePhieuThu(this);
            /*ParseQuery<ParseObject> query = ParseQuery.getQuery("PHIEU_THU");
            query.whereEqualTo("ID", IDPhieuThu);
            query.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> list, ParseException e) {
                    IDCodeServer = list.get(0).getObjectId();
                }
            });*/

        }

        return isthanhCong;
    }

    public void setTinhTrang(boolean tinhTrang) {
        TinhTrang = tinhTrang;
    }



    public Tree_Node getNode() {
        return node;
    }

    public void setNode(Tree_Node node) {
        this.node = node;
    }

    public int getIDTuanofListThang() {
        return IDTuanofListThang;
    }

    public void setIDTuanofListThang(int IDTuanofListThang) {
        this.IDTuanofListThang = IDTuanofListThang;
    }

    public int getIDNgayListNgayofTuan() {
        return IDNgayListNgayofTuan;
    }

    public void setIDNgayListNgayofTuan(int IDNgayListNgayofTuan) {
        this.IDNgayListNgayofTuan = IDNgayListNgayofTuan;
    }

    public int getPositioninListPTinDay() {
        return positioninListPTinDay;
    }

    public void setPositioninListPTinDay(int positioninListPTinDay) {
        this.positioninListPTinDay = positioninListPTinDay;
    }

    public Phieu_Thu(int IDKH, int IDPhieuThu, int donGia, double soLuong, double thanhTien) {
        this.IDKH = IDKH;
        this.IDPhieuThu = IDPhieuThu;
        DonGia = donGia;
        SoLuong = soLuong;
        ThanhTien = thanhTien;
    }

    public int getIDKH() {
        return IDKH;
    }

    public void setIDKH(int IDKH) {
        this.IDKH = IDKH;
    }

    public int getIDPhieuThu() {
        return IDPhieuThu;
    }

    public void setIDPhieuThu(int IDPhieuThu) {
        this.IDPhieuThu = IDPhieuThu;
    }

    public int getDonGia() {
        return DonGia;
    }

    public void setDonGia(int donGia) {
        DonGia = donGia;
    }

    public double getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(double soLuong) {
        SoLuong = soLuong;
    }

    public double getThanhTien() {
        return ThanhTien;
    }

    public void setThanhTien(double thanhTien) {
        ThanhTien = thanhTien;
    }
}
