package com.yc.bean;



import java.io.Serializable;

public class Person implements Serializable {
    private String name;
    private Integer age;
    private String gender;

    public Person() {
    }
//

    public static final class Builder {

        private String name;
        private Integer age;
        private String gender;

        public Builder name(String name) {
            this.name = name;
            return this;
        }
        public Builder age(Integer age) {
            this.age = age;
            return this;
        }
        public Builder gender(String gender) {
            this.gender = gender;
            return this;
        }

        public Person build(){
            return new Person(this);
        }

    }

    public Person(Builder b) {
        this.name = b.name;
        this.age = b.age;
        this.gender = b.gender;
    }

    public static Builder builder(){
        return new Builder();
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                '}';
    }
}
