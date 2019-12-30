package com.example.myapplication.AllActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
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
import com.example.myapplication.model.facultymodel;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class faculty extends AppCompatActivity {

    ListView listView;
    Context context;
    List<facultymodel> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty);
        listView=findViewById(R.id.lis1);
        list=new ArrayList<>();
        StringRequest request=new StringRequest(Request.Method.GET, "https://ankitgoswami1818.000webhostapp.com/database/facultyview.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray array=new JSONArray(response);
                    for (int i=0;i<array.length();i++)
                    {

                        JSONObject object=array.getJSONObject(i);
                        String image1=object.getString("image");
                        String name1=object.getString("name");
                        String course1=object.getString("course");
                        String sub1=object.getString("sub");

                        facultymodel model=new facultymodel();
                        model.setName(name1);
                        model.setCourse(course1);
                        model.setSubject(sub1);
                        model.setImage(image1);

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

                Toast.makeText(faculty.this, "No Internet", Toast.LENGTH_SHORT).show();
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
        convertView=in.inflate(R.layout.designfaculty,parent,false);
        ImageView img=convertView.findViewById(R.id.im1);
        TextView tex1=convertView.findViewById(R.id.txt1);
        TextView tex2=convertView.findViewById(R.id.txt2);
        TextView tex3=convertView.findViewById(R.id.txt3);

        facultymodel ff=list.get(position);
        tex1.setText(ff.getName());
        tex2.setText(ff.getCourse());
        tex3.setText(ff.getSubject());
        Picasso.with(getApplicationContext())
                .load(ff.getImage())
                .into(img);
        return convertView;
    }
}
}

