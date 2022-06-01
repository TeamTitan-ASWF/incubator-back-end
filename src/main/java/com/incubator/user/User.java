package com.incubator.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.incubator.Incubator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String userName;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private Boolean isReviewer = false;

    private String fName;
    private String lName;
    private String mI;
    private String dodId;
    private String rank;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Chicago")
    private LocalDate dob;

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public User() {
    }

    @OneToMany
    public Set<Incubator> applications;

    public Set<Incubator> getApplications() {
        return applications;
    }

    public void setApplications(Set<Incubator> applications) {
        this.applications = applications;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Boolean getIsReviewer() {
        return isReviewer;
    }

    public void setIsReviewer(Boolean reviewer) {
        this.isReviewer = reviewer;
    }

    public Boolean getReviewer() {
        return isReviewer;
    }

    public void setReviewer(Boolean reviewer) {
        isReviewer = reviewer;
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
}
