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
    private Loan loan;

    @BeforeEach
    public void setup(){
     library = new Library();
     book = new Book("A","B","C");
     user = new User();
     user.setId("1234");
     user.setName("David");
     library.addBook(book);
     library.addUser(user);
     loan = library.loanABook(user.getId(),book.getIsbn());
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


    /**
     * Esta esta fallando por index out of bounds
     */
    @Test
    void testShouldChangeLoanStatusToReturned(){
        assertEquals(LoanStatus.RETURNED,library.returnLoan(library.getLoans().get(0)).getStatus());
    }

    /**
     * igual falla
     */
    @Test
    void testShouldSetReturnDateToNow(){
        LocalDateTime beforeReturn = LocalDateTime.now();
        Loan returnedLoan = library.returnLoan(loan);
        LocalDateTime afterReturn =  LocalDateTime.now();
        assertTrue(
                (returnedLoan.getReturnDate().isAfter(beforeReturn) || returnedLoan.getReturnDate().isEqual(beforeReturn)) &&
                        (returnedLoan.getReturnDate().isBefore(afterReturn) || returnedLoan.getReturnDate().isEqual(afterReturn)));
    }

    /**
     * tambien
     */
    @Test
    void testShouldAddUpTheReturnedBook(){
        int oldQuantity = library.getQuantityOfBooks(library.getLoans().get(0).getBook());
        library.returnLoan(library.getLoans().get(0));
        assertEquals(oldQuantity+1,library.getQuantityOfBooks(library.getLoans().get(0).getBook()));
    }


    @Test
    void testShouldNotReturnLoan(){
        Loan badLoan = new Loan();
        badLoan.setLoanDate(LocalDateTime.of(2025,1,1,0,0));
        badLoan.setStatus(LoanStatus.ACTIVE);
        badLoan.setUser(user);
        badLoan.setBook(new Book("A1","B2","C3"));
        assertNull(library.returnLoan(badLoan));
    }

    /**
     * tambien falla
     */
    @Test
    void testCannotReturnAnAlreadyReturnedLoan(){
        library.returnLoan(loan);
        assertNull(library.returnLoan(loan));
    }


    @Test
     void loanACorrectBookTest() {
        Loan newLoan = library.getLoans().get(0);
        assertEquals(newLoan.getBook().getIsbn(), book.getIsbn());
    }

    @Test
     void loanABookTest() {
        assertEquals(1, library.getLoans().size());
    }

    @Test
     void noLoanWithoutNoUserTest() {
        library.addBook(book);
        Loan newLoan = library.loanABook("55555",book.getIsbn());
        assertNull(newLoan);
    }

    @Test
     void noLoanWithoutBookTest() {
        library.addUser(user);
        Loan newLoan = library.loanABook(user.getId(),"55555");
        assertNull(newLoan);

    }

    @Test
     void noLoanWithNoValidBookTest() {
        library.addUser(user);
        library.addBook(book);
        Loan newLoan = library.loanABook(user.getId(),"INVALID");
        assertNull(newLoan);
    }



    @AfterEach
    public  void cleanUp(){
        library = null;
        book= null;
        user= null;
    }

}
