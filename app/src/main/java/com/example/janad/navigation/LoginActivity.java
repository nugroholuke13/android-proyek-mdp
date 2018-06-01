package com.example.janad.navigation;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;

    @BindView(R.id.input_email)
    EditText _emailText;
    @BindView(R.id.input_password)
    EditText _passwordText;
    @BindView(R.id.btn_login)
    Button _loginButton;
    @BindView(R.id.link_signup)
    TextView _signupLink;
    @BindView(R.id.stat)
    TextView stat;
    String jenis = "";
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        
        _loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(_emailText.getText().toString().equals("") && _passwordText.getText().toString().equals("")){
                    //Toast.makeText(MainActivity.this,"Username dan Password tidak boleh kosong !",Toast.LENGTH_SHORT).show();
                    Toast.makeText(LoginActivity.this, "Username dan Password Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
                }
                else if(_emailText.getText().toString().equals("admin") && _passwordText.getText().toString().equals("admin")) {
                    Intent pindah = new Intent(LoginActivity.this,VerifikasiDokterActivity.class);
                    startActivity(pindah);
                }
                else{
                    LoginTask logintask = new LoginTask();
                    logintask.execute(_emailText.getText().toString(), _passwordText.getText().toString());
                }
            }
        });

        _signupLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
                finish();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });
    }

    private class LoginTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings){
            String response = "";
            try {
                URL url = new URL("http://192.168.43.9:8012/webservice/login.php");//ip
                HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                conn.setRequestMethod("POST");
                String parameter = "username=" + strings[0] + "&password=" + strings[1];
                OutputStream outputStream = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                writer.write(parameter);
                writer.flush();
                writer.close();
                outputStream.close();

                int responseCode = conn.getResponseCode();
                if(responseCode == HttpURLConnection.HTTP_OK){
                    String line = "";

                    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    while ((line = reader.readLine())!=null){
                        response += line;
                    }
                    reader.close();
                    if (jenis.equals("dokter")) {
                        Intent pindah = new Intent(LoginActivity.this,MainActivity.class);
                        startActivity(pindah);
                    } else if (jenis.equals("pasien")) {
                        Intent pindah = new Intent(LoginActivity.this,HomePasienActivity.class);
                        startActivity(pindah);
                    }

                }else{
                    response = "Gagal";
                }
            }catch (MalformedURLException e){
                e.printStackTrace();
            }catch (IOException e){
                e.printStackTrace();
            }
            return response;
        }

        @Override
        protected  void onPreExecute(){
            super.onPreExecute();
            stat.setText("Loading...");
        }

        @Override
        protected void onPostExecute(String s){
            super.onPostExecute(s);
            stat.setText(s);
            //Toast.makeText(LoginActivity.this, "Login Berhasil", Toast.LENGTH_SHORT).show();
        }
    }
}
