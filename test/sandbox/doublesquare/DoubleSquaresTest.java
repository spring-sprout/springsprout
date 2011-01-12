package sandbox.doublesquare;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
/**
 * User: Whiteship
 * Date: 11. 1. 10
 * Time: 오후 1:26
 */
public class DoubleSquaresTest {

    /**
     *
     */
    @Test
    public void getCountOfDoubleSquares(){
        DoubleSquares doubleSquares = new DoubleSquares();
        assertThat(doubleSquares.getCountOfDoubleSquares(1022907856), is(2));
        assertThat(doubleSquares.getCountOfDoubleSquares(1991891221), is(1));
        assertThat(doubleSquares.getCountOfDoubleSquares(858320077), is(1));
        assertThat(doubleSquares.getCountOfDoubleSquares(148208125), is(40));
        assertThat(doubleSquares.getCountOfDoubleSquares(326122507), is(0));
        assertThat(doubleSquares.getCountOfDoubleSquares(1538292481), is(2));
        assertThat(doubleSquares.getCountOfDoubleSquares(5), is(1));
        assertThat(doubleSquares.getCountOfDoubleSquares(625058908), is(0));
        assertThat(doubleSquares.getCountOfDoubleSquares(2027929049), is(2));
        assertThat(doubleSquares.getCountOfDoubleSquares(1041493518), is(0));
        assertThat(doubleSquares.getCountOfDoubleSquares(2), is(1));
        assertThat(doubleSquares.getCountOfDoubleSquares(3), is(0));
        assertThat(doubleSquares.getCountOfDoubleSquares(1475149141), is(1));
        assertThat(doubleSquares.getCountOfDoubleSquares(1000582589), is(2));
        assertThat(doubleSquares.getCountOfDoubleSquares(4225), is(5));
        assertThat(doubleSquares.getCountOfDoubleSquares(801125), is(16));
        assertThat(doubleSquares.getCountOfDoubleSquares(4005625), is(20));
        assertThat(doubleSquares.getCountOfDoubleSquares(10), is(1));
        assertThat(doubleSquares.getCountOfDoubleSquares(1077003976), is(1));
        assertThat(doubleSquares.getCountOfDoubleSquares(473200074), is(2));
    }

    @Test
    public void aboutInt(){
        System.out.println(46341 * 46341);
        System.out.println(2147483647);
        System.out.println(Math.sqrt(2147483647d));
    }

}
