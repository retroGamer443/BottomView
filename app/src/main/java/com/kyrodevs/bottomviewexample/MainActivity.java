package com.kyrodevs.bottomviewexample;


import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.kyrodevs.bottomviewexample.fragments.HomeFragment;
import com.kyrodevs.bottomviewexample.fragments.SearchFragment;
import com.kyrodevs.bottomviewexample.fragments.SettingFragment;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final int homeFragment = 0;
    private static final int searchFragment = 1;
    private static final int settingsFragment = 2;

    private FragmentManager fragMan = getSupportFragmentManager();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        FragmentTransaction tran = fragMan.beginTransaction();
        tran.add(R.id.fragment, new HomeFragment());
        tran.commit();

        BottomView bv = findViewById(R.id.bv);
        bv.setBgColor(Color.WHITE);
        bv.addBitmapStates(new BitmapState(processBitmap(R.drawable.ic_home_unclick),
                processBitmap(R.drawable.ic_home)));
        bv.addBitmapStates(new BitmapState(processBitmap(R.drawable.ic_search_uncheck),
                processBitmap(R.drawable.ic_search)));
        bv.addBitmapStates(new BitmapState(processBitmap(R.drawable.ic_settings_uncheck),
                processBitmap(R.drawable.ic_settings)));

        bv.addBottomViewClickListener(new OnBottomViewClickListener() {
            @Override
            public void onBottomItemClick(int pos) {
                FragmentTransaction ft = fragMan.beginTransaction();
                if (pos == homeFragment){
                    ft.replace(R.id.fragment, new HomeFragment()).commit();
                }
                if (pos == searchFragment) {
                    ft.replace(R.id.fragment, new SearchFragment()).commit();
                }
                if(pos == settingsFragment) {
                    ft.replace(R.id.fragment, new SettingFragment()).commit();
                }
            }
        });

    }

    public Bitmap processBitmap(int id) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), id);
        final int width = (int) Utility.dpToPixel(this, 24);
        final int height = (int) Utility.dpToPixel(this, 24);
        return Bitmap.createScaledBitmap(bitmap, width, height, false);
    }

}
