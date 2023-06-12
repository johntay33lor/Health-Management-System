package org.health;
import java.time.LocalDate;
import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        UserManagement userManagement = new UserManagement();
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("1: Create User");
            System.out.println("2: Login");
            System.out.println("3: Exit");
            System.out.println("Please enter your choice.");
            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Enter username: ");
                    String username = input.nextLine();
                    try {
                        userManagement.createUser(username);
                        System.out.println("User created successfully!");
                    } catch (UserCreationException e) {
                        System.out.println("Error creating user: " + e.getMessage());
                    }
                    break;

                case 2:
                    System.out.println("Enter username: ");
                    String loginUsername = input.nextLine();
                    try {
                        User user = userManagement.loginUser(loginUsername);
                        if (user != null) {
                            System.out.println("Logged in as " + user.getUsername());
                            showMenu();
                            handleMenuOptions(user, input);
                        } else {
                            System.out.println("Invalid username. Please try again.");
                        }
                    } catch (UserLoginException e) {
                        System.out.println("Error logging in: " + e.getMessage());
                    }
                    break;

                case 3:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    public static void showMenu() {
        System.out.println();
        System.out.println("Options:");
        System.out.println("1. Enter calorie intake");
        System.out.println("2. Enter exercise activity");
        System.out.println("3. Enter sleep record");
        System.out.println("4. Daily Caloric Balance");
        System.out.println("5. Sleep Analysis");
        System.out.println("6. Exercise Log");
        System.out.println("7. Health Summary");
        System.out.println("8. Exit");
    }

    public static void handleMenuOptions(User user, Scanner input) {
        HealthDataTracker tracker = new HealthDataTracker();

        boolean exit = false;
        while (!exit) {
            System.out.print("Select an option: ");
            int option = input.nextInt();
            input.nextLine(); // Consume newline character

            LocalDate currentDate = LocalDate.now();

            switch (option) {
                case 1:
                    tracker.inputCalorieIntake(currentDate);
                    break;
                case 2:
                    tracker.inputExerciseActivity(currentDate);
                    break;
                case 3:
                    tracker.inputSleepRecord(currentDate);
                    break;
                case 4:
                    tracker.calculateDailyCaloricBalance(currentDate);
                    break;
                case 5:
                    System.out.println("Enter start date (YYYY-MM-DD): ");
                    LocalDate startDate = LocalDate.parse(input.nextLine());
                    System.out.println("Enter end date (YYYY-MM-DD): ");
                    LocalDate endDate = LocalDate.parse(input.nextLine());
                    tracker.calculateAverageSleepDuration(startDate, endDate);
                    break;
                case 6:
                    tracker.displayExerciseLog(currentDate);
                    break;
                case 7:
                    System.out.println("Enter start date (YYYY-MM-DD): ");
                    LocalDate summaryStartDate = LocalDate.parse(input.nextLine());
                    tracker.generateWeeklySummary(summaryStartDate);
                    break;
                case 8:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option. Please enter a number between 1 and 8.");
                    break;
            }

            System.out.println();
        }
    }
}



