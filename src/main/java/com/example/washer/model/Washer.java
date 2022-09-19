package com.example.washer.model;

import com.example.washer.model.template.AbsEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Washer extends AbsEntity {
    private String name;
    private int telephoneNumber;
    private int stake;
    private byte[] image;
    private boolean isActive;
    @ManyToOne
    @JoinColumn(name = "wash_company_id")
    private WashCompany washCompany;

}
