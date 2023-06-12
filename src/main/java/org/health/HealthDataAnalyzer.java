//package org.health;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//public class HealthDataAnalyzer {
//    private Map<LocalDate, List<CalorieIntake>> calorieIntakeMap;
//    private Map<LocalDate, List<ExerciseActivity>> exerciseActivityMap;
//    private Map<LocalDate, List<SleepRecord>> sleepRecordMap;
//
//    public HealthDataAnalyzer(
//            Map<LocalDate, List<CalorieIntake>> calorieIntakeMap,
//            Map<LocalDate, List<ExerciseActivity>> exerciseActivityMap,
//            Map<LocalDate, List<SleepRecord>> sleepRecordMap
//    ) {
//        this.calorieIntakeMap = calorieIntakeMap;
//        this.exerciseActivityMap = exerciseActivityMap;
//        this.sleepRecordMap = sleepRecordMap;
//    }
//
//    public void calculateDailyCaloricBalance(LocalDate date) {
//        List<CalorieIntake> calorieIntakeList = calorieIntakeMap.getOrDefault(date, new ArrayList<>());
//        List<ExerciseActivity> exerciseActivityList = exerciseActivityMap.getOrDefault(date, new ArrayList<>());
//
//        int consumedCalories = 0;
//        for (CalorieIntake calorieIntake : calorieIntakeList) {
//            consumedCalories += calorieIntake.getNumOfCalories();
//        }
//
//        int burnedCalories = 0;
//        for (ExerciseActivity exerciseActivity : exerciseActivityList) {
//            burnedCalories += exerciseActivity.getCaloriesBurned();
//        }
//
//        int dailyCaloricBalance = consumedCalories - burnedCalories;
//
//        System.out.println("Daily Caloric Balance on " + date + ": " + dailyCaloricBalance);
//    }
//
//    public void calculateAverageSleepDuration(LocalDate startDate, LocalDate endDate) {
//        List<SleepRecord> sleepRecords = new ArrayList<>();
//        LocalDate currentDate = startDate;
//        while (!currentDate.isAfter(endDate)) {
//            List<SleepRecord> records = sleepRecordMap.getOrDefault(currentDate, new ArrayList<>());
//            sleepRecords.addAll(records);
//            currentDate = currentDate.plusDays(1);
//        }
//
//        int totalSleepDuration = 0;
//        for (SleepRecord sleepRecord : sleepRecords) {
//            totalSleepDuration += sleepRecord.getDuration();
//        }
//
//        int averageSleepDuration = sleepRecords.isEmpty() ? 0 : totalSleepDuration / sleepRecords.size();
//
//        System.out.println("Average Sleep Duration from " + startDate + " to " + endDate + ": " + averageSleepDuration + " hours");
//    }
//
//    public void addExerciseActivity(LocalDate date, ExerciseActivity activity) {
//        List<ExerciseActivity> exerciseActivities = exerciseActivityMap.getOrDefault(date, new ArrayList<>());
//        exerciseActivities.add(activity);
//        exerciseActivityMap.put(date, exerciseActivities);
//        System.out.println("Exercise activity added for " + date);
//    }
//
//    public void displayExerciseLog(LocalDate date) {
//        List<ExerciseActivity> exerciseActivities = exerciseActivityMap.getOrDefault(date, new ArrayList<>());
//        System.out.println("Exercise Log for " + date);
//        for (ExerciseActivity activity : exerciseActivities) {
//            System.out.println(activity);
//        }
//    }
//
//    public void generateWeeklySummary(LocalDate startDate) {
//        LocalDate endDate = startDate.plusWeeks(1).minusDays(1);
//        int totalCaloriesConsumed = 0;
//        int totalCaloriesBurned = 0;
//        int totalSleepDuration = 0;
//
//        List<CalorieIntake> calorieIntakes = new ArrayList<>();
//        List<ExerciseActivity> exerciseActivities = new ArrayList<>();
//        List<SleepRecord> sleepRecords = new ArrayList<>();
//
//        LocalDate currentDate = startDate;
//        while (!currentDate.isAfter(endDate)) {
//            calorieIntakes.addAll(calorieIntakeMap.getOrDefault(currentDate, new ArrayList<>()));
//            exerciseActivities.addAll(exerciseActivityMap.getOrDefault(currentDate, new ArrayList<>()));
//            sleepRecords.addAll(sleepRecordMap.getOrDefault(currentDate, new ArrayList<>()));
//
//            currentDate = currentDate.plusDays(1);
//        }
//
//        for (CalorieIntake calorieIntake : calorieIntakes) {
//            totalCaloriesConsumed += calorieIntake.getNumOfCalories();
//        }
//
//        for (ExerciseActivity exerciseActivity : exerciseActivities) {
//            totalCaloriesBurned += exerciseActivity.getCaloriesBurned();
//        }
//
//        for (SleepRecord sleepRecord : sleepRecords) {
//            totalSleepDuration += sleepRecord.getDuration();
//        }
//
//        int averageSleepDuration = sleepRecords.isEmpty() ? 0 : totalSleepDuration / sleepRecords.size();
//
//        System.out.println("Weekly Summary from " + startDate + " to " + endDate);
//        System.out.println("Total Calories Consumed: " + totalCaloriesConsumed);
//        System.out.println("Total Calories Burned: " + totalCaloriesBurned);
//        System.out.println("Average Sleep Duration: " + averageSleepDuration + " hours");
//
//    }
//
//    public void generateMonthlySummary(LocalDate startDate) {
//        LocalDate endDate = startDate.plusMonths(1).minusDays(1);
//        int totalCaloriesConsumed = 0;
//        int totalCaloriesBurned = 0;
//        int totalSleepDuration = 0;
//
//        List<CalorieIntake> calorieIntakes = new ArrayList<>();
//        List<ExerciseActivity> exerciseActivities = new ArrayList<>();
//        List<SleepRecord> sleepRecords = new ArrayList<>();
//
//        LocalDate currentDate = startDate;
//        while (!currentDate.isAfter(endDate)) {
//            calorieIntakes.addAll(calorieIntakeMap.getOrDefault(currentDate, new ArrayList<>()));
//            exerciseActivities.addAll(exerciseActivityMap.getOrDefault(currentDate, new ArrayList<>()));
//            sleepRecords.addAll(sleepRecordMap.getOrDefault(currentDate, new ArrayList<>()));
//
//            currentDate = currentDate.plusDays(1);
//        }
//
//        for (CalorieIntake calorieIntake : calorieIntakes) {
//            totalCaloriesConsumed += calorieIntake.getNumOfCalories();
//        }
//
//        for (ExerciseActivity exerciseActivity : exerciseActivities) {
//            totalCaloriesBurned += exerciseActivity.getCaloriesBurned();
//        }
//
//        for (SleepRecord sleepRecord : sleepRecords) {
//            totalSleepDuration += sleepRecord.getDuration();
//        }
//
//        int averageSleepDuration = sleepRecords.isEmpty() ? 0 : totalSleepDuration / sleepRecords.size();
//
//        System.out.println("Monthly Summary from " + startDate + " to " + endDate);
//        System.out.println("Total Calories Consumed: " + totalCaloriesConsumed);
//        System.out.println("Total Calories Burned: " + totalCaloriesBurned);
//        System.out.println("Average Sleep Duration: " + averageSleepDuration + " hours");
//    }
//}
