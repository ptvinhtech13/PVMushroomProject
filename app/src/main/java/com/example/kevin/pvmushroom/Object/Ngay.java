package com.example.kevin.pvmushroom.Object;

import android.widget.Toast;

import com.example.kevin.pvmushroom.DanhSachStatic.DS_NAM;
import com.example.kevin.pvmushroom.Fragment.frag_of_PhieuThu.Tree_Node;
import com.example.kevin.pvmushroom.Fragment.hover.SelectableItemHolder;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by kevin on 2/5/2016.
 */
public class Ngay {


    int IDServer; // ID ngay tren server
    String IDObjectOnServer = null;
    double TongTien;
    Date ngayBD;
    Tree_Node nodeNgay;
    ArrayList<Phieu_Thu> dsPhieuThu;
    int IDinList_Tuan;
    int IDinList_Thang;
    ParseObject poxNgay;

    int ID_Code_Week_in_Server;


    public int getID_Code_Week_in_Server() {
        return ID_Code_Week_in_Server;
    }

    public void setID_Code_Week_in_Server(int ID_Code_Week_in_Server) {
        this.ID_Code_Week_in_Server = ID_Code_Week_in_Server;
    }



    public String getIDObjectOnServer() {
        return IDObjectOnServer;
    }

    public void setIDObjectOnServer(String IDObjectOnServer) {
        this.IDObjectOnServer = IDObjectOnServer;
    }

    public ParseObject getPoxNgay() {
        return poxNgay;
    }

    public void setPoxNgay(ParseObject poxNgay) {
        this.poxNgay = poxNgay;
    }
    public boolean CapNhatLenServer()
    {
        boolean isthanhCong = false;
        if(IDObjectOnServer != null)
        {
            poxNgay.deleteInBackground();
        }
        else
        {
            //  Date date = new Date();
            ParseObject pox = new ParseObject("NGAY");
            pox.put("ID",IDServer);
            pox.put("ID_TUAN",ID_Code_Week_in_Server);
            pox.put("TongTien", TongTien);
            pox.put("NGAY_BAT_DAU",ngayBD);

            pox.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    if(e != null)
                        Toast.makeText(DS_NAM.getContext(),"Error: "+ e.toString() ,Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(DS_NAM.getContext(),"DONE" ,Toast.LENGTH_SHORT).show();
                }
            });

            Toast.makeText(DS_NAM.getContext(),"Cap Nhap server Tuan",Toast.LENGTH_SHORT).show();

        }

        return isthanhCong;
    }




    public int getIDinList_Tuan() {
        return IDinList_Tuan;
    }

    public void setIDinList_Tuan(int IDinList_Tuan) {
        this.IDinList_Tuan = IDinList_Tuan;
    }



    public int getIDinList_Thang() {
        return IDinList_Thang;
    }

    public void setIDTuanofDSThang(int IDTuanofThang) {
        this.IDinList_Thang = IDTuanofThang;
    }


    public Ngay(int IDServer, double tongTien, Date ngayBD, Tree_Node nodeNgay, ArrayList<Phieu_Thu> dsPhieuThu) {
        this.IDServer = IDServer;
        TongTien = tongTien;
        this.ngayBD = ngayBD;
        this.nodeNgay = nodeNgay;
        this.dsPhieuThu = dsPhieuThu;
    }
    public void KhoiTaoDSPhieuThu(List<ParseObject> list)
    {

        TongTien = 0;
        dsPhieuThu = new ArrayList<>();
        for(int i = 0; i < list.size(); i++)
        {
            ParseObject pox = list.get(i);
            Phieu_Thu ptx = new Phieu_Thu(Integer.parseInt(pox.getString("ID_KH")), Integer.parseInt(pox.getString("ID")),
                    pox.getInt("DON_GIA"), pox.getDouble("SO_LUONG"), pox.getInt("DON_GIA") * pox.getDouble("SO_LUONG"));
            ptx.setPox(pox);

            ptx.setIDNgayCuaServer(getIDServer());
            ptx.setIDCodeServer(pox.getObjectId());
            ptx.setTinhTrang(pox.getBoolean("TinhTrang"));
            ptx.setPositioninListPTinDay(i);
            ptx.setIDNgayListNgayofTuan(this.IDinList_Tuan);
            ptx.setIDTuanofListThang(this.IDinList_Thang);

            if(DS_NAM.getIDLastPhieuThu() < ptx.IDPhieuThu)
            {
                DS_NAM.setIDLastPhieuThu(ptx.IDPhieuThu);
            }

            TongTien+=ptx.getThanhTien();

            Tree_Node node = new Tree_Node(ptx).setViewHolder(new SelectableItemHolder(DS_NAM.getContext().getApplicationContext()));
            ptx.setNode(node);


            this.dsPhieuThu.add(ptx);
            this.nodeNgay.addChild(node);

        }
    }



    public void CongThemTien(double money)
    {
        TongTien+=money;
    }
    public void TruTien(double money)
    {
        TongTien-=money;
    }

    public Ngay(int IDServer, double tongTien, Date ngayBD, ArrayList<Phieu_Thu> dsPhieuThu) {
        this.IDServer = IDServer;
        TongTien = tongTien;
        this.ngayBD = ngayBD;
        this.dsPhieuThu = dsPhieuThu;
    }



    public Tree_Node getNodeNgay() {
        return nodeNgay;
    }

    public void setNodeNgay(Tree_Node nodeNgay) {
        this.nodeNgay = nodeNgay;
    }



    public ArrayList<Phieu_Thu> getDsPhieuThu() {
        return dsPhieuThu;
    }

    public void setDsPhieuThu(ArrayList<Phieu_Thu> dsPhieuThu) {
        this.dsPhieuThu = dsPhieuThu;
    }

    public int getIDServer() {
        return IDServer;
    }

    public void setIDServer(int IDServer) {
        this.IDServer = IDServer;
    }

    public double getTongTien() {
        return TongTien;
    }

    public void setTongTien(int tongTien) {
        TongTien = tongTien;
    }

    public Date getNgayBD() {
        return ngayBD;
    }

    public void setNgayBD(Date ngayBD) {
        this.ngayBD = ngayBD;
    }



}
