import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by andriusbaltrunas on 10/18/2017.
 */
public class MainConnection {

    public static void main(String... args){
        String name = "Andrius";
        String aaa = "";
        String a = "insert into students(name, surname...)values('"+name+"', '"+aaa+"')";

        List<Student> students = new ArrayList<>();
        try {

            // 1. sukuriame prisijungima prie db
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/kcs", "root", "MySQL");

            if (connection != null){
                System.out.println("Prisijungeme sekmingai");

                //2. kuriame statement objecta
                Statement statement = connection.createStatement();
                // 3. executinam query
                ResultSet resultSet = statement.executeQuery("select * from students order by id desc ");
                while (resultSet.next()){
                    Student student = new Student(resultSet.getInt("id"), resultSet.getString("name"),
                            resultSet.getString("surname"), resultSet.getString("phone"), resultSet.getString("email"));
                    students.add(student);
                  /*  System.out.println(resultSet.getInt(1) + " "+ resultSet.getString("name") + " "
                            + resultSet.getString("surname") +" "+ resultSet.getString("phone") +" "
                            + resultSet.getString("email"));*/
                }


            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void print(List<Student> students){
        students.forEach(student -> {
            System.out.println(student.getName() + " " + student.getSurname());
        });
    }
}
