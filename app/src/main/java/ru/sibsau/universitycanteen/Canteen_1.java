package ru.sibsau.universitycanteen;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;


public class Canteen_1 extends Fragment {

    public Canteen_1() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.dish_list, container, false);
        // Dish array
        final ArrayList<Dish> dishes = QueryUtils.extractDishes(0);

        // Create an {@link DishAdapter}, whose data source is a list of {@link Dish}s. The
        // adapter knows how to create list items for each item in the list.
        DishAdapter adapter = new DishAdapter(getActivity(), dishes);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // word_list.xml layout file.
        ListView listView = (ListView) rootView.findViewById(R.id.list);

        // Make the {@link ListView} use the {@link WordAdapter} we created above, so that the
        // {@link ListView} will display list items for each {@link Word} in the list.
        listView.setAdapter(adapter);
        return rootView;
    }
}
