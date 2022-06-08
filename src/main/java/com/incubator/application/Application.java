package com.incubator.application;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.incubator.exceptions.InvalidStatus;
import com.incubator.user.User;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "applications")
public class Application {

    @ManyToOne
    private User user;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // duplicate columns in USER table
//    private String fName;
//    private String lName;
//    private String mI;
//    private String dodId;
//    private String rank;
//    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Chicago")
//    private LocalDate dob;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Chicago")
    private LocalDate lastACFT;
    private Integer acftScore;
    private Integer height;
    private Float weight;
    @Column(length = 3000)
    private String techBG;
    @Column(length = 3000)
    private String motivation;

    private String referenceName;
    private String referenceRank;
    private String referenceEmail;
    private String referencePhone;

    private String referenceName2;
    private String referenceRank2;
    private String referenceEmail2;
    private String referencePhone2;

    private String referenceName3;
    private String referenceRank3;
    private String referenceEmail3;
    private String referencePhone3;

    private String status = "in progress";

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Chicago")
    private LocalDate dateSubmitted = LocalDate.now();

//    public Application(User user, Long id, LocalDate lastACFT, Integer acftScore, Integer height, Float weight, String techBG, String motivation, String referenceName, String referenceRank, String referenceEmail, String referencePhone, String referenceName2, String referenceRank2, String referenceEmail2, String referencePhone2, String referenceName3, String referenceRank3, String referenceEmail3, String referencePhone3, String status, LocalDate dateSubmitted) {
//        this.user = user;
//        this.id = id;
//        this.lastACFT = lastACFT;
//        this.acftScore = acftScore;
//        this.height = height;
//        this.weight = weight;
//        this.techBG = techBG;
//        this.motivation = motivation;
//        this.referenceName = referenceName;
//        this.referenceRank = referenceRank;
//        this.referenceEmail = referenceEmail;
//        this.referencePhone = referencePhone;
//        this.referenceName2 = referenceName2;
//        this.referenceRank2 = referenceRank2;
//        this.referenceEmail2 = referenceEmail2;
//        this.referencePhone2 = referencePhone2;
//        this.referenceName3 = referenceName3;
//        this.referenceRank3 = referenceRank3;
//        this.referenceEmail3 = referenceEmail3;
//        this.referencePhone3 = referencePhone3;
//        this.status = status;
//        this.dateSubmitted = dateSubmitted;
//    }

    public Application() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getLastACFT() {
        return lastACFT;
    }

    public void setLastACFT(LocalDate lastACFT) {
        this.lastACFT = lastACFT;
    }

    public Integer getAcftScore() {
        return acftScore;
    }

    public void setAcftScore(Integer acftScore) {
        this.acftScore = acftScore;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = Integer.valueOf(height);
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public void setWeight(String weight) {
        this.weight = Float.parseFloat(weight);
    }

    public String getTechBG() {
        return techBG;
    }

    public void setTechBG(String techBG) {
        this.techBG = techBG;
    }

    public String getMotivation() {
        return motivation;
    }

    public void setMotivation(String motivation) {
        this.motivation = motivation;
    }

    public String getReferenceName() {
        return referenceName;
    }

    public void setReferenceName(String referenceName) {
        this.referenceName = referenceName;
    }

    public String getReferenceRank() {
        return referenceRank;
    }

    public void setReferenceRank(String referenceRank) {
        this.referenceRank = referenceRank;
    }

    public String getReferenceEmail() {
        return referenceEmail;
    }

    public void setReferenceEmail(String referenceEmail) {
        this.referenceEmail = referenceEmail;
    }

    public String getReferencePhone() {
        return referencePhone;
    }

    public void setReferencePhone(String referencePhone) {
        this.referencePhone = referencePhone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) throws InvalidStatus {
        if (status.equals("pending") || status.equals("approved") || status.equals("denied") || status.equals("rescinded")|| status.equals("in progress")) {
            this.status = status;
        } else {
            throw new InvalidStatus("Invalid Status submitted, needs to be: pending, approved, denied, rescinded, or in progress" + " and you used: " + status);
        }
    }

    public LocalDate getDateSubmitted() {
        return dateSubmitted;
    }

    public void setDateSubmitted(LocalDate dateSubmitted) {
        this.dateSubmitted = dateSubmitted;
    }

    public String getReferenceName2() {
        return referenceName2;
    }

    public void setReferenceName2(String referenceName2) {
        this.referenceName2 = referenceName2;
    }

    public String getReferenceRank2() {
        return referenceRank2;
    }

    public void setReferenceRank2(String referenceRank2) {
        this.referenceRank2 = referenceRank2;
    }

    public String getReferenceEmail2() {
        return referenceEmail2;
    }

    public void setReferenceEmail2(String referenceEmail2) {
        this.referenceEmail2 = referenceEmail2;
    }

    public String getReferencePhone2() {
        return referencePhone2;
    }

    public void setReferencePhone2(String referencePhone2) {
        this.referencePhone2 = referencePhone2;
    }

    public String getReferenceName3() {
        return referenceName3;
    }

    public void setReferenceName3(String referenceName3) {
        this.referenceName3 = referenceName3;
    }

    public String getReferenceRank3() {
        return referenceRank3;
    }

    public void setReferenceRank3(String referenceRank3) {
        this.referenceRank3 = referenceRank3;
    }

    public String getReferenceEmail3() {
        return referenceEmail3;
    }

    public void setReferenceEmail3(String referenceEmail3) {
        this.referenceEmail3 = referenceEmail3;
    }

    public String getReferencePhone3() {
        return referencePhone3;
    }

    public void setReferencePhone3(String referencePhone3) {
        this.referencePhone3 = referencePhone3;
    }
}
