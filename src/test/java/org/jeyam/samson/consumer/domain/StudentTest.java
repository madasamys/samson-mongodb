package org.jeyam.samson.consumer.domain;

import org.jeyam.samson.consumer.domain.Student;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author: madasamy
 * @version: 0.1
 */
public class StudentTest
{
    private Student student1;
    private Student student2;

    @Before
    public void setUp()
    {
        student1 = new Student("foo", "bar", 23);
        student2 = new Student(student1.getFirstName(), student1.getLastName(), student1.getAge());
    }

    @Test
    public void testToString()
    {
        System.out.println("--toString--");
        String result = student1.toString();
        assertTrue(result.contains("foo"));
    }

    @Test
    public void testEquals()
    {
        System.out.println("--equals--");
        assertTrue(student1.equals(student2));
    }

    @Test
    public void testNotEquals()
    {
        System.out.println("--notEquals--");
        student2.setAge(12);
        assertFalse(student1.equals(student2));
    }

    @Test
    public void testHashCode()
    {
        Set<Student> students = new HashSet<Student>();
        students.add(student1);
        students.add(student2);
        assertEquals(1, students.size());
        assertEquals(student1.hashCode(), student2.hashCode());
    }
}
