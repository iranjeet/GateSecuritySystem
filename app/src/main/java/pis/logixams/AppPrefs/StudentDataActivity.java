package pis.logixams.AppPrefs;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import pis.logixams.AppHomeActivity;
import pis.logixams.InAttendenceActivity;
import pis.logixams.R;

public class StudentDataActivity extends AppCompatActivity {


    ProgressDialog progressDialog;


    ListView StudentDataListView;
    TextView StudentNameTextView;

    ArrayList<String> StudentDataArrayList;

    ArrayAdapter<String> arrayAdapter;
    Button InAttendenceButton , OutAttendenceButton;

    SharedPreferences sharedPreferences;

    String ROLL_NUMBER=null;
    String EMP_ID=null;
    Bitmap STUDENT_PIC=null;
    private static final int CAMERA_REQUEST = 1888;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_data);

        sharedPreferences=getSharedPreferences(AppPrefs.APP_DATA,MODE_PRIVATE);

        StudentNameTextView= (TextView) findViewById(R.id.student_name_id);
        StudentDataListView=(ListView) findViewById(R.id.student_data_list_id);
        InAttendenceButton= (Button) findViewById(R.id.in_attendence_btn);
        OutAttendenceButton=(Button) findViewById(R.id.out_attendence_btn);


        Bundle bundle = getIntent().getExtras();
        String BAR_CODE = bundle.getString("BAR_CODE");

        new FetchStudentDetails().execute(BAR_CODE);

        InAttendenceButton.setVisibility(View.GONE);
        OutAttendenceButton.setVisibility(View.GONE);




    }


    class FetchStudentDetails extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = new ProgressDialog(StudentDataActivity.this);
            progressDialog.setMessage("Please wait...");
            progressDialog.setCancelable(false);
            progressDialog.show();

            StudentDataArrayList=new ArrayList<>();
        }

        @Override
        protected String doInBackground(String... params) {

            String SERVER_RES = null;



            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(AppPrefs.BASE_URL+"fetch_student_data.php");
            ArrayList<NameValuePair> dataList = new ArrayList<NameValuePair>();
            dataList.add(new BasicNameValuePair("barcode", params[0]));

            try {
                httpPost.setEntity(new UrlEncodedFormEntity(dataList));
                HttpResponse httpResponse = httpClient.execute(httpPost);
                HttpEntity httpEntity = httpResponse.getEntity();
                SERVER_RES = EntityUtils.toString(httpEntity);
            } catch (Exception e) {
                Log.d("SERVER_EXP", e.toString());
            }
            return SERVER_RES;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Log.d("SERVER_RES", s);

            progressDialog.dismiss();



            if (s == null) {
                Toast.makeText(StudentDataActivity.this, "Try again", Toast.LENGTH_LONG).show();
            } else {
                try {
                    JSONArray jsonArray = new JSONArray(s);
                    JSONObject jsonObject;

                    for (int i = 0; i < jsonArray.length(); i++) {
                        jsonObject = jsonArray.getJSONObject(i);

                        StudentNameTextView.setText(jsonObject.getString("name"));

                        StudentDataArrayList.add("Roll number: "+jsonObject.getString("rollno"));
                        StudentDataArrayList.add("Name : "+jsonObject.getString("name"));


                        arrayAdapter=new ArrayAdapter<String>(StudentDataActivity.this , android.R.layout.simple_list_item_1 , StudentDataArrayList);

                        StudentDataListView.setAdapter(arrayAdapter);

                        ROLL_NUMBER=jsonObject.getString("rollno");
                        EMP_ID=sharedPreferences.getString("EMP_ID","");





                    }

                    if(!ROLL_NUMBER.equals("")||ROLL_NUMBER!=null)
                    {
                        InAttendenceButton.setVisibility(View.VISIBLE);
                        InAttendenceButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                /*Intent intent=new Intent(StudentDataActivity.this , InAttendenceActivity.class);
                                intent.putExtra("EMP_ID",EMP_ID);
                                intent.putExtra("ROLL_NUMBER",ROLL_NUMBER);
                                startActivity(intent);

                                new MarkAttendenceTask().execute(EMP_ID,ROLL_NUMBER,"in");*/

                                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                                startActivityForResult(cameraIntent, CAMERA_REQUEST);

                            }
                        });

                        OutAttendenceButton.setVisibility(View.VISIBLE);
                        OutAttendenceButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {



                                new MarkAttendenceTask().execute(EMP_ID,ROLL_NUMBER,"out");
                            }
                        });
                    }


                } catch (Exception e) {

                }

            }

            //Toast.makeText(DocetHomeActivity.this , s, Toast.LENGTH_LONG).show();


        }
    }

    class MarkAttendenceTask extends AsyncTask<String , Void , String>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = new ProgressDialog(StudentDataActivity.this);
            progressDialog.setMessage("Please wait...");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {

            String SERVER_RES = null;
            String BASE64IMAGE=null;

            if(params[2].equals("in"))
            {
                BASE64IMAGE = convertImageIntoString(STUDENT_PIC);
            }
            else if(params[2].equals("out"))
            {
               STUDENT_PIC= BitmapFactory.decodeResource(getResources() , R.mipmap.ic_launcher);
                BASE64IMAGE= convertImageIntoString(STUDENT_PIC);
            }




            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(AppPrefs.BASE_URL+"mark_attendence.php");
            ArrayList<NameValuePair> dataList = new ArrayList<NameValuePair>();
            dataList.add(new BasicNameValuePair("emp_id", params[0]));
            dataList.add(new BasicNameValuePair("roll_no", params[1]));
            dataList.add(new BasicNameValuePair("type", params[2]));
            dataList.add(new BasicNameValuePair("pic_name", String.valueOf(System.currentTimeMillis())));
            dataList.add(new BasicNameValuePair("student_image", BASE64IMAGE));

            try {
                httpPost.setEntity(new UrlEncodedFormEntity(dataList));
                HttpResponse httpResponse = httpClient.execute(httpPost);
                HttpEntity httpEntity = httpResponse.getEntity();
                SERVER_RES = EntityUtils.toString(httpEntity);
            } catch (Exception e) {
                Log.d("SERVER_EXP", e.toString());
            }
            return SERVER_RES;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            progressDialog.dismiss();

            if (s.equals("1"))
            {
                AlertDialog alrtbldr=new AlertDialog.Builder(StudentDataActivity.this).create();
                alrtbldr.setTitle("Entry Status");
                alrtbldr.setMessage("Success");
                alrtbldr.setButton("OK", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {

                        Intent  intent=new Intent(StudentDataActivity.this , AppHomeActivity.class);
                        startActivity(intent);

                    }
                });
                alrtbldr.show();
            }

            else
            {
               //
            }

            Toast.makeText(StudentDataActivity.this, s, Toast.LENGTH_SHORT).show();
        }
    }



    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            STUDENT_PIC = (Bitmap) data.getExtras().get("data");


            if(STUDENT_PIC!=null)
            {
                new MarkAttendenceTask().execute(EMP_ID,ROLL_NUMBER,"in");

            }


        }
    }

    public String convertImageIntoString(Bitmap img) {
        ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        img.compress(Bitmap.CompressFormat.JPEG, 100, arrayOutputStream);
        String encodedimg = Base64.encodeToString(arrayOutputStream.toByteArray(), Base64.DEFAULT);
        return encodedimg;
    }

}

