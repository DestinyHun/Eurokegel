package com.example.win_10.eurokegel;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

class SpinnerAdapter extends ArrayAdapter<String> {
    Context context;
    String[] items = new String[] {};

    public SpinnerAdapter(final Context context,
                          final int textViewResourceId, final String[] objects) {
        super(context, textViewResourceId, objects);
        this.items = objects;
        this.context = context;
    }

    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(
                    android.R.layout.simple_spinner_item, parent, false);
        }

        TextView tv = (TextView) convertView
                .findViewById(android.R.id.text1);
        tv.setText(items[position]);
        tv.setTextColor(Color.BLACK);
        tv.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
        tv.setTextSize(30);
        return convertView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(
                    android.R.layout.simple_spinner_item, parent, false);
        }

        // android.R.id.text1 is default text view in resource of the android.
        // android.R.layout.simple_spinner_item is default layout in resources of android.

        TextView tv = (TextView) convertView
                .findViewById(android.R.id.text1);
        tv.setText(items[position]);
        tv.setTextColor(Color.BLACK);
        tv.setTextSize(Constants.DefaultTextSizeLarge);
        return convertView;
    }
}