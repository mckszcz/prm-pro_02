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
    private Button fontSizeButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        addMenuButtons();
    }

    private void addMenuButtons() {
        addColorButton();
        addFontSizeButton();
    }

    private void addColorButton() {
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

    private void addFontSizeButton() {
        fontSizeButton = findViewById(R.id.fontSizeButton);
        fontSizeButton.setText(String.valueOf(sharedPreferences.getInt("Font_size", 150)));
        fontSizeButton.setOnClickListener(v -> {
            PopupMenu popupMenu = new PopupMenu(this, fontSizeButton);
            popupMenu.getMenuInflater().inflate(R.menu.font_size_menu, popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(item -> {
                Toast.makeText(SettingsActivity.this, "Font size: " + item.getTitle(), Toast.LENGTH_SHORT).show();
                saveFontSizeSettings(item.getTitle());
                return true;
            });
            popupMenu.show();
        });
    }

    private void saveColorSettings(CharSequence color) {
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

    private void saveFontSizeSettings(CharSequence fontSize) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        switch (Integer.valueOf(fontSize.toString())) {
            case 100:
                editor.putInt("Font_size", 100);
                fontSizeButton.setText(String.valueOf(100));
                break;
            case 110:
                editor.putInt("Font_size", 110);
                fontSizeButton.setText(String.valueOf(110));
                break;
            case 120:
                editor.putInt("Font_size", 120);
                fontSizeButton.setText(String.valueOf(120));
                break;
            case 130:
                editor.putInt("Font_size", 130);
                fontSizeButton.setText(String.valueOf(130));
                break;
            case 140:
                editor.putInt("Font_size", 140);
                fontSizeButton.setText(String.valueOf(140));
                break;
            case 150:
                editor.putInt("Font_size", 150);
                fontSizeButton.setText(String.valueOf(150));
                break;
            case 160:
                editor.putInt("Font_size", 160);
                fontSizeButton.setText(String.valueOf(160));
                break;
            case 170:
                editor.putInt("Font_size", 170);
                fontSizeButton.setText(String.valueOf(170));
                break;
            case 180:
                editor.putInt("Font_size", 180);
                fontSizeButton.setText(String.valueOf(180));
                break;
            case 190:
                editor.putInt("Font_size", 190);
                fontSizeButton.setText(String.valueOf(190));
                break;
            case 200:
                editor.putInt("Font_size", 200);
                fontSizeButton.setText(String.valueOf(200));
                break;
        }
        editor.apply();
    }

}
