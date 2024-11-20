package org.jisu.e_comm_inventory_service.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserResponse {
    private Integer id;
    private String name;
    private String email;
    private List<RoleResponse> roles;
    private String profilePhoto;
}
