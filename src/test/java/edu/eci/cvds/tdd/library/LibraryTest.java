package edu.eci.cvds.tdd.library;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.fail;

import edu.eci.cvds.tdd.library.book.Book;
import edu.eci.cvds.tdd.library.loan.Loan;
import edu.eci.cvds.tdd.library.loan.LoanStatus;
import edu.eci.cvds.tdd.library.user.User;
import org.junit.jupiter.api.*;
import java.time.LocalDateTime;

/**
 * Unit test for simple App.
 */
class LibraryTest {
    private Library library;
    private Book book;
    private User user;
    @BeforeEach
    public void setup(){
     library = new Library();
     book = new Book("A","B","C");
     user = new User();
     user.setId("1234");
     user.setName("David");
     library.loanABook(user.getId(),book.getIsbn());
    }
    /**
     * Rigorous Test :-)
     */
    @Test
    void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    @Test
    void testShouldAddNewBook(){
       if (!library.addBook(book)){
           fail();
       }
       assertEquals(1,library.getQuantityOfBooks(book));
    }
    @Test
    void testShouldAddExistingBook(){
        for(int i = 0 ;i < 4;i++){
            if(!library.addBook(book)){
                fail();
            }
        }
        assertEquals(4,library.getQuantityOfBooks(book));
    }
    @Test
    void testShouldChangeLoanStatusToReturned(){
        assertEquals(LoanStatus.RETURNED,library.returnLoan(library.getLoans().get(0)).getStatus());
    }
    @Test
    void testShouldSetReturnDateToNow(){
        assertEquals(LocalDateTime.now(),library.returnLoan(library.getLoans().get(0)).getReturnDate());
    }
    @Test
    void testShouldAddUpTheReturnedBook(){
        int oldQuantity = library.getQuantityOfBooks(library.getLoans().get(0).getBook());
        library.returnLoan(library.getLoans().get(0));
        assertEquals(oldQuantity+1,library.getQuantityOfBooks(library.getLoans().get(0).getBook()));
    }
    @Test
    void testShouldNotReturnLoan(){
        Loan loan = new Loan();
        loan.setLoanDate(LocalDateTime.of(2025,1,1,0,0));
        loan.setStatus(LoanStatus.ACTIVE);
        loan.setUser(user);
        loan.setBook(new Book("A1","B2","C3"));
        assertNull(library.returnLoan(loan));
    }
    @Test
    void testCannotReturnAnAlreadyReturnedLoan(){
        library.returnLoan(library.getLoans().get(0));
        assertNull(library.returnLoan(library.getLoans().get(0)));
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

    @AfterEach
    public  void cleanUp(){
        library = null;
        book= null;
        user= null;
    }

}
