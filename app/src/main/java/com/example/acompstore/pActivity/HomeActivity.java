package com.example.acompstore.pActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.acompstore.R;
import com.example.acompstore.pFragment.HistoryFragment;
import com.example.acompstore.pFragment.HomeFragment;
import com.example.acompstore.pFragment.NotificationFragment;
import com.example.acompstore.pFragment.SettingFragment;
import com.example.acompstore.pModel.ModelPembeliAlamat;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class HomeActivity extends AppCompatActivity {

    BottomNavigationView btNav;
    Fragment selected = null;
    SharedPreferences shared;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        btNav = findViewById(R.id.home_btnav);
        shared = getSharedPreferences("myapp-data", MODE_PRIVATE);
        SharedPreferences.Editor editor = shared.edit();
        getSupportFragmentManager().beginTransaction().replace(R.id.home_frmlayout, new HomeFragment()).commit();

        btNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menuhome_home:

                        editor.putInt("homeId", 1);
                        editor.commit();
                        selected = new HomeFragment();
                        break;
                    case R.id.menuhome_history:
                        editor.putInt("homeId", 2);
                        editor.commit();
                        selected = new HistoryFragment();
                        break;
                    case R.id.menuhome_notification:
                        editor.putInt("homeId", 3);
                        editor.commit();
                        selected = new NotificationFragment();
                        break;
                    case R.id.menuhome_setting:
                        editor.putInt("homeId", 4);
                        editor.commit();
                        selected = new SettingFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.home_frmlayout, selected, "coba").commit();

                return true;
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
//        Toast.makeText(this, "start", Toast.LENGTH_SHORT).show();
//                Fragment frg = null;
//        frg = getSupportFragmentManager().findFragmentByTag("coba");
//        if (frg==null){
//            Toast.makeText(this, "tidak", Toast.LENGTH_SHORT).show();
//        }else{
//            Toast.makeText(this, "ada", Toast.LENGTH_SHORT).show();
//        }
//        final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//        ft.detach(frg);
//        ft.attach(frg);
//        ft.commit();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
//        Toast.makeText(this, "restart", Toast.LENGTH_SHORT).show();
//        getSupportFragmentManager().beginTransaction().detach(HomeFragment)
//        Fragment f = getSupportFragmentManager().findFragmentById(R.id.home_frmlayout);
//        if (f instanceof HomeFragment) {
//            // doSomething() is a public method of FragmentA;
//            ((HomeFragment)f).
//        }
//        Fragment frg = null;
//        frg = getSupportFragmentManager().findFragmentByTag("coba");
//        if (frg==null){
//            Toast.makeText(this, "tidak", Toast.LENGTH_SHORT).show();
//        }else{
//            Toast.makeText(this, "ada", Toast.LENGTH_SHORT).show();
//        }
//        final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//        ft.detach(frg);
//        ft.attach(frg);
//        ft.commit();
//        View view2 = (BottomNavigationView)
//        shared = getSharedPreferences("myapp-data", MODE_PRIVATE);
//        SharedPreferences.Editor editor = shared.edit();
//        int checkKode = shared.getInt("homeId", 1);
//        switch (checkKode) {
//            case 1:
////                getSupportFragmentManager().beginTransaction().replace(R.id.home_frmlayout, new HomeFragment()).commit();
//                break;
//            case 2:
////                getSupportFragmentManager().beginTransaction().replace(R.id.home_frmlayout, new HistoryFragment()).commit();
//                break;
//        }
//        Toast.makeText(this, "" + checkKode, Toast.LENGTH_SHORT).show();
//        getSupportFragmentManager().beginTransaction().replace(R.id.home_frmlayout, selected).commit();
//        finish();
//        startActivity(getIntent());
//        getSupportFragmentManager().beginTransaction().replace(R.id.home_frmlayout, new HomeFragment()).commit();

    }
}