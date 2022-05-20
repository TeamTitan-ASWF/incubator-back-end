package com.incubator;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table (name = "incubators")
public class Incubator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String refNum;
    private  String fName;
    private  String lName;
    private String mI;
    private  String dodId;
    private  String rank;
    private LocalDate dob;
    private LocalDate lastACFT;
    private Integer acftScore;
    private Integer height;
    private  Integer weight;
    @Lob
    private String techBG;
    @Lob
    private String motivation;
    private String referenceName;
    private String referenceRank;
    private String referenceEmail;
}
