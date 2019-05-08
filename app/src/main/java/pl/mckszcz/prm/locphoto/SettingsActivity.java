package pl.mckszcz.prm.locphoto;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences = null;
    private Button colorButton;
    private Button fontSizeButton;
    private Button radiusButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        sharedPreferences = this.getSharedPreferences("settings", MODE_PRIVATE);
        addMenuButtons();
    }

    private void addMenuButtons() {
        addColorButton();
        addFontSizeButton();
        addRadiusButton();
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

    private void addRadiusButton() {
        radiusButton = findViewById(R.id.radiusButton);
        radiusButton.setText(sharedPreferences.getInt("Radius", 100) + "m");
        radiusButton.setOnClickListener(v -> {
            PopupMenu popupMenu = new PopupMenu(this, radiusButton);
            popupMenu.getMenuInflater().inflate(R.menu.radius_menu, popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(item -> {
                Toast.makeText(SettingsActivity.this, "Radius: " + item.getTitle(), Toast.LENGTH_SHORT).show();
                saveRadiusSettings(item.getTitle());
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
                colorButton.setText(R.string.font_white);
                break;
            case "Red":
                editor.putInt("Color", Color.RED);
                editor.putString("Color_name", "Red");
                colorButton.setText(R.string.font_red);
                break;
            case "Black":
                editor.putInt("Color", Color.BLACK);
                editor.putString("Color_name", "Black");
                colorButton.setText(R.string.font_black);
                break;
        }
        editor.apply();
        editor.commit();
    }

    private void saveFontSizeSettings(CharSequence fontSize) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        switch (Integer.valueOf(fontSize.toString())) {
            case 100:
                editor.putInt("Font_size", 100);
                fontSizeButton.setText("100");
                break;
            case 110:
                editor.putInt("Font_size", 110);
                fontSizeButton.setText("110");
                break;
            case 120:
                editor.putInt("Font_size", 120);
                fontSizeButton.setText("120");
                break;
            case 130:
                editor.putInt("Font_size", 130);
                fontSizeButton.setText("130");
                break;
            case 140:
                editor.putInt("Font_size", 140);
                fontSizeButton.setText("140");
                break;
            case 150:
                editor.putInt("Font_size", 150);
                fontSizeButton.setText("150");
                break;
            case 160:
                editor.putInt("Font_size", 160);
                fontSizeButton.setText("160");
                break;
            case 170:
                editor.putInt("Font_size", 170);
                fontSizeButton.setText("170");
                break;
            case 180:
                editor.putInt("Font_size", 180);
                fontSizeButton.setText("180");
                break;
            case 190:
                editor.putInt("Font_size", 190);
                fontSizeButton.setText("190");
                break;
            case 200:
                editor.putInt("Font_size", 200);
                fontSizeButton.setText("200");
                break;
        }
        editor.apply();
        editor.commit();
    }

    private void saveRadiusSettings(CharSequence radius) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        switch (Integer.valueOf(radius.toString())) {
            case 100:
                editor.putInt("Radius", 100);
                radiusButton.setText("100m");
                break;
            case 200:
                editor.putInt("Radius", 200);
                radiusButton.setText("200m");
                break;
            case 400:
                editor.putInt("Radius", 400);
                radiusButton.setText("400m");
                break;
            case 500:
                editor.putInt("Radius", 500);
                radiusButton.setText("500m");
                break;
            case 1000:
                editor.putInt("Radius", 1000);
                radiusButton.setText("1000m");
                break;
            case 2000:
                editor.putInt("Radius", 2000);
                radiusButton.setText("2000m");
                break;
            case 5000:
                editor.putInt("Radius", 5000);
                radiusButton.setText("5000m");
                break;
        }
        editor.apply();
        editor.commit();
    }

}
