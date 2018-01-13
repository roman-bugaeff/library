package com.library;

import com.library.model.Book;

import java.util.UUID;

/**
 * Created by rbuga on 1/7/2018.
 */
public class CreateBook {

    private static final int MIN_YEAR = 1990;
    private static final int MAX_YEAR = 2017;
    private static final int N_AUTHORS = 10;

    public static Book getRandomBook() {
        int id = generateUniqueId();
        String bookName = "title" + getRandomNumber(1, N_AUTHORS + 1);
        String author = "author" + getRandomNumber(1, N_AUTHORS + 1);
        Integer issueYear = getRandomNumber(MIN_YEAR, MAX_YEAR + 1);
        return new Book(id, bookName, author, issueYear);
    }
    private static int getRandomNumber(int min, int max) {
        return (int) (min + Math.random() * (max - min));
    }
    public static int generateUniqueId() {
        UUID idOne = UUID.randomUUID();
        String str = "" + idOne;
        int uid = str.hashCode();
        String filterStr = "" + uid;
        str = filterStr.replaceAll("-", "");
        return Integer.parseInt(str);
    }
}
