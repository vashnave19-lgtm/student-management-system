import java.sql.*;

public class StudentDAO {
    Connection con;

    public StudentDAO() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection(
                "jdbc:oracle:thin:@//localhost:1521/orcl",
                "system",     // change if needed
                "1234"    // change your password
            );
            System.out.println("Connected to DB!");
        } catch (Exception e) {
           // e.printStackTrace();
           System.out.println("Database connection failed: " + e.getMessage());
           
        }
    }

    // Add student
    public void addStudent(Student s) {
        try {
            String query = "INSERT INTO students(name, age, course) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, s.name);
            ps.setInt(2, s.age);
            ps.setString(3, s.course);
            ps.executeUpdate();
            System.out.println("Student Added!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // View students
    public void viewStudents() {
        try {
            String query = "SELECT * FROM students";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                System.out.println(
                    rs.getInt("id") + " " +
                    rs.getString("name") + " " +
                    rs.getInt("age") + " " +
                    rs.getString("course")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Delete student
    public void deleteStudent(int id) {
        try {
            String query = "DELETE FROM students WHERE id=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Student Deleted!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void searchStudent(int id) {
    try {
        String query = "SELECT * FROM students WHERE id=?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            System.out.println(
                rs.getInt("id") + " " +
                rs.getString("name") + " " +
                rs.getInt("age") + " " +
                rs.getString("course")
            );
        } else {
            System.out.println("Student not found!");
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
}

public void updateStudent(int id, String name, int age, String course) {
    try {
        String query = "UPDATE students SET name=?, age=?, course=? WHERE id=?";
        PreparedStatement ps = con.prepareStatement(query);

        ps.setString(1, name);
        ps.setInt(2, age);
        ps.setString(3, course);
        ps.setInt(4, id);

        int rows = ps.executeUpdate();

        if (rows > 0) {
            System.out.println("Student Updated!");
        } else {
            System.out.println("Student not found!");
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
}
}