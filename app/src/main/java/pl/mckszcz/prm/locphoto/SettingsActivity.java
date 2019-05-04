package pl.mckszcz.prm.locphoto;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        addMenuButtons();
    }

    private void addMenuButtons() {
        Button button = findViewById(R.id.colorButton);
        button.setOnClickListener(v -> {
            PopupMenu popupMenu = new PopupMenu(this, button);
            popupMenu.getMenuInflater().inflate(R.menu.color_menu, popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(item -> {
                Toast.makeText(SettingsActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
                return true;
            });
            popupMenu.show();
        });
    }

    private void saveColorSettings() {
        Context context = this;
    }

}
