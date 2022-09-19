package com.example.washer.model;

import com.example.washer.model.template.AbsEntity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
public class WashCompany extends AbsEntity {
    private String name;

    private String location;
    private byte[] avatar;

    @ManyToMany
    private List<Service> services;

    public WashCompany(String name, String location, byte[] avatar) {
        this.name = name;
        this.location = location;
        this.avatar = avatar;
    }
}
