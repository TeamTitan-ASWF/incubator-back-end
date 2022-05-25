package com.incubator;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public interface IncubatorRepository extends JpaRepository<Incubator, Long> {

    @Query("select new com.incubator.IncubatorList(u.id, u.fName, u.lName, u.mI, u.rank, u.status, u.dateSubmitted) from Incubator AS u order by u.lName")
    //@Query(value = "select id, f_Name, mI, l_Name, rank, date_Submitted, status from incubators", nativeQuery = true)
    public List<IncubatorList> getReviewerApplicationList();

    public Optional<Incubator> findFirstByDodIdEquals(String dodId);
}
