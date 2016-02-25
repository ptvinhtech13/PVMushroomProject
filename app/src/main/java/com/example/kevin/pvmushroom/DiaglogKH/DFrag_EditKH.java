package com.example.kevin.pvmushroom.DiaglogKH;

import android.app.Dialog;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.kevin.pvmushroom.R;
import com.parse.Parse;

/**
 * Created by kevin on 2/14/2016.
 */
public class DFrag_EditKH extends DialogFragment {

    EditText ed_dongia, ed_sokg;
    TextView tv_ngaythang, tv_tenKH, tv_ThanhTien;
    Button but_done;
    int PositioninList_PT_inDay, PositionDayinList_Tuan, PositionWeekinList_Thang;
    ToggleButton toggleBut;
    TextView tv_tinhtrang;

    private String stenKH;
    int sDongia, idNode, idNodeParent;
    double sSoluong, sThanhtien;
    boolean stinhtrang;

    private void sendResult(int REQUEST_CODE) {
        Intent intent = new Intent();
        intent.putExtra("IDNode",idNode);
        intent.putExtra("IDNodeParent",idNodeParent);
        intent.putExtra("DG",sDongia);
        intent.putExtra("SL",sSoluong);
        intent.putExtra("TT",sThanhtien);

        intent.putExtra("PosPT",PositioninList_PT_inDay);
        intent.putExtra("PosDay",PositionDayinList_Tuan);
        intent.putExtra("PosWeek",PositionWeekinList_Thang);
        intent.putExtra("TTR",stinhtrang);

        getTargetFragment().onActivityResult(
                getTargetRequestCode(), REQUEST_CODE, intent);
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Dialog dialog = new Dialog(getActivity());
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        dialog.setContentView(R.layout.dialog_frag_edit_infor_phieu_thu);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        ed_dongia = (EditText)dialog.findViewById(R.id.ed_dongia);
        ed_sokg = (EditText)dialog.findViewById(R.id.ed_sokg);
        tv_tenKH = (TextView)dialog.findViewById(R.id.ten_KH);
        tv_ngaythang = (TextView)dialog.findViewById(R.id.ngaymua);
        tv_ThanhTien = (TextView)dialog.findViewById(R.id.tv_Thanhtien);
        but_done = (Button)dialog.findViewById(R.id.but_done);

        toggleBut = (ToggleButton)dialog.findViewById(R.id.toggle_thanhtoan);
        tv_tinhtrang = (TextView)dialog.findViewById(R.id.tv_thanhtoan);

        toggleBut.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    tv_tinhtrang.setText(getContext().getResources().getString(R.string.tt_on));

                } else
                    tv_tinhtrang.setText(getContext().getResources().getString(R.string.tt_off));
                stinhtrang = isChecked;
            }
        });


        Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), "SanFranciscoDisplay-Light.otf");

        ed_dongia.setTypeface(tf);
        ed_sokg.setTypeface(tf);
        tv_tenKH.setTypeface(tf);
        tv_ngaythang.setTypeface(tf);
        tv_ThanhTien.setTypeface(tf);
        tv_tinhtrang.setTypeface(tf);


        stenKH = this.getArguments().getString("KH");
        sDongia = this.getArguments().getInt("DG");
        sSoluong = this.getArguments().getDouble("SL");
        sThanhtien = this.getArguments().getDouble("TT");
        idNode = this.getArguments().getInt("IDNODE");
        idNodeParent = this.getArguments().getInt("IDNODEParent");
        PositioninList_PT_inDay = this.getArguments().getInt("PosPT");
        PositionDayinList_Tuan = this.getArguments().getInt("PosDay");
        PositionWeekinList_Thang= this.getArguments().getInt("PosWeek");
        stinhtrang = this.getArguments().getBoolean("TTR");


        tv_tenKH.setText(stenKH);
        ed_dongia.setText(""+ (sDongia));
        ed_sokg.setText("" + sSoluong);
        tv_ThanhTien.setText(""+(sThanhtien));

        toggleBut.setChecked(stinhtrang);


        ed_dongia.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (ed_dongia.getText().toString().isEmpty())
                    tv_ThanhTien.setText("0");
                else
                    tv_ThanhTien.setText("" + ((Integer.parseInt(ed_dongia.getText().toString()) * Double.parseDouble(ed_sokg.getText().toString()))));

            }
        });
        ed_sokg.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(ed_sokg.getText().toString().isEmpty())
                    tv_ThanhTien.setText("0");
                else
                    tv_ThanhTien.setText(""+((Integer.parseInt(ed_dongia.getText().toString())*Double.parseDouble(ed_sokg.getText().toString()))));

            }
        });

        but_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sDongia = Integer.parseInt(ed_dongia.getText().toString());
                sSoluong = Double.parseDouble(ed_sokg.getText().toString());
                sThanhtien = Double.parseDouble(tv_ThanhTien.getText().toString());
                sendResult(11111);
                dismiss();
            }
        });

        return dialog;
    }
}
