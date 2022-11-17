package model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class User {
    private Integer id;
    private String name;
    private String family;
    private Integer age;
    private String nationalCode;
    private LocalDate birthday;

    private DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy");


    public User(String name, String family, String nationalId, LocalDate birthday) {
        this.id = id;
        this.name = name;
        this.family = family;
        this.nationalCode = nationalId;
        this.birthday = birthday;
        this.age = LocalDate.now().getYear()-birthday.getYear();
    }
    public User(Integer id,String name, String family, String nationalId, LocalDate birthday) {
        this.id = id;
        this.name = name;
        this.family = family;
        this.nationalCode = nationalId;
        this.birthday = birthday;
        this.age = LocalDate.now().getYear()-birthday.getYear();
    }

    public Integer getAge() {
        return age;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public String getName() {
        return name;
    }

    public String getFamily() {
        return family;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return "{" +
                "name=" + name +
                ", family='" + family +
                ", age=" + age +
                ", nationalId=" + nationalCode +
                ", birthday=" + birthday+
                "}\n";
    }
}
