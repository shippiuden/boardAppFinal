package com.edwinzapata.boardapp;

import android.graphics.Bitmap;
import android.os.Environment;
import android.view.View;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

/**
 * Created by edwinzapata on 29/12/17.
 */

public class CaptureCamera {

    private Bitmap takeScreenshot(View view) {
        try {
            // crear un bitmap con la captura de pantalla
            View v1 = view.getRootView();
            v1.setDrawingCacheEnabled(true);
            Bitmap bitmap = Bitmap.createBitmap(v1.getDrawingCache());
            v1.setDrawingCacheEnabled(false);
            return bitmap;
        } catch (Throwable e) {
            // Several error may come out with file handling or OOM
            e.printStackTrace();
            return null;
        }
    }

    private void saveScreenshot(Bitmap bitmap) {
        Date now = new Date();
        android.text.format.DateFormat.format("yyyy-MM-dd_hh:mm:ss", now);

        try {
            // nombre y ruta de la imagen a incluir
            String mPath = Environment.getExternalStorageDirectory().toString() + "/" + now + ".jpg";
            File imageFile = new File(mPath);
            FileOutputStream outputStream = new FileOutputStream(imageFile);
            int quality = 100;
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
            outputStream.flush();
            outputStream.close();

        } catch (Throwable e) {
            // Captura los distintos errores que puedan surgir
            e.printStackTrace();
        }
    }
}
