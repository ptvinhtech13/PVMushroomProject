package com.example.kevin.pvmushroom.Fragment.hover;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kevin.pvmushroom.DanhSachStatic.DS_NAM;
import com.example.kevin.pvmushroom.DanhSachStatic.Ds_Fragment;
import com.example.kevin.pvmushroom.DiaglogKH.DFrag_AddNewPT;
import com.example.kevin.pvmushroom.Fragment.frag_of_PhieuThu.Tree_Node;
import com.example.kevin.pvmushroom.Object.Ngay;
import com.example.kevin.pvmushroom.R;


/**
 * Created by kevin on 2/3/2016.
 */
public class SelectableHeaderHolder extends Tree_Node.BaseNodeViewHolder<IconTreeItemHolder.IconTreeItem> {
    private TextView tvValue, tvTongTien;
    private TextView arrowView;
    private CheckBox nodeSelector;
    private ImageView but_add_pt, but_delete_day;
    private Ngay ngayX;

    public SelectableHeaderHolder(Context context) {
        super(context);
    }

    @Override
    public View createNodeView(final Tree_Node node, IconTreeItemHolder.IconTreeItem value) {

        final LayoutInflater inflater = LayoutInflater.from(context);
        final View view = inflater.inflate(R.layout.frag_info_layout_selectable_header, null, false);


        ngayX = value.ngayX;

        tvValue = (TextView) view.findViewById(R.id.tv_Ngay_Header);
        tvValue.setText(value.text);

        tvTongTien = (TextView)view.findViewById(R.id.tv_tongTien_of_Ngay);
        tvTongTien.setText(ngayX.getTongTien() + " k");

        but_add_pt = (ImageView)view.findViewById(R.id.but_add_pt);
        but_delete_day = (ImageView)view.findViewById(R.id.but_delete_day);

        but_add_pt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"ADD_PT",Toast.LENGTH_SHORT).show();
                DFrag_AddNewPT addNewPT = new DFrag_AddNewPT();
                Bundle bundle = new Bundle();
                bundle.putInt("PosDay",ngayX.getIDinList_Tuan());
                bundle.putInt("PosWeek",ngayX.getIDinList_Thang());
                bundle.putInt("IDNgayX",ngayX.getIDServer());
                addNewPT.setArguments(bundle);

                addNewPT.setTargetFragment(Ds_Fragment.getItemList(3).getFragment(), 22222);
                addNewPT.show(DS_NAM.getFm(), "EDIT");
            }
        });
        but_delete_day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                ///
                Toast.makeText(context,"DELETE_DAY CHUA CAP NHAT LEN SERVER",Toast.LENGTH_SHORT).show();
                ngayX.getPoxNgay().deleteInBackground();
                Tree_Node parent = ngayX.getNodeNgay().getParent();

                parent.deleteChild(ngayX.getNodeNgay());

                DS_NAM.gettView().expandNode(parent);
            }
        });






        arrowView = (TextView) view.findViewById(R.id.arrow_icon);
        arrowView.setText(DS_NAM.getContext().getResources().getString(R.string.ic_keyboard_arrow_right));
        arrowView.setTypeface(Typeface.createFromAsset(context.getAssets(), "material-icon-font.ttf"));
        arrowView.setVisibility(View.VISIBLE);
        /*if (node.isLeaf()) {
            arrowView.setVisibility(View.GONE);
        }*/

       /* nodeSelector = (CheckBox) view.findViewById(R.id.node_selector);
        nodeSelector.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                node.setSelected(isChecked);
                for (Tree_Node n : node.getChildren()) {
                    getTreeView().selectNode(n, isChecked);
                }
            }
        });
        nodeSelector.setChecked(node.isSelected());*/

        return view;
    }

    @Override
    public void toggle(boolean active) {
        arrowView.setText(context.getResources().getString(active ? R.string.ic_keyboard_arrow_down : R.string.ic_keyboard_arrow_right));
    }

    @Override
    public void toggleSelectionMode(boolean editModeEnabled) {
        nodeSelector.setVisibility(editModeEnabled ? View.VISIBLE : View.GONE);
        nodeSelector.setChecked(mNode.isSelected());
    }
}