package pl.mckszcz.prm.locphoto;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

public class GalleryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        GridView gridView = findViewById(R.id.galleryGridView);
        PhotoAdapter adapter = new PhotoAdapter(this);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(this, PhotoActivity.class);
            intent.putExtra("photo", adapter.getItem(position));
            startActivity(intent);
        });
    }
}
