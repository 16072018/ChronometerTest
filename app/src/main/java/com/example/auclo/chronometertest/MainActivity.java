package com.example.auclo.chronometertest;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    public static Bundle bundle = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        // the default fragment opens with.
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FirstFragment()).addToBackStack(null).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    selectFragment(item);
                    return true;
                }
            };

    // 뒤로가기 눌렀을 때 이벤트
    @Override
    public void onBackPressed() {

        if (getSupportFragmentManager().getBackStackEntryCount() > 2) {

            getSupportFragmentManager().popBackStack();

        } else {

            super.onBackPressed();

        }

    }

    private void selectFragment(MenuItem item) {

        Fragment selectedFragment = null;

        switch (item.getItemId()) {

            case R.id.nav_first_fragment:
                selectedFragment = new FirstFragment();
                break;
            case R.id.nav_second_fragment:
                selectedFragment = new SecondFragment();
                break;
            case R.id.nav_third_fragment:
                selectedFragment = new ThirdFragment();
                break;

        }

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                selectedFragment).addToBackStack(null).commit();
    }

}

