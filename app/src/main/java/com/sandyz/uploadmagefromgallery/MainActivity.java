package com.sandyz.uploadmagefromgallery;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int Gallery_Request = 100;

    Button bt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt = (Button) findViewById(R.id.btn);
        bt.setOnClickListener(MainActivity.this);


    }

    @Override
    public void onClick(View v) {

        pickImage();
    }

    private void pickImage() {

        Intent getpic = new Intent(Intent.ACTION_PICK);
        getpic.setType("image/*");
        startActivityForResult(getpic, Gallery_Request);

    }

    public void onActivityResult(int resultCode, int requestCode, Intent resultData) {
        super.onActivityResult(resultCode, resultCode, resultData);


        switch (resultCode) {
            case Gallery_Request:
                if (requestCode == RESULT_OK) {

                    Uri selectedImage = resultData.getData();
                    InputStream imageStream = null;
                    try {
                        imageStream = getContentResolver().openInputStream(selectedImage);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    Bitmap yourSelectedImage = BitmapFactory.decodeStream(imageStream);
                    ImageView txt = (ImageView) findViewById(R.id.img);
                    txt.setImageBitmap(yourSelectedImage);
                }
                else
                        {
                Toast.makeText(MainActivity.this, "you havent selected the image", Toast.LENGTH_LONG).show();
            }

        }
    }
}












