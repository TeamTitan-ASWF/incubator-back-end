package com.incubator.application;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    @Query("select new com.incubator.application.ApplicationList(u.id, u.fName, u.lName, u.mI, u.rank, a.id, u.dob, a.status, a.dateSubmitted) from Application AS a, User AS u where a.user = u order by u.lName")
    public List<ApplicationList> getReviewerApplicationList();

    @Query("select new com.incubator.application.ApplicationList(u.id, u.fName, u.lName, u.mI, u.rank, a.id, u.dob, a.status, a.dateSubmitted) from Application AS a, User AS u where a.user = u and u.id =:userId order by u.lName")
    public List<ApplicationList> getAppsByUserId(@Param(value = "userId") Long userId);

    @Query("select new com.incubator.application.Application(u.fName, u.lName, u.mI, u.dodId, u.rank, u.dob, a.user, a.id, a.lastACFT, a.acftScore, a.height, a.weight, a.techBG, a.motivation, a.referenceName, a.referenceRank, a.referenceEmail, a.referencePhone, a.referenceName2, a.referenceRank2, a.referenceEmail2, a.referencePhone2, a.referenceName3, a.referenceRank3, a.referenceEmail3, a.referencePhone3, a.status, a.dateSubmitted) from Application AS a, User AS u where a.user = u and a.id =:appId")
    public Optional<Application> getOneApp(@Param(value = "appId") Long appId);

    //public List<Application> findByUserIdEquals(Long userId);
}
