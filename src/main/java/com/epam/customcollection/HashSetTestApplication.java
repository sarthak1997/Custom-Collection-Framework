package com.epam.customcollection;

import com.epam.customcollection.test.Student;
import com.epam.exceptions.DuplicateEntryException;
import com.epam.exceptions.ElementNotFoundException;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class HashSetTestApplication {

  static final Logger logger = Logger.getLogger(HashSetTestApplication.class);

  /**
   * Driver method to test the functionalities of HashSet Collection.
   * 
   * @param args for command line arguments
   */
  public static void main(String[] args) throws DuplicateEntryException,
      ElementNotFoundException {

    HashSet<Student> testObj = new HashSet<>();

    Student s1 = new Student(2, "John");
    Student s2 = new Student(1, "Doe");
    Student s3 = new Student(3, "Doe");
    Student s4 = new Student(6, "John");
    Student s5 = new Student(0, "John");
    Student s6 = new Student(8, "John");
    Student s7 = new Student(4, "John");

    // test for add
    testObj.add(s1);
    testObj.add(s2);
    testObj.add(s3);
    testObj.add(s4);
    testObj.add(s5);
    testObj.add(s6);
    testObj.add(s7);
    logger.info("SUCCESS: test for adding elements");

    // test for remove
    testObj.remove(s4);
    logger.info("SUCCESS: test for remove");

    // test for update
    testObj.update(s3, new Student(55, "Noob"));
    logger.info("SUCCESS: test for update");

    // test for Iterable
    for (Student s : testObj) {
      System.out.println(s);
    }
    logger.info("SUCCESS: test for iterability");

    System.out.println(testObj.find(s6) + " " + testObj.find(s4));
    logger.info("SUCCESS: test for finding in O(1)");

    // test for overloaded constructor
    List<Integer> testList = new ArrayList<>();

    testList.add(1);
    testList.add(2);
    testList.add(3);
    testList.add(4);
    testList.add(5);

    Set<Integer> testObj1 = new HashSet<>(testList);
    logger.info("SUCCESS: test for initializing with overloaded constructor");

    for (Integer s : testObj1) {
      System.out.println(s);
    }

  }

}
