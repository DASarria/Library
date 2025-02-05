package edu.eci.cvds.tdd.library;

import edu.eci.cvds.tdd.library.book.*;
import edu.eci.cvds.tdd.library.loan.Loan;
import edu.eci.cvds.tdd.library.loan.LoanStatus;
import edu.eci.cvds.tdd.library.user.User;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class LibraryTest {
    private  Library library;
        @BeforeEach
        public  void setUp() {
            library = new Library();
    }
    @Test
    public void loanABookTest() {
        Book book = new Book("AA", "BB", "CC1");
        User user = new User();
        user.setId("123456");
        user.setName("Juan");
        //library.addBook(book);
        library.addUser(user);
        library.loanABook(user.getId(), book.getIsbn());
        Loan loan = library.getLoans().get(0);
        assertEquals(loan.getBook().getIsbn(), "CC1");

    }
    
    @AfterEach
    public  void tearDown() {
        library = null;
    }
}