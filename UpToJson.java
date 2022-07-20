package Connector;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.json.simple.JSONObject;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
public class UpToJson {
    private Scanner sc = new Scanner(System.in);

    public void removeAllList(List<Student> students){
        students.clear();
    }
    public void addStudent(List<Student> students){
        System.out.println("Input id");
        long id = sc.nextLong();
        System.out.println("Input name :");
        String name = sc.next();
        System.out.println("Input email :");
        String email = sc.next();
        System.out.println("Input phone :");
        String phone = sc.next();
        Student student = new Student(id,name,email,phone);
        students.add(student);
    }

    public void WriteStudentToJson(List<Student> students) throws IOException{
        try{
            FileWriter fileWriter = new FileWriter("employee.json");
            for (Student student: students) {
                JSONObject customer = new JSONObject();
                customer.put("id",String.valueOf(student.getId()));
                customer.put("name",student.getName());
                customer.put("email",student.getEmail());
                customer.put("phone",student.getPhone());
                fileWriter.write(customer.toJSONString());
            }
            fileWriter.close();
        }
        catch (IOException a){
            a.printStackTrace();
        }
    }
    public void WritenXMLwithDom(List<Student> students) throws ParserConfigurationException, TransformerException {
        for (Student s : students) {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            // Create a Document_Object
            Document document = builder.newDocument();
            //Create root Element
            Element root = document.createElement("User");
            document.appendChild(root);
            Attr attr = document.createAttribute("id");
            attr.setValue(String.valueOf(s.getId()));
            Element name = document.createElement("name");
            name.setTextContent(s.getName());
            Element email = document.createElement("email");
            email.setTextContent(s.getEmail());
            Element mobile = document.createElement("mobile");
            mobile.setTextContent(s.getPhone());
            root.setAttributeNode(attr);
            root.appendChild(name);
            root.appendChild(email);
            root.appendChild(mobile);
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(new DOMSource(document), new StreamResult(new File("employee.xml")));
        }
        //DOM -> XML file
    }

    public void FindStudentByNameToJson(List<Student> students) throws IOException {
//        try {
//            FileReader reader = new FileReader("employee.json");
//            student = new Gson().fromJson(reader, new TypeToken<List<Student>>() {
//            }.getType());
//            System.out.println("nhập name :");
//            String name = "minh thanh";
//            System.out.println(name);
//            for (Student product : student) {
//                if (product.getName().equals(name)) {
//                    System.out.println(product);
//                    break;
//                } else {
//                    System.err.println("Not Found");
//                }
//            }
//        }catch (FileNotFoundException a){
//            a.printStackTrace();
//        }
        System.out.println("Nhập tên ");
        String name = sc.next();
        FileReader reader = new FileReader("employee.json");
        students = new Gson().fromJson(reader, new TypeToken<List<Student>>(){}.getType());

        for (Student product : students) {
            if (product.getName().equals(name)) {
                System.out.println(product);
                break;
            } else {
                System.err.println("Not Found");
            }
        }
        reader.close();



//        List<Student> list = null;
//        Student student1;
//        Reader reader = Files.newBufferedReader(Paths.get("employee.json"));
//        JsonObject parser = JsonParser.parseReader(reader).getAsJsonObject();
//        for (int i = 0;i< parser.size();i++){
//            if (parser.get("name").getAsString()==name){
//                long id = parser.get("id").getAsLong();
//                String name1 = parser.get("name").getAsString();
//                String email = parser.get("email").getAsString();
//                String phone = parser.get("phone").getAsString();
//                student1 = new Student(id,name1,email,phone);
//                list.add(student1);
//                for (Student student2 : list){
//                    if (student2.getName()==name){
//                        System.out.println(student2);
//                        break;
//                    }
//                }
//                break;
//            }
//        }
    }
}