package pl.mckszcz.prm.locphoto;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.GridView;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class GalleryActivity extends AppCompatActivity {

    private List<File> photoList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        GridView gridView = findViewById(R.id.galleryGridView);
        File photoDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        try {
            Files.list(photoDir.toPath()).forEach(file -> photoList.add(file.toFile()));
        } catch (IOException e) {
            Log.e("", e.getMessage(), e);
        }
    }


}
