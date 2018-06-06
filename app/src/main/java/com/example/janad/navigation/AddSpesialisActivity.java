package com.example.janad.navigation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AddSpesialisActivity extends AppCompatActivity {

    Button btn;
    TextView txtnama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_spesialis);

        btn = findViewById(R.id.btn_spesial);
        txtnama = findViewById(R.id.input_name);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tambahDokter();
            }
        });
    }

    public void tambahDokter() {
        String url = "http://mdpjjlg.000webhostapp.com/add_spesialis.php";
        //txtResult.setText("Loading . . .");
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //txtResult.setText(response + "\n");

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code_res = jsonObject.getInt("code");
                    String message_res = jsonObject.getString("message");

                    //txtResult.append(code_res + " - " + message_res);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //txtResult.setText(error.toString());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("namaSpesialis",txtnama.getText().toString());
                return params;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);
    }
}
