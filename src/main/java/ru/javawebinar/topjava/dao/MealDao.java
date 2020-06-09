package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.List;

public interface MealDao {

    List<Meal> getMeals();

    Meal update(Meal meal);

    Meal getById(int id);

    Meal create(LocalDateTime dateTime, String description, int calories);

    void removeMeal(int id);

}
