package com.incubator;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface IncubatorRepository extends CrudRepository <Incubator, Long> {

//    @Query("select u.id, u.fName, u.mI, u.lName, u.rank, u.dateSubmitted, u.status from Incubator u")
//    //@Query(value = "select id, f_Name, mI, l_Name, rank, date_Submitted, status from incubators", nativeQuery = true)
//    public Iterable<IncubatorList> getReviewerApplicationList();

    public Optional<Incubator> findFirstByDodIdEquals(String dodId);
}
