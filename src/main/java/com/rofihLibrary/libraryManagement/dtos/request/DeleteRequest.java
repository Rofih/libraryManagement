package com.rofihLibrary.libraryManagement.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DeleteRequest {
    private String bookName;
    private String author;
}
