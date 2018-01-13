package com.library.model;

import lombok.*;

/**
 * Created by rbuga on 1/7/2018.
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Data
public class Book {
    private int id;
    private String bookName;
    private String author;
    private Integer issueYear;

}

