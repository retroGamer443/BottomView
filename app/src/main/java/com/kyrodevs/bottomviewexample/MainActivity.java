package com.kyrodevs.bottomviewexample;


import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        final LinearLayout parent = new LinearLayout(this);
        parent.setGravity(Gravity.BOTTOM);
        parent.setId(1);
        parent.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        parent.setOrientation(LinearLayout.VERTICAL);
        parent.setBackgroundColor(Color.LTGRAY);


        BottomView bv = new BottomView(this);
        bv.setBgColor(Color.WHITE);
        bv.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                (int) Utility.dpToPixel(this, 50)));

        bv.addBitmapStates(new BitmapState(processBitmap(R.drawable.ic_home_unclick),
                processBitmap(R.drawable.ic_home)));
        bv.addBitmapStates(new BitmapState(processBitmap(R.drawable.ic_search_uncheck),
                processBitmap(R.drawable.ic_search)));
        bv.addBitmapStates(new BitmapState(processBitmap(R.drawable.ic_settings_uncheck),
                processBitmap(R.drawable.ic_settings)));

        bv.addBottomViewClickListener(new OnBottomViewClickListener() {
            @Override
            public void onBottomItemClick(int pos) { }
        });
        parent.addView(bv);
        setContentView(parent);
    }

    public Bitmap processBitmap(int id) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), id);
        final int width = (int) Utility.dpToPixel(this, 24);
        final int height = (int) Utility.dpToPixel(this, 24);
        return Bitmap.createScaledBitmap(bitmap, width, height, false);
    }

}
