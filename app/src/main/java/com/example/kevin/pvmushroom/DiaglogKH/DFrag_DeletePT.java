package com.example.kevin.pvmushroom.DiaglogKH;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kevin.pvmushroom.DanhSachStatic.DS_NAM;
import com.example.kevin.pvmushroom.Fragment.frag_of_PhieuThu.Tree_Node;
import com.example.kevin.pvmushroom.Object.Phieu_Thu;
import com.example.kevin.pvmushroom.R;

/**
 * Created by kevin on 2/17/2016.
 */
public class DFrag_DeletePT extends DialogFragment {

    Button but_cancel, button_ok;
    int PositioninList_PT_inDay,PositionDayinList_Tuan,PositionWeekinList_Thang;


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Dialog dialog = new Dialog(getActivity());
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        dialog.setContentView(R.layout.dialog_frag_canh_bao_xoa);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        TextView textView = (TextView)dialog.findViewById(R.id.text);
        button_ok = (Button)dialog.findViewById(R.id.but_ok_delete);
        but_cancel = (Button)dialog.findViewById(R.id.but_cannot_delete);


        Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), "SanFranciscoDisplay-Light.otf");
        but_cancel.setTypeface(tf);
        button_ok.setTypeface(tf);
        textView.setTypeface(tf);


        PositioninList_PT_inDay = this.getArguments().getInt("PosPT");
        PositionDayinList_Tuan = this.getArguments().getInt("PosDay");
        PositionWeekinList_Thang= this.getArguments().getInt("PosWeek");
        button_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /// xóa phieu thu chua cap nhat len server
                Phieu_Thu ptx = DS_NAM.getThangDcChon().getDsTuan().get(PositionWeekinList_Thang).getDsNgay().get(PositionDayinList_Tuan).getDsPhieuThu().get(PositioninList_PT_inDay);
                ptx.getPox().deleteInBackground();
                Tree_Node parentNode = ptx.getNode().getParent();
                parentNode.deleteChild(ptx.getNode());
                DS_NAM.gettView().expandNode(parentNode);
                dismiss();

            }
        });

        but_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });


        return dialog;
    }
}
