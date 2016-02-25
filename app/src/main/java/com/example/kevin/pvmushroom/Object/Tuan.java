package com.example.kevin.pvmushroom.Object;

import com.example.kevin.pvmushroom.DanhSachStatic.DS_NAM;
import com.example.kevin.pvmushroom.Fragment.frag_of_PhieuThu.Tree_Node;
import com.example.kevin.pvmushroom.Fragment.hover.IconTreeItemHolder;
import com.example.kevin.pvmushroom.Fragment.hover.SelectableHeaderHolder;
import com.example.kevin.pvmushroom.R;
import com.parse.ParseObject;
import java.util.Calendar;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by kevin on 2/5/2016.
 */
public class Tuan {
    int IDServer;

    public String getIDObjectOnServer() {
        return IDObjectOnServer;
    }

    public void setIDObjectOnServer(String IDObjectOnServer) {
        this.IDObjectOnServer = IDObjectOnServer;
    }

    String IDObjectOnServer = null;

    public int getIDinListThang() {
        return IDinListThang;
    }

    public void setIDinListThang(int IDinListThang) {
        this.IDinListThang = IDinListThang;
    }

    int IDinListThang;
    int TongTien;

    public String getTenTuan() {
        return TenTuan;
    }

    public void setTenTuan(String tenTuan) {
        TenTuan = tenTuan;
    }

    String TenTuan;

    public Tree_Node getNodeTuan() {
        return nodeTuan;
    }
    public void KhoiTaoDSNgayForTuan(List<ParseObject> list)
    {
        dsNgay = new ArrayList<>();
        for(int i = 0; i < list.size();i++)
        {
            String date = "";
            ParseObject pox = list.get(i);
            Ngay ngayx = new Ngay(pox.getInt("ID"),pox.getInt("TongTien"),pox.getDate("NGAY_BAT_DAU"),null);
            DS_NAM.setIDLastNgay(pox.getInt("ID"));
            ngayx.setIDinList_Tuan(i);
            ngayx.setIDTuanofDSThang(IDinListThang);
            ngayx.setPoxNgay(pox);
            ngayx.setIDObjectOnServer(pox.getObjectId());
            ngayx.setID_Code_Week_in_Server(IDServer);

            Calendar cal = Calendar.getInstance();
            cal.setTime(pox.getDate("NGAY_BAT_DAU"));
            cal.setTimeZone(TimeZone.getTimeZone("UTC"));
            cal.add(Calendar.HOUR_OF_DAY, 7);
            switch (cal.get(Calendar.DAY_OF_WEEK)) {
                case 1:
                    date += DS_NAM.getContext().getResources().getString(R.string.day_sun);
                    break;
                case 2:
                    date += DS_NAM.getContext().getResources().getString(R.string.day_mon);
                    break;
                case 3:
                    date += DS_NAM.getContext().getResources().getString(R.string.day_tu);
                    break;
                case 4:
                    date += DS_NAM.getContext().getResources().getString(R.string.day_wed);
                    break;
                case 5:
                    date += DS_NAM.getContext().getResources().getString(R.string.day_thur);
                    break;
                case 6:
                    date += DS_NAM.getContext().getResources().getString(R.string.day_fri);
                    break;
                case 7:
                    date += DS_NAM.getContext().getResources().getString(R.string.day_sat);
                    break;
            }
            date += " " + cal.get(Calendar.DAY_OF_MONTH) + "/"+ (cal.get(Calendar.MONTH) + 1)+ "/"  + cal.get(Calendar.YEAR);


            Tree_Node node = new Tree_Node(new IconTreeItemHolder.IconTreeItem(R.string.ic_keyboard_arrow_right,ngayx, date)).setViewHolder(new SelectableHeaderHolder(DS_NAM.getContext().getApplicationContext()));

            ngayx.setNodeNgay(node);

            nodeTuan.addChild(node);
            dsNgay.add(ngayx);

        }
    }

    public void setNodeTuan(Tree_Node nodeTuan) {
        this.nodeTuan = nodeTuan;
    }

    Tree_Node nodeTuan;
    public Tuan(int IDServer, int tongTien, ArrayList<Ngay> dsNgay, Date ngayBD, Date ngayKT) {
        this.IDServer = IDServer;
        TongTien = tongTien;
        this.dsNgay = dsNgay;
        this.ngayBD = ngayBD;
        this.ngayKT = ngayKT;
    }

    public ArrayList<Ngay> getDsNgay() {
        return dsNgay;
    }

    public void setDsNgay(ArrayList<Ngay> dsNgay) {
        this.dsNgay = dsNgay;
    }

    ArrayList<Ngay> dsNgay;

    public int getIDServer() {
        return IDServer;
    }

    public void setIDServer(int IDServer) {
        this.IDServer = IDServer;
    }

    public int getTongTien() {
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

    public Date getNgayKT() {
        return ngayKT;
    }

    public void setNgayKT(Date ngayKT) {
        this.ngayKT = ngayKT;
    }

    Date ngayBD;
    Date ngayKT;
}
