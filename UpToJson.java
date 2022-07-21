package Connector;

import Application1.Connector;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import org.json.simple.JSONObject;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
public class UpToJson {
    private Scanner sc = new Scanner(System.in);

    public void WriteStudentToJson1(List<Student> students) throws IOException{
        try{
            Gson gson = new Gson();
            Writer writer = new FileWriter("employee.json");
            for (Student student: students) {
                System.out.println(student.toString());
            }
            gson.toJson(students,writer);
            writer.close();
        }
        catch (IOException a){
            a.printStackTrace();
        }
    }

    public void WritenXMLwithDom() throws ParserConfigurationException, TransformerException, SQLException {
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document dom = builder.newDocument();
        Element root = dom.createElement("list");
        dom.appendChild(root);
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = Connector.getMssql();
        preparedStatement = connection.prepareStatement("select * from student ORDER By id ASC");
        resultSet = preparedStatement.executeQuery();
        List<Student> students = new ArrayList<>();
        int id1 = 0;
        String name1 = null;
        String email1 = null;
        String phone1 = null;
        while (resultSet.next()) {
            id1 = resultSet.getInt("id");
            name1 = resultSet.getString("name");
            email1 = resultSet.getString("email");
            phone1 = resultSet.getString("phone");
            Student s = new Student(id1, name1, email1, phone1);
            students.add(s);
        }
        for (Student stude : students) {

            Element user = dom.createElement("user");
            Attr attr = dom.createAttribute("id");
            attr.setValue(String.valueOf(id1));
            Element name = dom.createElement("name");
            name.setTextContent(name1);
            Element email = dom.createElement("email");
            email.setTextContent(email1);
            Element phone = dom.createElement("phone");
            phone.setTextContent(String.valueOf(phone1));

            user.setAttributeNode(attr);
            user.appendChild(name);
            user.appendChild(email);
            user.appendChild(phone);
            root.appendChild(user);

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(new DOMSource(dom), new StreamResult((new File("students.xml"))));
        }
    }
    public void FindStudentByNameToJson() throws IOException {
        Gson gson = new Gson();
        System.out.println("Nhập tên ");
        String name1 = sc.next();
        Reader reader = Files.newBufferedReader(Paths.get("employee.json"));
        List<Student> list = new Gson().fromJson(reader,new TypeToken<List<Student>>(){}.getType());
        for (Student s:list) {
            if (Objects.equals(s.getName(),name1)){
                System.out.println("Customer{" +
                        "id = " + s.getId() +
                        ", name = '" + s.getName() + '\'' +
                        ", age = " + s.getPhone() +
                        ", email = '" + s.getEmail() + '\'' +
                        '}');
                break;
            }else {
                System.out.println("not found");
            }
        }
    }
}