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

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

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

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignupActivity extends AppCompatActivity {
    private static final String TAG = "SignupActivity";

    @BindView(R.id.input_name)
    EditText _nameText;
    @BindView(R.id.input_username)
    EditText _usernameText;
    @BindView(R.id.input_email)
    EditText _emailText;
    @BindView(R.id.input_mobile)
    EditText _mobileText;
    @BindView(R.id.input_password)
    EditText _passwordText;
    @BindView(R.id.input_reEnterPassword)
    EditText _reEnterPasswordText;
    @BindView(R.id.btn_signup)
    Button _signupButton;
    @BindView(R.id.link_login)
    TextView _loginLink;
    @BindView(R.id.txtResult)
    TextView txtResult;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);

        _signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!_usernameText.getText().toString().equals("")&& !_passwordText.getText().toString().equals("")&& !_reEnterPasswordText.getText().toString().equals("")&& !_nameText.getText().toString().equals("")){
                    if (_passwordText.getText().toString().equals(_reEnterPasswordText.getText().toString())){
                        // memanggil web service
                        //RegisterTask registertask = new RegisterTask();
                        //registertask.execute(edUsername.getText().toString(),edPass.getText().toString(),edName.getText().toString());

                        registerProcess();

                    }else{
                        Toast.makeText(SignupActivity.this,"Password dan Konfirm Password harus sama",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(SignupActivity.this, "Isian tidak boleh kosong", Toast.LENGTH_SHORT).show();
                }
            }
        });

        _loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });
    }

    public void registerProcess(){
        //String url = "http://10.10.75.249:8012/serviceapp/register.php";
        String url = "http://mdpjjlg.000webhostapp.com/register_json.php";
        txtResult.setText("Loading . . .");
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                txtResult.setText(response + "\n");

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int code_res = jsonObject.getInt("code");
                    String message_res = jsonObject.getString("message");

                    txtResult.append(code_res + " - " + message_res);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                txtResult.setText(error.toString());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("username",_usernameText.getText().toString());
                params.put("password",_passwordText.getText().toString());
                params.put("nama",_nameText.getText().toString());
                params.put("telp",_mobileText.getText().toString());
                params.put("email", _emailText.getText().toString());
                return params;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);


    }

    private class RegisterTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            String response ="";
            //NOX  = ip laptop
            //real device = device jd hotspot, laptop konek ke device
            //emulator : 10.0.2.2
            //genmotion ; 10..0.3.3

            try {
                URL url = new URL("http://mdpjjlg.000webhostapp.com/register.php");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                String parameter = "username=" + strings[0] + "&password=" + strings[1]+ "&nama="+ strings[2]+"&email=" + strings[3]+ "&telp=" + strings[4];
                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
                writer.write(parameter);
                writer.flush();
                writer.close();
                os.close();


                int responseCode = conn.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK){
                    String line ="";
                    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    while((line = br.readLine())!= null){
                        response += line;
                    }
                    br.close();
                }
                else{
                    response ="gagal";
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return response;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            txtResult.setText("Loading . . .");
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            txtResult.setText(s);
            Toast.makeText(SignupActivity.this, "Register Berhasil", Toast.LENGTH_SHORT).show();
            Intent halLogin = new Intent(SignupActivity.this,LoginActivity.class
            );
            startActivity(halLogin);
        }

//    public void signup() {
//        Log.d(TAG, "Signup");
//
//        if (!validate()) {
//            onSignupFailed();
//            return;
//        }
//
//        _signupButton.setEnabled(false);
//
//        final ProgressDialog progressDialog = new ProgressDialog(SignupActivity.this,
//                R.style.AppTheme_Dark_Dialog);
//        progressDialog.setIndeterminate(true);
//        progressDialog.setMessage("Creating Account...");
//        progressDialog.show();
//
//        String name = _nameText.getText().toString();
//        String username = _usernameText.getText().toString();
//        String email = _emailText.getText().toString();
//        String mobile = _mobileText.getText().toString();
//        String password = _passwordText.getText().toString();
//        String reEnterPassword = _reEnterPasswordText.getText().toString();
//
//        // TODO: Implement your own signup logic here.
//
//        new android.os.Handler().postDelayed(
//                new Runnable() {
//                    public void run() {
//                        // On complete call either onSignupSuccess or onSignupFailed
//                        // depending on success
//                        onSignupSuccess();
//                        // onSignupFailed();
//                        progressDialog.dismiss();
//                    }
//                }, 3000);
//    }
//
//
//    public void onSignupSuccess() {
//        //  insert dbase
//        RequestParams params = new RequestParams();
//        params.put("name", _nameText.getText().toString());
//        params.put("username", _usernameText.getText().toString());
//        params.put("email", _emailText.getText().toString());
//        params.put("mobile", _mobileText.getText().toString());
//        params.put("password", _passwordText.getText().toString());
//
////        AsyncHttpClient client = new AsyncHttpClient();
////        client.post(getString(R.string.nomerip) + "/register.php",params ,new AsyncHttpResponseHandler() {
////            @Override
////            public void onSuccess(String response) {
////                if(response.equalsIgnoreCase("double")){
////                    Toast.makeText(getApplicationContext(), "Try Another Username Or Email", Toast.LENGTH_SHORT).show();
////                }else if(response.equalsIgnoreCase("sukses")){
////                    Toast.makeText(getApplicationContext(), "Welcome New Member!", Toast.LENGTH_SHORT).show();
////                }
////            }
////            @Override
////            public void onFailure(int statusCode, Throwable error, String content) {
////                Toast.makeText(getApplicationContext(), "Can't Create New Account", Toast.LENGTH_SHORT).show();
////            }
////        });
//
//        _signupButton.setEnabled(true);
//        setResult(RESULT_OK, null);
//        finish();
//    }

//    public void onSignupFailed() {
//        Toast.makeText(getBaseContext(), "Sign Up failed", Toast.LENGTH_LONG).show();
//
//        _signupButton.setEnabled(true);
//    }
//
//    public boolean validate() {
//        boolean valid = true;
//
//        String name = _nameText.getText().toString();
//        String address = _usernameText.getText().toString();
//        String email = _emailText.getText().toString();
//        String mobile = _mobileText.getText().toString();
//        String password = _passwordText.getText().toString();
//        String reEnterPassword = _reEnterPasswordText.getText().toString();
//
//        if (name.isEmpty() || name.length() < 3) {
//            _nameText.setError("at least 3 characters");
//            valid = false;
//        } else {
//            _nameText.setError(null);
//        }
//
//        if (address.isEmpty()) {
//            _usernameText.setError("Enter Valid Username");
//            valid = false;
//        } else {
//            _usernameText.setError(null);
//        }
//
//
//        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
//            _emailText.setError("Enter a valid email address");
//            valid = false;
//        } else {
//            _emailText.setError(null);
//        }
//
//        if (mobile.isEmpty() || mobile.length()!=10) {
//            _mobileText.setError("Enter Valid Mobile Number");
//            valid = false;
//        } else {
//            _mobileText.setError(null);
//        }
//
//        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
//            _passwordText.setError("between 4 and 10 alphanumeric characters");
//            valid = false;
//        } else {
//            _passwordText.setError(null);
//        }
//
//        if (reEnterPassword.isEmpty() || reEnterPassword.length() < 4 || reEnterPassword.length() > 10 || !(reEnterPassword.equals(password))) {
//            _reEnterPasswordText.setError("Password Do not match");
//            valid = false;
//        } else {
//            _reEnterPasswordText.setError(null);
//        }
//
//        return valid;
    }
}