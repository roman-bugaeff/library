package com.library.model;

import lombok.*;

/**
 * Created by rbuga on 1/7/2018.
 */
@NoArgsConstructor
@Setter
@Getter
@ToString

public class Reader {

    private String id;
    private String firstName;
    private String lastName;
    private Integer yearOfBirth;
}
