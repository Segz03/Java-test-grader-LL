//									All in Order
// This Program simulates the grading of students taking an machine scored multiple choice
// test. It reads in the student information, and the test answers using the ID to match the 
// 2 classes. The program grades the test against a key, calculates the the score and assigns
// a letter grade to each student. It uses 2 Linked List to organize the output information 
// sorting the 2 list alphabetically by last name and descending order based on the numeric
// score on the test.
// written by Francisco J Segarra Jr

import java.util.*;
import java.io.*;


public class Main 
{

   public static void main(String[] args) throws IOException 
   {
   	// Scanner for reading in students info
      File Students = new File("students.txt");
      Scanner students = new Scanner(Students);
   		
   	// Scanner for reading in test info
      File Tests = new File("rawTests.txt");
      Scanner test = new Scanner(Tests);
   		    
   	// making an output file for the grade distribution
      PrintWriter outFile = new PrintWriter(new FileOutputStream(new File("gradeData.txt"), false));
   		    
       // making an output file for the errors
      PrintWriter outFileE = new PrintWriter(new FileOutputStream(new File("errors.txt"), false));
   		    
      	// setting heading and sending it to the output file
      String heading = "First Name       Last Name         Avg     Grade\n================================================";
      outFile.println(heading);
   		    
       // declaring the variables
      String id, firstName, lastName, testID, answers, key = "";
   
   	
       // ArrayLists for each class
      ArrayList<Student> st = new ArrayList<>();
      ArrayList<Test> tst = new ArrayList<>();
      ArrayList<String> badID = new ArrayList<>();
       
       // loop inputting data into student class
      while (students.hasNextLine())
      {
         String line = students.nextLine();
         Scanner s = new Scanner(line);
                 
         id = s.next();
         firstName = s.next();
         lastName = s.next();
       
            // creating student objects
         st.add( new Student (id, firstName, lastName));
            
      } // end while loop
       
      students.close();				// close input student file
       
       // loop inputting data into test class
      while(test.hasNextLine())
      {
         String line = test.nextLine();
         Scanner a = new Scanner(line).useDelimiter(";");
          	
         testID = a.next();
         answers = a.next();
              
         if (testID.equals("000000"))
         {
            key = answers;
         }
              
         else if (idValidation(testID) == 0)
         {
            tst.add( new Test (testID, answers));
              
         }
              
         else
            badID.add( new String (testID));
              
      } // end while loop
          
      test.close();
        
       // loop for inputing the score in the Test class
      for(int i = 0; i < tst.size() ; i++)
      {	
       	// calling the test score method and passing the students answers and the key
         double score = testScore(tst.get(i).getAnswers(), key);
          	
          	// putting the graded scores in the test class
         tst.get(i).setScore(score);
          	
          	
      } // end for loop
          
          
       // loop for inputing letter grade in Test class 
      for(int i = 0; i < tst.size() ; i++)
      {
       	// taking the score of the tests and return a letter grade	
         String letGrade = letterGrade(tst.get(i).getScore());
          	
          	// putting the letter grade in the test class
         tst.get(i).setGrade(letGrade);
          	
      } // end for loop
          
       // loop for checking IDs and putting them in the error file if there's any errors
      for(int i = 0 ; i < badID.size() ; i++)
      {
          	
       	// calling id validation method test if there is errors
         int retVal = idValidation(badID.get(i));
          	
         if(retVal == 1)
         {
            outFileE.println(badID.get(i) + " is invalid - too many characters");
         }
         else if(retVal == 2)
         {
            outFileE.println(badID.get(i) + " is invalid - characters in the wrong order");
         }
         else if(retVal == 4)
         {
            outFileE.println(badID.get(i) + " is invalid - letters are not distinct");
         }
         else if(retVal == 3)
         {
            outFileE.println(badID.get(i) + " is invalid - character " + badID.get(i).charAt(2) + " should be a digit");
         }
         else if(retVal == 5)
         {
            outFileE.println(badID.get(i) + " is invalid - character " + badID.get(i).charAt(3) + " should be a digit");
         }
         else if(retVal == 7)
         {
            outFileE.println(badID.get(i) + " is invalid - character " + badID.get(i).charAt(4) + " should be a digit");
         }
         else if(retVal == 9)
         {
            outFileE.println(badID.get(i) + " is invalid - character " + badID.get(i).charAt(5) + " should be a digit");
         }
         else if(retVal == 8)
         {
            outFileE.println(badID.get(i) + " is invalid - duplicate digits");
         }
          		
      } // end for loop
          
          
       // linked list for last name order
      LinkedList nameLink = new LinkedList();
         
       // linked list for score order
      LinkedList scoreLink = new LinkedList();
           
      for(int i = 0; i < tst.size() ; i++)
      {
         double stScore = 0;
         String stLetGrade = "", fName = "", lName = "";
          	
         int j = 0;
          	
         boolean found = false;
         while(j < st.size() && (!found))
         {
            if (st.get(j).getID().equals(tst.get(i).getID()))
            {
               fName = st.get(j).getFirstName();
               lName = st.get(j).getLastName();
               stScore = tst.get(i).getScore();
               stLetGrade = tst.get(i).getGrade();
               	
               found = true;
                  
                  // Linked List
               nameLink.insertName(fName, lName, stScore, stLetGrade);
               scoreLink.insertScore(fName, lName, stScore, stLetGrade);
                  
            }
            j++;
            		
         } // end while
          	
         if (!found)
            outFileE.println("ID: "+tst.get(i).getID()+" - no name found "+tst.get(i).getScore()+" "+tst.get(i).getGrade());
          		
      } // end for loop
          
          
       // initializing the variables for the grade analysis
      int A = 0, a = 0, BB = 0, B = 0, b = 0, CC = 0, C = 0, c = 0, D = 0, F = 0;
          
       // loop to get the analysis of the grades 
      for(int i = 0; i < tst.size() ; i++)
      {
         String grade = tst.get(i).getGrade();
          	
         int j = 0;
          	
         boolean found = false;
         while(j < st.size() && (!found))
         {
            if (st.get(j).getID().equals(tst.get(i).getID()))
            {
               if (grade.equals("A"))
                  A++;	
               if (grade.equals("A-"))
                  a++;
               if (grade.equals("B+"))
                  BB++;
               if (grade.equals("B"))
                  B++;
               if (grade.equals("B-"))
                  b++;
               if (grade.equals("C+"))
                  CC++;
               if (grade.equals("C"))
                  C++;
               if (grade.equals("C-"))
                  c++;
               if (grade.equals("D"))
                  D++;
               if (grade.equals("F"))
                  F++;
               found = true;
            }
            j++;
            		
         } // end while
          	
      } // end for loop
          	    
          // adding the total of students graded	
   /*	      double sum = A+a+BB+B+b+CC+C+c+D+F;
          
         outFile.println("\n\nGrade  Count  Pct\n=====================");
         outFile.printf("  A    %2d    %4.2f%1s\n", A  , (A/sum)*100, "%");
         outFile.printf("  A-   %2d    %4.2f%1s\n", a  , (a/sum)*100, "%");
         outFile.printf("  B+   %2d    %4.2f%1s\n", BB , (BB/sum)*100, "%");
         outFile.printf("  B    %2d    %4.2f%1s\n", B  , (B/sum)*100, "%");
         outFile.printf("  B-   %2d    %4.2f%1s\n", b  , (b/sum)*100, "%");
         outFile.printf("  C+   %2d    %4.2f%1s\n", CC , (CC/sum)*100, "%");
         outFile.printf("  C    %2d    %4.2f%1s\n", C  , (C/sum)*100, "%");
         outFile.printf("  C-   %2d    %4.2f%1s\n", c  , (c/sum)*100, "%");
         outFile.printf("  D    %2d    %4.2f%1s\n", D  , (D/sum)*100, "%");
         outFile.printf("  F    %2d    %4.2f%1s\n", F  , (F/sum)*100, "%");
          	
   */	      
       // displaying the sorted linked List in the output files  
      nameLink.display(outFile);
      scoreLink.display(outFile);
       
       // close the output files
      outFile.close();
      outFileE.close();
          
   } // end main
		
		
	// method that returns the letter grade
   public static String letterGrade(double avg)
   {
   	  // Initializing grade
      String grade = " ";
       	
      if(avg > 46)
      {	
         grade = "A";
      }
            
      else if(avg >= 44)
      {	
         grade = "A-";
      }	
            
      else if(avg >= 42)
      {	
         grade = "B+";
      }
            
      else if(avg >= 40)
      {
         grade = "B";
      }
            
      else if(avg >= 38)
      {
         grade = "B-";
      }	
            
      else if(avg >= 36)
      {
         grade = "C+";
      }
           
      else if(avg >= 34)
      {
         grade = "C";
      }
            
      else if(avg >= 32)
      {
         grade = "C-";
      }
            
      else if(avg >= 30)
      {	
         grade = "D";
      }
            
      else if(avg < 30)
      {
         grade = "F";
      } 
           
      return grade;
   } // end of letterGrade method 
		
   public static double testScore(String t, String k)
   {
      double score = 0;
      int blank = 0, correct = 0, wrong = 0;
      		
      for (int i = 0 ; i < k.length() ; i++)
      {
       // testing the individual character in the String against the key characters
         char letKey =  k.charAt(i);
         char letTest = t.charAt(i);
         	
         if (letTest == letKey)
         {
            correct++;
         }
         		
         if (letTest == ' ')
         {
            blank++;
         }
         		
         if (letTest != letKey)
         {
            wrong++;
         }
         		
      } // end for loop
      		
      score = correct - (.25 * (wrong - blank));
      	
      return score;
   } // end method
		
   public static int idValidation(String input)
   {
       // declaring and initializing variables
      int retVal = 0;
      		
       // testing length of the ID
      if (input.length() != 6)
         retVal = 1;
         	
       // testing if first 2 positions are digits
      else if (Character.isDigit(input.charAt(0)) || Character.isDigit(input.charAt(1)))
         retVal = 2;
          
       // testing if the first 2 letters duplicate
      else if (input.charAt(0) == input.charAt(1))
         retVal = 4;
          
       // testing if the last four digits are alphabetic
      else if (Character.isAlphabetic(input.charAt(2)))
         retVal = 3;
      else if (Character.isAlphabetic(input.charAt(3)))
         retVal = 5;
      if (Character.isAlphabetic(input.charAt(4)))
         retVal = 7;
      if (Character.isAlphabetic(input.charAt(5)))
         retVal = 9;
          
       // test if the last four digits have duplicates
      if (input.charAt(2) == input.charAt(3)||input.charAt(2) == input.charAt(4)||input.charAt(2) == input.charAt(5))
         retVal = 8;
      if (input.charAt(3) == input.charAt(4)||input.charAt(3) == input.charAt(5))
         retVal = 8;
      if (input.charAt(4) == input.charAt(5))
         retVal = 8;
             
      return retVal;
         	
   } // end idValidation

} // end class
