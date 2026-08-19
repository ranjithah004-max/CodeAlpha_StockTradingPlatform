import java.util.ArrayList;
import java.util.Scanner;

public class StudentGradeTracker {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<String> names = new ArrayList<>();
        ArrayList<Double> grades = new ArrayList<>();

        System.out.print("Enter number of students: ");
        int numberOfStudents = input.nextInt();
        input.nextLine();

        double total = 0;
        double highest = 0;
        double lowest = 100;

        for (int i = 0; i < numberOfStudents; i++) {
            System.out.println("\nStudent " + (i + 1));

            System.out.print("Enter name: ");
            String name = input.nextLine();

            System.out.print("Enter grade: ");
            double grade = input.nextDouble();
            input.nextLine();

            names.add(name);
            grades.add(grade);

            total = total + grade;

            if (grade > highest) {
                highest = grade;
            }

            if (grade < lowest) {
                lowest = grade;
            }
        }

        double average = total / numberOfStudents;

        System.out.println("\n--- Student Grade Report ---");

        for (int i = 0; i < numberOfStudents; i++) {
            System.out.println(names.get(i) + " : " + grades.get(i));
        }

        System.out.println("\nAverage Grade: " + average);
        System.out.println("Highest Grade: " + highest);
        System.out.println("Lowest Grade: " + lowest);

        input.close();
    }
}