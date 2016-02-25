package com.example.kevin.pvmushroom.Fragment.frag_of_PhieuThu;

import android.app.ProgressDialog;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.kevin.pvmushroom.Adapters.AdapterLV_DS_Month;
import com.example.kevin.pvmushroom.Adapters.AdapterLV_DS_Year;
import com.example.kevin.pvmushroom.DanhSachStatic.DS_NAM;
import com.example.kevin.pvmushroom.DanhSachStatic.Ds_Fragment;
import com.example.kevin.pvmushroom.Fragment.hover.IconTreeItemHolder;
import com.example.kevin.pvmushroom.Fragment.hover.ProfileHolder;
import com.example.kevin.pvmushroom.Fragment.hover.SelectableHeaderHolder;
import com.example.kevin.pvmushroom.Fragment.hover.SelectableItemHolder;
import com.example.kevin.pvmushroom.Object.Thang;
import com.example.kevin.pvmushroom.R;
import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.unnamed.b.atv.model.TreeNode;
import com.unnamed.b.atv.view.AndroidTreeView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kevin on 2/2/2016.
 */
public class Frag__Info extends Fragment {

    ListView listView;
    AdapterLV_DS_Month adapterLVDsMonth;
    int VuDuocChon,Year;
    Button but_addNewMonth;

    ProgressDialog progressDialog;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_ds_thang_in_one_vu_mua,container,false);


        VuDuocChon = this.getArguments().getInt("MONTH");
        Year = this.getArguments().getInt("YEAR");
        new LoadListViewMonth().execute();

        Toast.makeText(getContext(),DS_NAM.getDs_Nam().get(Year).getDs_vumua().get(VuDuocChon).getTenVu() + " Harvest",Toast.LENGTH_SHORT).show();

        listView = (ListView)view.findViewById(R.id.lv_Month_frag);
        but_addNewMonth = (Button)view.findViewById(R.id.but_Add_Month);



        return view;
    }
    private class LoadListViewMonth extends AsyncTask<Void, Integer, Void> {

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

            ParseQuery<ParseObject> query2 = ParseQuery.getQuery("THANG"); // kết nối internet... save parsequery<parseobjet> để ko ket noi internet
            query2.whereEqualTo("ID_VU_MUA",DS_NAM.getDs_Nam().get(Year).getDs_vumua().get(VuDuocChon).getIDVuServer());
            query2.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> list, com.parse.ParseException e) {
                    if (e == null) {
                        DS_NAM.getDs_Nam().get(Year).getDs_vumua().get(VuDuocChon).KhoiTaoDSThangChoVuMua(list);

                        adapterLVDsMonth = new AdapterLV_DS_Month(DS_NAM.getDs_Nam().get(Year).getDs_vumua().get(VuDuocChon).getDs_Thang(),getContext(), Typeface.createFromAsset(getContext().getAssets(),"SanFranciscoDisplay-Light.otf"));
                        listView.setAdapter(adapterLVDsMonth);
                        but_addNewMonth.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                ArrayList<Thang> list = DS_NAM.getDs_Nam().get(Year).getDs_vumua().get(VuDuocChon).getDs_Thang();
                                list.add(0,new Thang(-1,getContext(),"THANG XXX",null,0,null));

                                adapterLVDsMonth.notifyDataSetChanged();

                            }
                        });

                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Bundle bundle = new Bundle();
                                bundle.putInt("YEAR", Year);
                                bundle.putInt("HARVEST", VuDuocChon);
                                bundle.putInt("MONTH", position); // thang dc chon
                                DS_NAM.RemoveViewToHSVTimeline();

                                DS_NAM.createTextView(getContext(), DS_NAM.getDs_Nam().get(Year).getDs_vumua().get(VuDuocChon).getDs_Thang().get(position).getTenThang() + "", true);
                                DS_NAM.createTextView(getContext(), "", false);
                                DS_NAM.createTextView(getContext(), getResources().getString(R.string.thong_tin_vu), true);

                                Ds_Fragment.getItemList(3).getFragment().setArguments(bundle);
                                Ds_Fragment.getItemList(3).CallFrag(3, true);
                            }
                        });
                        listView.post(new Runnable() {
                            @Override
                            public void run() {
                                listView.smoothScrollToPosition(0);
                            }

                            ;
                        });

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

}
