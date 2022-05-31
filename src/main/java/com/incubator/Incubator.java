package com.incubator;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.incubator.user.User;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table (name = "incubators")
public class Incubator {






    @ManyToOne
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private  String fName;
    private  String lName;
    private String mI;
    private  String dodId;
    private  String rank;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Chicago")
    private LocalDate dob;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Chicago")
    private LocalDate lastACFT;
    private Integer acftScore;
    private Integer height;
    private  Float weight;
    @Column(length = 3000)
    private String techBG;
    @Column(length = 3000)
    private String motivation;
    private String referenceName;
    private String referenceRank;
    private String referenceEmail;
    private String referencePhone;
    private String status = "pending";
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Chicago")
    private LocalDate dateSubmitted = LocalDate.now();

    public Incubator(String fName, String lName, String mI, String dodId, String rank, LocalDate dob, LocalDate lastACFT, Integer acftScore, Integer height, Float weight, String techBG, String motivation, String referenceName, String referenceRank, String referenceEmail, String referencePhone, String status, LocalDate dateSubmitted) {

        this.fName = fName;
        this.lName = lName;
        this.mI = mI;
        this.dodId = dodId;
        this.rank = rank;
        this.dob = dob;
        this.lastACFT = lastACFT;
        this.acftScore = acftScore;
        this.height = height;
        this.weight = weight;
        this.techBG = techBG;
        this.motivation = motivation;
        this.referenceName = referenceName;
        this.referenceRank = referenceRank;
        this.referenceEmail = referenceEmail;
        this.referencePhone = referencePhone;
        this.status = status;
        this.dateSubmitted = dateSubmitted;
    }

    public Incubator(String fName, String lName, String mI, String dodId, String rank, LocalDate dob, LocalDate lastACFT, Integer acftScore) {
        this.fName = fName;
        this.lName = lName;
        this.mI = mI;
        this.dodId = dodId;
        this.rank = rank;
        this.dob = dob;
        this.lastACFT = lastACFT;
        this.acftScore = acftScore;
    }

    public Incubator() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getmI() {
        return mI;
    }

    public void setmI(String mI) {
        this.mI = mI;
    }

    public String getDodId() {
        return dodId;
    }

    public void setDodId(String dodId) {
        this.dodId = dodId;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
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

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
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
        if (status.equals("pending") || status.equals("approved") || status.equals("denied")) {
            this.status = status;
        } else {
            throw new InvalidStatus("Invalid Status submitted, needs to be: pending, approved, or denied" + " and you used: " + status);
        }
    }

    public LocalDate getDateSubmitted() {
        return dateSubmitted;
    }

    public void setDateSubmitted(LocalDate dateSubmitted) {
        this.dateSubmitted = dateSubmitted;
    }
}
