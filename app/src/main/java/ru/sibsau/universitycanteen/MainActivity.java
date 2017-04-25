package ru.sibsau.universitycanteen;

import android.content.Intent;
import android.net.Uri;
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
                Intent intent = new Intent(MainActivity.this, CanteenActivity.class);
                Uri uri = Uri.parse("1");
//                send data
                intent.setData(uri);
//                 Start the new activity
                startActivity(intent);
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
                Intent intent = new Intent(MainActivity.this, CanteenActivity.class);
                Uri uri = Uri.parse("2");
//                send data
                intent.setData(uri);
                // Start the new activity
                startActivity(intent);
            }
        });

        // Find the View that shows the canteen_3 category
        TextView canteen_3 = (TextView) findViewById(R.id.canteen_3);

        // Set a click listener on that View
        canteen_3.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link NumbersActivity}
                Intent intent = new Intent(MainActivity.this, CanteenActivity.class);
                Uri uri = Uri.parse("3");
//                send data
                intent.setData(uri);
                // Start the new activity
                startActivity(intent);
            }
        });

        // Find the View that shows the bufet_p category
        TextView bufet_p = (TextView) findViewById(R.id.bufet_p);

        // Set a click listener on that View
        bufet_p.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link NumbersActivity}
                Intent intent = new Intent(MainActivity.this, CanteenActivity.class);
                Uri uri = Uri.parse("4");
//                send data
                intent.setData(uri);
                // Start the new activity
                startActivity(intent);
            }
        });
        // Find the View that shows the bufet_p category
        TextView bufet_l = (TextView) findViewById(R.id.bufet_l);

        // Set a click listener on that View
        bufet_l.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link NumbersActivity}
                Intent intent = new Intent(MainActivity.this, CanteenActivity.class);
                Uri uri = Uri.parse("5");
//                send data
                intent.setData(uri);
                // Start the new activity
                startActivity(intent);
            }
        });


    }
}