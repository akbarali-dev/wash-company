package com.example.washer.projection;

import java.time.LocalDate;
import java.util.UUID;

public interface OrderProjection {
    UUID getId();

    String getCarModel();

    String getCarNumber();

    String getClientName();

    Integer getClientNumber();

    LocalDate getDate();

    boolean getIsActive();

    Integer getPrice();

}
