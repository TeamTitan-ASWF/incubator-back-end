package com.incubator.application;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class ApplicationList {
    private Long id;

    // user data
    private  String fName;
    private  String lName;
    private String mI;
    private  String rank;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Chicago")
    private LocalDate dob;

    private String status = "pending";
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Chicago")
    private LocalDate dateSubmitted;

    public ApplicationList(Long id, String fName, String lName, String mI, String rank, LocalDate dob, String status, LocalDate dateSubmitted) {
        this.id = id;
        this.fName = fName;
        this.lName = lName;
        this.mI = mI;
        this.rank = rank;
        this.dob = dob;
        this.status = status;
        this.dateSubmitted = dateSubmitted;
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

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getDateSubmitted() {
        return dateSubmitted;
    }

    public void setDateSubmitted(LocalDate dateSubmitted) {
        this.dateSubmitted = dateSubmitted;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }
}
