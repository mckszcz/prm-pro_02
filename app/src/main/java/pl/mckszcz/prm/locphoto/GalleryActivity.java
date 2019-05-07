package pl.mckszcz.prm.locphoto;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

public class GalleryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        GridView gridView = findViewById(R.id.galleryGridView);
        gridView.setAdapter(new PhotoAdapter(this));
    }


}
