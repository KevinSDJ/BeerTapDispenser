package com.api.beerdispenser.projections;

import java.util.Date;

public interface ConsumptionFullData {
    Long get_id();
    Date getOpen_at();
    Date getClose_at();
    Double getUsage_amount();
}
