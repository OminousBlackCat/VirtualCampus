package com.seu.vCampus.util;

        import java.util.ArrayList;
        import java.util.List;

public class BookManage extends Message {

    private List<Book> BookList;

    public BookManage(){
        BookList = new ArrayList<>();
    }

    public List<Book> getBookList(){
        return BookList;//Changed by MaouSora 2019/9/7
    }
    public void AddBook(Book add){
        BookList.add(add);
    }
}
