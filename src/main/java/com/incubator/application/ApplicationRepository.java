package com.incubator.application;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    @Query("select new com.incubator.application.ApplicationList(u.id, u.fName, u.lName, u.mI, u.rank, u.dob, u.status, u.dateSubmitted) from Application AS u order by u.lName")
    public List<ApplicationList> getReviewerApplicationList();

    public List<Application> findByUserIdEquals(Long userId);
}
