package com.example.washer.projection;

import java.util.UUID;

public interface WasherProjection {
    UUID getId();
    Byte[] getImage();
    boolean getIsActive();
    String getName();
    int getStake();
    int getTelephoneNumber();
    UUID getWasherCompanyId();
    String getWasherCompanyName();








}
