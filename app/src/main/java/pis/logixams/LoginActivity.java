package pis.logixams;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import pis.logixams.AppPrefs.AppPrefs;

public class LoginActivity extends AppCompatActivity {
    EditText UserIdEditText, PassowrdEditText;
    Button LoginButton;
    String USER_ID, PASSWORD;
    ProgressDialog progressDialog;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();

        sharedPreferences = getSharedPreferences(AppPrefs.APP_DATA, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        UserIdEditText = (EditText) findViewById(R.id.login_user_id);
        PassowrdEditText = (EditText) findViewById(R.id.login_password_id);
        LoginButton = (Button) findViewById(R.id.login_btn_id);

        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                USER_ID = UserIdEditText.getText().toString();
                PASSWORD = PassowrdEditText.getText().toString();

                if (USER_ID.equals("") || PASSWORD.equals("")) {
                    Toast.makeText(LoginActivity.this, "Please enter user id and password", Toast.LENGTH_SHORT).show();
                } else {

                    new EmpLoginTask().execute(USER_ID,PASSWORD);
                }

            }
        });


    }

    class EmpLoginTask extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(LoginActivity.this);
            progressDialog.setCancelable(false);
            progressDialog.setMessage("Please Wait...");
            progressDialog.show();


        }

        @Override
        protected String doInBackground(String... strings) {

            String RES = null;
            try {
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(AppPrefs.BASE_URL + "login_validation.php");

                ArrayList<NameValuePair> DataList = new ArrayList<>();
                DataList.add(new BasicNameValuePair("empid", strings[0]));
                DataList.add(new BasicNameValuePair("password", strings[1]));

                httpPost.setEntity(new UrlEncodedFormEntity(DataList));
                HttpEntity httpEntity = httpclient.execute(httpPost).getEntity();
                RES = EntityUtils.toString(httpEntity);
            } catch (Exception e) {
                RES = e.toString();

            }


            return RES;
        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            progressDialog.dismiss();


            if (s != null) {

                if (s.equals("0"))
                {
                    Toast.makeText(LoginActivity.this, "Invalid access", Toast.LENGTH_SHORT).show();
                } else if (s.length() > 4)
                {

                    try {


                        JSONArray jsonArray = new JSONArray(s);

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject TempjsonObject = jsonArray.getJSONObject(i);

                            String LOGIN_STATUS = TempjsonObject.getString("login_status");


                            if (LOGIN_STATUS.equals("1"))
                            {


                                editor.putBoolean("LOGIN_STATUS",true);
                                editor.putString("EMP_ID", TempjsonObject.getString("empid"));
                                editor.putString("E_NAME", TempjsonObject.getString("ename"));
                                editor.putString("E_POST", TempjsonObject.getString("post"));
                                editor.putString("E_SALAY", TempjsonObject.getString("salary"));
                                editor.putString("E_FNAME", TempjsonObject.getString("fname"));
                                editor.putString("E_MOBILE", TempjsonObject.getString("mobile"));
                                editor.putString("E_GENDER", TempjsonObject.getString("gender"));
                                editor.putString("E_EAMIL", TempjsonObject.getString("email"));

                                editor.putString("E_ADDRESS", TempjsonObject.getString("address"));
                                editor.putString("E_IMEI", TempjsonObject.getString("imeno"));
                                editor.putString("E_PASSWORD", TempjsonObject.getString("password"));

                                editor.commit();

                                Intent intent = new Intent(LoginActivity.this, AppHomeActivity.class);
                                startActivity(intent);

                            }


                        }


                    } catch (Exception e)
                    {
                        Intent intent = new Intent(LoginActivity.this, LoginActivity.class);
                        startActivity(intent);

                        Toast.makeText(LoginActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                    }


                } else
                {
                    Toast.makeText(LoginActivity.this, "Invalid Access", Toast.LENGTH_SHORT).show();
                }

            }


        }

    }
}
