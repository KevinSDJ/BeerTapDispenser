package com.api.beerdispenser.projections;

import java.util.Date;

public record ConsumptionFull(Integer _id,Date open_at,Date close_at,Double usage_amount) {}
