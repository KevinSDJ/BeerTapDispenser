package com.api.beerdispenser.endpoints;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.api.beerdispenser.dto.dispenser.ReqStatusDispenserDTO;
import com.api.beerdispenser.dto.dispenser.RequestDispenserDTO;
import com.api.beerdispenser.dto.dispenser.ResponseDispenserDTO;
import com.api.beerdispenser.dto.summary.SummaryResponseDTO;
import com.api.beerdispenser.entity.Dispenser;
import com.api.beerdispenser.services.impl.DispensersServiceImpl;
import com.api.beerdispenser.services.impl.SummaryServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/beer-tap-dispenser/")
public class DispenserEndpoint {

    @Autowired
    private DispensersServiceImpl dispensersServiceImpl;
    
    @Autowired
    private SummaryServiceImpl summaryServiceImpl;

    @Operation(summary = "Create Dispensers")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dispenser created", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = RequestDispenserDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "flow price volume required", content = @Content),
            @ApiResponse(responseCode = "500", description = "internal error", content = @Content)
         })
    @PostMapping("/dispensers")
    public ResponseEntity<ResponseDispenserDTO> newDispenser(@RequestBody RequestDispenserDTO dispenser)
            throws Exception {
        Dispenser s = dispensersServiceImpl.create(dispenser);
        return ResponseEntity.ok(new ResponseDispenserDTO(s.get_id(), s.getFlow_volume()));
    }

    @Operation(summary = "Update state and usage")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dispenser created", content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation =ReqStatusDispenserDTO.class )) }),
            @ApiResponse(responseCode = "400", description = "already open or close", content = @Content),
            @ApiResponse(responseCode = "500", description = "internal error", content = @Content)
         })
    @PutMapping("/dispensers/{id}/status")
    public ResponseEntity<String> generateUsageDispenser(@PathVariable(name = "id") UUID id,
            @RequestBody ReqStatusDispenserDTO status) throws Exception {
        dispensersServiceImpl.updateState(id, status.status());
        return ResponseEntity.status(202).build();
    }
    
    @GetMapping("/dispensers/{id}/spending")
    public ResponseEntity<SummaryResponseDTO> getSpending(@PathVariable(name = "id") UUID id) {
        SummaryResponseDTO summary = summaryServiceImpl.getSummary(id);

        return ResponseEntity.ok(summary);
    }

}
