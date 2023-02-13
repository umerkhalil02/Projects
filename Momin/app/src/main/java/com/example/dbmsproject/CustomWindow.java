package com.example.dbmsproject;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CustomWindow extends AppCompatDialogFragment {
    String mosqueId, name;
    TextView mosque_name,fajr_time,zuhr_time,asr_time,maghrin_time,isha_time,jumma_time;
    private CustomWindowListener listener;
    public static final String URL = "http://192.168.48.141/db/API_Time.php";

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.custom_info,null);
        mosque_name = view.findViewById(R.id.mosque_name);
        fajr_time = view.findViewById(R.id.fajr_time);
        zuhr_time = view.findViewById(R.id.zuhr_time);
        asr_time = view.findViewById(R.id.asr_time);
        maghrin_time = view.findViewById(R.id.maghrib_time);
        isha_time = view.findViewById(R.id.isha_time);
        jumma_time = view.findViewById(R.id.jumma_time);

        builder.setView(view).setTitle("Timings").setNegativeButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        try {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try{
                                JSONArray mosques = new JSONArray(response);
                                for (int i = 0; i< mosques.length();i++){
                                    JSONObject mosque = mosques.getJSONObject(i);
                                    String mosque_id = mosque.getString("mosqueId") ;
                                    if( mosqueId.equals(mosque_id)){
                                        String ftime = mosque.getString("Fajr");
                                        String ztime = mosque.getString("Zuhr");
                                        String atime = mosque.getString("Asr");
                                        String mtime = mosque.getString("Maghrib");
                                        String itime = mosque.getString("Isha");
                                        String jtime = mosque.getString("Jumma");
                                        listener.applyTexts(mosque_name,name);
                                        listener.applyTexts(fajr_time,ftime);
                                        listener.applyTexts(zuhr_time,ztime);
                                        listener.applyTexts(asr_time,atime);
                                        listener.applyTexts(maghrin_time,mtime);
                                        listener.applyTexts(isha_time,itime);
                                        listener.applyTexts(jumma_time,jtime);
                                        break;
                                    }
                                    else {
                                        Toast.makeText(getContext(),"Error",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            } catch (JSONException e) {
                                Toast.makeText(getContext(),e.toString(),Toast.LENGTH_LONG).show();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getContext(), error.toString().toString(), Toast.LENGTH_LONG).show();
                }
            }){
                @Override
                protected Map<String,String> getParams() throws AuthFailureError {
                    Map<String,String> para = new HashMap<String,String>();
                    para.put("mosqueId",mosqueId);
                    return para;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(getContext());
            requestQueue.add(stringRequest);
        }catch (Exception e){
            Toast.makeText(getContext(),e.toString(),Toast.LENGTH_LONG).show();
        }
        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (CustomWindowListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException((context.toString()+" Must implement CustomWindowListener"));
        }
    }
    public interface CustomWindowListener{
        void applyTexts(TextView textView, String string);
        }
    }



