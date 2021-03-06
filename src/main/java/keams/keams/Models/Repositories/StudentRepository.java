package keams.keams.Models.Repositories;

import keams.keams.ConnectionCreator;
import keams.keams.Interfaces.StudentRepositoryInterface;
import keams.keams.Models.StudentModel;
import org.springframework.stereotype.Repository;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentRepository implements StudentRepositoryInterface {

    // AUTHOR(S): AP, LKB
    private ConnectionCreator connectionCreator = new ConnectionCreator();


    @Override
    public String createStudent(StudentModel p) {
        PreparedStatement preparedStatement = null;

        System.out.println("creating statement for Student=" + p.getName());
        String createString = "INSERT INTO students VALUES(?, ?, ?,?)";

        try {
            System.out.println("getting connection...");
            preparedStatement = connectionCreator.getConnection().prepareStatement(createString);

            System.out.println("creating student with CPR=" + p.getCpr());
            preparedStatement.setInt(1, p.getAge());
            preparedStatement.setString(2, p.getCpr());
            preparedStatement.setString(3, p.getName());
            preparedStatement.setInt(4, p.getAge());

        } catch (SQLException e) {
            e.printStackTrace();
        }

        String msg;

        try {
            System.out.println("executing...");
            preparedStatement.executeUpdate();
            msg = "Studenten Navn: " + p.getName() + ", CPR nr.: " + p.getCpr();
        } catch (SQLException e) {
            msg = "Student med CPR: " + p.getCpr() + " eksisterer allerede og kan derfor ikke oprettes.";
            e.printStackTrace();
        }

        try {
            System.out.println("closing connection...");
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return msg;
    }
    @Override
    public List<StudentModel> getStudentList() {
        List<StudentModel> students = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        System.out.println("creating select statement for all students");
        String getAllStudentsString = "SELECT * FROM students";

        try {
            System.out.println("getting connection...");
            preparedStatement = connectionCreator.getConnection().prepareStatement(getAllStudentsString);

            System.out.println("selecting all students");
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                students.add(new StudentModel(rs.getInt(1), rs.getString(2), rs.getString(3),rs.getInt(4)));
            }

            System.out.println("closing resultset...");
            rs.close();

            System.out.println("closing connection...");
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return students;
    }

    @Override
    public StudentModel get(String id) {
        System.out.println("creating select statement for ID=" + id);
        String getProductString = "SELECT * FROM students WHERE student_id = ?";
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        StudentModel product = null;

        try {
            System.out.println("getting connection...");
            preparedStatement = connectionCreator.getConnection().prepareStatement(getProductString);

            System.out.println("selecting student with ID=" + id);
            preparedStatement.setString(1, id);
            rs = preparedStatement.executeQuery();
            rs.next();
            product = new StudentModel(rs.getInt(1), rs.getString(2), rs.getString(3),
                    rs.getInt(4));

            System.out.println("closing resultset...");
            rs.close();

            System.out.println("closing connection...");
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return product;
    }

    @Override
    public void updateStudent(StudentModel p) {
        System.out.println("creating update statement for CPR=" + p.getId());
        String updateString = "UPDATE students SET student_cpr = ?, student_name = ?, student_age = ? "
                + " WHERE student_id = ?";
        PreparedStatement preparedStatement = null;

        try {
            System.out.println("getting connection...");
            preparedStatement = connectionCreator.getConnection().prepareStatement(updateString);

            System.out.println("updating product with ID=" + p.getId());
            preparedStatement.setString(1, p.getCpr());
            preparedStatement.setString(2, p.getName());
            preparedStatement.setInt(3, p.getAge());
            preparedStatement.setInt(4, p.getId());

            System.out.println("executing...");
            preparedStatement.executeUpdate();

            System.out.println("closing connection...");
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
