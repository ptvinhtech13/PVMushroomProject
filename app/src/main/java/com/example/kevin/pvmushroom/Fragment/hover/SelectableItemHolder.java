package com.example.kevin.pvmushroom.Fragment.hover;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kevin.pvmushroom.DanhSachStatic.DS_KHACH_HANG;
import com.example.kevin.pvmushroom.DanhSachStatic.DS_NAM;
import com.example.kevin.pvmushroom.DanhSachStatic.Ds_Fragment;
import com.example.kevin.pvmushroom.DiaglogKH.DFrag_DeletePT;
import com.example.kevin.pvmushroom.DiaglogKH.DFrag_EditKH;
import com.example.kevin.pvmushroom.Fragment.frag_of_PhieuThu.Tree_Node;
import com.example.kevin.pvmushroom.Object.Phieu_Thu;
import com.example.kevin.pvmushroom.R;


/**
 * Created by kevin on 2/3/2016.
 */
public class SelectableItemHolder extends Tree_Node.BaseNodeViewHolder<Phieu_Thu> {
    private TextView tvTenKH, tv_Dongia, tv_Thanhtien, tv_Soluong, tv_tinhtrang;
    private String stenKH;
    int sDongia, PositioninList_PT_inDay, PositionDayinList_Tuan, PositionWeekinList_Thang;
    double sThanhtien, sSoluong;
    private CheckBox nodeSelector;
    private Button but_menu;
    private Tree_Node Nodex;
    boolean Tinhtrang;

    public SelectableItemHolder(Context context) {
        super(context);
    }

    @Override
    public View createNodeView(final Tree_Node node, Phieu_Thu value) {
        final LayoutInflater inflater = LayoutInflater.from(context);
        final View view = inflater.inflate(R.layout.frag_info_layout_selectable_item, null, false);

        tvTenKH = (TextView) view.findViewById(R.id.tv_tenKH);
        tvTenKH.setText(DS_KHACH_HANG.getDs_KhachHang().get(value.getIDKH()).getHoTen());
        stenKH = DS_KHACH_HANG.getDs_KhachHang().get(value.getIDKH()).getHoTen();
        tv_Dongia = (TextView) view.findViewById(R.id.tv_DonGia);
        tv_Dongia.setText(context.getResources().getString(R.string.kh_dongia)+" " + value.getDonGia());
        sDongia = value.getDonGia();
        tv_Thanhtien = (TextView) view.findViewById(R.id.tv_Thanhtien);
        tv_Thanhtien.setText(context.getResources().getString(R.string.kh_thanhtien)+" " + value.getThanhTien());
        sThanhtien = value.getThanhTien();
        tv_Soluong = (TextView) view.findViewById(R.id.tv_Soluong);
        tv_Soluong.setText(context.getResources().getString(R.string.kh_soluong)+" " +value.getSoLuong());
        sSoluong = value.getSoLuong();
        tv_tinhtrang = (TextView)view.findViewById(R.id.tv_tinhtrang);


        PositioninList_PT_inDay = value.getPositioninListPTinDay();
        PositionDayinList_Tuan = value.getIDNgayListNgayofTuan();
        PositionWeekinList_Thang = value.getIDTuanofListThang();
        Tinhtrang = value.isTinhTrang();

        if(Tinhtrang)
        {
            tv_tinhtrang.setText("");
        }else
        {
            tv_tinhtrang.setText(context.getResources().getString(R.string.tt_off));
        }


        Typeface tf = Typeface.createFromAsset(context.getAssets(),"SanFranciscoDisplay-Light.otf");

        tv_Dongia.setTypeface(tf);
        tv_Soluong.setTypeface(tf);
        tvTenKH.setTypeface(tf);
        tv_Thanhtien.setTypeface(tf);
        tv_tinhtrang.setTypeface(tf);



        TextView circle_blank = (TextView)view.findViewById(R.id.circle_blank);
        circle_blank.setTypeface(Typeface.createFromAsset(context.getAssets(), "material-icon-font.ttf"));
        circle_blank.setText(context.getResources().getString(R.string.ic_check_circle_blank));

        Nodex = node;

        node.setLongClickListener(new Tree_Node.Tree_NodeLongClickListener() {
            @Override
            public boolean onLongClick(Tree_Node node, Object value) {
                Toast.makeText(context.getApplicationContext(),"long Key",Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        /*nodeSelector = (CheckBox) view.findViewById(R.id.node_selector);
        nodeSelector.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                node.setSelected(isChecked);
            }
        });
        nodeSelector.setChecked(node.isSelected());
*/
        if (node.isLastChild()) {
            view.findViewById(R.id.bot_line).setVisibility(View.INVISIBLE);
        }

        but_menu = (Button)view.findViewById(R.id.but_menu);
        but_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context wrapper = new ContextThemeWrapper(DS_NAM.getContext(),R.style.PopupMenu);
                PopupMenu popupMenu = new PopupMenu(wrapper,v);
                MenuInflater inflater = popupMenu.getMenuInflater();
                inflater.inflate(R.menu.menu_tools,popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getItemId() == R.id.mn_edit) {
                            Bundle bundle = new Bundle();
                            bundle.putString("KH", stenKH);
                            bundle.putInt("DG", sDongia);
                            bundle.putDouble("SL", sSoluong);
                            bundle.putDouble("TT", sThanhtien);
                            bundle.putInt("IDNODE", Nodex.getId());
                            bundle.putInt("IDNODEParent", Nodex.getParent().getId());
                            bundle.putInt("PosPT", PositioninList_PT_inDay);
                            bundle.putInt("PosDay", PositionDayinList_Tuan);
                            bundle.putInt("PosWeek", PositionWeekinList_Thang);
                            bundle.putBoolean("TTR", Tinhtrang);


                            DFrag_EditKH frag_editKH = new DFrag_EditKH();
                            frag_editKH.setArguments(bundle);

                            frag_editKH.setTargetFragment(Ds_Fragment.getItemList(3).getFragment(), 11111);
                            frag_editKH.show(DS_NAM.getFm(), "EDIT");
                        } else if (item.getItemId() == R.id.mn_delete) {
                            // cap nhat du lieu sau khi xóa
                            // bật alert diaglog cảnh báo
                            Bundle bundle = new Bundle();
                            bundle.putString("KH", stenKH);
                            bundle.putInt("DG", sDongia);
                            bundle.putDouble("SL", sSoluong);
                            bundle.putDouble("TT", sThanhtien);
                            bundle.putInt("IDNODE", Nodex.getId());
                            bundle.putInt("IDNODEParent", Nodex.getParent().getId());
                            bundle.putInt("PosPT", PositioninList_PT_inDay);
                            bundle.putInt("PosDay", PositionDayinList_Tuan);
                            bundle.putInt("PosWeek", PositionWeekinList_Thang);
                            bundle.putBoolean("TTR", Tinhtrang);


                            DFrag_DeletePT frag_editKH = new DFrag_DeletePT();
                            frag_editKH.setArguments(bundle);

                            frag_editKH.setTargetFragment(Ds_Fragment.getItemList(3).getFragment(), 33333);
                            frag_editKH.show(DS_NAM.getFm(), "DELETE");


                        }


                        return false;
                    }
                });


                popupMenu.show();
            }
        });

        return view;
    }


    @Override
    public void toggleSelectionMode(boolean editModeEnabled) {
        nodeSelector.setVisibility(editModeEnabled ? View.VISIBLE : View.GONE);
        nodeSelector.setChecked(mNode.isSelected());
    }
}