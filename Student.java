package Connector;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Student {
    private long id;
    private String name;
    private String email;
    private String phone;

    public Student() {
    }

    public Student(long id, String name, String email, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public long getId() {
        return id;
    }
    @XmlAttribute

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    @XmlElement

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }
    @XmlElement

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }
    @XmlElement

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
