// Creating objects in the test class
// written by: Francisco J Segarra Jr


public class Test 
{
	private String testID, answers;
	private double score;
	private String grade;
	
	public Test(String id,String a)
	{
		testID = id;
		answers = a;
		score = 0;
		grade = null;
		
	} // end constructor
	
	
	// set methods for the test scores
	public void setScore(double s)
	{
		score = s;
	}
	
	public void setGrade(String g)
	{
		grade = g;
	}
	
	
	// get methods
	public String getID()
	{
		return testID;
	}
	
	public String getAnswers()
	{
		
		return answers;
	}
	
	public double getScore()
	{
		return score;
	}
	
	public String getGrade()
	{
		return grade;
	}
	
	
	public String toString()
	{
		return testID + " " + answers + " " + score + " " + grade;
	}
	
} // end class
