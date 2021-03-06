package com.petr.persistence.entity.survey;

import com.petr.persistence.entity.Question;
import com.petr.persistence.entity.Status;
import com.petr.persistence.entity.SurveyResult;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Survey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status = Status.UNPUBLISH;

    private Long date = new Date().getTime();

    private Integer count;

    private Integer passed = 0;

    @OneToMany(mappedBy = "survey")
    private List<Question> questions;

    @OneToMany(mappedBy = "survey")
    private List<SurveyResult> surveyResults;

    @OneToMany(mappedBy = "survey")
    private List<SurveyLimit> surveyLimits;


}
