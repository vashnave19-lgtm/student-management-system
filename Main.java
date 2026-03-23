import java.util.*;

public class Main {
    public static void main(String[] args) {
        StudentDAO dao = new StudentDAO();
        try(Scanner sc = new Scanner(System.in)){

            while (true) {
                System.out.println("\n1. Add Student");
                System.out.println("2. View Students");
                System.out.println("3. Delete Student");
                System.out.println("4. Search Student");
                System.out.println("5. Update Student");
                System.out.println("6. Exit");
                System.out.print("Enter choice: ");
                int choice = sc.nextInt();

                switch (choice) {
                    case 1 -> {
                        sc.nextLine(); // consume newline
                        System.out.print("Enter Name: ");
                        String name = sc.nextLine();

                        System.out.print("Enter Age: ");
                        int age = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Enter Course: ");
                        String course = sc.nextLine();

                        dao.addStudent(new Student(name, age, course));
                    }

                    case 2 -> dao.viewStudents();

                    case 3 -> {
                        System.out.print("Enter ID to delete: ");
                        int id = sc.nextInt();
                        dao.deleteStudent(id);
                    }

                    case 4 ->{
                                System.out.print("Enter ID to search: ");
                                int searchId = sc.nextInt();
                                dao.searchStudent(searchId);
                    }

                    case 5->{
                        System.out.print("Enter ID to update: ");
                        int updateId = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Enter new Name: ");
                        String newName = sc.nextLine();

                        System.out.print("Enter new Age: ");
                        int newAge = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Enter new Course: ");
                        String newCourse = sc.nextLine();

                        dao.updateStudent(updateId, newName, newAge, newCourse);
                    }

                    case 6->{
                        System.out.println("Exiting...");
                        return;
                    }
                    
                    default -> System.out.println("Invalid choice!");
                }
            }
        }
    }
}