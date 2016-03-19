package com.hiwhitley.groupview;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private GroupView mGroupView1;
    private GroupView mGroupView2;
    private int[] ids = new int[]{R.drawable.img1, R.drawable.img2,
            R.drawable.img3, R.drawable.img4,R.drawable.img1, R.drawable.img2,
            R.drawable.img3, R.drawable.img4,R.drawable.img1, R.drawable.img2,
            R.drawable.img3, R.drawable.img4};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.smart_main);
        ArrayList<Bitmap> mBmps1 = new ArrayList<Bitmap>();

        for (int i = 0; i < 5; i++) {
            mBmps1.add(BitmapFactory.decodeResource(getResources(),ids[i]));
        }

        mGroupView1 = (GroupView) findViewById(R.id.gv_face2);
        mGroupView1.setImageBitmaps(mBmps1.toArray(new Bitmap[mBmps1.size()]));
        mGroupView2 = (GroupView) findViewById(R.id.gv_face3);
        mGroupView2.setImageBitmaps(mBmps1.toArray(new Bitmap[mBmps1.size()]));
    }
}
