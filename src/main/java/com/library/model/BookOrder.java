package com.library.model;

import lombok.*;

import java.util.Date;

/**
 * Created by rbuga on 1/7/2018.
 */
@NoArgsConstructor
@Setter
@Getter
@ToString
@Data

public class BookOrder {

    private Book book;
    private Reader reader;
    private Date orderDate;
    private Date returnDate;


}
