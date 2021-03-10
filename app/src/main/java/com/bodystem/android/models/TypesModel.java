package com.bodystem.android.models;

public class TypesModel {
    String name;
    String id;

    public TypesModel() {

    }

    public TypesModel(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
