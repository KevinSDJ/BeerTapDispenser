package com.api.beerdispenser.projections;

import java.util.UUID;

public interface DispenserFull{
    UUID get_id();
    Double getFlow_amount();
    String getStatus();
}