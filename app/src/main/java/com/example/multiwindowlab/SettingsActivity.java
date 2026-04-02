package com.example.multiwindowlab;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    private RadioGroup radioGroup;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        radioGroup = findViewById(R.id.radioGroupSize);
        btnSave = findViewById(R.id.btnSave);

        // Загружаем сохранённый размер
        SharedPreferences prefs = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        int savedSize = prefs.getInt("figure_size", 120); // по умолчанию 120dp
        if (savedSize == 80) {
            radioGroup.check(R.id.radioSmall);
        } else if (savedSize == 120) {
            radioGroup.check(R.id.radioMedium);
        } else if (savedSize == 160) {
            radioGroup.check(R.id.radioLarge);
        }

        btnSave.setOnClickListener(v -> {
            int selectedId = radioGroup.getCheckedRadioButtonId();
            int size = 120; // default
            if (selectedId == R.id.radioSmall) size = 80;
            else if (selectedId == R.id.radioMedium) size = 120;
            else if (selectedId == R.id.radioLarge) size = 160;

            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt("figure_size", size);
            editor.apply();

            finish();
        });
    }
}