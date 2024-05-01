package com.example.laba9;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class MainActivity extends AppCompatActivity {

    private ConstraintLayout mainLayout;
    private ImageView circleImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setSupportActionBar(findViewById(R.id.toolbar));

        mainLayout = findViewById(R.id.main);

        findViewById(R.id.text_view).setOnCreateContextMenuListener(this);

        // Создаем ImageView для круга
        circleImageView = new ImageView(MainActivity.this);
        Drawable circleDrawable = getResources().getDrawable(R.drawable.circle);
        circleImageView.setLayoutParams(new ViewGroup.LayoutParams(SMALL_CIRCLE_SIZE, SMALL_CIRCLE_SIZE));
        circleImageView.setImageDrawable(circleDrawable);

        // Получаем размеры экрана
        int screenWidth = getResources().getDisplayMetrics().widthPixels;
        int screenHeight = getResources().getDisplayMetrics().heightPixels;

        // Устанавливаем позицию круга в центре экрана
        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(SMALL_CIRCLE_SIZE, SMALL_CIRCLE_SIZE);
        layoutParams.leftToLeft = ConstraintLayout.LayoutParams.PARENT_ID;
        layoutParams.rightToRight = ConstraintLayout.LayoutParams.PARENT_ID;
        layoutParams.topToTop = ConstraintLayout.LayoutParams.PARENT_ID;
        layoutParams.bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID;
        layoutParams.leftMargin = (screenWidth - SMALL_CIRCLE_SIZE) / 2;
        layoutParams.topMargin = (screenHeight - SMALL_CIRCLE_SIZE) / 2;
        circleImageView.setLayoutParams(layoutParams);

        // Добавляем ImageView на экран
        mainLayout.addView(circleImageView);

        // Добавляем кнопки для увеличения и перемещения круга
        Button increaseButton = findViewById(R.id.increase_button);
        Button moveLeftButton = findViewById(R.id.move_left_button);
        Button moveRightButton = findViewById(R.id.move_right_button);

        increaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCircleSize(getNextCircleSize(circleImageView.getWidth()));
            }
        });

        moveLeftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveCircle(-MOVE_DISTANCE, 0);
            }
        });

        moveRightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveCircle(MOVE_DISTANCE, 0);
            }
        });
    }

    private static final int SMALL_CIRCLE_SIZE = 50;
    private static final int MEDIUM_CIRCLE_SIZE = 100;
    private static final int LARGE_CIRCLE_SIZE = 150;
    private static final int MOVE_DISTANCE = 20; // Расстояние для перемещения при каждом нажатии кнопки

    private void setCircleSize(int size) {
        ViewGroup.LayoutParams params = circleImageView.getLayoutParams();
        params.width = size;
        params.height = size;
        circleImageView.setLayoutParams(params);
    }

    private int getNextCircleSize(int currentSize) {
        if (currentSize == SMALL_CIRCLE_SIZE) {
            return MEDIUM_CIRCLE_SIZE;
        } else if (currentSize == MEDIUM_CIRCLE_SIZE) {
            return LARGE_CIRCLE_SIZE;
        } else {
            return SMALL_CIRCLE_SIZE;
        }
    }

    private void moveCircle(int deltaX, int deltaY) {
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) circleImageView.getLayoutParams();
        layoutParams.leftMargin += deltaX;
        layoutParams.topMargin += deltaY;
        circleImageView.setLayoutParams(layoutParams);
    }
}
