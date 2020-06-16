package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.slf4j.LoggerFactory.getLogger;
import static ru.javawebinar.topjava.util.MealsUtil.DEFAULT_CALORIES_PER_DAY;

public class SecurityUtil {
    private static final Logger log = getLogger(SecurityUtil.class);
    private static Integer userId = 1;

    public static int authUserId() {
        return  userId;
    }

    public static void setUserId(int id){
        log.info("Set authUserId = {}", id);
        SecurityUtil.userId = id;
    }

    public static int authUserCaloriesPerDay() {
        return DEFAULT_CALORIES_PER_DAY;
    }
}