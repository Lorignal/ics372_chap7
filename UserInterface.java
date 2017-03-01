/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ics372_ch_7;

/**
 *
 * @author David
 */
public class UserInterface {

	// private Library library;   ??

	public static void main (String[] s) {
		UserInterface.instance().process();
	}

	// Singleton instance
	public static UserInterface instance() {
		if (userInterface == null) {
			return userInterface = new UserInterface();
		} else {
			return userInterface;
		}
	}

	// check if serialized version of Library object exists in file "LibraryData"
	// must address serialization and singleton p 180
	private UserInterface() {
		File file = new File("LibraryData");
		if (file.exists() && file.canRead()) {
			if (yesOrNo("Saved data exists. Use it?")) {
				retrieve();
			}
		}
		library = Library.instance();
	}

	// provide user with list of options: add member; add books; issue books; return books;
	// renew books; remove books; place hold on book; remove hold on book; process holds;
	// print member's transactions for a date; save data for long-term storage; retrieve data from storage;
	// exit (0); help (13)
	public void process() {
		int command;
		help();
		while ((command = getCommand()) != EXIT) {
			switch (command) {
				case ADD_MEMBER:	addMember();
									break;
				case ADD_BOOKS:		addBooks();
									break;
				case ISSUE_BOOKS:	issueBooks();
									break;
				// several lines of code not shown}
				case HELP:			help();
									break;
			}
		}
	}

	public void addBooks() {
		Book result;
		do {
			String title = getToken("Enter book title");
			String author = getToken("Enter author");
			String bookID = getToken("Enter id");
			result = library.addBook(title, author, bookID);
			if (result != null) {
				System.out.println(result);
			} else {
				System.out.println("Book could not be added");
			}
			if (!yesOrNo("Add more books?")) {
				break;
			}
		} while (true);
	} // end addBooks
	

	// get member ID, set up loop, remember memberId throughout process
	// invoke Library's issueBook method repeatedly based on invocation return values
	public void issueBooks() {
		Book result;
		String memberId = getToken("Enter member id");
		if (library.searchMembership(memberId) == null) {
			System.out.println("No such member");
			return;
		}		
		do {
			String bookID = getToken("Enter book id");
			result = library.issueBook(memberId, bookId);
			if (result != null){
				System.out.println(result.getTitle() + "  " + result.getDueDate());
			} else {
				System.out.println("Book could not be issued");
			}
			if (!yesOrNo("Issue more books?")) {
				break;
			}
		} while (true);
	}


	getToken
	getNumber
	getDate
	getCommand
	yesOrNo(String ??)
	

	// Library returns null when a member is not in Memberlist;
	// otherwise an iterator to the filtered collection is returned
	// UserInterface extracts necessary information and displays it in preferred format
	public void getTransactions() {
		Iterator result;
		String memberID = getToken("Enter member id");
		Calendar date = getDate("Please enter the date for which you want " + 
								"records as mm/dd/yy");
		result = library.getTransactions(memberID, date);
		if (result == null) {
			System.out.println("Invalid Member ID");
		} else {
			while(result.hasNext()) {
				Transaction transaction = (Transaction) result.next();
				System.out.println(transaction.getType() + "  "  + 
									transaction.getTitle() + "\n");
			}
			System.out.println("\n There are no more transaction \n" );
		}
	}

	
	public void save() {
		if (library.save()) {
			System.out.println("The library has been successfully saved" );
		} else {
			System.out.println("There has been an error in saving \n" );
		}
	} // end save
	
	

} // end UserInterface
