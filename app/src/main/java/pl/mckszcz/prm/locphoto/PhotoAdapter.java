package pl.mckszcz.prm.locphoto;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class PhotoAdapter extends BaseAdapter {

    private Context context;
    private List<File> photoList = new ArrayList<>();

    public PhotoAdapter(Context context) {
        this.context = context;
        init();
    }

    private void init() {
        File photoDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        try {
            Files.list(photoDir.toPath()).forEach(file -> photoList.add(file.toFile()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getCount() {
        return photoList.size();
    }

    @Override
    public Object getItem(int position) {
        return photoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        Bitmap bitmap = (BitmapFactory.decodeFile(photoList.get(position).getAbsolutePath()));
        if (convertView == null) {
            imageView = new ImageView(context);
            imageView.setLayoutParams(new GridView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        } else {
            imageView = (ImageView) convertView;
        }
        imageView.setImageBitmap(Bitmap.createScaledBitmap(bitmap, 400, 300, false));
        return imageView;
    }
}
