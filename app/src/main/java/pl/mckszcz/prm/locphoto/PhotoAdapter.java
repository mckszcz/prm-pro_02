package pl.mckszcz.prm.locphoto;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class PhotoAdapter extends ArrayAdapter {

    private Context context;
    private int resourceId;
    private ArrayList data = new ArrayList();

    public PhotoAdapter(Context context, int resourceId, ArrayList data) {
        super(context, resourceId, data);
        this.context = context;
        this.resourceId = resourceId;
        this.data = data;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        
        return null;
    }
}
