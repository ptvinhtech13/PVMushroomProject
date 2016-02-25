package com.example.kevin.pvmushroom.Fragment.frag_of_PhieuThu;

import android.support.v4.app.Fragment;
import android.app.ProgressDialog;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.kevin.pvmushroom.Adapters.AdapterLV_DS_Year;
import com.example.kevin.pvmushroom.DanhSachStatic.DS_NAM;
import com.example.kevin.pvmushroom.DanhSachStatic.Ds_Fragment;
import com.example.kevin.pvmushroom.Object.Nam;
import com.example.kevin.pvmushroom.R;
import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

/**
 * Created by kevin on 1/30/2016.
 */
public class Frag__Year extends Fragment {

    public AdapterLV_DS_Year listAdapter_PT;
    public ListView lv_PT;
    ProgressDialog progressDialog;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_year,container,false);
        lv_PT = (ListView)view.findViewById(R.id.lv_Year_frag);
        new LoadListViewYear().execute();

        return view;
    }


    private class LoadListViewYear extends AsyncTask<Void, Integer, Void> {

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

            ParseQuery<ParseObject> query2 = ParseQuery.getQuery("VU_MUA"); // kết nối internet... save parsequery<parseobjet> để ko ket noi internet
            query2.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> list, com.parse.ParseException e) {
                    if (e == null && DS_NAM.getDs_Nam() == null) {
                        DS_NAM.KhoiTaoDanhSachYEAR(list);
                        listAdapter_PT = new AdapterLV_DS_Year(getActivity().getApplicationContext(), DS_NAM.getDs_Nam(), R.layout.item_year_in_dsnam, Typeface.createFromAsset(getActivity().getAssets(), "SanFranciscoDisplay-Light.otf"));
                        lv_PT.setAdapter(listAdapter_PT);

                        lv_PT.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Bundle bundle = new Bundle();
                                bundle.putInt("YEAR", position);
                                DS_NAM.getLy_ContainerHSV().removeAllViews();
                                DS_NAM.createTextView(getContext(), DS_NAM.getDs_Nam().get(position).getIDNam() + "", true);
                                DS_NAM.createTextView(getContext(), "", false); // tao dau > do check false
                                DS_NAM.createTextView(getContext(), getResources().getString(R.string.vu_mua), true);

                                Ds_Fragment.getItemList(1).getFragment().setArguments(bundle);
                                Ds_Fragment.getItemList(1).CallFrag(1, true);
                            }
                        });

                    } else {
                        listAdapter_PT = new AdapterLV_DS_Year(getActivity().getApplicationContext(), DS_NAM.getDs_Nam(), R.layout.item_year_in_dsnam, Typeface.createFromAsset(getActivity().getAssets(), "SanFranciscoDisplay-Light.otf"));
                        lv_PT.setAdapter(listAdapter_PT);
                        lv_PT.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Bundle bundle = new Bundle();
                                bundle.putInt("YEAR", position);
                                DS_NAM.getLy_ContainerHSV().removeAllViews();
                                DS_NAM.createTextView(getContext(), DS_NAM.getDs_Nam().get(position).getIDNam() + "", true);
                                DS_NAM.createTextView(getContext(), "", false); // tao dau >
                                DS_NAM.createTextView(getContext(), getResources().getString(R.string.vu_mua), true);

                                Ds_Fragment.getItemList(1).getFragment().setArguments(bundle);
                                Ds_Fragment.getItemList(1).CallFrag(1, true);
                            }
                        });
                    }
                    DS_NAM.setListAdapter_PT(listAdapter_PT);
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
