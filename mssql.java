package Connector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class mssql {
    public static Connection getMssQLConnectionone() throws SQLException {
        String dbURL = "jdbc:sqlserver://localhost:1433;databaseName=xmltest";
        String username ="sa";
        String password ="123";
        Connection connection = DriverManager.getConnection(dbURL,username,password);
        return connection;
    }

    public void createxmltest()  throws SQLException {
        Scanner sc = new Scanner(System.in);
        Connection conn = getMssQLConnectionone();
        String query = "insert into student values(?,?,?,?)";
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        System.out.println("Nhập id: ");
        long id = sc.nextLong();
        System.out.println("Nhập name: ");
        String name = sc.next();
        System.out.println("Nhập email: ");
        String email = sc.next();
        System.out.println("Nhập phone: ");
        String phone = sc.next();
        preparedStatement.setLong(1,id);
        preparedStatement.setString(2,name);
        preparedStatement.setString(3,email);
        preparedStatement.setString(4,phone);
        int rowInserted = preparedStatement.executeUpdate();
        if (rowInserted > 0){
            System.out.println("Create thanh cong");
        }
    }

    public  void updateDataxmltest() throws SQLException {
        Scanner sc = new Scanner(System.in);
        Connection connection = getMssQLConnectionone();
        String query = "update student set name = ? , email = ? , phone = ? WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        System.out.println("Nhập tên cần đổi : ");
        String name = sc.next();
        System.out.println("Nhập email cần đổi : ");
        String email = sc.next();
        System.out.println("Nhập phone cần đổi : ");
        String phone = sc.next();
        System.out.println("Nhập vị trí id: ");
        long id = sc.nextLong();
        preparedStatement.setString(1,name);
        preparedStatement.setString(2,email);
        preparedStatement.setString(3,phone);
        preparedStatement.setLong(4,id);
        int rowUpdate = preparedStatement.executeUpdate();
        if (rowUpdate > 0){
            System.out.println("update thanh cong");
        }
    }

    public  void deleteDataxmltest() throws SQLException {
        Scanner sc = new Scanner(System.in);
        Connection connection = getMssQLConnectionone();
        String query = "DELETE from student WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        System.out.println("Nhập id cần xóa :");
        long id = sc.nextLong();
        preparedStatement.setLong(1,id);
        int rowDelete = preparedStatement.executeUpdate();
        if (rowDelete > 0){
            System.out.println("Xoa id Thanh cong");
        }
    }

    public List<Student> readDataxmltest() throws SQLException{
        List<Student> students = new ArrayList<>();
        Student student = null;
        Connection connection = getMssQLConnectionone();
        String query = "select * from student ORDER By id ASC";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()){
                long id = resultSet.getLong(1);
                String name = resultSet.getString(2);
                String email = resultSet.getString(3);
                String phone = resultSet.getString(4);
            System.out.println("Student{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", email='" + email + '\'' +
                    ", phone='" + phone + '\'' +
                    '}');
                student = new Student(id,name,email,phone);
            students.add(student);
            }
        return students;
    }
}
