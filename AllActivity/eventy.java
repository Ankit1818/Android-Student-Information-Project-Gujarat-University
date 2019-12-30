
package com.example.myapplication.AllActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.R;
import com.example.myapplication.model.eventmodel;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class eventy extends AppCompatActivity {

    ListView listView;
    List<eventmodel> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventy);

        list=new ArrayList<>();
        listView=findViewById(R.id.lis12);

        StringRequest request=new StringRequest(Request.Method.GET, "https://ankitgoswami1818.000webhostapp.com/database/event.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray array=new JSONArray(response);
                    for (int i=0;i<array.length();i++)
                    {

                        JSONObject object=array.getJSONObject(i);
                        String name1=object.getString("name");
                        String image1=object.getString("image");
                        String date1=object.getString("date");
                        String time1=object.getString("time");
                        String day=object.getString("day");
                        String vanu=object.getString("vanue");

                        eventmodel model=new eventmodel();
                        model.setImage(image1);
                        model.setVanue(vanu);
                        model.setTime(time1);
                        model.setDay(day);
                        model.setName(name1);
                        model.setDate(date1);

                        list.add(model);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                myAdapter adapter=new myAdapter();
                listView.setAdapter(adapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(eventy.this, "No Internet", Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue queue=Volley.newRequestQueue(getApplicationContext());
        queue.add(request);

    }
    public  class  myAdapter extends BaseAdapter
    {

        @Override
        public int getCount() {
            return  list.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater in=LayoutInflater.from(getApplicationContext());
            convertView=in.inflate(R.layout.designevent,parent,false);


            eventmodel ff=list.get(position);
            ImageView imi=convertView.findViewById(R.id.image1);
            TextView t1=convertView.findViewById(R.id.txe1);
            TextView t2=convertView.findViewById(R.id.txe2);
            TextView t3=convertView.findViewById(R.id.txe3);
            TextView t4=convertView.findViewById(R.id.txe4);
            TextView t5=convertView.findViewById(R.id.txe5);

            t1.setText(ff.getName());
            t2.setText(ff.getDate());
            t3.setText(ff.getTime());
            t4.setText(ff.getDay());
            t5.setText(ff.getVanue());


            Picasso.with(getApplicationContext())
                    .load(ff.getImage())
                    .into(imi);
            return convertView;
        }

    }
}

