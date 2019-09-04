package com.seu.vCampus.util;

import java.util.ArrayList;
import java.util.List;

public class BookManage extends Message {

    private List<Book> BookList;

    public BookManage(){
        BookList = new ArrayList<>();
    }

    private void AddBook(Book add){
        BookList.add(add);
    }
}
