package deprecated;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.fdmgroup.daos.AuthorDAO;
import com.fdmgroup.daos.AuthorDaoImpl;
import com.fdmgroup.entities.Author;
import com.fdmgroup.entities.Book;

public class BookFileReader {

	public List<Book> readBooksFromFile(EntityManagerFactory factory) {
		List<Book> listOfBooks = new ArrayList<Book>();

		Book book = null;
		try {
			FileReader reader = new FileReader("H:/Java/Books Table.csv");
			BufferedReader br = new BufferedReader(reader);

			String line;
			while ((line = br.readLine()) != null) {
				book = createBook(line, factory);
				listOfBooks.add(book);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException i) {
			i.printStackTrace();
		}

		return listOfBooks;
	}

	private Book createBook(String bookRow, EntityManagerFactory factory) {
		String[] arr = bookRow.split(",");
		long isbn = Long.parseLong(arr[0]);

		String title = arr[1];

		if (arr.length > 7) {
			int extraCommas = arr.length - 7;
			for (int i = 2; i < extraCommas + 2; i++) {
				title += "," + arr[i];
			}
			title = title.substring(1, title.length() - 1);
		}

		String category = arr[arr.length - 5];
		String authorString = arr[arr.length - 4];
		int year = Integer.parseInt(arr[arr.length - 3]);
		int numberOfPages = Integer.parseInt(arr[arr.length - 2]);
		double price = Double.parseDouble(arr[arr.length - 1].substring(1));

		String[] authorArray = authorString.split(" ");

		int arraySize = authorArray.length;
		String firstName = authorArray[0];
		
		String lastName = authorArray[1];
		String email = firstName.toLowerCase() + "." + lastName.toLowerCase() + "@books4u.com";
		email = email.replaceAll("\\s+","");

		AuthorDAO authorDAO = new AuthorDaoImpl(factory);
		Author author = authorDAO.getAuthor(email);

		Book book = new Book(isbn, title, category, numberOfPages, year, price);
		book.setAuthor(author);

		return book;
	}

}
