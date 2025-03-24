import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ManagerStudent {
	private static int max; // Maximum number of students
    private static int count = 0; // Counter for students added
    private static List<Student> listStudent = new ArrayList<>(); // List to store students
    private static Scanner sc = new Scanner(System.in); // Scanner for user input

    // Method to set maximum number of students
    public static void maxStudent() {
        while (true) {
            try {
                System.out.print("Enter the maximum number of students: ");
                max = sc.nextInt();
                if (max > 0) {
                    sc.nextLine();
                    break;
                } else {
                    System.out.println("Please enter a positive integer.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter an integer.");
                sc.nextLine();
            }
        }
    }

    // Method to add a new student  
    public static void addStudent() {  
        if (count < max) {  // Check if the student list has reached the maximum limit  
            String studentId;
            while (true) {
                try {
                    System.out.print("Enter student ID: ");  
                    studentId = sc.nextLine(); 

                    // Check if the input is empty
                    if (studentId.isEmpty()) {
                        throw new IllegalArgumentException("Error: Student ID cannot be empty! Please enter a valid ID.");
                    }

                    // Check if student ID already exists
                    boolean exists = false;
                    for (Student student : listStudent) {
                        if (student.getStudentId().equals(studentId)) {
                            exists = true;
                            break;
                        }
                    }

                    if (exists) {
                        System.out.println("Error: Student ID already exists! Please enter a unique ID.");
                    } else {
                        break; // Exit loop if ID is unique and not empty
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage()); // Display error message
                }
            }

            String studentName;
            while (true) {
                try {
                    System.out.print("Enter student name: ");  
                    studentName = sc.nextLine();

                    // Check if the input is empty
                    if (studentName.isEmpty()) {
                        throw new IllegalArgumentException("Error: Student name cannot be empty! Please enter a valid name.");
                    }

                    break; // Exit loop if input is valid
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage()); // Display error message
                }
            }

            double mark;
            while (true) {  
                try {  
                    System.out.print("Enter student mark (0-10): ");  
                    String input = sc.nextLine(); // Read input as String to check if empty
                    
                    // Check if input is empty
                    if (input.isEmpty()) {
                        System.out.println("Error: Mark cannot be empty! Please enter a valid mark.");
                        continue; // Prompt user again
                    }

                    mark = Double.parseDouble(input); // Convert input to double
                    
                    // Validate that the mark is within the allowed range (0-10)
                    if (mark >= 0 && mark <= 10) {  
                        sc.nextLine(); // Clear buffer  
                        break; // Exit loop if input is valid
                    } else {  
                        System.out.println("Error: Mark must be between 0 and 10.");  
                    }  
                } catch (Exception e) {  
                    System.out.println("Invalid input! Please enter a number between 0 and 10.");  
                    sc.nextLine(); // Clear invalid input from scanner  
                }  
            }  

            // Create a new Student object and add it to the list  
            Student student = new Student(studentId, studentName, mark);  
            listStudent.add(student);  
            count++; // Increase student count  
            System.out.println("Student added successfully!");  
        } else {  
            System.out.println("Cannot add more students, maximum limit reached.");  
        }  
    }

    // Method to display all students
    public static void printAllStudents() {
        if (listStudent.isEmpty()) {
            System.out.println("Student list is empty.");
        } else {           
            for (Student student : listStudent) {
                student.infoStudent();
            }
        }
    }
	
    // Method to update student information
    public static void updateInfoStudent() {
        while (true) {
            // Display update menu options
            System.out.println("------------------------------------");
            System.out.println("Please choose update method");
            System.out.println("1. Update by student ID");
            System.out.println("2. Update by student name");
            System.out.println("3. Exit");
            System.out.println("------------------------------------");

            int choice = 0;
            try {
                System.out.print("Enter your choice: ");
                choice = sc.nextInt();
                sc.nextLine(); // Consume newline character
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter an integer.");
                sc.nextLine(); // Clear input buffer
                continue; // Restart loop
            }

            if (choice == 3) { // Exit condition
                System.out.println("Exiting update menu...");
                return;
            }

            while (true) {
                // Prompt user to enter search term (ID or name) based on choice
                System.out.print("Enter " + (choice == 1 ? "student ID" : "student name") + " to update: ");
                String searchTerm = sc.nextLine().trim();

                if (searchTerm.isEmpty()) { // Check for empty input
                    System.out.println("Error: Input cannot be empty! Try again.");
                    continue;
                }

                boolean found = false;
                for (Student student : listStudent) { // Iterate through student list
                    if ((choice == 1 && student.getStudentId().equals(searchTerm)) ||
                        (choice == 2 && student.getStudentName().equalsIgnoreCase(searchTerm))) {

                        if (choice == 1) { // Update student name if searched by ID
                            System.out.print("Enter new student name: ");
                            String newStudentName = sc.nextLine().trim();
                            while (newStudentName.isEmpty()) { // Validate input
                                System.out.print("Error: Name cannot be empty! Enter again: ");
                                newStudentName = sc.nextLine().trim();
                            }
                            student.setStudentName(newStudentName);
                        }

                        if (choice == 2) { // Update student ID if searched by name
                            String newStudentId;
                            while (true) {
                                System.out.print("Enter new student ID: ");
                                newStudentId = sc.nextLine().trim();

                                if (newStudentId.isEmpty()) {
                                    System.out.println("Error: Student ID cannot be empty! Try again.");
                                    continue;
                                }

                                boolean idExists = false;
                                for (Student s : listStudent) {
                                    if (s.getStudentId().equals(newStudentId)) {
                                        idExists = true;
                                        break;
                                    }
                                }

                                if (idExists) {
                                    System.out.println("Error: Student ID already exists! Try another.");
                                } else {
                                    break;
                                }
                            }
                            student.setStudentId(newStudentId);
                        }

                        double mark;
                        while (true) { // Validate student mark input
                            try {
                                System.out.print("Enter new student mark (0-10): ");
                                mark = Double.parseDouble(sc.nextLine().trim());
                                if (mark >= 0 && mark <= 10)
                                    break; // Valid mark, exit loop
                                System.out.println("Error: Mark must be between 0 and 10.");
                            } catch (NumberFormatException e) {
                                System.out.println("Invalid input! Please enter a number between 0 and 10.");
                            }
                        }

                        student.setMark(mark); // Update student mark
                        System.out.println("Student information updated successfully!");
                        found = true;
                        break; // Exit loop once update is done
                    }
                }

                if (!found) { // Notify if no matching student is found
                    System.out.println("Student not found. Try again or enter 'exit' to return to the menu.");
                    String exitChoice = sc.nextLine().trim();
                    if (exitChoice.equalsIgnoreCase("exit")) {
                        return;
                    }
                } else {
                    break;
                }
            }
        }
    }
    
    // Method to delete information student
    public static void deleteStudent() {
        while (true) {
            // Display delete menu options
            System.out.println("------------------------------------");
            System.out.println("Please choose delete method");
            System.out.println("1. Delete by student ID");
            System.out.println("2. Delete by student name");
            System.out.println("3. Exit");
            System.out.println("------------------------------------");

            int choice;
            try {
                System.out.print("Enter your choice: ");
                choice = sc.nextInt();
                sc.nextLine(); // Consume newline character
                if (choice == 3) {
                    System.out.println("Returning to the main menu...");
                    return;
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number (1, 2, or 3). ");
                sc.nextLine(); // Clear input buffer
                continue; // Restart loop
            }

            boolean deleted = false;
            while (!deleted) {
                // Prompt user to enter student ID or name for deletion
                System.out.print("Enter " + (choice == 1 ? "student ID" : "student name") + " to delete: ");
                String searchTerm = sc.nextLine().trim();

                for (int i = 0; i < listStudent.size(); i++) {
                    Student student = listStudent.get(i);
                    // Check if student matches the input for deletion
                    if ((choice == 1 && student.getStudentId().equals(searchTerm)) ||
                        (choice == 2 && student.getStudentName().equalsIgnoreCase(searchTerm))) {
                        listStudent.remove(i); // Remove student from list
                        count--; // Decrement student count
                        System.out.println("Student deleted successfully.");
                        deleted = true;
                        break;
                    }
                }

                if (!deleted) { // If no student found, prompt user for retry or exit
                    System.out.println("Student not found. Try again or enter 'exit' to return to the menu.");
                    String exitChoice = sc.nextLine().trim();
                    if (exitChoice.equalsIgnoreCase("exit")) {
                        return; // Exit deletion process if user types 'exit'
                    }
                }
            }
        }
    }
    
    // Method to sort student
    public static void sortStudents() {
        while (true) {
            System.out.println("------------------------------------");
            System.out.println("Please choose sorting method");
            System.out.println("1. Sort by student ID");
            System.out.println("2. Sort by student name");
            System.out.println("3. Sort by student mark");
            System.out.println("4. Exit");
            System.out.println("------------------------------------");

            int choice = 0;
            try {
            	System.out.print("Enter your choice: ");
                choice = sc.nextInt(); // Read user input
                sc.nextLine(); // Consume the newline character
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter an integer.");
                sc.nextLine(); // Clear input buffer
                continue; // Restart loop to ask for input again
            }
            
            // Process user choice
            switch (choice) {
                case 1:
                    sortById(); // Sort by student ID
                    System.out.println("\nList of students sorted by student id: ");
                    printAllStudents();
                    break;
                case 2:
                    sortByName(); // Sort by student name
                    System.out.println("\nList of students sorted by student name: ");
                    printAllStudents();
                    break;
                case 3:
                    sortByMark(); // Sort by student mark
                    System.out.println("\nList of students sorted by student mark: ");
                    printAllStudents();
                    break;
                case 4:
                    System.out.println("Exiting sorting menu...");
                    return; // Exit the method
                default:
                    System.out.println("Invalid choice. Please choose again.");
                    continue;
            }
        }
    }

    // Sort the student list by ID using insertion sort
    private static void sortById() {
        for (int i = 1; i < listStudent.size(); i++) {
            Student key = listStudent.get(i);
            int j = i - 1;
            while (j >= 0 && listStudent.get(j).getStudentId().compareTo(key.getStudentId()) > 0) {
                listStudent.set(j + 1, listStudent.get(j));
                j--;
            }
            listStudent.set(j + 1, key);
        }
    }

    // Sort the student list by name using insertion sort
    private static void sortByName() {
        for (int i = 1; i < listStudent.size(); i++) {
            Student key = listStudent.get(i);
            int j = i - 1;
            while (j >= 0 && listStudent.get(j).getStudentName().compareToIgnoreCase(key.getStudentName()) > 0) {
                listStudent.set(j + 1, listStudent.get(j));
                j--;
            }
            listStudent.set(j + 1, key);
        }
    }

    // Sort the student list by marks using insertion sort
    private static void sortByMark() {
        for (int i = 1; i < listStudent.size(); i++) {
            Student key = listStudent.get(i);
            int j = i - 1;
            while (j >= 0 && listStudent.get(j).getMark() > key.getMark()) {
                listStudent.set(j + 1, listStudent.get(j));
                j--;
            }
            listStudent.set(j + 1, key);
        }
    }
 
	// Method to search for a student by ID or name
    public static void searchStudents() {
        while (true) {
            System.out.println("------------------------------------");
            System.out.println("Please choose searching method");
            System.out.println("1. Search by student name");
            System.out.println("2. Search by student ID");
            System.out.println("3. Exit");
            System.out.println("------------------------------------");

            int choice = 0;
            try {
                System.out.print("Enter your choice: ");
                choice = sc.nextInt();
                sc.nextLine(); // Consume newline
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter an integer.");
                sc.nextLine(); // Clear input buffer
                continue;
            }

            if (choice == 3) {
                System.out.println("Exiting search menu...");
                return; // Exit method
            } else if (choice == 1 || choice == 2) {
                while (true) { // Keep searching until student is found or user exits
                    System.out.print("Enter " + (choice == 1 ? "student name" : "student ID") + " to search: ");
                    String searchTerm = sc.nextLine().trim();
                    boolean found = false;

                    for (Student student : listStudent) {
                        if ((choice == 1 && student.getStudentName().equalsIgnoreCase(searchTerm)) ||
                            (choice == 2 && student.getStudentId().equals(searchTerm))) {
                            System.out.println("Student found:");
                            student.infoStudent();
                            found = true;
                            break;
                        }
                    }

                    if (found) {
                        break; // Exit loop if student is found
                    } else {
                        System.out.println("Student not found. Try again or enter 'exit' to return to the menu.");
                        String exitChoice = sc.nextLine().trim();
                        if (exitChoice.equalsIgnoreCase("exit")) {
                            return; // Exit search if user types 'exit'
                        }
                    }
                }
            } else {
                System.out.println("Invalid choice! Please enter 1, 2, or 3.");
            }
        }
    }
}
