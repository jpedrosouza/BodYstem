package com.bodystem.android.models;

public class FoodsModel {
    String name;
    String type_id;
    String id;
    double calories;

    public FoodsModel() {

    }

    public FoodsModel(String name, String type_id, String id, double calories) {
        this.name = name;
        this.type_id = type_id;
        this.id = id;
        this.calories = calories;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType_id() {
        return type_id;
    }

    public void setType_id(String type_id) {
        this.type_id = type_id;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }
}
