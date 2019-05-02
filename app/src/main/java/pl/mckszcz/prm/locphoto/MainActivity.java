package pl.mckszcz.prm.locphoto;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.location.Address;
import android.location.Geocoder;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    File photoFile = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        addCameraButtonListener();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            ExifInterface exifInterface = new ExifInterface(photoFile.toPath().toString());
            float[] latLong = {0, 0};
            exifInterface.getLatLong(latLong);
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(latLong[0], latLong[1], 1);
            addAddress(addresses.get(0));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addAddress(Address address) {
        Bitmap bitmap = BitmapFactory.decodeFile(photoFile.getAbsolutePath());
        Bitmap mutableBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);
        Canvas canvas = new Canvas(mutableBitmap);
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setTextSize(400);
        canvas.drawText(address.getCountryName() + ", " + address.getLocality(), 10, 10, paint);
        canvas.save();
        try (FileOutputStream fos = new FileOutputStream(photoFile.getAbsolutePath())) {
            mutableBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void addCameraButtonListener() {
        Button cameraButton = findViewById(R.id.cameraButton);
        cameraButton.setOnClickListener(v -> handleTakePictureIntent());
    }

    private void handleTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            try {
                photoFile = createImageFile();
            } catch (IOException e) {
                Log.e(MainActivity.class.toString(), e.getMessage(), e);
            }
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this, "pl.mckszcz.android.fileprovider", photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, 1);
            }
        }
    }

    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File storageDirectory = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        return File.createTempFile(timeStamp, ".jpg", storageDirectory);
    }

}
