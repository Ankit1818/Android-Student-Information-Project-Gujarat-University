package com.example.myapplication.AllActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.R;

import java.util.HashMap;
import java.util.Map;

public class complain extends AppCompatActivity {

    EditText ed1,ed2,ed3;
    Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complain);

    ed1=findViewById(R.id.ed1);
        ed2=findViewById(R.id.ed2);
        ed3=findViewById(R.id.ed3);
        send=findViewById(R.id.send);


        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            final String name1=ed1.getText().toString();
            final String mail=ed2.getText().toString();
            final String com=ed3.getText().toString();
            if (name1.length()==0&&mail.length()==0&&com.length()==0)
            {
                ed1.setError("Enter Name");
                ed2.setError("XYZ@mail.com");
                ed3.setError("Enter Your Complain");
            }
            else if (name1.length()==0)
            {
                ed1.setError("Enter Name");
            }
            else if (mail.length()==0)
            {
                ed2.setError("XYZ@mail.com");
            }
            else if (com.length()==0)
            {
                ed3.setError("Enter Your Complain");
            }
            else
            {

                StringRequest request=new StringRequest(Request.Method.POST, "https://ankitgoswami1818.000webhostapp.com/database/complain.php", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        Toast.makeText(complain.this, "Your Compalian Success Fulley Send..", Toast.LENGTH_SHORT).show();
                        Intent i=new Intent(complain.this,dashbord.class);
                        startActivity(i);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(complain.this, "No Internet", Toast.LENGTH_SHORT).show();

                    }
                })
                {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        HashMap map=new HashMap();
                        map.put("name",name1);
                        map.put("email",mail);
                        map.put("complain",com);


                        return map;
                    }
                };

                RequestQueue queue= Volley.newRequestQueue(getApplicationContext());
                queue.add(request);


            }



            }
        });

    }
}
