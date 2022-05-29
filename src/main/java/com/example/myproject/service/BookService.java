package com.example.myproject.service;

import java.util.List;


import com.example.myproject.domain.Book;
import com.example.myproject.domain.Page;

public interface BookService {
    void manageBookPages(String isbn , long pageIdToDelete, long pageIdtoUpdate,String content,String chapter,List<Page> newPageList) throws Exception;
}

