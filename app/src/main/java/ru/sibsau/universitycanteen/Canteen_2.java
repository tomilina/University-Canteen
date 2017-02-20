package ru.sibsau.universitycanteen;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;


public class Canteen_2 extends Fragment {

    public Canteen_2() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dish_list, container, false);

        // Dish array
        final ArrayList<Dish> dishes = QueryUtils.extractDishes(1);

//        final ArrayList<Dish> dishes = new ArrayList<Dish>();
////       create object inline and add new object to ArrayList
//        dishes.add(new Dish("Салат \"Новость\" с майонезом ", 80.2, 10.46, "Холодные"));
//        dishes.add(new Dish("Свекла с сыром, чесноком, майонезом", 7.8, 45.6, "Первые"));
//        dishes.add(new Dish("Каша молочная рисовая со сливочным маслом", 4.6,6.7, "Вторые"));
//        dishes.add(new Dish("Чай черный", 4.6,6.7, "Напитки"));
//        dishes.add(new Dish("Пирожки печеные с рисом яйцом", 4.6,6.7, "Выпечка"));
//        dishes.add(new Dish("Хлеб ржаной", 4.6,6.7, "Гарниры"));
//        dishes.add(new Dish("Салат \"Новость\" с майонезом ", 80.2, 10.46, "Холодные"));
//        dishes.add(new Dish("Свекла с сыром, чесноком, майонезом", 7.8, 45.6, "Первые"));
//        dishes.add(new Dish("Каша молочная рисовая со сливочным маслом", 4.6,6.7, "Вторые"));
//        dishes.add(new Dish("Чай черный", 4.6,6.7, "Напитки"));
//        dishes.add(new Dish("Пирожки печеные с рисом яйцом", 4.6,6.7, "Выпечка"));
//        dishes.add(new Dish("Хлеб ржаной", 4.6,6.7, "Гарниры"));

        // Create an {@link WordAdapter}, whose data source is a list of {@link Word}s. The
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
