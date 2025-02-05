package edu.eci.cvds.tdd.library;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.fail;

import edu.eci.cvds.tdd.library.book.Book;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * Unit test for simple App.
 */
public class LibraryTest {
    private static Library library;

    @BeforeAll
    public static void setup(){
     library = new Library();
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
       Book book = new Book("A","B","C");
       if (library.addBook(book)){
           fail();
       }
       assertEquals(1,library.getQuantityOfBooks(book));
    }
    @Test
    void testShouldAddExistingBook(){
        Book book = new Book("A","B","C");
        for(int i=0;i < 4;i++){
            if(library.addBook(book)){
                fail();
            }
        }
        assertEquals(4,library.getQuantityOfBooks(book));
    }
    @AfterAll
    public static void cleanUp(){
        library = null;
    }
}
