package org.jisu.e_comm_inventory_service.dto.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ErrorResponse {
    @JsonIgnore
    private Integer status;
    private String error;
    private List<String> suberrors;
}
