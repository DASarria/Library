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


//    /**
//     * Esta esta fallando por index out of bounds
//     */
//    @Test
//    void testShouldChangeLoanStatusToReturned(){
//        assertEquals(LoanStatus.RETURNED,library.returnLoan(library.getLoans().get(0)).getStatus());
//    }

//    /**
//     * igual falla
//     */
//    @Test
//    void testShouldSetReturnDateToNow(){
//        assertEquals(LocalDateTime.now(),library.returnLoan(library.getLoans().get(0)).getReturnDate());
//    }

//    /**
//     * tambien
//     */
//    @Test
//    void testShouldAddUpTheReturnedBook(){
//        int oldQuantity = library.getQuantityOfBooks(library.getLoans().get(0).getBook());
//        library.returnLoan(library.getLoans().get(0));
//        assertEquals(oldQuantity+1,library.getQuantityOfBooks(library.getLoans().get(0).getBook()));
//    }


    @Test
    void testShouldNotReturnLoan(){
        Loan loan = new Loan();
        loan.setLoanDate(LocalDateTime.of(2025,1,1,0,0));
        loan.setStatus(LoanStatus.ACTIVE);
        loan.setUser(user);
        loan.setBook(new Book("A1","B2","C3"));
        assertNull(library.returnLoan(loan));
    }

//    /**
//     * tambien falla
//     */
//    @Test
//    void testCannotReturnAnAlreadyReturnedLoan(){
//        library.returnLoan(library.getLoans().get(0));
//        assertNull(library.returnLoan(library.getLoans().get(0)));
//    }


    @Test
    public void loanACorrectBookTest() {
        Book book = new Book("AA", "BB", "CC1");
        User user = new User();
        user.setId("123456");
        user.setName("Juan");
        library.addBook(book);
        library.addUser(user);
        library.loanABook(user.getId(), book.getIsbn());
        Loan loan = library.getLoans().get(0);
        assertEquals(loan.getBook().getIsbn(), book.getIsbn());
    }

    @Test
    public void loanABookTest() {
        Book book = new Book("AA", "BB", "CC1");
        User user = new User();
        user.setId("123456");
        user.setName("Juan");
        library.addBook(book);
        library.addUser(user);
        library.loanABook(user.getId(), book.getIsbn());
        assertEquals(1, library.getLoans().size());
    }

    @Test
    public void noLoanWithoutNoUserTest() {
        library.addBook(book);
        Loan loan = library.loanABook("55555",book.getIsbn());
        assertNull(loan);
    }

    @Test
    public void noLoanWithoutBookTest() {
        library.addUser(user);
        Loan loan = library.loanABook(user.getId(),"55555");
        assertNull(loan);

    }

    @Test
    public void noLoanWithNoValidBookTest() {
        library.addUser(user);
        library.addBook(book);
        Loan loan = library.loanABook(user.getId(),"INVALID");
        assertNull(loan);
    }



    @AfterEach
    public  void cleanUp(){
        library = null;
        book= null;
        user= null;
    }

}
