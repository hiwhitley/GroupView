package com.hiwhitley.groupview;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private GroupView mGroupView;
    private List<Bitmap> bitmapList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.smart_main);
        bitmapList = new ArrayList<Bitmap>();

        bitmapList.add(BitmapFactory.decodeResource(getResources(), R.drawable.face));
        mGroupView = (GroupView) findViewById(R.id.gv_face1);
        mGroupView.setImageBitmaps(bitmapList.toArray(new Bitmap[bitmapList.size()]));
        mGroupView = (GroupView) findViewById(R.id.gv_face2);
        mGroupView.setImageBitmaps(bitmapList.toArray(new Bitmap[bitmapList.size()]));
        mGroupView = (GroupView) findViewById(R.id.gv_face3);
        mGroupView.setImageBitmaps(bitmapList.toArray(new Bitmap[bitmapList.size()]));

        bitmapList.add(BitmapFactory.decodeResource(getResources(), R.drawable.face));

        mGroupView = (GroupView) findViewById(R.id.gv_face4);
        mGroupView.setImageBitmaps(bitmapList.toArray(new Bitmap[bitmapList.size()]));
        mGroupView = (GroupView) findViewById(R.id.gv_face5);
        mGroupView.setImageBitmaps(bitmapList.toArray(new Bitmap[bitmapList.size()]));
        mGroupView = (GroupView) findViewById(R.id.gv_face6);
        mGroupView.setImageBitmaps(bitmapList.toArray(new Bitmap[bitmapList.size()]));

        bitmapList.add(BitmapFactory.decodeResource(getResources(), R.drawable.face));
        mGroupView = (GroupView) findViewById(R.id.gv_face7);
        mGroupView.setImageBitmaps(bitmapList.toArray(new Bitmap[bitmapList.size()]));
        mGroupView = (GroupView) findViewById(R.id.gv_face8);
        mGroupView.setImageBitmaps(bitmapList.toArray(new Bitmap[bitmapList.size()]));

        bitmapList.add(BitmapFactory.decodeResource(getResources(), R.drawable.face));
        mGroupView = (GroupView) findViewById(R.id.gv_face9);
        mGroupView.setImageBitmaps(bitmapList.toArray(new Bitmap[bitmapList.size()]));

    }
}
