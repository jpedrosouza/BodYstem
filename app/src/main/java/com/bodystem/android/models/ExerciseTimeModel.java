package com.bodystem.android.models;


public class ExerciseTimeModel {

    // CALORIAS POR MINUTO DE CADA ESPORTE
    public static final double CAMINHADA = 5.5;
    public static final double CORRIDA = 10;
    public static final double MOUNTAIN_BIKE = 12;
    public static final double COZINHAR = 2.8;
    public static final double DORMIR = 1;
    public static final double ESTUDAR = 2;
    public static final double FUTEBOL = 9;

    double calories;

    public ExerciseTimeModel() {

    }

    public ExerciseTimeModel(double calories) {
        this.calories = calories;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public double getCaminhadaTime() {
        return this.calories / CAMINHADA;
    }

    public double getCorridaTime() {
        return this.calories / CORRIDA;
    }

    public double getMountainBikeTime() {
        return this.calories / MOUNTAIN_BIKE;
    }

    public double getCozinharTime() {
        return this.calories / COZINHAR;
    }

    public double getDormirTime() {
        return this.calories / DORMIR;
    }

    public double getEstudarTime() {
        return this.calories / ESTUDAR;
    }

    public double getFutebolTime() {
        return this.calories / FUTEBOL;
    }
}
