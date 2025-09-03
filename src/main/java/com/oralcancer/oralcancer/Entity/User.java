package com.oralcancer.oralcancer.Entity;

import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchConnectionDetails;

@Entity
@Table(name="user2")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String userName;
    @Column
    private String password;
    @Column
    private String role;
    @Column
    private String aadhar;
    @Column
    private String phn;
    @Column
    private String dob;
    @Column
    private String adress;

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public User() {
    }
    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAadhar() {
        return aadhar;
    }

    public void setAadhar(String aadhar) {
        this.aadhar = aadhar;
    }

    public String getPhn() {
        return phn;
    }

    public void setPhn(String phn) {
        this.phn = phn;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", aadhar='" + aadhar + '\'' +
                ", phn='" + phn + '\'' +
                ", dob='" + dob + '\'' +
                ", adress='" + adress + '\'' +
                '}';
    }

    public User(int id, String userName, String password, String role, String aadhar, String phn, String dob) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.role = role;
        this.aadhar = aadhar;
        this.phn = phn;
        this.dob = dob;
    }


}
