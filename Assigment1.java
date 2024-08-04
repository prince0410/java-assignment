import java.util.ArrayList;
import java.util.Scanner;

// Class representing a student
class Student {
    private int studentID;
    private String name;
    private int age;
    private String department;

    // Constructor to initialize student details
    public Student(int studentID, String name, int age, String department) {
        this.studentID = studentID;
        this.name = name;
        this.age = age;
        this.department = department;
    }

    // Getter for student ID
    public int getStudentID() {
        return studentID;
    }

    // Getter for student name
    public String getName() {
        return name;
    }

    // Getter for student age
    public int getAge() {
        return age;
    }

    // Getter for student department
    public String getDepartment() {
        return department;
    }

    // Override toString method to display student details
    @Override
    public String toString() {
        return "Student ID: " + studentID + ", Name: " + name + ", Age: " + age + ", Department: " + department;
    }
}

// Class managing student records
class StudentRecordSystem {
    private ArrayList<Student> students;
    private int count;

    // Constructor to initialize the student list
    public StudentRecordSystem() {
        students = new ArrayList<>();
        count = 0;
    }

    // Method to add a new student to the record
    public void addStudent(Student student) {
        students.add(student);
        count++;
    }

    // Method to retrieve a student by their ID
    public Student getStudent(int studentID) {
        for (Student student : students) {
            if (student.getStudentID() == studentID) {
                return student;
            }
        }
        return null; // Return null if student not found
    }

    // Method to display all students in the record
    public void displayAllStudents() {
        for (Student student : students) {
            System.out.println(student);
        }
    }
}

// Main class to interact with the user
public class StudentRecordMGMT {
    public static void main(String[] args) {
        StudentRecordSystem system = new StudentRecordSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Display menu options to the user
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student by ID");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // Prompt user to enter student details
                    System.out.print("Enter Student ID: ");
                    int studentID = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();

                    System.out.print("Enter Age: ");
                    int age = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    System.out.print("Enter Department: ");
                    String department = scanner.nextLine();

                    // Create a new student and add to the system
                    Student student = new Student(studentID, name, age, department);
                    system.addStudent(student);
                    break;

                case 2:
                    // Display all student records
                    system.displayAllStudents();
                    break;

                case 3:
                    // Prompt user to enter student ID to search
                    System.out.print("Enter Student ID to search: ");
                    int searchID = scanner.nextInt();
                    Student foundStudent = system.getStudent(searchID);
                    if (foundStudent != null) {
                        System.out.println(foundStudent);
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;

                case 4:
                    // Exit the program
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    // Handle invalid menu choices
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}
