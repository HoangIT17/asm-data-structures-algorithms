import java.util.Scanner;

public class AppControl {
    public void run() {
        Scanner sc = new Scanner(System.in);
        
        // Initialize the maximum number of students
        Student.maxStudent(); 

        boolean exit = false;
        while (!exit) {
            // Display menu options
            System.out.println("------------------------------------");
            System.out.println("Please select an option (1-7):");
            System.out.println("1. Add a student");
            System.out.println("2. Display all students");
            System.out.println("3. Update student information");
            System.out.println("4. Delete a student");
            System.out.println("5. Sort students");
            System.out.println("6. Search for a student");
            System.out.println("7. Exit");
            System.out.println("------------------------------------");
            
            int choice;
            // Loop until a valid choice is entered
            while (true) {
                try {
                    System.out.print("Enter your choice: ");
                    choice = sc.nextInt();

                    if (choice >= 1 && choice <= 7) {
                        break; // Exit the loop if the input is valid
                    } else {
                        System.out.println("Invalid choice! Please enter a number between 1 and 7.");
                    }
                } catch (Exception e) {
                    System.out.println("Invalid input! Please enter a number between 1 and 7.");
                    sc.nextLine(); // Clear invalid input
                }
            }
            
            // Handle user choice
            switch (choice) {
                case 1: 
                    Student.addStudent(); // Add a new student
                    break;
                case 2:
                    Student.printAllStudents(); // Display all student details
                    break;
                case 3:
                    Student.updateInfoStudent(); // Update student information
                    break;
                case 4:
                    Student.deleteStudent(); // Remove a student
                    break;
                case 5:
                    Student.sortStudents(); // Sort students
                    break;
                case 6:
                    Student.searchStudents(); // Search for a student
                    break;
                case 7:
                	System.out.println("Exiting program...");
                    exit = true; // Exit the program
                    break;
                default:
                    System.out.println("Invalid choice, please select between 1 and 7.");
            }
        }
        sc.close(); // Close scanner
    }
}
