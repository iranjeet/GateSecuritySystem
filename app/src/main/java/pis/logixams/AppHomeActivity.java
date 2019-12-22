package pis.logixams;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import pis.logixams.AppPrefs.AppPrefs;

public class AppHomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    String E_NAME,E_EMP_ID;

    TextView EmpIdTextView , EmpNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sharedPreferences = getSharedPreferences(AppPrefs.APP_DATA, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        View DrwaerHeaderView= navigationView.getHeaderView(0);


        EmpIdTextView= (TextView) DrwaerHeaderView.findViewById(R.id.emp_id_id);
        EmpNameTextView= (TextView) DrwaerHeaderView.findViewById(R.id.emp_name_id);

        E_EMP_ID=sharedPreferences.getString("EMP_ID","");
        E_NAME=sharedPreferences.getString("E_NAME","");

        EmpIdTextView.setText(E_EMP_ID);
        EmpNameTextView.setText(E_NAME);


        getSupportActionBar().setTitle("Scan I Card");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.BLACK));
        ScanStudent homeFragment = new ScanStudent();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame,homeFragment)
                .addToBackStack(null)
                .commit();



    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.app_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.logout_id)
        {
            editor.putBoolean("LOGIN_STATUS",false);
            editor.commit();
            Intent intent=new Intent(AppHomeActivity.this , LoginActivity.class);
            startActivity(intent);



        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    protected void onResume() {
        super.onResume();

       boolean login_status= sharedPreferences.getBoolean("LOGIN_STATUS",false);

        if(!login_status)
        {
            Intent intent=new Intent(AppHomeActivity.this , LoginActivity.class);
            startActivity(intent);
        }

    }
}
