package ru.sibsau.universitycanteen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the content of the activity to use the activity_main.xml layout file
        setContentView(R.layout.activity_main);

        // Find the View that shows the canteen_1 category
        TextView canteen_1 = (TextView) findViewById(R.id.canteen_1);

        // Set a click listener on that View
        canteen_1.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link NumbersActivity}
                Intent numbersIntent = new Intent(MainActivity.this, Canteen1Activity.class);

                // Start the new activity
                startActivity(numbersIntent);
            }
        });

        // Find the View that shows the canteen_2 category
        TextView canteen_2 = (TextView) findViewById(R.id.canteen_2);

        // Set a click listener on that View
        canteen_2.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link NumbersActivity}
                Intent numbersIntent = new Intent(MainActivity.this, Canteen2Activity.class);

                // Start the new activity
                startActivity(numbersIntent);
            }
        });


    }
}