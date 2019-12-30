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
import com.example.myapplication.model.Devmodel;
import com.example.myapplication.R;
import com.example.myapplication.model.facultymodel;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class devloper extends AppCompatActivity {
    ListView listView;
    List<Devmodel> list;
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devloper);

    list=new ArrayList<>();
    listView=findViewById(R.id.lii1);

      StringRequest request=new StringRequest(Request.Method.GET, "https://ankitgoswami1818.000webhostapp.com/database/devloperview.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray array=new JSONArray(response);
                    for (i=0;i<=array.length();i++)
                    {
                        JSONObject object=array.getJSONObject(i);
                        String name1=object.getString("name");
                        String add=object.getString("address");
                        String mai=object.getString("email");
                        String ima=object.getString("image");

                        Devmodel devmodel=new Devmodel();
                        devmodel.setName(name1);
                        devmodel.setEmail(mai);
                        devmodel.setAddress(add);
                        devmodel.setImage(ima);
                        list.add(devmodel);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                myAdaptere adaptere=new myAdaptere();
                listView.setAdapter(adaptere);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(devloper.this, "No Internet", Toast.LENGTH_SHORT).show();

            }
        });

        RequestQueue queue= Volley.newRequestQueue(getApplicationContext());
        queue.add(request);

    }
    class  myAdaptere extends BaseAdapter
    {

        @Override
        public int getCount() {
            return list.size();
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
            convertView=in.inflate(R.layout.devdesign,parent,false);
            ImageView img=convertView.findViewById(R.id.devimage);
            TextView tex1=convertView.findViewById(R.id.text01);
            TextView tex2=convertView.findViewById(R.id.text02);
            TextView tex3=convertView.findViewById(R.id.text03);

            Devmodel ff=list.get(position);
            tex1.setText(ff.getName());
            tex2.setText(ff.getAddress());
            tex3.setText(ff.getEmail());
            Picasso.with(getApplicationContext())
                    .load(ff.getImage())
                    .into(img);

            return convertView;
        }
    }
    }

