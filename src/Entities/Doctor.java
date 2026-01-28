package Entities;

public class Doctor {
    private int id;
    private String email;
    private String specialization;
    private String name;

    public Doctor(){}

    public Doctor(int id, String email, String specialization, String name){
        this.email=email;
        this.id=id;
        this.specialization=specialization;
        this.name=name;
    }
    public int getId(){return id;}
    public void setId(int id){this.id=id;}

    public String getEmail() {return email;}
    public void setEmail(String email){this.email=email;}

    public String getSpecialization() {return specialization;}
    public void setSpecialization(String specialization){this.specialization=specialization;}

    public String getName() {return name;}
    public void setName(String name) {this.name=name;}
    }
