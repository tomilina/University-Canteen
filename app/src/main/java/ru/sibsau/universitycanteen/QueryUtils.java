package ru.sibsau.universitycanteen;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 14.02.17.
 */

public class QueryUtils {

    /** Tag for the log messages */
    private static final String LOG_TAG = QueryUtils.class.getSimpleName();

//    private static final String SAMPLE_JSON_RESPONSE = "{\"canteens_menu\": [{\"canteen_name\": \"\\u0411\\u0443\\u0444\\u0435\\u0442 \\u043a\\u043e\\u0440\\u043f\\u0443\\u0441 \\\"\\u041b\\\" \\u0421\\u0438\\u0431\\u0413\\u0410\\u0423\", \"menu_items\": [{\"category\": \"\\u0425\\u041e\\u041b\\u041e\\u0414\\u041d\\u042b\\u0415 \\u0411\\u041b\\u042e\\u0414\\u0410\", \"price\": 26.04, \"name\": \"\\u0421\\u0430\\u043b\\u0430\\u0442 \\u0438\\u0437 \\u0441\\u0432\\u0435\\u0436\\u0438\\u0445 \\u043e\\u0433\\u0443\\u0440\\u0446\\u043e\\u0432, \\u0441\\u0432\\u0435\\u0436\\u0438\\u0445 \\u043f\\u043e\\u043c\\u0438\\u0434\\u043e\\u0440, \\u0441 \\u043c\\u0430\\u0439\\u043d\\u0435\\u0437\\u043e\\u043c , \\u043f\\u0435\\u0442\\u0440\\u0443\\u0448\\u043a\\u043e\\u0439\", \"weight\": \"40/40/20/2\"}, {\"category\": \"\\u0425\\u041e\\u041b\\u041e\\u0414\\u041d\\u042b\\u0415 \\u0411\\u041b\\u042e\\u0414\\u0410\", \"price\": 9.44, \"name\": \"\\u0421\\u0430\\u043b\\u0430\\u0442 \\\"\\u041b\\u044e\\u0431\\u0438\\u0442\\u0435\\u043b\\u044c\\u0441\\u043a\\u0438\\u0439\\u00bb \\u0441\\u043e \\u0441\\u0432\\u0435\\u0436\\u0438\\u043c\\u0438 \\u043f\\u043e\\u043c\\u0438\\u0434\\u043e\\u0440\\u0430\\u043c\\u0438 , \\u043f\\u0435\\u0442\\u0440\\u0443\\u0448\\u043a\\u043e\\u0439 ( \\u043a\\u0430\\u043f\\u0443\\u0441\\u0442\\u0430 \\u0441\\u0432\\u0435\\u0436\\u0430\\u044f, \\u043a\\u0438\\u0441\\u043b\\u043e\\u0442\\u0430 \\u043b\\u0438\\u043c\\u043e\\u043d\\u0430\\u044f ,\\u043f\\u043e\\u043c\\u0438\\u0434\\u043e\\u0440\\u044b \\u0441\\u0432\\u0435\\u0436\\u0438\\u0435,\\u0441\\u0430\\u0445\\u0430\\u0440, \\u043c\\u0430\\u0441\\u043b\\u043e \\u0440\\u0430\\u0441\\u0442\\u0438\\u0442\\u0435\\u043b\\u044c\\u043d\\u043e\\u0435)\", \"weight\": \"100/2\"}, {\"category\": \"\\u0425\\u041e\\u041b\\u041e\\u0414\\u041d\\u042b\\u0415 \\u0411\\u041b\\u042e\\u0414\\u0410\", \"price\": 11.96, \"name\": \"\\u0421\\u0432\\u0435\\u043a\\u043b\\u0430 \\u0441 \\u0441\\u044b\\u0440\\u043e\\u043c, \\u0447\\u0435\\u0441\\u043d\\u043e\\u043a\\u043e\\u043c, \\u043c\\u0430\\u0439\\u043e\\u043d\\u0435\\u0437\\u043e\\u043c\", \"weight\": \"85/15\"}, {\"category\": \"2-\\u0435 \\u0411\\u041b\\u042e\\u0414\\u0410\", \"price\": 11.94, \"name\": \"\\u041a\\u0430\\u0448\\u0430 \\u043c\\u043e\\u043b\\u043e\\u0447\\u043d\\u0430\\u044f \\u043f\\u0448\\u0435\\u043d\\u043d\\u0430\\u044f \\u0441 \\u0441\\u043b\\u0438\\u0432\\u043e\\u0447\\u043d\\u044b\\u043c \\u043c\\u0430\\u0441\\u043b\\u043e\\u043c\", \"weight\": \"200/10\"}, {\"category\": \"2-\\u0435 \\u0411\\u041b\\u042e\\u0414\\u0410\", \"price\": 34.86, \"name\": \"\\u041f\\u0435\\u0447\\u0435\\u043d\\u044c \\u043f\\u043e -\\u0441\\u0442\\u0440\\u043e\\u0433\\u0430\\u043d\\u043e\\u0432\\u0441\\u043a\\u0438 (\\u043f\\u0435\\u0447\\u0435\\u043d\\u044c, \\u0441\\u043c\\u0435\\u0442\\u0430\\u043d\\u0430, \\u043c\\u0430\\u0439\\u043e\\u043d\\u0435\\u0437,\\u0442\\u043e\\u043c\\u0430\\u0442\\u043d\\u0430\\u044f \\u043f\\u0430\\u0441\\u0442\\u0430, \\u0441\\u043f\\u0435\\u0446\\u0438\\u0438, \\u043c\\u0430\\u0441\\u043b\\u043e \\u0440\\u0430\\u0441\\u0442\\u0438\\u0442\\u0435\\u043b\\u044c\\u043d\\u043e\\u0435)\", \"weight\": \"75/75\"}, {\"category\": \"2-\\u0435 \\u0411\\u041b\\u042e\\u0414\\u0410\", \"price\": 33.17, \"name\": \"\\u041f\\u043b\\u043e\\u0432 \\u0441 \\u0444\\u0438\\u043b\\u0435 \\u043a\\u0443\\u0440\\u0438\\u0446\\u044b (\\u0440\\u0438\\u0441, \\u043a\\u0443\\u0440\\u0438\\u043d\\u043e\\u0435 \\u0444\\u0438\\u043b\\u0435.\\u043b\\u0443\\u043a, \\u043c\\u043e\\u0440\\u043a\\u043e\\u0432\\u044c, \\u0442\\u043e\\u043c\\u0430\\u0442\\u043d\\u0430\\u044f \\u043f\\u0430\\u0441\\u0442\\u0430,\\u0441\\u043f\\u0435\\u0446\\u0438\\u0438,\\u043c\\u0430\\u0441\\u043b\\u043e \\u0440\\u0430\\u0441\\u0442\\u0438\\u0442\\u0435\\u043b\\u044c\\u043d\\u043e\\u0435)\", \"weight\": \"50/200\"}, {\"category\": \"1-\\u0435 \\u0411\\u041b\\u042e\\u0414\\u0410\", \"price\": 15.3, \"name\": \"\\u0411\\u043e\\u0440\\u0449 \\u0438\\u0437 \\u0441\\u0432\\u0435\\u0436\\u0435\\u0439 \\u043a\\u0430\\u043f\\u0443\\u0441\\u0442\\u044b \\u0441 \\u043c\\u044f\\u0441\\u043e\\u043c \\u0433\\u043e\\u0432\\u044f\\u0434\\u0438\\u043d\\u044b, \\u0441\\u043e \\u0441\\u043c\\u0435\\u0442\\u0430\\u043d\\u043e\\u0439(\\u043a\\u0430\\u043f\\u0443\\u0441\\u0442\\u0430 \\u0441\\u0432\\u0435\\u0436,\\u043c\\u043e\\u0440\\u043a\\u043e\\u0432\\u044c, \\u0441\\u0432\\u0435\\u043a\\u043b\\u0430, \\u043b\\u0443\\u043a, \\u043c\\u044f\\u0441\\u043e \\u0433\\u043e\\u0432, \\u0442\\u043e\\u043c \\u043f\\u0430\\u0441\\u0442\\u0430, \\u0441\\u043c\\u0435\\u0442\\u0430\\u043d\\u0430)\", \"weight\": \"250/12/10\"}, {\"category\": \"2-\\u0435 \\u0411\\u041b\\u042e\\u0414\\u0410\", \"price\": 16.26, \"name\": \"\\u0421\\u043e\\u0441\\u0438\\u0441\\u043a\\u0430 \\u043e\\u0442\\u0432\\u0430\\u0440\\u043d\\u0430\\u044f \", \"weight\": \"60\"}, {\"category\": \"2-\\u0435 \\u0411\\u041b\\u042e\\u0414\\u0410\", \"price\": 26.77, \"name\": \"\\u0426\\u044b\\u043f\\u043b\\u044f\\u0442\\u0430 \\u043e\\u0442\\u0432\\u0430\\u0440\\u043d\\u044b\\u0435\", \"weight\": \"100\"}, {\"category\": \"\\u0413\\u0410\\u0420\\u041d\\u0418\\u0420\\u042b\", \"price\": 1.31, \"name\": \"\\u041c\\u0430\\u0439\\u043e\\u043d\\u0435\\u0437\", \"weight\": \"10\"}, {\"category\": \"\\u0413\\u0410\\u0420\\u041d\\u0418\\u0420\\u042b\", \"price\": 3.33, \"name\": \"\\u0421\\u043e\\u0443\\u0441 \\u0441\\u043c\\u0435\\u0442\\u0430\\u043d\\u043d\\u044b\\u0439 (\\u0441\\u043c\\u0435\\u0442\\u0430\\u043d\\u0430, \\u043c\\u0443\\u043a\\u0430, \\u0441\\u043f\\u0435\\u0446\\u0438\\u0438)\", \"weight\": \"50\"}, {\"category\": \"\\u0413\\u0410\\u0420\\u041d\\u0418\\u0420\\u042b\", \"price\": 3.68, \"name\": \"\\u041c\\u0430\\u0441\\u043b\\u043e \\u0441\\u043b\\u0438\\u0432\\u043e\\u0447\\u043d\\u043e\\u0435\", \"weight\": \"10\"}, {\"category\": \"\\u0413\\u0410\\u0420\\u041d\\u0418\\u0420\\u042b\", \"price\": 5.27, \"name\": \"\\u0420\\u043e\\u0436\\u043a\\u0438 \\u043e\\u0442\\u0432\\u0430\\u0440\\u043d\\u044b\\u0435\", \"weight\": \"150\"}, {\"category\": \"\\u041d\\u0410\\u041f\\u0418\\u0422\\u041a\\u0418\", \"price\": 3.98, \"name\": \"\\u041a\\u043e\\u043c\\u043f\\u043e\\u0442 \\u0438\\u0437 \\u0441\\u0443\\u0445\\u043e\\u0444\\u0440\\u0443\\u043a\\u0442\\u043e\\u0432\", \"weight\": \"200\"}, {\"category\": \"\\u041d\\u0410\\u041f\\u0418\\u0422\\u041a\\u0418\", \"price\": 1.89, \"name\": \"\\u0427\\u0430\\u0439 \\u0447\\u0435\\u0440\\u043d\\u044b\\u0439 \", \"weight\": \"2\"}, {\"category\": \"\\u041d\\u0410\\u041f\\u0418\\u0422\\u041a\\u0418\", \"price\": 1.84, \"name\": \"\\u0427\\u0430\\u0439 \\u0437\\u0435\\u043b\\u0435\\u043d\\u044b\\u0439 \", \"weight\": \"2\"}, {\"category\": \"\\u0412\\u042b\\u041f\\u0415\\u0427\\u041a\\u0410\", \"price\": 4.35, \"name\": \"\\u041f\\u0438\\u0440\\u043e\\u0436\\u043a\\u0438 \\u043f\\u0435\\u0447\\u0435\\u043d\\u044b\\u0435 \\u0441 \\u043a\\u0430\\u0440\\u0442\\u043e\\u0444\\u0435\\u043b\\u0435\\u043c \", \"weight\": \"75\"}, {\"category\": \"\\u0412\\u042b\\u041f\\u0415\\u0427\\u041a\\u0410\", \"price\": 4.44, \"name\": \"\\u041f\\u0438\\u0440\\u043e\\u0436\\u043a\\u0438 \\u043f\\u0435\\u0447\\u0435\\u043d\\u044b\\u0435 \\u0441 \\u0440\\u0438\\u0441\\u043e\\u043c \\u044f\\u0439\\u0446\\u043e\\u043c\", \"weight\": \"75\"}, {\"category\": \"\\u0412\\u042b\\u041f\\u0415\\u0427\\u041a\\u0410\", \"price\": 4.19, \"name\": \"\\u0422\\u0440\\u0443\\u0431\\u043e\\u0447\\u043a\\u0430 \\u043f\\u0435\\u0441\\u043e\\u0447\\u043d\\u0430\\u044f\", \"weight\": \"50\"}, {\"category\": \"\\u0412\\u042b\\u041f\\u0415\\u0427\\u041a\\u0410\", \"price\": 1.63, \"name\": \"\\u0425\\u043b\\u0435\\u0431 \\u043f\\u0448\\u0435\\u043d\\u0438\\u0447\\u043d\\u044b\\u0439\", \"weight\": \"40\"}, {\"category\": \"\\u0412\\u042b\\u041f\\u0415\\u0427\\u041a\\u0410\", \"price\": 1.63, \"name\": \"\\u0425\\u043b\\u0435\\u0431 \\u0440\\u0436\\u0430\\u043d\\u043e\\u0439\", \"weight\": \"40\"}]}]}";


    /**
     * Create a private constructor because no one should ever create a {@link QueryUtils} object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).
     */
    private QueryUtils() {
    }

    /**
     * Query dataset and return a list of {@link Dish} objects.
     */
    public static List<Dish> fetchDishData(String requestUrl) {
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        // Create URL object
        URL url = createUrl(requestUrl);

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }

        // Extract relevant fields from the JSON response and create a list of {@link Dish}s
        List<Dish> dishes = extractFeatureFromJson(jsonResponse);

        // Return the list of {@link Dish}s
        return dishes;
    }

    /**
     * Returns new URL object from the given string URL.
     */
    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Problem building the URL ", e);
        }
        return url;
    }

    /**
     * Make an HTTP request to the given URL and return a String as the response.
     */
    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

//            Если url пустая строка
        if(url == null){
            Log.e("MainActivity","Dish - makeHttpRequest URL: " + url);
            return jsonResponse;
        }


        URL resourceUrl, base, next;
        String location;
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;

        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.connect();

//                Только если сервер ответил успехом делаем действия
//                Log.e("MainActivity","Dish - server response: " + urlConnection.getResponseCode());
//                if ( urlConnection.getResponseCode()==200 || urlConnection.getResponseCode()==301) {
//                    inputStream = urlConnection.getInputStream();
//                    jsonResponse = readFromStream(inputStream);
//                    Log.e("MainActivity","Dish - server response: " + jsonResponse);
//                }
//                http://stackoverflow.com/questions/1884230/urlconnection-doesnt-follow-redirect
            switch (urlConnection.getResponseCode())
            {
                case HttpURLConnection.HTTP_ACCEPTED:
                    inputStream = urlConnection.getInputStream();
                    jsonResponse = readFromStream(inputStream);
//                    redirecting the response
                case HttpURLConnection.HTTP_MOVED_PERM:
                case HttpURLConnection.HTTP_MOVED_TEMP:
                    location = urlConnection.getHeaderField("Location");
                    base     = url;
                    next     = new URL(base, location);  // Deal with relative URLs
                    url      = new URL(next.toExternalForm());
                    urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setRequestMethod("GET");
                    urlConnection.setReadTimeout(10000 /* milliseconds */);
                    urlConnection.setConnectTimeout(15000 /* milliseconds */);
                    urlConnection.connect();
                    inputStream = urlConnection.getInputStream();
                    jsonResponse = readFromStream(inputStream);
            }

//            else {
//                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
//            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the earthquake JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                // Closing the input stream could throw an IOException, which is why
                // the makeHttpRequest(URL url) method signature specifies than an IOException
                // could be thrown.
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    /**
     * Convert the {@link InputStream} into a String which contains the
     * whole JSON response from the server.
     */
    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    /**
     * Return a list of {@link Dish} objects that has been built up from
     * parsing a JSON response.
     */
    private static List<Dish> extractFeatureFromJson(String dishJSON) {
        // If the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty(dishJSON)) {
            return null;
        }

        // Create an empty ArrayList that we can start adding earthquakes to
        List<Dish> dishes = new ArrayList<>();

        // Try to parse the JSON response string. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {

            // Create a JSONObject from the JSON response string
            JSONObject baseJsonResponse = new JSONObject(dishJSON);

            JSONArray menuItemsArray = baseJsonResponse.getJSONArray("menu_items");

            if (menuItemsArray.length() > 0){

                for (int i = 0; i < menuItemsArray.length(); i++) {

                    JSONObject menuItem = menuItemsArray.getJSONObject(i);

                    // Извлекаем название блюда
                    String name = menuItem.getString("name");

                    // Извлекаем вес блюда
//                    double weight = properties.getDouble("weight");
                    double weight = menuItem.getDouble("price");

                    // извлекаем цену блюда
                    double price = menuItem.getDouble("price");

                    // Извлекаем категорию блюда
                    String category = menuItem.getString("category");

                    // Add the new {@link Dish} to the list of earthquakes.
                    dishes.add(new Dish(name, weight, price, category));
                }
            }

        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }

        // Return the list of earthquakes
        return dishes;
    }

}
