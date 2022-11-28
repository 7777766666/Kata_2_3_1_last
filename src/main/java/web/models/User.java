package web.models;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import java.util.Objects;

@Entity
@Table(name = "User")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotEmpty(message = "Name shouldn't be empty")
    @Size(min = 2, max = 30, message = "Name should be 2 and 30 characters")
    @Column(name = "name")
    private String name;

    @NotEmpty(message = "Name shouldn't be empty")
    @Size(min = 2, max = 30, message = "Name should be 2 and 30 characters")
    @Column(name = "last_name")
    private String lastName;
    @Min(value = 0, message = "Age should be greater than 0")
    @Column(name = "age")
    private int age;


    public User() {}

    public User(String name, String lastName, int age) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                '}';
    }
    //    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        User user = (User) o;
//        return id == user.id && name == user.name && age == user.age && lastName == user.lastName;
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, name, age, lastName);
//    }



}
