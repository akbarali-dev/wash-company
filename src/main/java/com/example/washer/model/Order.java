package com.example.washer.model;

import com.example.washer.model.template.AbsEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "orders")
public class Order extends AbsEntity {
    private int price;
    private String carModel;
    @ManyToMany
    private Set<Service> services;
    @ManyToMany
    private Set<Washer> washers;

    private String carNumber;
    private String clientName;
    private int clientNumber;
    private boolean isActive;
    @Column(columnDefinition = "boolean default false")
    private boolean isCancelled;
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "wash_company_id")
    private WashCompany washCompany;

    public Order(int price, String carModel, String carNumber,
                 String clientName, int clientNumber, boolean isActive,
                 LocalDate date, WashCompany washCompany) {
        this.price = price;
        this.carModel = carModel;
        this.carNumber = carNumber;
        this.clientName = clientName;
        this.clientNumber = clientNumber;
        this.isActive = isActive;
        this.date = date;
        this.washCompany = washCompany;
    }
}
