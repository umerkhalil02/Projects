package com.example.dbmsproject;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<Mosque> {
    List<Mosque> mosque;
    Context context;

    public CustomAdapter(@NonNull Context context, List<Mosque> mosque) {
        super(context,R.layout.layout_listview,mosque);
        this.context = context;
        this.mosque = mosque;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listview,null,true);
        TextView mos_name = view.findViewById(R.id.text_mos_name);
        TextView mos_address = view.findViewById(R.id.text_mos_address);
        mos_name.setText(mosque.get(position).name);
        mos_address.setText(mosque.get(position).address);
        return view;
    }

}
