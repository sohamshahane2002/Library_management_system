package com.qsp.lms.view;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.qsp.lms.controller.Controller;
import com.qsp.lms.model.Book;
import com.qsp.lms.model.Library;

public class View {
	private static Library library = new Library();
	static Scanner myInput = new Scanner(System.in); //to avoid redundancy. You can make of single blocks too
	
	public static Library getLibrary() {
		return library;
	}
	
	public static void setLibrary(Library library) {
		View.library = library;
	}
	
	static {
		System.out.println("----Welcome to library----");
		System.out.print("Enter name of library : ");
		library.setName(myInput.nextLine());
		System.out.print("Enter address : ");
		library.setAddress(myInput.nextLine());
	}
	
	public static void main(String[] args) {
		do {
			System.out.println("Select an operation to be performed: ");
			System.out.println(" 1. Add book \n 2. Fetch all books \n 4. Update book \n 3. remove book \n 5. Exit");
			System.out.println("Enter digit respective to desired option : ");
			byte userChoice = myInput.nextByte();
			myInput.nextLine();
			
			switch(getValidByteInput()) {
	        case 0:
	        	System.out.println("----EXITED-----");
	        	myInput.close();
	        	System.exit(0);
	        	break;
	        case 1:
	        	Book bookToAdd = new Book();
	        	System.out.println("Enter id: ");
	        	bookToAdd.setId(myInput.nextInt());
	        	myInput.nextLine();
	        	Book bookData = addOrUpdateInputs();
	        	bookToAdd.setName(bookData.getName());
	        	bookToAdd.setAuthor(bookData.getAuthor());
	        	bookToAdd.setPrice(bookData.getPrice());
	        	Controller.addBook(bookToAdd);
	        	break;
	        case 2:
	            displayAllBooks(Controller.fetchAllBooks());
	        	break;
	        case 3:
	        	ArrayList<Book> allBooks = Controller.fetchAllBooks();
	        	displayAllBooks(Controller.fetchAllBooks());
	        	if(allBooks == null || allBooks.isEmpty())
	        	{
	        		System.out.println("No book to update");
	        	} else {
	        		System.out.println("Enter book id to update");
		        	int bookIdToUpdate = myInput.nextInt();
		        	myInput.nextLine();
		        	if(Controller.updateBook(Controller.findBookById(bookIdToUpdate), addOrUpdateInputs())){
		        		System.out.println("Book updated");
		        	} else {
		        		System.out.println("Unable to update book");
		        	}
	        		
	        	}
	        	
	        	
	        	break;
	        case 4:
	        	displayAllBooks(Controller.fetchAllBooks());
	        	System.out.println("Enetr book ID to remove");
	        	int bookIdToRemove = myInput.nextInt();
	        	myInput.nextLine();
	        	if(Controller.removeBook(bookIdToRemove)==1) {
	        		System.out.println("Book has been removed");
	        	}else {
	        		System.out.println("Unable to remove book");	
	        	}
	    
	        	
	        	break;
	        default:
	        	System.out.println("-----Invalid Selection------");
	        	break;
	        }
	        
		}while(true);
		

	}
    public static void displayAllBooks(ArrayList<Book> allBooks) {
    	if(allBooks==null || allBooks.isEmpty()) {
    		System.out.println("No books in the library");
    	}else {
			//printing table header
			System.out.printf("%-5s %-20s %-20s %-10s%n","ID","BOOK NAME","AUTHOR NAME","PRICE");
			System.out.println("-------------------------------------------------------");
//    		//printing table row
    		for(Book book : allBooks) {
    			System.out.printf("%-5d %-20s %-20s %-10f%n",book.getId(),book.getName(),book.getAuthor(),book.getPrice());
   		}
    }

}
    public static Book addOrUpdateInputs() {
    	Book bookdata = new Book();
    	System.out.print("Enter Book Name:");
    	bookdata.setName(myInput.nextLine());
    	
    	System.out.print("Enter Author Name:");
    	bookdata.setAuthor(myInput.nextLine());
    	
    	System.out.print("Enter Price:");
    	bookdata.setPrice(myInput.nextDouble());
    	myInput.nextLine();
    	return bookdata;
    }

	public static byte getValidByteInput() {
		while (true) {
			try {
				return myInput.nextByte();
			} catch (InputMismatchException e) {
				System.out.print("Invalid input,please enter valid digit:");
				myInput.nextLine();//to clear invalid input
			}
		}
	}
}