package ru.sibsau.universitycanteen;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.List;

/**
 * Loads a list of earthquakes by using an AsyncTask to perform the
 * network request to the given URL.
 */

public class DishLoader extends AsyncTaskLoader<List<Dish>> {
    /** Tag for log messages */
    public static final String LOG_TAG = DishLoader.class.getName();

    /** Query URL */
    private String mUrl;

    /**
     * Constructs a new {@link DishLoader}.
     *
     * @param context of the activity
     * @param url to load data from
     */
    public DishLoader(Context context, String url) {
        super(context);
        mUrl = url;
        Log.v("Check", "DishLoader");
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    /**
     * This is on a background thread.
     */
    @Override
    public List<Dish> loadInBackground() {
        if (mUrl == null) {
            return null;
        }

        // Perform the network request, parse the response, and extract a list of earthquakes.
        List<Dish> dishes = QueryUtils.fetchDishData(mUrl);
        return dishes;
    }
}
