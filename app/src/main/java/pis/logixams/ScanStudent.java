package pis.logixams;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class ScanStudent extends Fragment {



Button LaunchBarCodeSacnner;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView=inflater.inflate(R.layout.fragment_scan_student, container, false);


        LaunchBarCodeSacnner= (Button) rootView.findViewById(R.id.launch_scanner_btn_id);



        LaunchBarCodeSacnner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent=new Intent(getActivity() , ScanICardActivity.class);

                getActivity().startActivity(intent);

            }
        });




        return rootView;
    }

}
