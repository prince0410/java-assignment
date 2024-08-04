import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

// Class representing a student
class Student {
    private int studentID;
    private String name;

    // Constructor to initialize student details
    public Student(int studentID, String name) {
        this.studentID = studentID;
        this.name = name;
    }

    // Getter for student ID
    public int getStudentID() {
        return studentID;
    }

    // Getter for student name
    public String getName() {
        return name;
    }

    // Override toString method to display student details
    @Override
    public String toString() {
        return "Student ID: " + studentID + ", Name: " + name;
    }
}

// Class representing a grade
class Grade {
    private int studentID;
    private int courseID;
    private char grade;

    // Constructor to initialize grade details
    public Grade(int studentID, int courseID, char grade) {
        this.studentID = studentID;
        this.courseID = courseID;
        this.grade = grade;
    }

    // Getter for student ID
    public int getStudentID() {
        return studentID;
    }

    // Getter for course ID
    public int getCourseID() {
        return courseID;
    }

    // Getter for grade
    public char getGrade() {
        return grade;
    }

    // Override toString method to display grade details
    @Override
    public String toString() {
        return "Student ID: " + studentID + ", Course ID: " + courseID + ", Grade: " + grade;
    }
}

// Class managing the grading system
class GradingSystem {
    private ArrayList<Student> students;
    private ArrayList<Grade> grades;
    private HashMap<Integer, Integer> courseCredits; // Map courseID to credits

    // Constructor to initialize the grading system
    public GradingSystem() {
        students = new ArrayList<>();
        grades = new ArrayList<>();
        courseCredits = new HashMap<>();
    }

    // Method to add a student
    public void addStudent(Student student) {
        students.add(student);
    }

    // Method to add a grade
    public void addGrade(Grade grade) {
        grades.add(grade);
    }

    // Method to add course credits
    public void addCourseCredits(int courseID, int credits) {
        courseCredits.put(courseID, credits);
    }

    // Method to calculate GPA for a student
    public double calculateGPA(int studentID) {
        int totalPoints = 0;
        int totalCredits = 0;
        for (Grade grade : grades) {
            if (grade.getStudentID() == studentID) {
                int credits = courseCredits.get(grade.getCourseID());
                totalCredits += credits;
                totalPoints += gradeToPoints(grade.getGrade()) * credits;
            }
        }
        return (totalCredits == 0) ? 0.0 : (double) totalPoints / totalCredits;
    }

    // Method to convert grade to grade points
    private int gradeToPoints(char grade) {
        switch (grade) {
            case 'A': return 4;
            case 'B': return 3;
            case 'C': return 2;
            case 'D': return 1;
            case 'F': return 0;
            default: return 0;
        }
    }

    // Method to display grade report for a student
    public void displayGradeReport(int studentID) {
        System.out.println("Grade Report for Student ID " + studentID + ":");
        for (Grade grade : grades) {
            if (grade.getStudentID() == studentID) {
                System.out.println(grade);
            }
        }
        System.out.printf("GPA: %.2f%n", calculateGPA(studentID));
    }
}

// Main class to interact with the user
public class GradingSystemMGMT {
    public static void main(String[] args) {
        GradingSystem gradingSystem = new GradingSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Display menu options to the user
            System.out.println("1. Add Student");
            System.out.println("2. Add Grade");
            System.out.println("3. Add Course Credits");
            System.out.println("4. Calculate GPA");
            System.out.println("5. Print Grade Report");
            System.out.println("6. Exit");
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
                    gradingSystem.addStudent(new Student(studentID, name));
                    break;

                case 2:
                    // Prompt user to enter grade details
                    System.out.print("Enter Student ID: ");
                    studentID = scanner.nextInt();
                    System.out.print("Enter Course ID: ");
                    int courseID = scanner.nextInt();
                    System.out.print("Enter Grade (A/B/C/D/F): ");
                    char grade = scanner.next().charAt(0);
                    gradingSystem.addGrade(new Grade(studentID, courseID, grade));
                    break;

                case 3:
                    // Prompt user to enter course credits
                    System.out.print("Enter Course ID: ");
                    courseID = scanner.nextInt();
                    System.out.print("Enter Credits: ");
                    int credits = scanner.nextInt();
                    gradingSystem.addCourseCredits(courseID, credits);
                    break;

                case 4:
                    // Prompt user to enter student ID to calculate GPA
                    System.out.print("Enter Student ID: ");
                    studentID = scanner.nextInt();
                    double gpa = gradingSystem.calculateGPA(studentID);
                    System.out.printf("GPA: %.2f%n", gpa);
                    break;

                case 5:
                    // Prompt user to enter student ID to print grade report
                    System.out.print("Enter Student ID: ");
                    studentID = scanner.nextInt();
                    gradingSystem.displayGradeReport(studentID);
                    break;

                case 6:
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
