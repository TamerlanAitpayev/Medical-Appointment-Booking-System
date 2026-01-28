package Entities;

public class Patient {
    private int age;
    private String name;
    private String lastname;
    private String gender;
    private double weight;
    private double height;
    private int id;
    private String email;
    private String phoneNumber;

    public Patient() {}

    public Patient(int age, String name, String lastname, String gender, double weight, double height, int id, String email, String PhoneNumber){
    this.name=name;
    this.lastname=lastname;
    this.gender=gender;
    this.weight=weight;
    this.height=height;
    this.id=id;
    this.email=email;
    this.age=age;
    this.phoneNumber=PhoneNumber;
}
    public int getId() {return id;}
    public void setId(int id){this.id=id;}

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    public int getAge(){return age;}
    public void setAge(int age){this.age=age;}

    public String getLastName(){return lastname;}
    public void setLastName(String lastname){this.lastname=lastname;}

    public String getGender(){return gender;}
    public void setGender(String gender){this.gender=gender;}

    public String getName(){return name;}
    public void setName(String name){this.name=name;}

    public double getWeight(){return weight;}
    public void setWeight(double weight){this.weight=weight;}

    public double getHeight(){return height;}
    public void setHeight(double height){this.height=height;}

    public String getPhoneNumber() {return phoneNumber;}
    public void setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber;}
}
