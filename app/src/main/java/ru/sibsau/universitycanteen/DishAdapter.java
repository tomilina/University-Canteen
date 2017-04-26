package ru.sibsau.universitycanteen;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by user on 17.01.17.
 */

public class DishAdapter extends ArrayAdapter<Dish> {

//    private int mColorId;
//    , int colorId

    public DishAdapter(Activity context, ArrayList<Dish> dishes) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, dishes);
//        mColorId = colorId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Get the object located at this position in the list
        Dish currentDish = getItem(position);

        ImageView dishImage = (ImageView) listItemView.findViewById(R.id.image);
        dishImage.setImageResource(getCategoryImage(currentDish.getDishCategory()));


        // Find the TextView in the list_item.xml layout with the ID mowik word
        TextView nameTextView = (TextView) listItemView.findViewById(R.id.name_text_view);
        // Get the version name from the current AndroidFlavor object and
        // set this text on the name TextView
        nameTextView.setText(currentDish.getDishName());

        // Find the TextView in the list_item.xml layout with the ID version_number
        TextView priceTextView = (TextView) listItemView.findViewById(R.id.price_text_view);
        // Get the version number from the current AndroidFlavor object and
        // set this text on the number TextView
        priceTextView.setText(currentDish.getDishPrice());

        // Find the TextView in the list_item.xml layout with the ID version_number
        TextView defaultTextView = (TextView) listItemView.findViewById(R.id.weight_text_view);
        // Get the version number from the current AndroidFlavor object and
        // set this text on the number TextView
        defaultTextView.setText(currentDish.getDishWeight());

//        // Установим подходящий цвет подложки в сообветсвии с категорией
//        // Set the theme color for the list item
//        View textContainer = listItemView.findViewById(R.id.text_container);
//
//        int color = ContextCompat.getColor(getContext(), mColorId);
//        // Set the background color of the text container View
//        textContainer.setBackgroundColor(color);

        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return listItemView;
    }

    private int getCategoryImage(int category) {
        int categoryImageResourceId;
        switch (category) {
            case 1:
                categoryImageResourceId = R.drawable.salad;
                break;
            case 2:
                categoryImageResourceId = R.drawable.dishes;
                break;
            case 3:
                categoryImageResourceId = R.drawable.fried_chicken;
                break;
            case 4:
                categoryImageResourceId = R.drawable.porridge;
                break;
            case 5:
                categoryImageResourceId = R.drawable.hot_drink;
                break;
            case 6:
                categoryImageResourceId = R.drawable.bread;
                break;
            default:
                categoryImageResourceId = R.mipmap.ic_launcher;
                break;
        }
        return categoryImageResourceId;
    }
}
