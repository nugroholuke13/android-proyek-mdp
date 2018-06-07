package com.example.janad.navigation;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class jadwalLiburDokter extends AppCompatActivity {
    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    Button btnSubmitLibur;
    TextView txtLibur,txtload;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal_libur_dokter);
        txtload = findViewById(R.id.textView8);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Menginisiasi  NavigationView
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        //Mengatur Navigasi View Item yang akan dipanggil untuk menangani item klik menu navigasi
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                //Memeriksa apakah item tersebut dalam keadaan dicek  atau tidak,
                if(menuItem.isChecked()) menuItem.setChecked(false);
                else menuItem.setChecked(true);
                //Menutup  drawer item klik
                drawerLayout.closeDrawers();
                //Memeriksa untuk melihat item yang akan dilklik dan melalukan aksi
                switch (menuItem.getItemId()){
                    // pilihan menu item navigasi akan menampilkan pesan toast klik kalian bisa menggantinya
                    //dengan intent activity
                    case R.id.navigation1:
                        Intent intent1 = new Intent(jadwalLiburDokter.this, HomeDokterActivity.class);
                        startActivity(intent1);
                        return true;
                    case R.id.navigation2:
                        Intent intent2 = new Intent(jadwalLiburDokter.this, jadwalDokter.class);
                        startActivity(intent2);
                        return true;
                    case R.id.navigation7:
                        Intent intent3 = new Intent(jadwalLiburDokter.this, jadwalLiburDokter.class);
                        startActivity(intent3);
                        return true;
                    case R.id.navigation9:
                        Intent intent4 = new Intent(jadwalLiburDokter.this, pembatalan.class);
                        startActivity(intent4);
                        return true;
                    case R.id.navigation3:
                        Intent intent5 = new Intent(jadwalLiburDokter.this, jadwalLive.class);
                        startActivity(intent5);
                        return true;
                    case R.id.navigation8:
                        Intent intent6 = new Intent(jadwalLiburDokter.this, jadwalOffline.class);
                        startActivity(intent6);
                        return true;
                    case R.id.navigation4:
                        Intent intent7 = new Intent(jadwalLiburDokter.this, HomeDokterActivity.class);
                        startActivity(intent7);
                        return true;
                    case R.id.navigation5:
                        Intent intent8 = new Intent(jadwalLiburDokter.this, HomeDokterActivity.class);
                        startActivity(intent8);
                        return true;
                    case R.id.navigation6:
                        Intent intent9 = new Intent(jadwalLiburDokter.this, LoginActivity.class);
                        startActivity(intent9);
                        return true;
                    default:
                        Toast.makeText(getApplicationContext(),"Kesalahan Terjadi ",Toast.LENGTH_SHORT).show();
                        return true;
                }
            }
        });
        // Menginisasi Drawer Layout dan ActionBarToggle
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.openDrawer, R.string.closeDrawer){
            @Override
            public void onDrawerClosed(View drawerView) {
                // Kode di sini akan merespons setelah drawer menutup disini kita biarkan kosong
                super.onDrawerClosed(drawerView);
            }
            @Override
            public void onDrawerOpened(View drawerView) {
                //  Kode di sini akan merespons setelah drawer terbuka disini kita biarkan kosong
                super.onDrawerOpened(drawerView);
            }
        };
        //Mensetting actionbarToggle untuk drawer layout
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        //memanggil synstate
        actionBarDrawerToggle.syncState();

        btnSubmitLibur = findViewById(R.id.btnSubmitLibur);
        txtLibur = findViewById(R.id.txtHariLibur);

        btnSubmitLibur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                liburDokter();
            }
        });

    }

    public void liburDokter(){
        //String url = "http://10.10.75.249:8012/serviceapp/register.php";
        String url = "http://mdpjjlg.000webhostapp.com/liburdokter_json.php";
        txtload.setText("Loading . . .");
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                txtload.setText(response + "\n");

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code_res = jsonObject.getInt("code");
                    String message_res = jsonObject.getString("message");

                    txtload.append(code_res + " - " + message_res);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                txtload.setText(error.toString());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("harilibur",txtLibur.getText().toString());
                return params;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        // Disable going back to the MainActivity
        moveTaskToBack(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
