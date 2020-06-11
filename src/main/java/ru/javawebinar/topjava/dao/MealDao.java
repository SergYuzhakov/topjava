package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface MealDao {

    List<Meal> getMeals();

    boolean update(Meal meal);

    Meal getById(int id);

    Meal create(Meal meal);

    void removeMeal(int id);

}
