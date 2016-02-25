package com.example.kevin.pvmushroom.Fragment.frag_of_PhieuThu;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.kevin.pvmushroom.DanhSachStatic.DS_NAM;
import com.example.kevin.pvmushroom.Fragment.hover.IconTreeItemHolder;
import com.example.kevin.pvmushroom.Fragment.hover.SelectableHeaderHolder;
import com.example.kevin.pvmushroom.Fragment.hover.SelectableItemHolder;
import com.example.kevin.pvmushroom.Object.Ngay;
import com.example.kevin.pvmushroom.Object.Phieu_Thu;
import com.example.kevin.pvmushroom.Object.Thang;
import com.example.kevin.pvmushroom.Object.Tuan;
import com.example.kevin.pvmushroom.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.unnamed.b.atv.view.AndroidTreeView;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;


/**
 * Created by kevin on 2/5/2016.
 */
public class Frag_Infor_Month extends Fragment {

    private Android_Tree_View tView;
    private boolean selectionModeEnabled = false;
    Tree_Node root;
    int VuDuocChon,Year,Month;
    Thang thangx;
    ProgressDialog progressDialog;
    ViewGroup containerView;
    Bundle savedInstanceState;
    boolean fristWeek = false;
    Handler handler;
    Handler.Callback callback = new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            Bundle bundle = msg.getData();
            int idNode = bundle.getInt("IDNode");
            int idNodeParent = bundle.getInt("IDNodeParent");
            int sDongia = bundle.getInt("DG");
            int PositioninList_PT_inDay = bundle.getInt("PosPT");
            int PositionDayinList_Tuan = bundle.getInt("PosDay");
            int PositionWeekinList_Thang= bundle.getInt("PosWeek");
            double sSoluong = bundle.getDouble("SL");
            double sThanhtien = bundle.getDouble("TT");
            boolean stinhtrang = bundle.getBoolean("TTR");

            Ngay ngayx = thangx.getDsTuan().get(PositionWeekinList_Thang).getDsNgay().get(PositionDayinList_Tuan);
            Phieu_Thu ptx = ngayx.getDsPhieuThu().get(PositioninList_PT_inDay);

            ngayx.TruTien(ptx.getThanhTien());

            ptx.setDonGia(sDongia);
            ptx.setTinhTrang(stinhtrang);
            ptx.setThanhTien(sThanhtien);
            ptx.setSoLuong(sSoluong);

            ngayx.CongThemTien(ptx.getThanhTien());

            String date ="";
            Calendar cal = Calendar.getInstance();
            cal.setTime(ngayx.getNgayBD());
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




            Tree_Node nodex = ptx.getNode();
            nodex.setmValue(ptx);
            nodex.setViewHolder(new SelectableItemHolder(DS_NAM.getContext().getApplicationContext()));

            ngayx = thangx.getDsTuan().get(PositionWeekinList_Thang).getDsNgay().get(PositionDayinList_Tuan);

          /*  Tree_Node nodeNgay = ngayx.getNodeNgay();
            List<Tree_Node> listSubNode = nodeNgay.getChildren();
*/
           /* nodeNgay.deleteAllChild();

            ngayx.setTongTien(-1111);
            nodeNgay.setmValue(new IconTreeItemHolder.IconTreeItem(R.string.ic_keyboard_arrow_right, ngayx, date));
            nodeNgay.setViewHolder(new SelectableHeaderHolder(DS_NAM.getContext().getApplicationContext()));*/

          //  tView.collapseNode(nodeNgay);
            tView.expandNode(nodex.getParent().getParent());

            //nodeNgay.addChild(listSubNode.get(0));
           // nodeNgay.addChild(listSubNode.get(1));








         //   tView.expandNode(nodex.getParent());


            ptx.CapNhatLenServer();
          //  Toast.makeText(getContext(),"Update: " +  listSubNode.size(),Toast.LENGTH_SHORT).show();
            return false;
        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == 11111)
        {
            int idNode = data.getIntExtra("IDNode",-1);
            int idNodeParent = data.getIntExtra("IDNodeParent",-1);
            int sDongia = data.getIntExtra("DG", -1);
            int PositioninList_PT_inDay = data.getIntExtra("PosPT",-1);
            int PositionDayinList_Tuan = data.getIntExtra("PosDay",-1);
            int PositionWeekinList_Thang= data.getIntExtra("PosWeek",-1);
            double sSoluong = data.getDoubleExtra("SL", -1);
            double sThanhtien = data.getDoubleExtra("TT", -1);
            boolean stinhtrang = data.getBooleanExtra("TTR", false);

            if(idNode != -1 && idNodeParent != -1 && sDongia != -1 && PositioninList_PT_inDay != -1 && sDongia != -1)
            {
                Bundle bundle = new Bundle();
                bundle.putInt("IDNode",idNode);
                bundle.putInt("IDNodeParent",idNodeParent);
                bundle.putInt("DG",sDongia);
                bundle.putInt("PosPT",PositioninList_PT_inDay);
                bundle.putInt("PosDay", PositionDayinList_Tuan);
                bundle.putInt("PosWeek", PositionWeekinList_Thang);
                bundle.putDouble("SL", sSoluong);
                bundle.putDouble("TT", sThanhtien);
                bundle.putBoolean("TTR", stinhtrang);
                Message message = new Message();
                message.setData(bundle);
                handler.sendMessage(message);

            }else
            {
                Toast.makeText(getContext(),"ERROR -1" + PositioninList_PT_inDay + "/",Toast.LENGTH_SHORT).show();

            }

            //root.getChildren().get(idNodeParent).getChildren().get(idNode)
        }
        else if(resultCode == 22222)
        {
            Phieu_Thu ptx = (Phieu_Thu)data.getSerializableExtra("PTHU");
            int PositionWeekinList_Thang = ptx.getIDTuanofListThang();
            int PositionDayinList_Tuan = ptx.getIDNgayListNgayofTuan();
            Ngay ngayx = thangx.getDsTuan().get(PositionWeekinList_Thang).getDsNgay().get(PositionDayinList_Tuan);
            ngayx.getNodeNgay().addChild(ptx.getNode());
            ptx.CapNhatLenServer();
            tView.expandNode(ngayx.getNodeNgay());


        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.savedInstanceState = savedInstanceState;
        View view = inflater.inflate(R.layout.frag_info_selectable_nodes,container,false);
        containerView = (ViewGroup) view.findViewById(R.id.container);
        handler = new Handler(callback);
        root = Tree_Node.root();
        DS_NAM.setNODEROOT(root);



        VuDuocChon = this.getArguments().getInt("HARVEST");
        Year = this.getArguments().getInt("YEAR");
        Month = this.getArguments().getInt("MONTH");
        new LoadListViewData().execute();
        return view;
    }


    private class LoadListViewDataForDay extends AsyncTask<Tuan, Integer, Void> {


        @Override
        protected void onPreExecute() {
            // Before starting background task
            // Show Progress Dialog etc,.

            progressDialog = new ProgressDialog(getContext());
            progressDialog.setMessage("Cho xiu..");
            progressDialog.setIndeterminate(true);
            progressDialog.setCancelable(false);
            progressDialog.show();
        }
        @Override
        protected Void doInBackground(Tuan... params) {
            final Tuan tuanx = params[0];
            ParseQuery<ParseObject> query = ParseQuery.getQuery("NGAY");
            query.whereEqualTo("ID_TUAN", tuanx.getIDServer());
            query.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> list, ParseException e) {
                    if (e == null && tuanx.getDsNgay() == null) {
                        tuanx.KhoiTaoDSNgayForTuan(list);
                    }
                    if (tuanx.getDsNgay().size() != 0) {
                        for (int i = 0; i < tuanx.getDsNgay().size(); i++) {
                            final Ngay ngayx = tuanx.getDsNgay().get(i);
                            ParseQuery<ParseObject> query2 = ParseQuery.getQuery("PHIEU_THU");
                            query2.whereEqualTo("ID_NGAY_MUA", ngayx.getIDServer());
                            query2.findInBackground(new FindCallback<ParseObject>() {
                                @Override
                                public void done(List<ParseObject> list, ParseException e) {
                                    if (e == null && ngayx.getDsPhieuThu() == null) {
                                        ngayx.KhoiTaoDSPhieuThu(list);
                                    }
                                   /* else
                                        ngayx.KhoiTaoDSPhieuThu(list); *////// NOTE
                                }
                            });
                        }
                    }
                }
            });



            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            progressDialog.dismiss();
         }


    }
    private class LoadListViewData extends AsyncTask<Void, Integer, Void> {

        @Override
        protected void onPreExecute() {
            // Before starting background task
            // Show Progress Dialog etc,.

            progressDialog = new ProgressDialog(getContext());
            progressDialog.setMessage("Cho xiu..");
            progressDialog.setIndeterminate(true);
            progressDialog.setCancelable(false);
            progressDialog.show();
        }
        @Override
        protected Void doInBackground(Void... params) {

            ParseQuery<ParseObject> query2 = ParseQuery.getQuery("TUAN"); // kết nối internet... save parsequery<parseobjet> để ko ket noi internet
            query2.whereEqualTo("ID_THANG", DS_NAM.getDs_Nam().get(Year).getDs_vumua().get(VuDuocChon).getDs_Thang().get(Month).getIDThangServer());
            query2.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> list, com.parse.ParseException e) {
                    if (e == null) {
                        thangx = DS_NAM.getDs_Nam().get(Year).getDs_vumua().get(VuDuocChon).getDs_Thang().get(Month);
                        thangx.KhoiTaoDSTuanCuaThang(list, root);

                        DS_NAM.setThangDcChon(thangx);


                        tView = new Android_Tree_View(getActivity(), root);
                        tView.setDefaultAnimation(true);
                        DS_NAM.settView(tView);

                        containerView.addView(tView.getView());

                        if (savedInstanceState != null) {
                            String state = savedInstanceState.getString("tState");
                            if (!TextUtils.isEmpty(state)) {
                                tView.restoreState(state);
                            }
                        }

                        for (int i = 0; i < DS_NAM.getDs_Nam().get(Year).getDs_vumua().get(VuDuocChon).getDs_Thang().get(Month).getDsTuan().size(); i++) {
                            final Tuan tuanx = DS_NAM.getDs_Nam().get(Year).getDs_vumua().get(VuDuocChon).getDs_Thang().get(Month).getDsTuan().get(i);
                            Tree_Node nodeHeader = tuanx.getNodeTuan();
                            if (i != 0) {
                                nodeHeader.setClickListener(new Tree_Node.Tree_NodeClickListener() {
                                    @Override
                                    public void onClick(Tree_Node node, Object value) {
                                        new LoadListViewDataForDay().execute(tuanx);
                                    }
                                });
                            } else {
                                fristWeek = true;
                                new LoadListViewDataForDay().execute(tuanx);
                            }

                        }

                    } else {

                        tView = new Android_Tree_View(getActivity(), root);
                        tView.setDefaultAnimation(true);

                        containerView.addView(tView.getView());

                        if (savedInstanceState != null) {
                            String state = savedInstanceState.getString("tState");
                            if (!TextUtils.isEmpty(state)) {
                                tView.restoreState(state);
                            }
                        }

                        for (int i = 0; i < DS_NAM.getDs_Nam().get(Year).getDs_vumua().get(VuDuocChon).getDs_Thang().get(Month).getDsTuan().size(); i++) {
                            final Tuan tuanx = DS_NAM.getDs_Nam().get(Year).getDs_vumua().get(VuDuocChon).getDs_Thang().get(Month).getDsTuan().get(i);
                            Tree_Node nodeHeader = tuanx.getNodeTuan();
                            if (i != 0) {
                                nodeHeader.setClickListener(new Tree_Node.Tree_NodeClickListener() {
                                    @Override
                                    public void onClick(Tree_Node node, Object value) {
                                        new LoadListViewDataForDay().execute(tuanx);
                                    }
                                });
                            } else {
                                new LoadListViewDataForDay().execute(tuanx);
                            }
                        }

                    }

                    /*if (DS_NAM.isFristStartDefaultItemClick()) {
                        lv_PT.performItemClick(lv_PT.getAdapter().getView(3, null, null), 3, lv_PT.getAdapter().getItemId(3));
                        DS_NAM.setFristStartDefaultItemClick(false);
                    }*/
                }
            });
            return null;
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            progressDialog.dismiss();
            Toast.makeText(getContext(), "Tai Du Lieu Xong", Toast.LENGTH_SHORT).show();

        }
    }
    /*private void fillFolder(Tree_Node folder) {
        Tree_Node file1 = new Tree_Node("File1").setViewHolder(new SelectableItemHolder(getActivity()));
        Tree_Node file2 = new Tree_Node("File2").setViewHolder(new SelectableItemHolder(getActivity()));
        Tree_Node file3 = new Tree_Node("File3").setViewHolder(new SelectableItemHolder(getActivity()));
        folder.addChildren(file1, file2, file3);
    }*/


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("tState", tView.getSaveState());
    }
}
