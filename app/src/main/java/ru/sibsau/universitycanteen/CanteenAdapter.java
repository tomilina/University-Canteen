package ru.sibsau.universitycanteen;

import android.app.Activity;
import android.content.res.Resources;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by user on 26.04.17.
 */

public class CanteenAdapter extends ArrayAdapter<Canteen> {

    public CanteenAdapter(Activity context, ArrayList<Canteen> canteens) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, canteens);
//        mColorResourceId = colorResourceId;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.canteen_item, parent, false);

            listItemView.setMinimumHeight((Resources.getSystem().getDisplayMetrics().heightPixels-300)/3);
        }

        // Get the object located at this position in the list
        Canteen currentCanteen = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID mowik word
        TextView nameTextView = (TextView) listItemView.findViewById(R.id.canteen_name);
        // Get the version name from the current AndroidFlavor object and
        // set this text on the name TextView
        nameTextView.setText(currentCanteen.getCanteenName());

        // Find the TextView in the list_item.xml layout with the ID version_number
        TextView priceTextView = (TextView) listItemView.findViewById(R.id.canteen_location);
        // Get the version number from the current AndroidFlavor object and
        // set this text on the number TextView
        priceTextView.setText(currentCanteen.getCanteenLocation());

        // Find the TextView in the list_item.xml layout with the ID version_number
        TextView defaultTextView = (TextView) listItemView.findViewById(R.id.canteen_working_hours);
        // Get the version number from the current AndroidFlavor object and
        // set this text on the number TextView
        defaultTextView.setText(currentCanteen.getCanteenWorkingHours());
        int color;
        switch (currentCanteen.getCanteenCode()){
            case "1":
                color = ContextCompat.getColor(getContext(), R.color.colorCanteen1);
                // Set the background color of the text container View
                listItemView.setBackgroundColor(color);
                break;
            case "2":
                color = ContextCompat.getColor(getContext(), R.color.colorCanteen2);
                // Set the background color of the text container View
                listItemView.setBackgroundColor(color);
                break;
            case "3":
                color = ContextCompat.getColor(getContext(), R.color.colorCanteen3);
                // Set the background color of the text container View
                listItemView.setBackgroundColor(color);
                break;
            case "4":
                color = ContextCompat.getColor(getContext(), R.color.colorBufetP);
                // Set the background color of the text container View
                listItemView.setBackgroundColor(color);
                break;
            case "5":
                color = ContextCompat.getColor(getContext(), R.color.colorBufetL);
                // Set the background color of the text container View
                listItemView.setBackgroundColor(color);
                break;
            default:
                color = ContextCompat.getColor(getContext(), R.color.colorPrimary);
                TextView hourstTextView = (TextView) listItemView.findViewById(R.id.hours);
                hourstTextView.setText("");
                // Set the background color of the text container View
                listItemView.setBackgroundColor(color);
                break;
        }

        return listItemView;
    }
}
