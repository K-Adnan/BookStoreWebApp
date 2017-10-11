package deprecated;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.fdmgroup.daos.BookDAO;
import com.fdmgroup.daos.BookDaoImpl;
import com.fdmgroup.entities.Book;

public class BatchBookCreator {
	
	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("DemoPersistence");
		
		BookDAO bookDao = new BookDaoImpl(factory);
		
		BookFileReader br = new BookFileReader();
		
		List<Book> listOfBooks = br.readBooksFromFile(factory);
		
		for (Book eachBook : listOfBooks){
//			bookDao.addBook(eachBook);
			System.out.println(eachBook + " HAS BEEN ADDED");
		}
		
		factory.close();
	}

}
