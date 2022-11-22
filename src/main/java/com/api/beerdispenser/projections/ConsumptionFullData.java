package com.api.beerdispenser.projections;

import java.util.Date;

public interface ConsumptionFullData {
    Long getId();
    Date getOpen_at();
    Date getClose_at();
}
