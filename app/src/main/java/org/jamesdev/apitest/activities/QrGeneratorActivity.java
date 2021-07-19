package org.jamesdev.apitest.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.WriterException;

import org.jamesdev.apitest.R;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class QrGeneratorActivity extends AppCompatActivity {

    ImageView qr_code;
    EditText text_value;
    Button generate_button;

    Bitmap bitmap;
    QRGEncoder qrgEncoder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_generator);

        qr_code = (ImageView) findViewById(R.id.qr_code_imageview);
        text_value = (EditText) findViewById(R.id.text_value);
        generate_button = (Button) findViewById(R.id.generate_button);

        generate_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(TextUtils.isEmpty(text_value.getText().toString())){
                    makeToast("Text cannot be empty.");
                } else {
                    WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);

                    Display display = manager.getDefaultDisplay();
                    Point point = new Point();
                    display.getSize(point);

                    int width = point.x;
                    int height = point.y;

                    int dimen = Math.min(width, height);
                    dimen = dimen * 3/4;

                    qrgEncoder = new QRGEncoder(text_value.getText().toString(), null, QRGContents.Type.TEXT, dimen);
                    try{
                        bitmap = qrgEncoder.encodeAsBitmap();
                        qr_code.setImageBitmap(bitmap);
                    } catch (WriterException e){
                        Log.e("Tag", e.toString());
                    }

                }
            }
        });



    }


    public void makeToast(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }



}