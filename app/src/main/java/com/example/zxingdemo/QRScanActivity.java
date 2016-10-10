package com.example.zxingdemo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import com.google.zxing.client.android.CaptureActivity;

import java.net.URLEncoder;

public class QRScanActivity extends CaptureActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onSetContentView() {
        setContentView(R.layout.qr_scan_activity);
    }


    @Override
    public void handleDecodeUri(Uri uri) {
        try {
            Toast.makeText(this, URLEncoder.encode(uri.toString(), "utf-8"), Toast.LENGTH_SHORT).show();
        } catch (Exception e) {

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (resultCode == RESULT_OK) {
            finish();
        }
    }
}
