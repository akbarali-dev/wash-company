package com.example.washer.model;

import com.example.washer.model.template.AbsEntity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


import javax.persistence.Column;
import javax.persistence.Entity;

@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "services")
public class Service extends AbsEntity {
    @Column(unique = true)
    private String name;
    private int duration;
    private int price;


}
