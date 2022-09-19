package com.example.washer.model;

import com.example.washer.model.template.AbsEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "journals")
public class Journal extends AbsEntity {
    @Column(columnDefinition = "json")
    private String changes;

    @ManyToOne
    @JoinColumn(name = "wash_company_id")
    private WashCompany washCompany;

}
