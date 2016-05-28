package ru.pooch.myapplication;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class ImageActivity extends AppCompatActivity {
    ImageView imageView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        String i = getIntent().getStringExtra("jpg");
        imageView2 = (ImageView) findViewById(R.id.imageView2);
        imageView2.setImageURI(Uri.parse(i));
    }
}
