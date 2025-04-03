package com.rofihLibrary.libraryManagement.dtos.request;

import com.rofihLibrary.libraryManagement.data.models.enums.Role;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserRequest {
    @NotNull
    private String username;
    @NotNull
    private String password;
    @NotNull
    private String email;
    @NotNull
    private Role role;
}
