package com.example.kevin.pvmushroom.DiaglogKH;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.kevin.pvmushroom.Adapters.AdapterLV_DS_KH;
import com.example.kevin.pvmushroom.DanhSachStatic.DS_KHACH_HANG;
import com.example.kevin.pvmushroom.DanhSachStatic.DS_NAM;
import com.example.kevin.pvmushroom.Fragment.frag_of_PhieuThu.Tree_Node;
import com.example.kevin.pvmushroom.Fragment.hover.SelectableItemHolder;
import com.example.kevin.pvmushroom.Object.Phieu_Thu;
import com.example.kevin.pvmushroom.R;

/**
 * Created by kevin on 2/18/2016.
 */
public class DFrag_AddNewPT extends DialogFragment {
    Spinner spin_dsKhachHang;
    EditText edit_soluong, edit_dongia;
    TextView tv_thanhtien, tv_tinhtrang;

    boolean stinhtrang;
    ToggleButton toggleButton_tinhtrang;
    Button but_done;
    Phieu_Thu ptx;
    int IDNgayServerx = -1;
    int IDKH = -1, PositionDayinList_Tuan,PositionWeekinList_Thang ;


    private void sendResult(int REQUEST_CODE) {
        Intent intent = new Intent();
        intent.putExtra("PTHU",ptx);
        getTargetFragment().onActivityResult(
                getTargetRequestCode(), REQUEST_CODE, intent);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity());
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        dialog.setContentView(R.layout.dialog_frag_add_new_phieu_thu);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        spin_dsKhachHang = (Spinner)dialog.findViewById(R.id.spinner_container_ten_KH);
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(),"SanFranciscoDisplay-Light.otf");

        AdapterLV_DS_KH adapterLVDsKh = new AdapterLV_DS_KH(getContext(),R.layout.spinner_ten_kh_item, DS_KHACH_HANG.getDs_KhachHang(), tf);

        spin_dsKhachHang.setAdapter(adapterLVDsKh);


        edit_soluong = (EditText)dialog.findViewById(R.id.ed_sokg);
        edit_dongia = (EditText)dialog.findViewById(R.id.ed_dongia);
        tv_thanhtien = (TextView)dialog.findViewById(R.id.tv_Thanhtien);
        toggleButton_tinhtrang = (ToggleButton)dialog.findViewById(R.id.toggle_thanhtoan);
        tv_tinhtrang = (TextView)dialog.findViewById(R.id.tv_thanhtoan);
        but_done = (Button)dialog.findViewById(R.id.but_done);

        tv_thanhtien.setText("0");


        PositionWeekinList_Thang = this.getArguments().getInt("PosWeek");
        PositionDayinList_Tuan = this.getArguments().getInt("PosDay");
        IDNgayServerx = this.getArguments().getInt("IDNgayX");

        Toast.makeText(getContext(),IDNgayServerx + " L1",Toast.LENGTH_SHORT).show();

        spin_dsKhachHang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            //    Toast.makeText(getContext(),DS_KHACH_HANG.getDs_KhachHang().get(position).getHoTen() + "",Toast.LENGTH_SHORT).show();
                IDKH = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        toggleButton_tinhtrang.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    tv_tinhtrang.setText(getContext().getResources().getString(R.string.tt_on));

                } else
                    tv_tinhtrang.setText(getContext().getResources().getString(R.string.tt_off));
                stinhtrang = isChecked;
            }
        });

        toggleButton_tinhtrang.setChecked(false);

        edit_dongia.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!edit_soluong.getText().toString().equals(""))
                    tv_thanhtien.setText("" + ((Integer.parseInt(edit_dongia.getText().toString()) * Double.parseDouble(edit_soluong.getText().toString()))));
                else
                    tv_thanhtien.setText("0");
                Toast.makeText(getContext(), edit_dongia.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        edit_soluong.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!edit_dongia.getText().toString().equals(""))
                    tv_thanhtien.setText(""+((Integer.parseInt(edit_dongia.getText().toString())*Double.parseDouble(edit_soluong.getText().toString()))));
                else
                    tv_thanhtien.setText("0");
            }
        });

        but_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /// kiem tra du lieu khi nhap
                if (!edit_dongia.getText().toString().equals("") && !edit_soluong.getText().toString().equals("")) {
                    ptx = new Phieu_Thu(IDKH, DS_NAM.generateIDLastPhieuThu(), Integer.parseInt(edit_dongia.getText().toString()), Double.parseDouble(edit_soluong.getText().toString()), Double.parseDouble(tv_thanhtien.getText().toString()));
                    ptx.setIDCodeServer(null);
                    ptx.setIDNgayCuaServer(IDNgayServerx);
                    Toast.makeText(getContext(),IDNgayServerx + " L2",Toast.LENGTH_SHORT).show();
                    ptx.setTinhTrang(stinhtrang);
                    ptx.setIDNgayListNgayofTuan(PositionDayinList_Tuan);
                    ptx.setIDTuanofListThang(PositionWeekinList_Thang);
                    Tree_Node node = new Tree_Node(ptx).setViewHolder(new SelectableItemHolder(DS_NAM.getContext().getApplicationContext()));
                    ptx.setNode(node);
                    sendResult(22222);
                    dismiss();
                } else {
                    Toast.makeText(getContext(),getResources().getString(R.string.notify_PT),Toast.LENGTH_SHORT).show();
                }
            }
        });

        return dialog;
    }
}
