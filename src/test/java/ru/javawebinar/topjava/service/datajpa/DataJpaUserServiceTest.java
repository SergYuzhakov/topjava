package ru.javawebinar.topjava.service.datajpa;

import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.service.UserServiceTest;

import java.util.List;

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.*;

@ActiveProfiles(Profiles.DATAJPA)
public class DataJpaUserServiceTest extends UserServiceTest {
/*
    @Test
    public void getUserWithMeal() throws Exception {
        var actual = service.getUserWithMeal(ADMIN_ID);
        USER_MATCHER.assertMatch(actual, ADMIN);

       // var expected = List.of(ADMIN_MEAL1, ADMIN_MEAL2);
       // MEAL_MATCHER.assertMatch(actual.getMeals(), expected);
    }
@Test
    public void getUserWithoutMeals() throws Exception {
        User actual = service.getUserWithMeal(USER_WITHOUT_MEALS_ID);
        USER_MATCHER.assertMatch(actual, USER_WITHOUT_MEALS);
}

 */

}
