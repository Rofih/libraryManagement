package com.rofihLibrary.libraryManagement.dtos.request;

import com.rofihLibrary.libraryManagement.data.models.enums.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserRequest {
    private String username;
    private String password;
    private String email;
    private Role role;
}
