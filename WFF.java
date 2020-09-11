package cs291Project;

import java.util.ArrayList;

//This class generates Well Formed Formulas and creates a list of tautologies
public class WFF extends Parse
{

	public static void main(String[] args) 
	{
		String[] rules = {"F", "T", "A", "B", "C", "W>W", "W&W", "W+W", "~W", "(W)"};

		ArrayList<String> terminals = new ArrayList<String>();
		
		ArrayList<String> toDo = new ArrayList<String>();
		
		ArrayList<String> seen = new ArrayList<String>(); //don't want to add duplicate non-terminals to string
		
		ArrayList<String> tautologies = new ArrayList<String>();
		
		toDo.add("W=W");
		seen.add("W=W");
		
		
		while (tautologies.size() < 1000)
		{
			String current = toDo.remove(0);
			for (int index = 0; index < 10; index++)
			{	
				String newWFF = current.replaceFirst("W", rules[index]);
				if (newWFF.contains("W"))
				{
					if(!seen.contains(newWFF))
					{
						toDo.add(newWFF);
					}
				}
				else if (newWFF.indexOf('A') == -1 && newWFF.indexOf('B') == -1
						&& newWFF.indexOf('C') == -1)
				{
					terminals.add(newWFF);
					boolean tautology = isTautology(newWFF);
					if (tautology == true)
					{
						tautologies.add(newWFF);
					}
				}
				else if (newWFF.indexOf('A') != -1 && newWFF.indexOf('B') != -1
				&& newWFF.indexOf('A') < newWFF.indexOf('B') 
				&& newWFF.indexOf('B') < newWFF.indexOf('C') )
				{
					terminals.add(newWFF);
					boolean tautology = isTautology(newWFF);
					if (tautology == true)
					{
						tautologies.add(newWFF);
					}
				}
				else if (newWFF.indexOf('A') != -1 && newWFF.indexOf('C') == -1
						&& newWFF.indexOf('A') < newWFF.indexOf('B') )
				{
					terminals.add(newWFF);
					boolean tautology = isTautology(newWFF);
					if (tautology == true)
					{
						tautologies.add(newWFF);
					}
				}
				else if (newWFF.indexOf('A') != -1 && newWFF.indexOf('C') == -1
						&& newWFF.indexOf('B') == -1 )
				{
					terminals.add(newWFF);
					boolean tautology = isTautology(newWFF);
					if (tautology == true)
					{
						tautologies.add(newWFF);
					}
				}
			}
		}
		
		
		System.out.println("Current size of to-do list:");
		System.out.println(toDo.size());
		
		System.out.println();
		
		System.out.println("Tautologies:");
		
		for(int i = 0; i < tautologies.size(); i++)
		{
			System.out.printf("%d. %s \n", i+1, tautologies.get(i));
		}


	}

}
