// Assignment #: Arizona State University CSE205 #11
//         Name: Wyatt Colyn
//    StudentID: 121-547-4364
//      Lecture: TTh 4:30 - 5:45 p.m
//  Description: This is a utility class that provide a static method that
//				 takes an infix string, checked and determine if parentheses
//				 are matching, if matching, returns a postfix string.

import java.util.Stack;

public class InfixToPostfixConverter
{
	//**********************************************************************
	//The precedence method determines the precedence between two operators.
  	//If the first operator is of higher or equal precedence than the second
  	//operator, it returns true, otherwise it returns false.
    //***********************************************************************
   public static boolean precedence(char first, char second)
   {
	   int charObj = Character.valueOf(first);
	   int charObj2 = Character.valueOf(second);
	   int q1 = 0,q2 = 0;
		if (charObj == 42) // *
			q1 = 2;
		if (charObj == 47) // '/'
			q1 = 2;
		if (charObj == 43) // +
			q1 = 1;
		if (charObj == 45) // -
			q1 = 1;
		if (charObj == 40)
			q1 = 0;
		if (charObj2 == 42) // *
			q2 = 2;
		if (charObj2 == 47) // '/'
			q2 = 2;
		if (charObj2 == 43) // +
			q2 = 1;
		if (charObj2 == 45) // -
			q2 = 1;
	   
	   if(q1 >= q2)
		   return true;
	   return false;

   }

	//*************************************************************************
	//The static convertToPostfix method will convert the infixString
	//into the corresponding postfix string. Check the algorithm on
	//assignment #11's description page. Mark each case clearly inside the code
    //*************************************************************************
   public static String convertToPostfix(String infixString)
   {
      //initialize the resulting postfix string
      String postfixString = "";
      int cq = 0;

      //initialize the stack
      Stack<Character> stack1 = new Stack<Character>();

     //Obtain the character at index i in the string
      for (int i=0; i < infixString.length(); i++)
      {
         char currentChar = infixString.charAt(i);
         Character c = Character.valueOf(currentChar);
      	//Case A:
         if(c > 64 && c < 91 || c > 96 && c < 123)  //if operand append to string output
        	 postfixString += currentChar;

      	//Case B:
         else if(c == 40)
        	 stack1.push(currentChar);   //if left parenthesis, push onto stack

      	//Case C:
         else if((c == 43 || c == 42 || c == 47 || c == 45) && stack1.size() == 0) //if operator and stack is empty, push onto stack
        	 stack1.push(currentChar);

        //Case D:
         else if((c == 40 || c == 43 || c == 42 || c == 47 || c == 45) && stack1.size() != 0) //if operator and stack is not empty, compare precedences 
         {
         char p = stack1.peek();
         if(precedence(p, currentChar) == true)
         {  
        	 do  
        	 {
        	 postfixString += stack1.pop();
        	 if(stack1.size() == 0)
        		 break;
        	 p = stack1.peek();
        	 }while(precedence(p, currentChar) == true);
         }
				stack1.push(currentChar);
			}
			// Case E:
			else if (c == 41 && stack1.size() != 0)  //if right parenthesis, pop elements from stack and append to string until left parenthesis is met
			{
				char p = stack1.peek();
				do 
				{
					if (p == 40)
						break;
					postfixString += stack1.pop();
					if (stack1.size() == 0)
						break;
					p = stack1.peek();
				} while (p != 40);

				if (stack1.size() == 0)
					cq = -2;
				else if (stack1.peek() == 40)
					stack1.pop();
			}
			
			else if(stack1.size() == 0 && c== 41)//special case when stack is empty and the element read is a right parenthesis
				cq = -2;

      } //end of for loop

		// Case F:
		if (stack1.size() > 0) //pop the remaining elements on the stack and append to string
		{
			char c;
			while (stack1.size() > 0) 
			{
				c = stack1.peek();
				if (c == 40) 
				{
					cq = -1;
					break;
				}
				if (stack1.size() == 0)
					break;
				postfixString += stack1.pop();
				if (stack1.size() == 0)
					break;
			}
		}

		if (cq == -1)
			return "-1";
		if (cq == -2)
			return "-2";
		return postfixString;

   }//end of convertToPostfix method

}//end of the InfixToPostfixConverter class