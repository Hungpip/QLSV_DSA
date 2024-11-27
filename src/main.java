import dsa.LinkedList;
import dsa.Student;
import dsa.Utils;

import java.util.regex.Pattern;

import static dsa.Utils.println;
import static dsa.Utils.readLine;

public class main {
    static LinkedList<Student> list = new LinkedList<>();

    public static void main(String[] args) {
        while (true) {
            printHelp();
            String choice = readLine("Please choose(1->8): ");
            switch (choice) {
                case "0":
                    list.add(Student.newRandomStudent());
                    break;
                case "1":
                    addStudent();
                    break;
                case "2":
                    deleteStudent();
                    break;
                case "3":
                    updateStudent();
                    break;
                case "4":
                    searchStudent();
                    break;
                case "5":
                    listStudent();
                    break;
                case "6":
                    bubbleSort();
                    break;
                case "7":
                    quickSort();
                    break;
                case "8":
                    selectionSort();
                    break;
                case "9":
                    return;
                default:
                    println("Invalid choice");
                    break;
            }
        }
    }

    private static void printHelp() {
        println("1. Add student");
        println("2. Delete student");
        println("3. Update student");
        println("4. Search student");
        println("5. list student");
        println("6. Bubble sort");
        println("7. Selection sort");
        println("8. Quick sort");
        println("9. Exit");
    }

    private static void addStudent() {
        Student student = Student.newStudent();
        list.add(student);
        println("Add student successfully");
    }


    private static void deleteStudent() {
        String id = readLine("Enter student id(BHxxxx): ");
        int index = list.find(id, (s) -> s.id);
        if (index == -1)
            println("Student not found");
        else {
            list.remove(index);
            println("Delete student successfully");
        }

    }

    private static void updateStudent() {
        String id = readLine("Enter student id(BHxxxx): ", String::toUpperCase);
        int index = list.find(id, (s) -> s.id);
        if (index == -1)
            println("Student not found");
        else {
            Student student = list.get(index);
            Pattern namePattern = Pattern.compile("[A-Za-z ]+");
            String name = readLine("Name: ", s -> s, (s) -> namePattern.matcher(s).matches());
            double score = readLine("Score: ", Double::parseDouble, (s) -> s >= 0.0 && s <= 10.0);
            list.set(index, student.copy(name, score));
            println("Update student successfully");
        }
    }

    private static void searchStudent() {
        String id = readLine("Enter student id(BHxxxx): ", String::toUpperCase);
        int index = list.find(id, (s) -> s.id);
        if (index == -1)
            println("Student not found");
        else
            println(list.get(index));
    }

    private static void listStudent() {
        println("==List Student==");
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) != null)
                println(list.get(i));
        }
    }

    private static void bubbleSort() {
        var array = list.toArray(new Student[list.size()]);
        Utils.bubbleSort(array, Student::compareTo);
        list.applyArray(array);
    }

    public static void quickSort() {
        var array = list.toArray(new Student[list.size()]);
        Utils.quickSort(array, 0, array.length - 1, Student::compareTo);
        list.applyArray(array);
    }

    public static void selectionSort() {
        var array = list.toArray(new Student[list.size()]);
        Utils.selectionSort(array, Student::compareTo);
        list.applyArray(array);
    }


}