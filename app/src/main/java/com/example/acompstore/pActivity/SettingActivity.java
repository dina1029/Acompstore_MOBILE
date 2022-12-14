package com.example.acompstore.pActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.acompstore.R;
import com.example.acompstore.pFragment.HistoryFragment;
import com.example.acompstore.pFragment.HomeFragment;
import com.example.acompstore.pFragment.NotificationFragment;
import com.example.acompstore.pFragment.SettingFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class SettingActivity extends AppCompatActivity {

    BottomNavigationView btNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        getSupportFragmentManager().beginTransaction().replace(R.id.setting_frmlayout, new SettingFragment()).commit();

        btNav = findViewById(R.id.home_btnav);
        btNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selected = null;
                switch (item.getItemId()) {
                    case R.id.menuhome_home:
                        selected = new HomeFragment();
                        break;
                    case R.id.menuhome_history:
                        selected = new HistoryFragment();
                        break;
                    case R.id.menuhome_notification:
                        selected = new NotificationFragment();
                        break;
                    case R.id.menuhome_setting:
                        selected = new SettingFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.setting_frmlayout, selected).commit();
                return true;
            }
        });
    }
}