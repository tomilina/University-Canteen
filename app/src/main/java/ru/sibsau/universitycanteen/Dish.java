package ru.sibsau.universitycanteen;

/**
 * Created by user on 17.01.17.
 */

public class Dish {

    private String mDishName;
    private double mDishWeight;
    private double mDishPrice;
    private String mDishCategory;

//    Конструктор
    public Dish(String dishName, double dishWeight, double dishPrice, String dishCategory){
        mDishName = dishName;
        mDishWeight = dishWeight;
        mDishPrice = dishPrice;
        mDishCategory = dishCategory;
    }

    public String getDishName() { return mDishName;}
    public String getDishWeight() { return mDishWeight + " г.";}
    public String getDishPrice() { return mDishPrice + " руб.";}
    public int getDishCategory() {
        int dishCategoryCode;
        switch (mDishCategory) {
            case "ХОЛОДНЫЕ БЛЮДА":
                dishCategoryCode = 1;
                break;
            case "1-е БЛЮДА":
                dishCategoryCode = 2;
                break;
            case "2-е БЛЮДА":
                dishCategoryCode = 3;
                break;
            case "ГАРНИРЫ":
                dishCategoryCode = 4;
                break;
            case "НАПИТКИ":
                dishCategoryCode = 5;
                break;
            case "ВЫПЕЧКА":
                dishCategoryCode = 6;
                break;
            default:
                dishCategoryCode = 0;
                break;
        }
        return dishCategoryCode;
    }

}
