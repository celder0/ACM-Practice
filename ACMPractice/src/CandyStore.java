import java.util.ArrayList;
import java.util.Scanner;



class CandyStore {

	public static void main(String[] args) {
	
		Scanner in = new Scanner(System.in);
		int count = 0;
		String[] line = new String[1];
		Double money = 0.0;
		ArrayList<String> array = new ArrayList<String>();
		ArrayList<String> maxCal = new ArrayList<String>();
		do
		{
			//Takes in the starting values setting how many candies there are and the amount of money
			line = in.nextLine().split("\\s+"); 
			count = Integer.parseInt(line[0]); 
			money = Double.parseDouble(line[1]);
			if (!line[0].equalsIgnoreCase("0") && !line[1].equalsIgnoreCase("0.00"))
			{
				do
				{
					//Takes in each line adding its data to the ArrayList
					line = in.nextLine().split("\\s+"); 
					array.add(line[0]); 
					array.add(line[1]); 
					count--;
				}while(count>0);
				maxCal.add(maximumCalories(array,money));
				array.clear();
			}
		}while (!line[0].equalsIgnoreCase("0") && !line[1].equalsIgnoreCase("0.00"));
		for(int i = 0; i<maxCal.size(); i++)
		{
			System.out.println(maxCal.get(i));
		}
		in.close();
	}

	//Determines the maximum calories that can be optained from the given candies with the given money(m)
	private static String maximumCalories(ArrayList<String> array, Double m)
	{
		Double maxCPP = 1.0;
		int maxLocation = 0;
		int maxCalories = 0;
		int calories = 0;
		Double money = 0.0;
		
		while (maxCPP > 0.0)
		{
			maxCPP = 0.0;
			calories = 0;
			money = m;
			//Determines the largest Cost per Piece of candy
			for(int i = 0; i < array.size(); i+=2)
			{
				if(maxCPP < Double.parseDouble((String) array.get(i))/Double.parseDouble((String) array.get(i+1)))
				{
					maxCPP = Double.parseDouble((String) array.get(i))/Double.parseDouble((String) array.get(i+1));
					maxLocation = i;
				}
			}
			if(array.size()>0)
			{
				// Adds as many calories as possible at the highest cost
				do
				{
					calories += Double.parseDouble((String) array.get(maxLocation));
					money = money - Double.parseDouble((String) array.get(maxLocation+1));
				}while (money >= Double.parseDouble((String) array.get(maxLocation+1)));
				
				//Removes the highest cost from the array
				array.remove(maxLocation);
				array.remove(maxLocation);
				array.trimToSize();
			}
			
			for( int j = 0; j < array.size(); j+=2)
			{
				if(money%Double.parseDouble((String) array.get(j+1)) == 0)
				{
					do
					{
						calories += Double.parseDouble((String) array.get(j));
						money -= Double.parseDouble((String) array.get(j+1));
					}while (money > Double.parseDouble((String) array.get(j+1)));
				}
			}
			if(maxCalories< calories)
			{
				maxCalories = calories;
			}
		}
		return String.valueOf(maxCalories);
	}
}
