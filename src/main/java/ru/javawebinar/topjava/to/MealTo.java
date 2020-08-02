package ru.javawebinar.topjava.to;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.time.LocalDateTime;
import java.util.Objects;

public class MealTo {
    private  Integer id;

    private  LocalDateTime dateTime;

    private  String description;

    private  int calories;

    private  boolean excess;

public MealTo(){

}

    public MealTo(@JsonProperty("id") Integer id,
                  @JsonProperty("dateTime") LocalDateTime dateTime,
                  @JsonProperty("description") String description,
                  @JsonProperty("calories") int calories,
                  @JsonProperty("excess") boolean excess) {
        this.id = id;
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.excess = excess;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;
        MealTo mealTo = (MealTo) obj;
        return getCalories() == mealTo.getCalories() &&
                isExcess() == mealTo.isExcess() &&
                Objects.equals(getId(), mealTo.getId()) &&
                Objects.equals(getDateTime(), mealTo.getDateTime()) &&
                Objects.equals(getDescription(), mealTo.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(),getDateTime(),getDescription(),getCalories(),isExcess());
    }

    public Integer getId() {
        return id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public boolean isExcess() {
        return excess;
    }

    @Override
    public String toString() {
        return "MealTo{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                ", excess=" + excess +
                '}';
    }
}
