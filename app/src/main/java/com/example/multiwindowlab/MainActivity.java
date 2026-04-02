package com.example.multiwindowlab;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private View square1, square2;
    private ImageView circle1, circle2;
    private GridLayout gridLayout;
    private int currentSize = 120; // размер в dp

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        square1 = findViewById(R.id.square1);
        square2 = findViewById(R.id.square2);
        circle1 = findViewById(R.id.circle1);
        circle2 = findViewById(R.id.circle2);
        gridLayout = findViewById(R.id.gridLayout);

        Button btnSettings = findViewById(R.id.btnSettings);
        Button btnAbout = findViewById(R.id.btnAbout);

        // Загружаем размер из настроек
        loadSize();

        // Применяем размер к фигурам
        applySize();

        btnSettings.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent);
        });

        btnAbout.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // При возврате из настроек перезагружаем размер и применяем
        loadSize();
        applySize();
    }

    private void loadSize() {
        SharedPreferences prefs = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        currentSize = prefs.getInt("figure_size", 120);
    }

    private void applySize() {
        // Преобразуем dp в пиксели
        float density = getResources().getDisplayMetrics().density;
        int sizePx = (int) (currentSize * density);

        // Меняем размеры квадратов и кругов
        square1.getLayoutParams().width = sizePx;
        square1.getLayoutParams().height = sizePx;
        square1.requestLayout();

        square2.getLayoutParams().width = sizePx;
        square2.getLayoutParams().height = sizePx;
        square2.requestLayout();

        circle1.getLayoutParams().width = sizePx;
        circle1.getLayoutParams().height = sizePx;
        circle1.requestLayout();

        circle2.getLayoutParams().width = sizePx;
        circle2.getLayoutParams().height = sizePx;
        circle2.requestLayout();
    }
}