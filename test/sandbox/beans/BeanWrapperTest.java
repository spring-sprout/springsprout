package sandbox.beans;

import org.junit.Test;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.util.ClassUtils;
import static org.hamcrest.CoreMatchers.*;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: whiteship
 * Date: 2009. 11. 21
 * Time: 오후 3:08:29
 */
public class BeanWrapperTest {

    @Test
    public void create(){
        // 1. by object

        Book book = new Book();
        BeanWrapper bookWrapper = new BeanWrapperImpl(book);
        assertNotNull(bookWrapper);
        
        // 2. class
        BeanWrapper bookBeanWrapperByClass = new BeanWrapperImpl(Book.class);
        assertThat(bookBeanWrapperByClass, is(notNullValue()));
    }

    @Test
    public void getPropertyAndSetProperties(){
        Book book = new Book();
        book.setYear(2010);
        book.setName("봄싹 All in one");
        book.setAuther("봄싹");
        book.setPublishedDate(new Date());

        BeanWrapper bookWrapper = new BeanWrapperImpl(book);

        assertThat((Integer) bookWrapper.getPropertyValue("year"), is(2010));
        assertThat((String) bookWrapper.getPropertyValue("name"), is("봄싹 All in one"));

        bookWrapper.setPropertyValue("year", 2011);
        assertThat(book.getYear(), is(2011));

        bookWrapper.setPropertyValue("year", "2013");
        assertThat(book.getYear(), is(2013));

    }

}
