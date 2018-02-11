package com.accede.user.adapter;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.accede.user.testapp.R;

import java.util.ArrayList;

public class PoliceControlAdapter extends ArrayAdapter<String>{

    private final Activity context;
    private final String[] web;
    private final String[] imageId;
    public PoliceControlAdapter(Activity context,
                      String[] web, String[] imageId) {
        super(context, R.layout.customlist_police_control, web);
        this.context = context;
        this.web = web;
        this.imageId = imageId;

    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.customlist_police_control, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.textcontrolroom);

        TextView imageView = (TextView) rowView.findViewById(R.id.textphno);
        txtTitle.setText(web[position]);

        imageView.setText(imageId[position]);
        return rowView;
    }
}