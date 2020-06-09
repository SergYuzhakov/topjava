package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MealDaoImpl implements MealDao {
    private static final AtomicInteger AUTO_ID = new AtomicInteger(1);
    private static Map<Integer, Meal> mealDaoMap = new ConcurrentHashMap<>();

    static {

        Meal meal1 = new Meal(AUTO_ID.getAndIncrement(), LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500);
        mealDaoMap.put(meal1.getId(), meal1);

        Meal meal2 = new Meal(AUTO_ID.getAndIncrement(), LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000);
        mealDaoMap.put(meal2.getId(), meal2);

        Meal meal3 = new Meal(AUTO_ID.getAndIncrement(), LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500);
        mealDaoMap.put(meal3.getId(), meal3);

        Meal meal4 = new Meal(AUTO_ID.getAndIncrement(), LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100);
        mealDaoMap.put(meal4.getId(), meal4);

        Meal meal5 = new Meal(AUTO_ID.getAndIncrement(), LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000);
        mealDaoMap.put(meal5.getId(), meal5);

        Meal meal6 = new Meal(AUTO_ID.getAndIncrement(), LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500);
        mealDaoMap.put(meal6.getId(), meal6);

        Meal meal7 = new Meal(AUTO_ID.getAndIncrement(), LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410);
        mealDaoMap.put(meal7.getId(), meal7);

    }

    @Override
    public List<Meal> getMeals() {
        return new ArrayList<>(mealDaoMap.values());
    }

    @Override
    public Meal update(Meal meal) {
        mealDaoMap.merge(meal.getId(), meal, ((value1, value2) -> value2));
        return meal;
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
    public Meal create(LocalDateTime dateTime, String description, int calories) {
        Meal meal = new Meal(AUTO_ID.getAndIncrement(), dateTime, description, calories);
        mealDaoMap.put(meal.getId(), meal);
        return meal;
    }
}
