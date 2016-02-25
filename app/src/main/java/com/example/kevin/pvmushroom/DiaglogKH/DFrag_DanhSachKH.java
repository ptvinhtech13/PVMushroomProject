package com.example.kevin.pvmushroom.DiaglogKH;

import android.app.Dialog;
import android.app.DialogFragment;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.kevin.pvmushroom.Adapters.AdapterLV_DS_KH;
import com.example.kevin.pvmushroom.DanhSachStatic.DS_KHACH_HANG;
import com.example.kevin.pvmushroom.R;

/**
 * Created by kevin on 1/27/2016.
 */
public class DFrag_DanhSachKH extends DialogFragment {
    public Button but_OK;
    public ListView lv_DsKH;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity());

        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);

        dialog.setContentView(R.layout.dialog_frag_danh_sach_khach_hang);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));




        TextView tilte = (TextView)dialog.findViewById(R.id.tv_tilte);
        Typeface tf = Typeface.createFromAsset(dialog.getContext().getAssets(),"SanFranciscoDisplay-Light.otf");

        tilte.setTypeface(tf);

        lv_DsKH = (ListView)dialog.findViewById(R.id.lv_ds_khach_hang);


        AdapterLV_DS_KH adapterLV_ds_kh = new AdapterLV_DS_KH(dialog.getContext(),R.layout.item_kh_in_ds_kh, DS_KHACH_HANG.getDs_KhachHang(),tf);

        lv_DsKH.setAdapter(adapterLV_ds_kh);

        dialog.findViewById(R.id.but_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        return dialog;
    }
}
