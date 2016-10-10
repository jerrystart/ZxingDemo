package com.example.zxingdemo;

import android.graphics.Bitmap;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.util.Hashtable;

/**
 * TODO:功能说明
 *
 * @author: hejie
 * @date: 2016-10-09 22:06
 */
public class ZxingImageUtils {
    private static final int BLACK = 0xFF000000;
    private static final int CLEAR = 0;

    // 生成二维码
    public static Bitmap encodeAsBitmap(String contents, int dimen_width, int dimen_height, int contentColor)
            throws WriterException {
        Hashtable<EncodeHintType, Object> hints = new Hashtable<>(2);
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        QRCodeWriter writer = new QRCodeWriter();
        BitMatrix result = writer.encode(contents, BarcodeFormat.QR_CODE, dimen_width,
                dimen_height, hints);
        int width = result.getWidth();
        int height = result.getHeight();
        int[] pixels = new int[width * height];
        // All are 0, or black, by default
        for (int y = 0; y < height; y++) {
            int offset = y * width;
            for (int x = 0; x < width; x++) {
                pixels[offset + x] = result.get(x, y) ? contentColor : CLEAR;
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return bitmap;
    }

    // 生成二维码
    public static Bitmap encodeAsBitmap(String contents, int dimen_width, int dimen_height)
            throws WriterException {
        return encodeAsBitmap(contents, dimen_width, dimen_height, BLACK);
    }

    //条形码生成 不支持中文
    public static Bitmap CreateOneDCode(String contents, int dimen_width, int dimen_height) throws WriterException {
        // 生成一维条码,编码时指定大小,不要生成了图片以后再进行缩放,这样会模糊导致识别失败
        BitMatrix result = new MultiFormatWriter().encode(contents, BarcodeFormat.CODE_128, dimen_width,
                dimen_height);
        int width = result.getWidth();
        int height = result.getHeight();
        int[] pixels = new int[width * height];
        for (int y = 0; y < height; y++) {
            int offset = y * width;
            for (int x = 0; x < width; x++) {
                pixels[offset + x] = result.get(x, y) ? 0xFF000000 : CLEAR;
            }
        }

        Bitmap bitmap = Bitmap.createBitmap(width, height,
                Bitmap.Config.ARGB_8888);
        // 通过像素数组生成bitmap,具体参考api
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return bitmap;
    }
}
