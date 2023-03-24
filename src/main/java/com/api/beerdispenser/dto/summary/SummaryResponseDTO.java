package com.api.beerdispenser.dto.summary;

import java.util.Collection;
import com.api.beerdispenser.dto.usage.UsageResponseDTO;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(title="Summary")
public record SummaryResponseDTO(Double amount,Collection<UsageResponseDTO> usages) {
}
