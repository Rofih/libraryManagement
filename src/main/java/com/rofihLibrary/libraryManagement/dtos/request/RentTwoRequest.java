package com.rofihLibrary.libraryManagement.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@NoArgsConstructor
@AllArgsConstructor
@Data
public class RentTwoRequest {
    private String bookName;
    private String bookAuthor;
    private String userName;
    private String returnDate;
}
