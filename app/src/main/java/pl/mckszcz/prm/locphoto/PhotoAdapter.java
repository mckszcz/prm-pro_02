package pl.mckszcz.prm.locphoto;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class PhotoAdapter extends BaseAdapter {

    private Context context;
    private List<File> photoList = new ArrayList<>();

    PhotoAdapter(Context context) {
        this.context = context;
        init();
    }

    private void init() {
        File photoDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        try (Stream<Path> stream = Files.list(Objects.requireNonNull(photoDir).toPath())) {
            stream.forEach(file -> photoList.add(file.toFile()));
        } catch (IOException e) {
            Log.e("", e.getMessage(), e);
        }
    }

    @Override
    public int getCount() {
        return photoList.size();
    }

    @Override
    public File getItem(int position) {
        return photoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = (ImageView) convertView;
        if (imageView == null) {
            imageView = new ImageView(context);
        }
        Picasso
                .get()
                .load(getItem(position))
                .resize(400, 400)
                .centerCrop()
                .into(imageView);
        imageView.setAdjustViewBounds(true);
        return imageView;
    }
}
