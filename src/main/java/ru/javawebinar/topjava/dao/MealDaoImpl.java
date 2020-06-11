package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MealDaoImpl implements MealDao {
    private final AtomicInteger autoId = new AtomicInteger(1);
    private Map<Integer, Meal> mealDaoMap = new ConcurrentHashMap<>();

    public MealDaoImpl() {
        List<Meal> listMeals = Arrays.asList(
                new Meal(0, LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
                new Meal(0, LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
                new Meal(0, LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
                new Meal(0, LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
                new Meal(0, LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
                new Meal(0, LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
                new Meal(0, LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410));
        for (Meal meal : listMeals) {
            save(meal);
        }

    }

    @Override
    public List<Meal> getMeals() {
        return new ArrayList<>(mealDaoMap.values());
    }

    @Override
    public void removeMeal(int id) {
        mealDaoMap.remove(id);

    }

    @Override
    public Meal getById(int id) {
        return mealDaoMap.get(id);
    }

    @Override
    public Meal save(Meal meal) {
        if (meal.isNew()) {
            meal.setId(autoId.getAndIncrement());
        }
        return mealDaoMap.put(meal.getId(), meal);
    }
}
