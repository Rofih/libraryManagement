package com.rofihLibrary.libraryManagement.dtos.response;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserResponse {
    private String userId;
    @NotNull
    private String userName;
    @NotNull
    private String message;
    @NotNull
    private String Token;
}
