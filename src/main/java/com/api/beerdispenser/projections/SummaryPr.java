package com.api.beerdispenser.projections;

import java.util.Set;

import com.api.beerdispenser.entity.Usage;

public interface SummaryPr {
    Double getTotal_amount();
    Set<Usage> getUsages();
}
