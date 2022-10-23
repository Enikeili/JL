package com.enikeili.jlab1;

import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Testing the MyContainer
 * @author enikeili
 */
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class MyContainerTests
{
    private MyContainer<String> container;
    @BeforeEach
    public void set_up()
    {
        container = new MyContainer<String>();
    }

    /**
     * Checking: count of container elements = 0
     */
    @Test
    public void test_container_init()
    {
        assertTrue(container.getCount_elements() == 0);
    }
    
    /**
     * Checking: size = negativ number
     */
    @Test
    public void test_bad_size()
    {
        assertThrows(NegativeArraySizeException.class, () ->
        {
            container = new MyContainer < String > (-1);
        });
    }

    /**
     * Checking: insert_element() method
     */
    @Test
    public void test_insert_elements()
    {
        container.insert_element("null");

        assertEquals("null", container.getElement_by_index(0));

        container.insert_element("ein");

        assertEquals("null", container.getElement_by_index(0));
        assertEquals("ein", container.getElement_by_index(1));

        assertTrue(container.getCount_elements() == 2);
    }

    /**
     * Checking: insert_element() method with new element = null
     */
    @Test
    public void test_insert_element_null() 
    {
        assertThrows(NullPointerException.class, () ->
        {
            container.insert_element(null);
        });

    }

    /**
     * Checking: setElement_by_index() method with element = null
     */
    @Test
    public void test_setElement_by_index_null()
    {
        assertThrows(NullPointerException.class, () ->
        {
            container.insert_element("positive");
            container.setElement_by_index(0, null);
        });
    }

    /**
     * Checking: setElement_by_index() method
     */
    @Test
    public void test_setElement_by_index()
    {
        container.insert_element("null");
        container.insert_element("ein");

        container.setElement_by_index(1, "zwei");

        assertEquals("null", container.getElement_by_index(0));
        assertEquals("zwei", container.getElement_by_index(1));
    }

    /**
     * Checking: extract_element_by_index() method
     */
    @Test
    public void test_extract_element_by_index()
    {
        container.insert_element("null");
        container.insert_element("ein");

        assertEquals("null", container.extract_element_by_index(0));
        assertTrue(container.getCount_elements() == 1);
        assertEquals("ein", container.getElement_by_index(0));
    }

    /**
     * Checking: extract_element_by_index() method with negative index
     */
    @Test
    public void testRemoveOutOfBounds()
    {
    	assertThrows(IndexOutOfBoundsException.class, () ->
    	{
                container.extract_element_by_index(-1);
        });
    }

}