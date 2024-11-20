package org.jisu.e_comm_inventory_service.dto.response;

import java.util.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PaginatedResponse {
    private List<?> content;
    private Integer currentPage;
    private Integer pageSize;
    private Integer totalItems;
    private Integer currentPageItemsNumber;
    private Integer totalPages;
    private boolean isLastPage;
}
