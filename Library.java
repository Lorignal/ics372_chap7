package ics372_ch_7;

/**
 *
 * @author David
 */
public class Library{

	private MemberList members;
	private Catalog books;

	//communication between Library and UserInterface p 180
	public static final int BOOK_NOT_FOUND = 1;
	public static final int BOOK_NOT_ISSUED = 2;
	// MEMBER_NOT_FOUND, etc.
	

	// p 182 called by UserInterface
	public Book addBook(String title, String author, String id) {
		Book book = new Book(title, author, id);
		if (books.insertBook(book)) { 	// typo in book "catalog" instead of "books"
			return (book);
		}
		return null;
	} // end addBook

	
	public Member addMember(String name, String address, String phone){

	} // end addMember


	// invoked by UserInterface
	// return a reference to an issued book
	// page 183	
	public Book issueBook(String memberId, String bookId) {
		Book book = books.search(bookId);		// typo in book "catalog" instead of "books"
		if (book == null) {
			return null;
		}
		if (book.getBorrower() != null) {
			return null;
		}
		Member member = members.search(memberId);		// typo in book for "members" and not "memberList"?
		if (member == null) {
			return null;
		}
		if (!(book.issue(member) && member.issue(book))) {
			return null;
		}
		return book;

	} // end issueBook


	public int returnBook(String bookId) {




	} // end returnBook

	public int removeBook(String bookId) {




	} // end removeBook	
	
	public int placeHold(String memberId, String bookId, int duration) {




	} // end returnBook	
	
	public Member processHold(String bookId) {




	} // end processHold	
	

	public int removeHold(String memberId, String bookId) {




	} // end removeHold


	public Member searchMembership(String memberId) {




	} // end searchMembership
	
	
	public Book renewBook(String memberId, String bookId) {




	} // end renewBook
	

	// Library provides a query that returns an Interator of all transactions of a member
	// on a given date
	// this is implemented by passing the query to the appropriate Member object
	// p 184
	public Iterator getTransactions(String memberId, Calendar date) {




	} // end getTransactions
	
	
	public Iterator getBooks(String memberId) {




	} // end getBooks	
	
	
	// write the Library object to a file named "LibraryData" and return true if nothing goes wrong
	// page 189
	public boolean save() {
		FileOutputStream file = new FileOutputStream("LibraryData");
		ObjectOutputStream output = new ObjectOutputStream(file);
		output.writeObject(library);
		// replace library with "this"?
		return true;
	}	
	
	
	
	// override default readObject method to ignore retrieval if a copy exists in memory
	// prevents UserInterface from doing direct deserialization
	// p 189
	// need to implement in Catalog and MemberList analagously p 191
	private void readObject(java.io.ObjectInputStream input) {
		try {
			input.defaultReadObject();
			if (library == null) {
				library = (Library) input.readObject();
			} else {
				input.readObject();
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		) catch (Exception e) {
			e.printStackTrace();
		}
	} // end readObject
	

	// if there is no memory-resident copy of the Library object,
	// retrieve reads the disk copy;
	// otherwise, it returns the copy in memory
	// in case of unexpected error, it returns null 
	// p 190
	public static Library retrieve() {
		try {
			FileInputStream file = new FileInputStream("LibraryData");
			ObjectInputStream input = new ObjectInputStream(file);
			input.readObject();
			return library;
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return null;
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
			return null;
		}	
	} // end retrieve
		


} // end Library