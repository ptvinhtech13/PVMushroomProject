package com.example.kevin.pvmushroom.Fragment.hover;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.kevin.pvmushroom.Fragment.frag_of_PhieuThu.Tree_Node;
import com.example.kevin.pvmushroom.Object.Ngay;
import com.example.kevin.pvmushroom.Object.Tuan;
import com.example.kevin.pvmushroom.R;


import java.util.Date;

/**
 * Created by kevin on 2/3/2016.
 */
public class IconTreeItemHolder extends Tree_Node.BaseNodeViewHolder<IconTreeItemHolder.IconTreeItem> {
    private TextView tvValue;
    private TextView arrowView;

    public IconTreeItemHolder(Context context) {
        super(context);
    }


    @Override
    public View createNodeView(final Tree_Node node, IconTreeItem value) {
        final LayoutInflater inflater = LayoutInflater.from(context);
        final View view = inflater.inflate(R.layout.frag_info_icon_node, null, false);
        tvValue = (TextView) view.findViewById(R.id.tv_tenKH);
        tvValue.setText(value.text);

        final TextView iconView = (TextView) view.findViewById(R.id.icon);
        iconView.setText(context.getResources().getString(value.icon));



        arrowView = (TextView) view.findViewById(R.id.arrow_icon);
        arrowView.setText(context.getResources().getString(R.string.ic_keyboard_arrow_right));




       /* //if My computer
        if (node.getLevel() == 1) {
            view.findViewById(R.id.btn_delete).setVisibility(View.GONE);
        }*/

        return view;
    }

    @Override
    public void toggle(boolean active) {
        arrowView.setText(context.getResources().getString(active ? R.string.ic_keyboard_arrow_down : R.string.ic_keyboard_arrow_right));
    }

    public static class IconTreeItem {
        public int icon;
        public String text;
        public Date ngay_Start;
        public Date ngay_End;
        public Ngay ngayX = null;

        public IconTreeItem(int icon, String text, Date ngay_End, Date ngay_Start, Tuan tuanX) {
            this.icon = icon;
            this.text = text;
            this.ngay_End = ngay_End;
            this.ngay_Start = ngay_Start;
            this.tuanX = tuanX;
        }

        public IconTreeItem(int icon, Tuan tuanX, String text) {
            this.icon = icon;
            this.tuanX = tuanX;
            this.text = text;
        }

        public Tuan tuanX = null;

        public IconTreeItem(int icon, Ngay ngayX, String text) {
            this.icon = icon;
            this.ngayX = ngayX;
            this.text = text;
        }

        public IconTreeItem(int icon, String text, Tuan tuanX) {
            this.icon = icon;
            this.text = text;
            this.tuanX = tuanX;
        }


        public IconTreeItem(int icon, String text, Date ngay_Start, Date ngay_End) {
            this.icon = icon;
            this.text = text;
            this.ngay_Start = ngay_Start;
            this.ngay_End = ngay_End;
        }


        public IconTreeItem(int icon, String text) {
            this.icon = icon;
            this.text = text;
        }
    }
}
