package ru.sibsau.universitycanteen;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by user on 26.04.17.
 */

public class DishActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish);

        setTitle("Информация о блюде");
    }
}
