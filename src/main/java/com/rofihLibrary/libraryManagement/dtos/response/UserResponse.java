package com.rofihLibrary.libraryManagement.dtos.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserResponse {
    private String userId;
    private String userName;
    private String message;
}
