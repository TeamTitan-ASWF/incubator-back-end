package com.incubator.application;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    @Query("select new com.incubator.application.ApplicationList(u.id, u.fName, u.lName, u.mI, u.rank, u.dob, u.status, u.dateSubmitted) from Application AS u order by u.lName")
    //@Query(value = "select id, f_Name, mI, l_Name, rank, date_Submitted, status from incubators", nativeQuery = true)
    public List<ApplicationList> getReviewerApplicationList();

    public Optional<Application> findFirstByDodIdEquals(String dodId);

    public Optional<Application> findFirstByUserIdEquals(Long userId);

    public List<Application> findByUserIdEquals(Long userId);
}
