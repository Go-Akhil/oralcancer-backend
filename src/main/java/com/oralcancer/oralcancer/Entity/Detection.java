package com.oralcancer.oralcancer.Entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="detection")
public class Detection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String imageurl;
    @Column
    private String result;
    @Column
    private LocalDateTime createdAt;

    public Detection( String imageurl, String result) {

        this.imageurl = imageurl;
        this.result = result;
    }

    public Detection(int id, String imageurl, String result) {
        this.id = id;
        this.imageurl = imageurl;
        this.result = result;
    }

    @Override
    public String toString() {
        return "Detection{" +
                "id=" + id +
                ", imageurl='" + imageurl + '\'' +
                ", result='" + result + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }

    public Detection() {
    }

   @PrePersist
   protected void onCreate(){
        this.createdAt=LocalDateTime.now();
   }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
