package pl.mckszcz.prm.locphoto;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.Objects;

public class PhotoActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        ImageView imageView = findViewById(R.id.fullScreenImageView);
        Picasso
                .get()
                .load((File) Objects.requireNonNull(Objects.requireNonNull(getIntent().getExtras()).get("photo")))
                .into(imageView);
    }
}
