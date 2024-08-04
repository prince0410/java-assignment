import java.util.ArrayList;
import java.util.Scanner;

// Class representing a course
class Course {
    private int courseID;
    private String courseName;
    private int credits;

    // Constructor to initialize course details
    public Course(int courseID, String courseName, int credits) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.credits = credits;
    }

    // Getter for course ID
    public int getCourseID() {
        return courseID;
    }

    // Getter for course name
    public String getCourseName() {
        return courseName;
    }

    // Getter for credits
    public int getCredits() {
        return credits;
    }

    // Override toString method to display course details
    @Override
    public String toString() {
        return "Course ID: " + courseID + ", Course Name: " + courseName + ", Credits: " + credits;
    }
}

// Class managing course enrollments
class Enrollment {
    private ArrayList<int[]> studentCourses;
    private int[] count;

    // Constructor to initialize enrollment system
    public Enrollment() {
        studentCourses = new ArrayList<>();
        count = new int[100]; // Assuming max 100 students for simplicity
        for (int i = 0; i < 100; i++) {
            studentCourses.add(new int[100]); // Assuming max 100 courses
        }
    }

    // Method to enroll a student in a course
    public void enroll(int studentID, int courseID) {
        studentCourses.get(studentID)[courseID] = 1;
        count[studentID]++;
    }

    // Method to drop a course for a student
    public void drop(int studentID, int courseID) {
        studentCourses.get(studentID)[courseID] = 0;
        count[studentID]--;
    }

    // Method to get and display all courses a student is enrolled in
    public void getEnrolledCourses(int studentID, ArrayList<Course> courseCatalog) {
        System.out.println("Courses enrolled by Student ID " + studentID + ":");
        for (Course course : courseCatalog) {
            if (studentCourses.get(studentID)[course.getCourseID()] == 1) {
                System.out.println(course);
            }
        }
    }
}

// Main class to interact with the user
public class CourseEnrollment {
    public static void main(String[] args) {
        ArrayList<Course> courseCatalog = new ArrayList<>();
        Enrollment enrollmentSystem = new Enrollment();
        Scanner scanner = new Scanner(System.in);

        // Adding some courses to the catalog for testing
        courseCatalog.add(new Course(0, "Mathematics", 3));
        courseCatalog.add(new Course(1, "Physics", 4));
        courseCatalog.add(new Course(2, "Chemistry", 4));

        while (true) {
            // Display menu options to the user
            System.out.println("1. Enroll in a Course");
            System.out.println("2. Drop a Course");
            System.out.println("3. View Enrolled Courses");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // Prompt user to enter student ID and course ID to enroll
                    System.out.print("Enter Student ID: ");
                    int studentID = scanner.nextInt();
                    System.out.print("Enter Course ID: ");
                    int courseID = scanner.nextInt();
                    enrollmentSystem.enroll(studentID, courseID);
                    System.out.println("Enrolled successfully!");
                    break;

                case 2:
                    // Prompt user to enter student ID and course ID to drop
                    System.out.print("Enter Student ID: ");
                    studentID = scanner.nextInt();
                    System.out.print("Enter Course ID: ");
                    courseID = scanner.nextInt();
                    enrollmentSystem.drop(studentID, courseID);
                    System.out.println("Dropped successfully!");
                    break;

                case 3:
                    // Prompt user to enter student ID to view enrolled courses
                    System.out.print("Enter Student ID: ");
                    studentID = scanner.nextInt();
                    enrollmentSystem.getEnrolledCourses(studentID, courseCatalog);
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
