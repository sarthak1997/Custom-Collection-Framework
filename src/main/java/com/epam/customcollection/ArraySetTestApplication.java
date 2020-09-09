package com.epam.customcollection;

import com.epam.customcollection.test.Student;
import com.epam.exceptions.DuplicateEntryException;
import com.epam.exceptions.ElementNotFoundException;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;


/** Driver class. */
public class ArraySetTestApplication {

  static final Logger logger = Logger.getLogger(ArraySetTestApplication.class);

  /**
   * Driver method to test the functioning of ArraySetTestApplication.
   * @param args
   *          are the {@code String[]} i.e command line arguments
   * @throws ElementNotFoundException
   *           in case element not found in set
   * @throws DuplicateEntryException
   *           in case of adding duplicate
   */
  public static void main(String[] args) throws DuplicateEntryException, ElementNotFoundException {
    ArraySet<Student> testObj = new ArraySet<>();

    Student s1 = new Student(2, "John");
    Student s2 = new Student(1, "Doe");
    Student s3 = new Student(3, "Doe");
    Student s4 = new Student(6, "John");
    Student s5 = new Student(0, "John");
    Student s6 = new Student(8, "John");
    Student s7 = new Student(4, "John");

    testObj.add(s1);
    testObj.add(s2);
    testObj.add(s3);
    testObj.add(s4);
    testObj.add(s5);
    testObj.add(s6);
    testObj.add(s7);
    logger.info("SUCCESS: test for adding students more than the default capacity");

    // test for remove with index
    testObj.remove(2);
    logger.info("SUCCESS: test for remove at index");

    // test for remove with element
    testObj.remove(s4);
    logger.info("SUCCESS: test for remove with element");

    // test for indexOf
    int index = testObj.indexOf(s5);
    System.out.println(index);
    System.out.println();
    logger.info("SUCCESS: test for indexOf operation");

    // test for update
    testObj.update(2, new Student(5, "Grey"));
    logger.info("SUCCESS: test for updating a student");

    // test for Iterable
    for (Student s : testObj) {
      System.out.println((Student) s);
    }
    logger.info("SUCCESS: test for iterabilityof the collection");

    System.out.println();

    // test for find with lambda expressions
    ArraySet<Student> resultSet = testObj.find((Student temp) -> temp.getName().equals("John"));
    logger.info("SUCCESS: test for find with lambda expression");

    for (Student result : resultSet) {
      System.out.println(result);
    }
    System.out.println();

    // test for sorting using user defined comparator
    ArraySet.sort(testObj, s1);
    logger.info("SUCCESS: test for sorting with a user defined comparator");

    // test for adding after sorting
    testObj.add(new Student(85, "Sarthak"));

    // test for toString
    System.out.println(testObj);
    System.out.println();
    logger.info("SUCCESS: test for to String method");

    // test for overloaded constructor
    List<Integer> testList = new ArrayList<>();

    testList.add(1);
    testList.add(2);
    testList.add(3);
    testList.add(4);

    Set<Integer> testObj1 = new ArraySet<>(testList);
    logger.info("SUCCESS: test for overloaded collection");

    // testing shrink of the ArraySet
    testObj1.remove(3);
    testObj1.remove(2);
    testObj1.remove(1);
    logger.info("SUCCESS: test for shrinking of the collection while removal");

    System.out.println(testObj1);
  }

}
