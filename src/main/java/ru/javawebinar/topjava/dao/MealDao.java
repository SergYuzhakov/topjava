package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface MealDao {

    List<Meal> getMeals();

    Meal getById(int id);

    Meal save(Meal meal);

    void removeMeal(int id);

}
