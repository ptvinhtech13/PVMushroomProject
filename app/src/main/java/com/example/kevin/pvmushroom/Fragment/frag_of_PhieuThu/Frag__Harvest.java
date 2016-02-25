package com.example.kevin.pvmushroom.Fragment.frag_of_PhieuThu;

import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.kevin.pvmushroom.Adapters.AdapterLV_DS_VuMua;
import com.example.kevin.pvmushroom.DanhSachStatic.DS_NAM;
import com.example.kevin.pvmushroom.DanhSachStatic.Ds_Fragment;
import com.example.kevin.pvmushroom.R;

/**
 * Created by kevin on 1/30/2016.
 */
public class Frag__Harvest extends Fragment {

    ListView lv_harvest;
    AdapterLV_DS_VuMua adapterLVDsVuMua;
    int positionYearInDsYear;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_harvest, container, false);

        positionYearInDsYear = this.getArguments().getInt("YEAR");
        //Toast.makeText(getContext(),DS_NAM.getDs_Nam().get(positionYearInDsYear).getIDNam() + " FragHarvest",Toast.LENGTH_SHORT).show();
        lv_harvest = (ListView)view.findViewById(R.id.lv_Harverst_frag);
        adapterLVDsVuMua = new AdapterLV_DS_VuMua(DS_NAM.getDs_Nam().get(positionYearInDsYear).getDs_vumua(),getContext(), Typeface.createFromAsset(getActivity().getAssets(),"SanFranciscoDisplay-Light.otf"));


        lv_harvest.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                bundle.putInt("YEAR",positionYearInDsYear);
                bundle.putInt("MONTH", position);
                DS_NAM.RemoveViewToHSVTimeline();

                DS_NAM.createTextView(getContext(), DS_NAM.getDs_Nam().get(positionYearInDsYear).getDs_vumua().get(position).getTenVu() + "", true);
                DS_NAM.createTextView(getContext(), "", false);
                DS_NAM.createTextView(getContext(), getResources().getString(R.string.ds_thang), true);

                Ds_Fragment.getItemList(2).getFragment().setArguments(bundle);
                Ds_Fragment.getItemList(2).CallFrag(2, true);
            }
        });
        lv_harvest.post(new Runnable() {
                            @Override
                            public void run() {
                                lv_harvest.smoothScrollToPosition(0);
                            };
                        });

        lv_harvest.setAdapter(adapterLVDsVuMua);
        return view;
    }
}
