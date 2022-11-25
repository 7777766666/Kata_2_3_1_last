package web.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import java.util.Objects;

public class User {
    private int id;
//    @NotEmpty(message = "Name shouldn't be empty")
//    @Size(min = 2, max = 30, message = "Name should be 2 and 30 characters")
    private String name;
//    @Min(value = 0, message = "Age should be greater than 0")
    private int age;
//    @NotEmpty(message = "Email shouldn't be empty")
//    @Email(message = "Email should be valid")
    private String email;

    public User() {}
    public User(int id, String name, int age, String email) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && name == user.name && age == user.age && email == user.email;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age, email);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name=" + name +
                ", lastname=" + age +
                ", email=" + email +
                '}';
    }
}
