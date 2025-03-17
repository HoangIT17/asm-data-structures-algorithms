import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Student class to manage student details
public class Student {
    private String studentId;
    private String studentName;
    private double mark;

    // Constructor to initialize student details
    public Student(String studentId, String studentName, double mark) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.mark = mark;
    }

    // Getter and Setter methods for student attributes
    public String getStudentId() {
        return this.studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return this.studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public double getMark() {
        return this.mark;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }

    // Display student information along with ranking
    public void infoStudent() {
        System.out.println(this.studentId + " - " + this.studentName + " - " + this.mark + " - " + getRanking());
    }

    // Determine ranking based on the mark
    public String getRanking() {
        if (this.mark > 0 && this.mark < 5.0) {
            return "Fail";
        } else if (this.mark >= 5.0 && this.mark < 6.5) {
            return "Medium";
        } else if (this.mark >= 6.5 && this.mark < 7.5) {
            return "Good";
        } else if (this.mark >= 7.5 && this.mark < 9.0) {
            return "Very Good";
        } else if (this.mark >= 9.0 && this.mark <= 10.0) {
            return "Excellent";
        } else {
            return "Enter invalid mark";
        }
    }

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
                    studentId = sc.nextLine(); // Keep raw input without using trim()

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
            System.out.println("\nStudent List: ");
            for (Student student : listStudent) {
                student.infoStudent();
            }
        }
    }
	
    // Method to update information student
    public static void updateInfoStudent() {
        // Prompt the user to enter the Student ID that needs to be updated
        System.out.print("Enter student ID to update: ");
        String studentId = sc.nextLine().trim(); // Read input and trim leading/trailing spaces
        boolean found = false; // Flag to check if student exists

        // Validate if the Student ID is empty
        if (studentId.isEmpty()) {
            System.out.println("Error: Student ID cannot be empty!"); // Display error message
            return; // Exit the method
        }

        // Iterate through the list of students to find the matching Student ID
        for (Student student : listStudent) {
            if (student.getStudentId().equals(studentId)) {
                String studentName;

                // Loop until a valid student name is entered
                while (true) {
                    try {
                        System.out.print("Enter new student name: ");
                        studentName = sc.nextLine().trim(); // Read input and trim spaces

                        // Validate if student name is empty
                        if (studentName.isEmpty()) {
                            throw new IllegalArgumentException("Error: Student name cannot be empty! Please enter a valid name.");
                        }
                        break; // Exit loop if valid input is provided
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage()); // Display error message and retry input
                    }
                }

                double mark; // Variable to store the student mark

                // Loop until a valid mark is entered
                while (true) {  
                    try {  
                        System.out.print("Enter new student mark (0-10): ");  
                        mark = sc.nextDouble(); // Read student mark input

                        // Validate if the mark is within the allowed range
                        if (mark >= 0 && mark <= 10) {  
                            sc.nextLine(); // Clear scanner buffer
                            break; // Exit loop if valid input is provided  
                        } else {  
                            System.out.println("Error: Mark must be between 0 and 10."); // Display error message
                        }  
                    } catch (Exception e) {  
                        System.out.println("Invalid input! Please enter a number between 0 and 10."); // Handle non-numeric inputs
                        sc.nextLine(); // Clear invalid input from scanner buffer  
                    }  
                }  

                // Update the student details with new values
                student.setStudentName(studentName);
                student.setMark(mark);
                System.out.println("Student information updated successfully!"); // Confirmation message
                found = true; // Set flag to indicate student was found
                break; // Exit the loop since the student is found
            }
        }

        // If no student with the given ID was found, display an error message
        if (!found) {
            System.out.println("Error: Student ID not found in the system.");
        }
    }

 // Method to delete information student
    public static void deleteStudent() {
        System.out.println("------------------------------------");
        System.out.println("Please choose delete method");
        System.out.println("1. Delete by student ID");
        System.out.println("2. Delete by student name");
        System.out.println("3. Exit");
        System.out.println("------------------------------------");

        int choice;

        // Loop until the user enters a valid choice (1, 2, or 3)
        while (true) {
            try {
                choice = sc.nextInt(); // Read user input
                sc.nextLine(); // Consume the newline character

                if (choice == 1 || choice == 2 || choice == 3) {
                    break; // Exit the loop if input is valid
                } else {
                    System.out.println("Invalid choice. Please enter 1, 2, or 3.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number (1, 2, or 3).");
                sc.nextLine(); // Clear the input buffer
            }
        }

        // Exit if user chooses option 3
        if (choice == 3) {
            System.out.println("Returning to the main menu...");
            return;
        }

        boolean deleted = false;

        // Loop until a valid student is found and deleted
        while (!deleted) {
            // Ask for student information based on the chosen delete method
            System.out.print("Enter the student information to delete (Student ID or Student Name): ");
            String searchTerm = sc.nextLine();

            // Iterate through the student list to find and delete the matching student
            for (int i = 0; i < listStudent.size(); i++) {
                Student student = listStudent.get(i);
                if ((choice == 1 && student.getStudentId().equals(searchTerm)) ||
                    (choice == 2 && student.getStudentName().equalsIgnoreCase(searchTerm))) {
                    listStudent.remove(i); // Remove the student from the list
                    count--; // Decrease the student count
                    System.out.println("Student deleted successfully.");
                    deleted = true;
                    break; // Exit loop after deleting
                }
            }

            // If no student was found, ask for input again
            if (!deleted) {
                System.out.println("Student not found. Please enter a valid student ID or name.");
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
                    printAllStudents();
                    break;
                case 2:
                    sortByName(); // Sort by student name
                    printAllStudents();
                    break;
                case 3:
                    sortByMark(); // Sort by student mark
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
                    System.out.println("Enter search term (student name or student ID):");
                    String searchTerm = sc.nextLine();
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
                        String exitChoice = sc.nextLine();
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





	
