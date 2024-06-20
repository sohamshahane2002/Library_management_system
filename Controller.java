package com.qsp.lms.controller;

import java.util.ArrayList;

import com.qsp.lms.model.Book;
import com.qsp.lms.model.Library;
import com.qsp.lms.view.View;

public class Controller {
	static Library library=View.getLibrary();
	public static void addBook(Book book) {
		ArrayList<Book> books=library.getBooks();
		if(books==null) {
			books=new ArrayList<Book>();
		}
		books.add(book);
		library.setBooks(books);                                     
		
	}
	
	public static ArrayList<Book> fetchAllBooks() {
		return library.getBooks();
		
	}
	
	public static Book findBookById(int id) {
		ArrayList<Book> allBooks=fetchAllBooks();
		for (Book book : allBooks) {
			if(book.getId()==id) {
				return book;
			}
			
		}
		return null;
	}
	
	public static int removeBook(int bookIdToRemove) {
		Book book=findBookById(bookIdToRemove);
		if(book!=null) {
			ArrayList<Book>existingBooks=library.getBooks();
			int initialSize=existingBooks.size();
			existingBooks.remove(book);
			int finalSize=existingBooks.size();
			library.setBooks(existingBooks);
			return initialSize-finalSize;
		}
		return 0;
	}

	public static boolean updateBook(Book existingBookData, Book newDataForExistingBook) {
		if(existingBookData == null) 
			return false;
		ArrayList<Book> books = library.getBooks();
		newDataForExistingBook.setId(existingBookData.getId());
		books.remove(existingBookData);
		books.add(newDataForExistingBook);
		return true;
		
		
	}



}