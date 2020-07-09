package ru.javawebinar.topjava.service.datajpa;

import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.UserTestData;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.service.UserServiceTest;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.List;

import static org.junit.Assert.assertThrows;
import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.*;

@ActiveProfiles(Profiles.DATAJPA)
public class DataJpaUserServiceTest extends UserServiceTest {

    @Test
    public void getUserWithMeal() throws Exception {
        var user = service.getUserWithMeal(ADMIN_ID);
        USER_MATCHER.assertMatch(ADMIN, user);
        var meals = List.of(ADMIN_MEAL2, ADMIN_MEAL1);
        MEAL_MATCHER.assertMatch(meals, user.getMeals());
    }

    @Test
    public void getUserNotFoundMeals() throws Exception {
        assertThrows(NotFoundException.class, () -> service.getUserWithMeal(UserTestData.NOT_FOUND));

    }
/*
    @Test
    public void getUserWithoutMeals() throws Exception{
        var user = service.getUserWithMeal(USER_WITHOUT_MEALS_ID);
        USER_MATCHER.assertMatch(USER_WITHOUT_MEALS, user);
    }
*/

}
