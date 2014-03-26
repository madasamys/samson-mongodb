package org.jeyam.samson.consumer.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * @author: madasamy
 * @version: 0.1
 */
@Document
public class Student implements Serializable
{
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private int age;

    public Student(String firstName, String lastName, int age)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public Student()
    {

    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    @Override
    public String toString()
    {
        ToStringBuilder builder = new ToStringBuilder(this);
        builder.append("firstName", getFirstName());
        builder.append("lastName", getLastName());
        builder.append("age", getAge());
        return builder.toString();
    }

    @Override
    public boolean equals(Object object)
    {
        EqualsBuilder builder = new EqualsBuilder();
        if (object instanceof Student) {
            Student otherObject = (Student) object;
            builder.append(getFirstName(), otherObject.getFirstName());
            builder.append(getLastName(), otherObject.getLastName());
            builder.append(getAge(), otherObject.getAge());
            return builder.isEquals();
        }
        return false;
    }

    @Override
    public int hashCode()
    {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append(getFirstName());
        builder.append(getLastName());
        builder.append(getAge());
        return builder.hashCode();
    }
}
