package com.example.kevin.pvmushroom.Adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.kevin.pvmushroom.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by kevin on 1/22/2016.
 */
public class ExpandableListAdapter_PT extends BaseExpandableListAdapter {

    private Context _context;
    private List<String> _listDataHeader;
    private HashMap<String, List<String>> _listDataChild;
    HashMap<String, HashMap<String, List<String>>> tapDulieu;
    ArrayList<String> Lheaders;

    public ExpandableListAdapter_PT(Context context, List<String> listDataHeader,
                                    HashMap<String, List<String>> listChildData) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
        prepareData();

    }

    public void prepareData()
    {
        tapDulieu = new HashMap<String, HashMap<String, List<String>>>();

        Lheaders = new ArrayList<>();
        Lheaders.add("1234");
        Lheaders.add("4567");
        Lheaders.add("3213");
        Lheaders.add("6756");

        tapDulieu.put(Lheaders.get(0),_listDataChild);
        tapDulieu.put(Lheaders.get(1),_listDataChild);

    }
    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.explist_group_phieu_thu, null);
        }

        Typeface tf = Typeface.createFromAsset(convertView.getContext().getAssets(),"SanFranciscoDisplay-Light.otf");

        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.lblListHeader_PT);
        lblListHeader.setTypeface(tf, Typeface.BOLD);
        lblListHeader.setText(headerTitle);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final String childText = (String) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.explist_item_phieu_thu, null);
        }

        Typeface tf = Typeface.createFromAsset(convertView.getContext().getAssets(),"SanFranciscoDisplay-Light.otf");


        TextView txtListChild = (TextView) convertView
                .findViewById(R.id.lblListItem_PT);
        txtListChild.setText(childText);
        txtListChild.setTypeface(tf);

        return convertView;
    }



    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
