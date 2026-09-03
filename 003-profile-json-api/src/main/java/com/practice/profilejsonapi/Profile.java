package com.practice.profilejsonapi;

public class Profile {
        String name;
        int age;
        String city;

        Profile(String name, int age, String city) {
            this.name = name;
            this.age = age;
            this.city = city;
        }

    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
    public String getCity() {
        return city;
    }

    public void setName(String newName) {
        this.name = newName;
    }
    public void setAge(int newAge) {
        this.age = newAge;
    }
    public void setCity(String newCity) {
        this.city = newCity;
    }





}
