package pis.logixams;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import me.dm7.barcodescanner.zbar.ZBarScannerView;
import pis.logixams.AppPrefs.StudentDataActivity;

public class ScanICardActivity extends Activity implements ZBarScannerView.ResultHandler {

    String EMP_ID;
    EditText EmployeeIdEditText;

    private ZBarScannerView mScannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




        mScannerView =new ZBarScannerView(this);
        setContentView(mScannerView);

    }

    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }

    @Override
    public void handleResult(me.dm7.barcodescanner.zbar.Result result) {

        String scanContent=result.getContents();
        String scanFormat=result.getBarcodeFormat().getName();

       // Toast.makeText(ScanICardActivity.this, scanContent+"\n"+scanFormat, Toast.LENGTH_SHORT).show();



       if(scanContent!=null)
       {
           Intent intent=new Intent(this,StudentDataActivity.class);
           intent.putExtra("BAR_CODE",scanContent);
           startActivity(intent);
       }

    }
}
