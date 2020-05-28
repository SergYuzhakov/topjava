package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExcess;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> meals = Arrays.asList(
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
        );

        List<UserMealWithExcess> mealsTo = filteredByCycles(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        mealsTo.forEach(System.out::println);

        System.out.println(filteredByStreams(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000));
    }

    public static List<UserMealWithExcess> filteredByCycles(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> userMealGroupByDay = new HashMap<>();

        for (UserMeal user : meals) {
            userMealGroupByDay.merge(user.getDateTime().toLocalDate(), user.getCalories(), Integer::sum);
        }

        List<UserMealWithExcess> listUserMealWithExcess = new ArrayList<>();

        for (UserMeal user : meals) {
            LocalDate ld = user.getDateTime().toLocalDate();
            LocalTime lt = user.getDateTime().toLocalTime();
            if (TimeUtil.isBetweenHalfOpen(lt, startTime, endTime)) {
                int sum = userMealGroupByDay.get(ld);
                boolean excess = sum > caloriesPerDay;
                listUserMealWithExcess.add(new UserMealWithExcess(user.getDateTime(), user.getDescription(), user.getCalories(), excess));
            }
        }
        return listUserMealWithExcess;
    }

    public static List<UserMealWithExcess> filteredByStreams(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

        Map<LocalDate, Integer> userMealGroupByDay = meals.stream()
                .collect(Collectors.groupingBy(meal -> meal.getDateTime().toLocalDate(),Collectors.summingInt(UserMeal::getCalories)));

        return meals.stream()
                .filter(userMeal ->
                        TimeUtil.isBetweenHalfOpen(userMeal.getDateTime().toLocalTime(), startTime, endTime))
                .map(filteredUserMeal -> {
                    int sum = userMealGroupByDay.get(filteredUserMeal.getDateTime().toLocalDate());
                    boolean excess = sum > caloriesPerDay;
                    return new UserMealWithExcess(filteredUserMeal.getDateTime(), filteredUserMeal.getDescription(), filteredUserMeal.getCalories(), excess);
                })
                .collect(Collectors.toList());
    }
}
