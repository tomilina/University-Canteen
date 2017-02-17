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
            case "Холодные":
                dishCategoryCode = 1;
                break;
            case "Первые":
                dishCategoryCode = 2;
                break;
            case "Вторые":
                dishCategoryCode = 3;
                break;
            case "Гарниры":
                dishCategoryCode = 4;
                break;
            case "Напитки":
                dishCategoryCode = 5;
                break;
            case "Выпечка":
                dishCategoryCode = 6;
                break;
            default:
                dishCategoryCode = 0;
                break;
        }
        return dishCategoryCode;
    }

}
