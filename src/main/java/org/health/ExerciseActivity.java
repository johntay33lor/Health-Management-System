package org.health;

public class ExerciseActivity {
    private String exerciseType;
    private int duration;
    private int caloriesBurned;

    public ExerciseActivity(String exerciseType, int duration, int caloriesBurned) {
        this.exerciseType = exerciseType;
        this.duration = duration;
        this.caloriesBurned = caloriesBurned;
    }

    public String getActivity() {

        return exerciseType;
    }

    public int getDurationInMinutes() {

        return duration;
    }

    public int getCaloriesBurned() {

        return caloriesBurned;
    }

    public String getType() {
        return exerciseType;
    }
}
