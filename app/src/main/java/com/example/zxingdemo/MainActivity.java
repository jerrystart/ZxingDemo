package com.example.zxingdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;

import com.google.zxing.WriterException;

public class MainActivity extends FragmentActivity {
    ImageView qrImg, qrOneImg;

    //DecodeHandler   rawResult = multiFormatReader.decodeWithState(bitmap);  这样只能识别二维码
    //multiFormatReader.decode(bitmap) 既能识别二维码也能识别条形码


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        qrImg = (ImageView) findViewById(R.id.qr_img);
        qrOneImg = (ImageView) findViewById(R.id.qr_one_img);
        findViewById(R.id.test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, QRScanActivity.class);
                startActivity(intent);
            }
        });
        try {
            qrOneImg.setImageBitmap(ZxingImageUtils.CreateOneDCode("qwer", 500, 200));
            qrImg.setImageBitmap(ZxingImageUtils.encodeAsBitmap("张三张三张三张三张三张三张三张三张三张三", 600, 600));
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }
}
