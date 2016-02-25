package com.example.kevin.pvmushroom.Fragment.hover;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kevin.pvmushroom.DanhSachStatic.DS_NAM;
import com.example.kevin.pvmushroom.Fragment.frag_of_PhieuThu.Tree_Node;
import com.example.kevin.pvmushroom.Object.Ngay;
import com.example.kevin.pvmushroom.Object.Tuan;
import com.example.kevin.pvmushroom.R;


import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by kevin on 2/3/2016.
 */
public class ProfileHolder extends Tree_Node.BaseNodeViewHolder<IconTreeItemHolder.IconTreeItem> {



    Tuan tuan;
    public ProfileHolder(Context context) {
        super(context);
    }

    @Override
    public View createNodeView(Tree_Node node, IconTreeItemHolder.IconTreeItem value) {
        final LayoutInflater inflater = LayoutInflater.from(context);
        final View view = inflater.inflate(R.layout.frag_info_layout_profile_node, null, false);

        TextView tvValue = (TextView) view.findViewById(R.id.tv_Month);
        tvValue.setText(value.text);
        tuan = value.tuanX;

        ImageView but_Add_day = (ImageView)view.findViewById(R.id.but_add_new_day);
        but_Add_day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Ngay ngayx = new Ngay(DS_NAM.generateIDLastNgay(),0,new Date(),null,null);

                ngayx.setIDinList_Tuan(tuan.getDsNgay().size());
                ngayx.setIDTuanofDSThang(tuan.getIDinListThang());
                ngayx.setID_Code_Week_in_Server(tuan.getIDServer());
                ngayx.setPoxNgay(null);
                ngayx.setIDObjectOnServer(null);

                String date = "";
                Calendar cal = Calendar.getInstance();
                cal.setTimeZone(TimeZone.getTimeZone("UTC"));
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

                Tree_Node node = new Tree_Node(new IconTreeItemHolder.IconTreeItem(R.string.ic_keyboard_arrow_right,ngayx, date)).setViewHolder(new SelectableHeaderHolder(DS_NAM.getContext().getApplicationContext()));

                ngayx.setNodeNgay(node);

                tuan.getDsNgay().add(ngayx);
                tuan.getNodeTuan().addChild(node);

                ngayx.CapNhatLenServer();

                DS_NAM.gettView().expandNode(ngayx.getNodeNgay().getParent());

            }
        });

        Calendar cal = Calendar.getInstance();
        cal.setTime(value.ngay_Start);
        cal.setTimeZone(TimeZone.getTimeZone("UTC"));
        cal.add(Calendar.HOUR_OF_DAY, 7);

        TextView tv_day_start = (TextView)view.findViewById(R.id.tv_day_Started);
        tv_day_start.setText("Start: " + cal.get(Calendar.DAY_OF_MONTH) + "/" + (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.YEAR));


        cal = Calendar.getInstance();
        cal.setTime(value.ngay_End);
        cal.setTimeZone(TimeZone.getTimeZone("UTC"));
        cal.add(Calendar.HOUR_OF_DAY, 7);

        TextView tv_day_end = (TextView)view.findViewById(R.id.tv_day_End);
        tv_day_end.setText("End: " + cal.get(Calendar.DAY_OF_MONTH) + "/" + (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.YEAR));


        /*final TextView iconView = (TextView) view.findViewById(R.id.icon);
        iconView.setTypeface(Typeface.createFromAsset(context.getAssets(), "material-icon-font.ttf"));
        iconView.setText(context.getResources().getString(value.icon));*/

        return view;
    }

    @Override
    public void toggle(boolean active) {
    }

    @Override
    public int getContainerStyle() {
        return R.style.TreeNodeStyleCustom;
    }
}
