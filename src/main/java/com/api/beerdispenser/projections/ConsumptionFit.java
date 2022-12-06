package com.api.beerdispenser.projections;

import java.util.Date;

public interface ConsumptionFit {
    Date getOpen_at();
    Date getClose_at();
    Double getFlow_volume();
    Double getTotal_spent();
}
