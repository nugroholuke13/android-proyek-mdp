package com.example.janad.navigation;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
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

import java.util.HashMap;
import java.util.Map;


public class AddDokterActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    Button btnsub;
    TextView txtNama,txtUsername,txtAlamat,txtKota,txttelp1,txttelp2,txtnoijin,txtload;
    Spinner txtspesial;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dokter);

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
                        Intent intent1 = new Intent(AddDokterActivity.this, MainActivity.class);
                        startActivity(intent1);
                        return true;
                    case R.id.navigation2:
                        Intent intent2 = new Intent(AddDokterActivity.this, AddDokterActivity.class);
                        startActivity(intent2);
                        return true;
                    case R.id.navigation3:
                        Intent intent3 = new Intent(AddDokterActivity.this, VerifikasiDokterActivity.class);
                        startActivity(intent3);
                        return true;
                    case R.id.navigation4:
                        Intent intent4 = new Intent(AddDokterActivity.this, ListDokterActivity.class);
                        startActivity(intent4);
                        return true;
                    case R.id.navigation5:
                        Intent intent5 = new Intent(AddDokterActivity.this, AddSpesialisActivity.class);
                        startActivity(intent5);
                        return true;
                    case R.id.navigation6:
                        Intent intent6 = new Intent(AddDokterActivity.this, ListDokterActivity.class);
                        startActivity(intent6);
                        return true;
                    case R.id.navigation7:
                        Intent intent7 = new Intent(AddDokterActivity.this, MainActivity.class);
                        startActivity(intent7);
                        return true;
                    case R.id.navigation8:
                        Intent intent8 = new Intent(AddDokterActivity.this, MainActivity.class);
                        startActivity(intent8);
                        return true;
                    case R.id.navigation9:
                        Intent intent9 = new Intent(AddDokterActivity.this, LoginActivity.class);
                        startActivity(intent9);
                        return true;
                    default:
                        Toast.makeText(getApplicationContext(),"Kesalahan Terjadi ",Toast.LENGTH_SHORT).show();
                        return true;
                }
            }
        });
        // Menginisasi Drawer Layout dan ActionBarToggle
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer1);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(AddDokterActivity.this,drawerLayout,toolbar,R.string.openDrawer, R.string.closeDrawer){
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

        btnsub = findViewById(R.id.btn_add_dokter);
        txtNama = findViewById(R.id.input_name);
        txtUsername = findViewById(R.id.input_username);
        txtAlamat = findViewById(R.id.input_alamat);
        txtKota = findViewById(R.id.input_kota);
        txttelp1 = findViewById(R.id.input_mobile);
        txttelp2 = findViewById(R.id.input_mobile2);
        txtspesial = findViewById(R.id.spinner3);
        txtnoijin = findViewById(R.id.input_nomer_siup);
        txtload = findViewById(R.id.txtload99);
        btnsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDokter();
            }
        });

    }

    public void addDokter(){
        //String url = "http://10.10.75.249:8012/serviceapp/register.php";
        String url = "http://mdpjjlg.000webhostapp.com/adddokter_json.php";
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
                params.put("nama",txtNama.getText().toString());
                params.put("username",txtUsername.getText().toString());
                params.put("alamat",txtAlamat.getText().toString());
                params.put("kota",txtKota.getText().toString());
                params.put("telp1",txttelp1.getText().toString());
                params.put("telp2",txttelp2.getText().toString());
                params.put("ijin",txtnoijin.getText().toString());  
                return params;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);
    }

    @Override
    public void onBackPressed() {
        // Disable going back to the MainActivity
        moveTaskToBack(true);
    }


}
