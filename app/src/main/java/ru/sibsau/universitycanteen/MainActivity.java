package ru.sibsau.universitycanteen;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{

    /** Adapter for the list of earthquakes */
    private CanteenAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the content of the activity to use the activity_main.xml layout file
        setContentView(R.layout.activity_main);




        final ArrayList<Canteen> canteens = new ArrayList<Canteen>();
        canteens.add(new Canteen("Столовая 1","Корпус А, цоколь", "11:00 - 15:30"));
        canteens.add(new Canteen("Столовая 2","Корпус C", "10:00 - 14:00"));
        canteens.add(new Canteen("Столовая 3","Общежитие №1", "9:00 - 15:30"));
        canteens.add(new Canteen("Буфет корпус П","", "11:00 - 16:30"));
        canteens.add(new Canteen("Буфет корпус Л","", "11:00 - 16:30"));
        canteens.add(new Canteen("Справка","", ""));

        // Find a reference to the {@link ListView} in the layout
//        ListView canteenListView = (ListView) findViewById(R.id.canteen_list);
        GridView canteenListView = (GridView) findViewById(R.id.gridview);

        // Create a new adapter that takes an empty list of canteens as input
        mAdapter = new CanteenAdapter(this, canteens);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        canteenListView.setAdapter(mAdapter);

        // Set an item click listener on the ListView, which sends an intent to a web browser
        // to open a website with more information about the selected earthquake.
        canteenListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Canteen currentCanteen = mAdapter.getItem(position);

                // Convert the String URL into a URI object (to pass into the Intent constructor)
                Uri uri = Uri.parse(currentCanteen.getCanteenCode());
                Intent intent = new Intent(MainActivity.this, CanteenActivity.class);
//                send data
                intent.setData(uri);
                // Start the new activity
                startActivity(intent);
            }
        });




//
//        // Find the View that shows the canteen_1 category
//        TextView canteen_1 = (TextView) findViewById(R.id.canteen_1);
//
//        // Set a click listener on that View
//        canteen_1.setOnClickListener(new View.OnClickListener() {
//            // The code in this method will be executed when the numbers category is clicked on.
//            @Override
//            public void onClick(View view) {
//                // Create a new intent to open the {@link NumbersActivity}
//                Intent intent = new Intent(MainActivity.this, CanteenActivity.class);
//                Uri uri = Uri.parse("1");
////                send data
//                intent.setData(uri);
////                 Start the new activity
//                startActivity(intent);
//            }
//        });
//
//        // Find the View that shows the canteen_2 category
//        TextView canteen_2 = (TextView) findViewById(R.id.canteen_2);
//
//        // Set a click listener on that View
//        canteen_2.setOnClickListener(new View.OnClickListener() {
//            // The code in this method will be executed when the numbers category is clicked on.
//            @Override
//            public void onClick(View view) {
//                // Create a new intent to open the {@link NumbersActivity}
//                Intent intent = new Intent(MainActivity.this, CanteenActivity.class);
//                Uri uri = Uri.parse("2");
////                send data
//                intent.setData(uri);
//                // Start the new activity
//                startActivity(intent);
//            }
//        });
//
//        // Find the View that shows the canteen_3 category
//        TextView canteen_3 = (TextView) findViewById(R.id.canteen_3);
//
//        // Set a click listener on that View
//        canteen_3.setOnClickListener(new View.OnClickListener() {
//            // The code in this method will be executed when the numbers category is clicked on.
//            @Override
//            public void onClick(View view) {
//                // Create a new intent to open the {@link NumbersActivity}
//                Intent intent = new Intent(MainActivity.this, CanteenActivity.class);
//                Uri uri = Uri.parse("3");
////                send data
//                intent.setData(uri);
//                // Start the new activity
//                startActivity(intent);
//            }
//        });
//
//        // Find the View that shows the bufet_p category
//        TextView bufet_p = (TextView) findViewById(R.id.bufet_p);
//
//        // Set a click listener on that View
//        bufet_p.setOnClickListener(new View.OnClickListener() {
//            // The code in this method will be executed when the numbers category is clicked on.
//            @Override
//            public void onClick(View view) {
//                // Create a new intent to open the {@link NumbersActivity}
//                Intent intent = new Intent(MainActivity.this, CanteenActivity.class);
//                Uri uri = Uri.parse("4");
////                send data
//                intent.setData(uri);
//                // Start the new activity
//                startActivity(intent);
//            }
//        });
//        // Find the View that shows the bufet_p category
//        TextView bufet_l = (TextView) findViewById(R.id.bufet_l);
//
//        // Set a click listener on that View
//        bufet_l.setOnClickListener(new View.OnClickListener() {
//            // The code in this method will be executed when the numbers category is clicked on.
//            @Override
//            public void onClick(View view) {
//                // Create a new intent to open the {@link NumbersActivity}
//                Intent intent = new Intent(MainActivity.this, CanteenActivity.class);
//                Uri uri = Uri.parse("5");
////                send data
//                intent.setData(uri);
//                // Start the new activity
//                startActivity(intent);
//            }
//        });


    }
}