package com.example.myqrscanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.myqrscanner.controller.MyScanner;
import com.google.zxing.WriterException;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class MainActivity extends AppCompatActivity {

    EditText myValue; //for value
    Button generateButtons, scannerButton; //generate button
    ImageView image; //for image
    QRGEncoder qrgEncoder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myValue = findViewById(R.id.input);
        generateButtons = findViewById(R.id.generate_button);
        scannerButton = findViewById(R.id.scanner_button);
        image = findViewById(R.id.qr_placeholder);
//

        generateButtons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = myValue.getText().toString();
                if (data.isEmpty()) {
                    myValue.setError("Value Required.");
                } else {
                     qrgEncoder = new QRGEncoder(data, null, QRGContents.Type.TEXT, 500);
                    try {
                        Bitmap qrBits = qrgEncoder.encodeAsBitmap();
                        image.setImageBitmap(qrBits);
                    } catch (WriterException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        scannerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MyScanner.class));
                finish();
            }
        });

    }


}