package ru.sibsau.universitycanteen;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 02.03.17.
 */

public class CanteenActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<List<Dish>> {

    /** Content URI for the existing pet (null if it's a new pet) */
    private Uri mCurrentCanteenUri;

    /** URL for earthquake data from the USGS dataset */
    private String MENU_REQUEST_URL;// = "http://menu.pld.sibsau.ru/android/canteens_2?canteen_code=1";
//           static final  "http://menu.pld.sibsau.ru/android/canteens_2?canteen_code=1";
//            "http://172.17.11.218:8069/android/canteens_2?canteen_code=1";

    /**
     * Constant value for the earthquake loader ID. We can choose any integer.
     * This really only comes into play if you're using multiple loaders.
     */
    private static final int DISH_LOADER_ID = 1;

    /** Adapter for the list of dishes */
    private DishAdapter mAdapter;

    /** TextView that is displayed when the list is empty */
    private TextView mEmptyStateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dish_list);
        // Find a reference to the {@link ListView} in the layout
        ListView dishListView = (ListView) findViewById(R.id.list);

        mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);
        dishListView.setEmptyView(mEmptyStateTextView);

        // Examine the intent that was used to launch this activity,
        // in order to figure out if we're creating a new pet or editing an existing one.
        Intent intent = getIntent();
        mCurrentCanteenUri = intent.getData();
//        Log.e(LOG_TAG, "CanteenActivity sended URI: " + mCurrentCanteenUri);

        switch (mCurrentCanteenUri.toString()){
            case "1":
//                Log.e(LOG_TAG, "CanteenActivity 1 HERE!!!!!!!!!!");
                setTitle("Столовая №1");
                MENU_REQUEST_URL = "http://menu.pld.sibsau.ru/android/canteens_2?canteen_code=1";
                break;
            case "2":
                setTitle("Столовая №2");
                MENU_REQUEST_URL = "http://menu.pld.sibsau.ru/android/canteens_2?canteen_code=2";
                break;
            case "3":
                setTitle("Столовая №3");
                MENU_REQUEST_URL = "http://menu.pld.sibsau.ru/android/canteens_2?canteen_code=3";
                break;
            case "4":
                setTitle("Буфет корпуса П");
                MENU_REQUEST_URL = "http://menu.pld.sibsau.ru/android/canteens_2?canteen_code=4";
                break;
            case "5":
                setTitle("Буфет корпуса Л");
                MENU_REQUEST_URL = "http://menu.pld.sibsau.ru/android/canteens_2?canteen_code=5";
                break;
            default:
                setTitle("Справка");
                break;
        }


        // Create a new adapter that takes an empty list of dishes as input
        mAdapter = new DishAdapter(this, new ArrayList<Dish>());

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        dishListView.setAdapter(mAdapter);

//        dishListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
//                Dish currentDish = mAdapter.getItem(position);
//
//                // Convert the String URL into a URI object (to pass into the Intent constructor)
////                Uri uri = Uri.parse(currentCanteen.getCanteenCode());
//                Intent intent = new Intent(CanteenActivity.this, DishActivity.class);
////                send data
////                intent.setData(uri);
//                // Start the new activity
//                startActivity(intent);
//            }
//        });

        // Get a reference to the ConnectivityManager to check state of network connectivity
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        // Get details on the currently active default data network
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        // If there is a network connection, fetch data
        if (networkInfo != null && networkInfo.isConnected()) {
            // Get a reference to the LoaderManager, in order to interact with loaders.
            LoaderManager loaderManager = getLoaderManager();

            // Initialize the loader. Pass in the int ID constant defined above and pass in null for
            // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
            // because this activity implements the LoaderCallbacks interface).
            loaderManager.initLoader(DISH_LOADER_ID, null, this);
        }
//        else if (mCurrentCanteenUri.toString() == "0"){
//            View loadingIndicator = findViewById(R.id.loading_indicator);
//            loadingIndicator.setVisibility(View.GONE);
//            mEmptyStateTextView.setText("Здесь будет справка");
//        }
        else {
            // Otherwise, display error
            // First, hide loading indicator so error message will be visible
            View loadingIndicator = findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);

            // Update empty state with no connection error message
            mEmptyStateTextView.setText(R.string.no_internet_connection);
        }


    }

//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//        return;
//    }

    @Override
    public Loader<List<Dish>> onCreateLoader(int i, Bundle bundle) {
        // Create a new loader for the given URL
        return new DishLoader(this, MENU_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<Dish>> loader, List<Dish> dishes) {
        // Hide loading indicator because the data has been loaded
        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);

        // Set empty state text to display "No dishes found."
        mEmptyStateTextView.setText(R.string.no_dishes);


        // Clear the adapter of previous earthquake data
        mAdapter.clear();

        // If there is a valid list of {@link Dish}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (dishes != null && !dishes.isEmpty()) {
            mAdapter.addAll(dishes);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Dish>> loader) {
        // Loader reset, so we can clear out our existing data.
        mAdapter.clear();
    }
}
