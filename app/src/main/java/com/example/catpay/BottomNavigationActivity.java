package com.example.catpay;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.catpay.ui.dashboard.PayFragment;
import com.example.catpay.ui.home.HomeFragment;
import com.example.catpay.ui.notifications.RechargeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


public class BottomNavigationActivity extends AppCompatActivity {
BottomNavigationView bottomNavigationView;
Fragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottom_navigation);
        bottomNavigationView = findViewById(R.id.nav_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        openFragment(new HomeFragment());
    }

        BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.navigation_home:
                                openFragment(new HomeFragment());
                                return true;
                            case R.id.navigation_recharge:
                                openFragment(new RechargeFragment());
                                return true;
                            case R.id.navigation_pay:
                                openFragment(new PayFragment());
                                return true;
                        }
                        return false;
                    }
                };

    public void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}
