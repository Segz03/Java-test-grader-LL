// Creating student objects
// written by: Francisco J Segarra Jr


public class Student 
{
	private String firstName, lastName, ID;
	
	public Student(String id, String fn, String ln)
	{
		ID = id;
		firstName = fn;
		lastName = ln;
		
	} // end constructor
	
	
	
	// the get methods for the private Student data
	public String getFirstName()
	{
		return firstName;
	}
	
	public String getLastName()
	{
		return lastName;
	}
	
	public String getID()
	{
		return ID;
	}
	
	
	
	public String toString()
	{
		return ID + " " + firstName + " " + lastName;
	}
	
	
} // end class
