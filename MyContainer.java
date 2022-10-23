package com.enikeili.jlab1;

import java.util.Arrays;

/**
* My Container
* @author enikeili
*/
public class MyContainer<T>
{
	public static final int DEFAULT_SIZE = 10; //default container size
  private int size; //container size
  private int count_elements; //count of container elements
  private T[] elements; //array of container elements
  
  /**
  * Default constructor, creates container with size = MyContainer.DEFAULT_SIZE 
  */
  public MyContainer()
  {
      this.count_elements = 0;
      this.size = MyContainer.DEFAULT_SIZE;
      this.elements = (T[]) new Object[size];
  }
  
  /**
   * Constuctor with given size, creates container with size = given size
   * @param size new container size
   */
  public MyContainer(int size)
  {
      this.count_elements = 0;
      this.size = size;
      this.elements = (T[]) new Object[size];
  }

  /**
   * Clears container, container size doesn't change, count of container elements to 0
   */
  public void clear()
  {
      for(T element: this.elements)
      {
          element = null;
      }
      this.count_elements = 0;
  }
  
  /**
   * Returns container size
   * @return container size
   */
  public int getSize()
  {
      return this.size;
  }
  
  /**
   * Returns count of container elements
   * @return count of container elements
   */
  public int getCount_elements()
  {
      return this.count_elements;
  }

  /**
   * Sets container size
   * @param new_size new container size
   */
  private void setSize(int new_size)
  {
      this.size = new_size;
  }

  /**
   * Sets count of container elements
   * @param new_count_elements new count of container elements
   */
  private void setCount_elements(int new_count_elements)
  {
      this.count_elements = new_count_elements;
  }
  
  /**
   * Retuns container element by index
   * @param index index of container element 
   * @return container element by index
   * @throws IndexOutOfBoundsException 
   */
  public T getElement_by_index(int index) throws IndexOutOfBoundsException
  {
      if (index < 0 || index > this.getCount_elements() - 1)
          throw new IndexOutOfBoundsException();
      else
          return this.elements[index];
  }

  /**
   * Sets container element by index
   * @param index index of container element
   * @param new_value new value of container element
   * @throws NullPointerException
   * @throws IndexOutOfBoundsException 
   */
  public void setElement_by_index(int index, T new_value) throws NullPointerException, IndexOutOfBoundsException
  {
      if (new_value == null)
          throw new NullPointerException();
      else
          if (index < 0 || index > this.getCount_elements() - 1)
              throw new IndexOutOfBoundsException();
          else
              this.elements[index] = new_value;
  }
  
  /**
   * Expansions container capacity, doublings container capasity 
   */
  private void expansion_of_capasity()
  {
      if (this.count_elements == this.size)
      {
          this.setSize(2*this.size);
          MyContainer<T> new_container = new MyContainer<T>(this.size);
          System.arraycopy(this.elements, 0, new_container.elements, 0, this.size/2);
          this.elements = (T[]) new Object[this.size];
          System.arraycopy(new_container.elements, 0, this.elements, 0, this.size/2);
      }
  }

  /**
   * Inserts new container element to the end of container
   * @param new_value new value of container element
   * @throws NullPointerException 
   */
  public void insert_element(T new_value) throws NullPointerException
  {
      if (new_value == null)
          throw new NullPointerException();
      else
      {
          expansion_of_capasity();
          setElement_by_index(this.getCount_elements(), new_value);
          setCount_elements(this.getCount_elements() + 1);
      }
  }

  /**
   * Delets container element by index
   * @param index index of container element
   * @throws IndexOutOfBoundsException 
   */
  public void delete_element_by_index(int index) throws IndexOutOfBoundsException
  {
      if (index < 0 || index > this.getCount_elements() - 1)
          throw new IndexOutOfBoundsException();
      else
      {
          MyContainer<T> new_container = new MyContainer<T>(this.size);
          for (int i = 0; i < this.count_elements; i++)
          {
              if (i < index)
                  new_container.insert_element(elements[i]);
              if (i > index)
                  new_container.insert_element(elements[i-1]);
          }
          this.elements = (T[]) new Object[this.size];
          System.arraycopy(new_container.elements, 0, this.elements, 0, this.size);
          this.count_elements -= 1;
      }
  }
  
  /**
   * Extacts container element by index, returns value of container element and delets container element
   * @param index index of container element
   * @return container element with index = given index
   */
  public T extract_element_by_index(int index)
  {
      T return_val = this.getElement_by_index(index);
      delete_element_by_index(index);
      return return_val;
  }
}