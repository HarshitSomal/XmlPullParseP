package com.assignment.materialdesign.xmlpullparsep;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YOUHAI on 1/25/2016.
 */
public class ListAdapter extends ArrayAdapter<String> {
    List<String> list = new ArrayList<>();
    public ListAdapter(Context context, int resource, List<String> strings) {
        super(context, resource, strings);
        list = strings;
    }

    @Override
    public int getCount() {
        return list.size();
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return super.getView(position, convertView, parent);
    }
}

