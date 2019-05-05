package pl.mckszcz.prm.locphoto;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences = null;
    private Button colorButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        addMenuButtons();
    }

    private void addMenuButtons() {
        colorButton = findViewById(R.id.colorButton);
        colorButton.setText(sharedPreferences.getString("Color_name", "Color"));
        colorButton.setOnClickListener(v -> {
            PopupMenu popupMenu = new PopupMenu(this, colorButton);
            popupMenu.getMenuInflater().inflate(R.menu.color_menu, popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(item -> {
                Toast.makeText(SettingsActivity.this, "Text colour: " + item.getTitle(), Toast.LENGTH_SHORT).show();
                saveColorSettings(item.getTitle());
                return true;
            });
            popupMenu.show();
        });
    }

    private void saveColorSettings(CharSequence color) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        switch (color.toString()) {
            case "White":
                editor.putInt("Color", Color.WHITE);
                editor.putString("Color_name", "White");
                colorButton.setText("White");
                break;
            case "Red":
                editor.putInt("Color", Color.RED);
                editor.putString("Color_name", "Red");
                colorButton.setText("Red");
                break;
            case "Black":
                editor.putInt("Color", Color.BLACK);
                editor.putString("Color_name", "Black");
                colorButton.setText("Black");
                break;
        }
        editor.apply();
    }

}
