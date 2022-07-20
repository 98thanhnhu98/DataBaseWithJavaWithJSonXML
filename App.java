package Connector;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class App {
    public static List<Student> students = new ArrayList<>();
    public App(){
    }
    public static void menu() {
        System.out.println("\n1. Write Data'Student to Json" +
                "\n2. Write Data'Student to XML" +
                "\n3. Create database"+
                "\n4. Read database"+
                "\n5. Update database"+
                "\n6. Delete database"+
                "\n7. find by name by Json "+
                "\n8. Exit");
    }
    public static Connection getMssql() throws SQLException{
        String dbURL = "jdbc:sqlserver://localhost:1433;databaseName=book";
        String username ="sa";
        String password ="123";
        Connection connection = DriverManager.getConnection(dbURL,username,password);
        return connection;
    }

    public static void main(String[] args) throws SQLException, IOException, ParserConfigurationException, TransformerException {
        Scanner sc = new Scanner(System.in);
        UpToJson upToJson = new UpToJson();
        mssql mssql = new mssql();
        boolean falg = false;
        if (getMssql() != null) {
            System.out.println("Successfully");
            falg = true;
        }
        while (falg == true) {
            menu();
            System.out.println("Chọn :");
            int chon = sc.nextInt();
            switch (chon) {
                case 1:
                    upToJson.WriteStudentToJson(students);
                    break;
                case 2:
                    upToJson.WritenXMLwithDom(students);
                    break;
                case 3:
                    mssql.createxmltest();
                    break;
                case 4:
                    students = mssql.readDataxmltest();
                    break;
                case 5:
                    mssql.updateDataxmltest();
                    break;
                case 6:
                    mssql.deleteDataxmltest();
                    break;
                case 7:
                    upToJson.FindStudentByNameToJson(students);
                    break;
                case 8:
                    System.exit(0);
                    break;
                case 9:
                    break;
                default:
                    System.out.println("Wrong");
                    break;
            }
        }

    }
}
