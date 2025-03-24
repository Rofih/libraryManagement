package com.rofihLibrary.libraryManagement.data.models;

import com.rofihLibrary.libraryManagement.data.models.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    private String userId;
    private String username;
    private String password;
    private String email;
    private Role role;
}
