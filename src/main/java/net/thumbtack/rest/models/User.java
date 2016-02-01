package net.thumbtack.rest.models;


public class User extends DomainObject {
    private String name;

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    private Integer age;

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public User(int id, String name, int age) {
        super(id);
        this.name = name;
        this.age = age;

    }

    public User(String name, int age) {
        super(0);
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }


}
