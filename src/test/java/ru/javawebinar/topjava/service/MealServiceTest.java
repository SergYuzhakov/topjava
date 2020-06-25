package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {
 /*
    static {
        // Only for postgres driver logging
        // It uses java.util.logging and logged via jul-to-slf4j bridge
        SLF4JBridgeHandler.install();
    }
   Вместо этого статичного блока можно использовать метод Spring  -  MethodInvokingBean, прописав его в spring-db.xml
   */

    @Autowired
    private MealService mealService;
    @Autowired
    private MealRepository mealRepository;


    @Test
    public void get() {
        Meal meal = mealService.get(MEAL_ID_1, USER_ID);
        assertMatch(meal, MealTestData.MEAL_USER_1);
    }

    @Test
    public void getForeignMeals() {
        assertThrows(NotFoundException.class, () -> mealService.get(MEAL_ID_ADMIN_1, USER_ID));
    }

    @Test
    public void delete() {
        mealService.delete(MEAL_ID_1, USER_ID);
        assertNull(mealRepository.get(MEAL_ID_1, USER_ID));
    }

    @Test
    public void deleteForeignMeals() {
        assertThrows(NotFoundException.class, () -> mealService.delete(MEAL_ID_1, MEAL_ID_ADMIN_1));
    }

    @Test
    public void getBetweenInclusive() {
        List<Meal> mealBetweenInclusive = mealService.getBetweenInclusive(LocalDate.of(2020, 1, 30), LocalDate.of(2020, 1, 30), USER_ID);
        assertMatch(mealBetweenInclusive, MEAL_USER_3, MEAL_USER_2, MEAL_USER_1);
    }

    @Test
    public void getAll() {
        List<Meal> allMealUser = mealService.getAll(USER_ID);
        assertMatch(allMealUser, MEAL_USER_7, MEAL_USER_6, MEAL_USER_5, MEAL_USER_4, MEAL_USER_3, MEAL_USER_2, MEAL_USER_1);
    }

    @Test
    public void update() {
        Meal meal = MealTestData.getUpdated();
        mealService.update(meal, USER_ID);
        assertMatch(mealService.get(MEAL_ID_1, USER_ID), getUpdated()); // сравниваем апдейтнутую еду с эталоном(getUpdated())
    }

    @Test
    public void updateForeignMeals() {
        assertThrows(NotFoundException.class, () -> mealService.update(MEAL_ADMIN_1, USER_ID));
    }

    @Test
    public void create() {
        Meal mealCreated = mealService.create(getNew(), USER_ID);
        Integer newId = mealCreated.getId();
        Meal meal = MealTestData.getNew();
        meal.setId(newId);
        assertMatch(mealCreated, meal); // сравниваем вновь созданную еду с эталоной новой едой
        assertMatch(mealRepository.get(meal.getId(), USER_ID), meal);
    }

    @Test
    public void duplicateDateTime(){
        Meal meal = new Meal(null, LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак1", 400);
        assertThrows(DataAccessException.class,() -> mealService.create(meal, USER_ID));
    }
}