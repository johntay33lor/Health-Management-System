package org.health;

import java.io.*;
import java.util.*;
import java.util.ArrayList;
import java.time.LocalTime;
import java.time.LocalDate;

public class HealthDataTracker {
    private Map<LocalDate, Integer> calorieIntakeMap;
    private Map<LocalDate, List<ExerciseActivity>> exerciseActivityMap;
    private Map<LocalDate, List<SleepRecord>> sleepRecordMap;

    private static final String CALORIE_INTAKE_FILE = "calorie_intake.dat";
    private static final String EXERCISE_ACTIVITY_FILE = "exercise_activity.dat";
    private static final String SLEEP_RECORD_FILE = "sleep_record.dat";

    public void HealthTracker() {
        calorieIntakeMap = new HashMap<>();
        exerciseActivityMap = new HashMap<>();
        sleepRecordMap = new HashMap<>();
        loadCalorieIntakeData();
        loadExerciseActivityData();
        loadSleepRecordData();
    }

    public void start() {
        Scanner input = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            System.out.println("Health Tracker");
            System.out.println("1. Record Calorie Intake");
            System.out.println("2. Record Exercise Activity");
            System.out.println("3. Record Sleep Record");
            System.out.println("4. Calculate Daily Caloric Balance");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = input.nextInt();
            input.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    System.out.println("Enter the date (yyyy-MM-dd): ");
                    String dateString = input.nextLine();
                    LocalDate date = LocalDate.parse(dateString);
                    inputCalorieIntake(date);
                    break;
                case 2:
                    System.out.println("Enter the date (yyyy-MM-dd): ");
                    dateString = input.nextLine();
                    date = LocalDate.parse(dateString);
                    inputExerciseActivity(date);
                    break;
                case 3:
                    System.out.println("Enter the date (yyyy-MM-dd): ");
                    dateString = input.nextLine();
                    date = LocalDate.parse(dateString);
                    inputSleepRecord(date);
                    break;
                case 4:
                    System.out.println("Enter the date (yyyy-MM-dd): ");
                    dateString = input.nextLine();
                    date = LocalDate.parse(dateString);
                    calculateDailyCaloricBalance(date);
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    public void inputCalorieIntake(LocalDate date) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter calorie intake for " + date + ": ");
        int calorieIntake = input.nextInt();
        calorieIntakeMap.put(date, calorieIntake);
        System.out.println("Calorie intake recorded for " + date);
        saveCalorieIntakeData();
    }

    public void inputExerciseActivity(LocalDate date) {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter exercise type: ");
        String exerciseType = input.nextLine();

        System.out.println("Enter exercise duration in minutes: ");
        int duration = input.nextInt();

        System.out.println("Enter calories burned: ");
        int caloriesBurned = input.nextInt();

        ExerciseActivity exerciseActivity = new ExerciseActivity(exerciseType, duration, caloriesBurned);

        List<ExerciseActivity> exerciseActivityList = exerciseActivityMap.getOrDefault(date, new ArrayList<>());
        exerciseActivityList.add(exerciseActivity);

        exerciseActivityMap.put(date, exerciseActivityList);
        System.out.println("Exercise activity recorded for " + date);
        saveExerciseActivityData();
    }

    public void inputSleepRecord(LocalDate date) {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter start time (HH:mm): ");
        String startTimeString = input.nextLine();
        LocalTime startTime = LocalTime.parse(startTimeString);

        System.out.println("Enter end time (HH:mm): ");
        String endTimeString = input.nextLine();
        LocalTime endTime = LocalTime.parse(endTimeString);

        SleepRecord sleepRecord = new SleepRecord(startTime, endTime);

        List<SleepRecord> sleepRecordList = sleepRecordMap.getOrDefault(date, new ArrayList<>());
        sleepRecordList.add(sleepRecord);

        sleepRecordMap.put(date, sleepRecordList);
        System.out.println("Sleep record recorded for " + date);
        saveSleepRecordData();
    }

    public void calculateDailyCaloricBalance(LocalDate date) {
        int calorieIntake = calorieIntakeMap.getOrDefault(date, 0);
        int caloriesBurned = 0;

        List<ExerciseActivity> exerciseActivityList = exerciseActivityMap.getOrDefault(date, new ArrayList<>());
        for (ExerciseActivity exerciseActivity : exerciseActivityList) {
            caloriesBurned += exerciseActivity.getCaloriesBurned();
        }

        int sleepDuration = 0;
        List<SleepRecord> sleepRecordList = sleepRecordMap.getOrDefault(date, new ArrayList<>());
        for (SleepRecord sleepRecord : sleepRecordList) {
            sleepDuration += sleepRecord.getDuration();
        }

        int caloricBalance = calorieIntake - caloriesBurned;

        System.out.println("Date: " + date);
        System.out.println("Calorie Intake: " + calorieIntake);
        System.out.println("Calories Burned: " + caloriesBurned);
        System.out.println("Sleep Duration: " + sleepDuration + " minutes");
        System.out.println("Caloric Balance: " + caloricBalance);
    }

    private void saveCalorieIntakeData() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\admin\\IdeaProjects\\Health_Management_System\\src\\main\\java\\org\\health\\calorie_intake.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(calorieIntakeMap);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            System.out.println("Failed to save calorie intake data to file.");
        }
    }

    private void saveExerciseActivityData() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\admin\\IdeaProjects\\Health_Management_System\\src\\main\\java\\org\\health\\exercise_activity.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(exerciseActivityMap);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            System.out.println("Failed to save exercise activity data to file.");
        }
    }

    private void saveSleepRecordData() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\admin\\IdeaProjects\\Health_Management_System\\src\\main\\java\\org\\health\\sleep_record.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(sleepRecordMap);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            System.out.println("Failed to save sleep record data to file.");
        }
    }

    public void calculateAverageSleepDuration(LocalDate startDate, LocalDate endDate) {
        List<SleepRecord> sleepRecords = new ArrayList<>();
        LocalDate currentDate = startDate;
        while (!currentDate.isAfter(endDate)) {
            List<SleepRecord> records = sleepRecordMap.getOrDefault(currentDate, new ArrayList<>());
            sleepRecords.addAll(records);
            currentDate = currentDate.plusDays(1);
        }
    }

    public void displayExerciseLog(LocalDate date) {
        List<ExerciseActivity> exerciseActivities = exerciseActivityMap.getOrDefault(date, new ArrayList<>());
        System.out.println("Exercise Log for " + date);
        for (ExerciseActivity activity : exerciseActivities) {
            System.out.println(activity);
        }
    }


    public void generateWeeklySummary(LocalDate startDate) {
        LocalDate endDate = startDate.plusWeeks(1).minusDays(1);
        int totalCaloriesConsumed = 0;
        int totalCaloriesBurned = 0;
        int totalSleepDuration = 0;
        Map<LocalDate, List<CalorieIntake>> calorieIntakeMap = new HashMap<>();

        List<CalorieIntake> calorieIntakes = new ArrayList<>();
        List<ExerciseActivity> exerciseActivities = new ArrayList<>();
        List<SleepRecord> sleepRecords = new ArrayList<>();

        LocalDate currentDate = startDate;
        while (!currentDate.isAfter(endDate)) {
            calorieIntakes.addAll(calorieIntakeMap.getOrDefault(currentDate, new ArrayList<>()));
            exerciseActivities.addAll(exerciseActivityMap.getOrDefault(currentDate, new ArrayList<>()));
            sleepRecords.addAll(sleepRecordMap.getOrDefault(currentDate, new ArrayList<>()));

            currentDate = currentDate.plusDays(1);
        }

        for (CalorieIntake calorieIntake : calorieIntakes) {
            totalCaloriesConsumed += calorieIntake.getNumOfCalories();
        }

        for (ExerciseActivity exerciseActivity : exerciseActivities) {
            totalCaloriesBurned += exerciseActivity.getCaloriesBurned();
        }

        for (SleepRecord sleepRecord : sleepRecords) {
            totalSleepDuration += sleepRecord.getDuration();
        }

        int averageSleepDuration = sleepRecords.isEmpty() ? 0 : totalSleepDuration / sleepRecords.size();

        System.out.println("Weekly Summary from " + startDate + " to " + endDate);
        System.out.println("Total Calories Consumed: " + totalCaloriesConsumed);
        System.out.println("Total Calories Burned: " + totalCaloriesBurned);
        System.out.println("Average Sleep Duration: " + averageSleepDuration + " hours");

    }
    @SuppressWarnings("unchecked")
    private void loadCalorieIntakeData() {
        try {
            FileInputStream fileInputStream = new FileInputStream("C:\\Users\\admin\\IdeaProjects\\Health_Management_System\\src\\main\\java\\org\\health\\calorie_intake.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            calorieIntakeMap = (Map<LocalDate, Integer>) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Failed to load calorie intake data from file.");
        }
    }

    @SuppressWarnings("unchecked")
    private void loadExerciseActivityData() {
        try {
            FileInputStream fileInputStream = new FileInputStream("C:\\Users\\admin\\IdeaProjects\\Health_Management_System\\src\\main\\java\\org\\health\\exercise_activity.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            exerciseActivityMap = (Map<LocalDate, List<ExerciseActivity>>) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Failed to load exercise activity data from file.");
        }
    }

    @SuppressWarnings("unchecked")
    private void loadSleepRecordData() {
        try {
            FileInputStream fileInputStream = new FileInputStream("C:\\Users\\admin\\IdeaProjects\\Health_Management_System\\src\\main\\java\\org\\health\\sleep_record.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            sleepRecordMap = (Map<LocalDate, List<SleepRecord>>) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Failed to load sleep record data from file.");
        }
    }
}