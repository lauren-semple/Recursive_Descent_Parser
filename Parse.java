package cs291Project;

public class Parse 
{
	
	public static String testing;
	public static char current;
	public static char next;
	public static int currentIndex;
	
	public static void getNext()
	{
		if(currentIndex < testing.length()-1)
		{
		currentIndex = currentIndex+1;
		current = testing.charAt(currentIndex);
		}
	}
	
	public static boolean implies(boolean left, boolean right)
	{
		if (left == true && right == false)
		{
			return false;
		}
		else	
		{
			return true;
		}
	}
	
	public static boolean L() throws RuntimeException
	{
		//System.out.println("In L");
		if (current == 'T')
		{
			getNext();
			//System.out.println("Returning T from L");
			return true;
		}
		else if (current == 'F')
		{
			getNext();
			//System.out.println("Returning F from L");
			return false;
		}
		else
		{
			throw new RuntimeException("Syntax error");
		}
	}
	
	public static boolean A() throws RuntimeException
	{
		//System.out.println("In A");
		if (current == '~')
		{
			getNext();
			boolean temp = A();
			//System.out.printf("Returning %b from A\n", !temp);
			return !temp;
		}
		else if (current == '(')
		{
			getNext();
			boolean temp = B();
			if (current == ')')
			{
				getNext();
				//System.out.printf("Returning %b from A\n", temp);
				return temp;
			}
			else
			{
 				throw new RuntimeException("Syntax error");
			}
		}
		else
		{
			//System.out.println("Returning L from A");
			return L();
		}
	}
	
	public static boolean Cprime(boolean incoming)
	{
		//System.out.println("In Cprime");
		if (current == '&')
		{
			getNext();
			boolean temp = A();
			boolean result = (incoming && temp);
			boolean temp2 = Cprime(result);
			//System.out.printf("Returning %b from Cprime\n", temp2);
			return temp2;
		}
		else
		{
			//System.out.printf("Returning %b from Cprime\n", incoming);
			return incoming;
		}
	}
	
	public static boolean C()
	{
		//System.out.println("In C");
		boolean temp = A();
		//System.out.printf("Passing %b from C to Cprime\n", temp);
		return Cprime(temp);
	}
	
	public static boolean Dprime(boolean incoming)
	{
		//System.out.println("In Dprime");
		if (current == '+')
		{
			getNext();
			boolean temp = C();
			boolean result = (incoming || temp);
			boolean temp2 = Dprime(result);
			//System.out.printf("Returning %b from Dprime\n", temp2);
			return temp2;
		}
		else
		{
			//System.out.printf("Returning %b from Dprime\n", incoming);
			return incoming;
		}
	}
	
	public static boolean D()
	{
		//System.out.println("In D");
		boolean temp = C();
		//System.out.printf("Passing %b from D to Dprime\n", temp);
		return Dprime(temp);
	}
	
	public static boolean Iprime(boolean incoming)
	{
		//System.out.println("In Iprime");
		if (current == '>')
		{
			getNext();
			boolean temp = D();
			boolean result = implies(incoming, temp);
			boolean temp2 = Iprime(result);
			//System.out.printf("Returning %b from Iprime\n", temp2);
			return temp2;
		}
		else
		{
			//System.out.printf("Returning %b from Iprime\n", incoming);
			return incoming;
		}
	}
	
	public static boolean I()
	{
		//System.out.println("In I");
		boolean temp = D();
		//System.out.printf("Passing %b from I to Iprime\n", temp);
		return Iprime(temp);
	}
	
	public static boolean Eprime(boolean incoming)
	{
		//System.out.println("In Eprime");
		if (current == '=')
		{
			getNext();
			boolean temp = I();
			boolean result = (incoming == temp);
			boolean temp2 = Eprime(result);
			//System.out.printf("Returning %b from Eprime\n", temp2);
			return temp2;
		}
		else
		{
			//System.out.printf("Returning %b from Eprime\n", incoming);
			return incoming;
		}
	}
	
	public static boolean E()
	{
		//System.out.println("In E");
		boolean temp = I();
		//System.out.printf("Passing %b from E to Eprime\n", temp);
		return Eprime(temp);
	}
	
	public static boolean B()
	{
		//System.out.println("In B");
		return E();
	}
	
	//This method tests if a string returns the value true or false
	public static boolean testString()
	{
		
        currentIndex = 0;
		current = testing.charAt(currentIndex);

		boolean tautology = B();
		if (tautology == true)
		{
			return true;
		}
		else 
		{
			return false;
		}
	}
	
	//This method takes a string and test if it is true for all possible combinatiosn of truth values
	public static boolean isTautology(String input)
	{
		testing = input;
		testing = testing.replace('A', 'F');
		testing = testing.replace('B', 'F');
		testing = testing.replace('C', 'F');
		
		boolean test = testString();
		if (test == false)
		{
			return false;
		}
		else 
		{
			testing = input;
			testing = testing.replace('A', 'F');
			testing = testing.replace('B', 'T');
			testing = testing.replace('C', 'T');
			//System.out.println("In test string: " + testing);
			
			test = testString();
			if(test == false)
			{
				return false;
			}
			else
			{
				testing = input;
				testing = testing.replace('A', 'F');
				testing = testing.replace('B', 'F');
				testing = testing.replace('C', 'T');
				//System.out.println("In test string: " + testing);
				
				test = testString();
				if(test == false)
				{
					return false;
				}
				else
				{
					testing = input;
					testing = testing.replace('A', 'F');
					testing = testing.replace('B', 'F');
					testing = testing.replace('C', 'F');
					//System.out.println("In test string: " + testing);
					
					test = testString();
					if(test == false)
					{
						return false;
					}
					else
					{
						testing = input;
						testing = testing.replace('A', 'F');
						testing = testing.replace('B', 'T');
						testing = testing.replace('C', 'F');
						//System.out.println("In test string: " + testing);
						
						test = testString();
						if(test == false)
						{
							return false;
						}
						else
						{
							testing = input;
							testing = testing.replace('A', 'T');
							testing = testing.replace('B', 'T');
							testing = testing.replace('C', 'F');
							//System.out.println("In test string: " + testing);
							
							test = testString();
							if(test == false)
							{
								return false;
							}
							else
							{
								testing = input;
								testing = testing.replace('A', 'T');
								testing = testing.replace('B', 'F');
								testing = testing.replace('C', 'F');
								//System.out.println("In test string: " + testing);
								
								test = testString();
								if(test == false)
								{
									return false;
								}
								else
								{
									testing = input;
									testing = testing.replace('A', 'T');
									testing = testing.replace('B', 'F');
									testing = testing.replace('C', 'T');
									//System.out.println("In test string: " + testing);
									test = testString();
									if(test == false)
									{
										return false;
									}
									else
									{
										return true;
									}
								}
							}
						}
					}
				}
			}
		}		
	}

}
