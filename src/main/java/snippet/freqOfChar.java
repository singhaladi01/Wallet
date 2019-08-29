package snippet;

import java.util.HashMap;
import java.util.Map;

public class freqOfChar {

	public static void main(String[] args) {
		String str = "aaabbgggaajjyyjjbb";
		
		HashMap<String,Integer> map = new HashMap<>();
		int count =0;
		
		for(int j=0;j<str.length();j++)
		{
			if(map.containsKey(str.substring(j,j+1)))
			{
				count = map.get(str.substring(j,j+1));
				count++;
				map.put(str.substring(j,j+1),count);
			}
			else
			{
				map.put(str.substring(j,j+1), 1);
			}	
		}
		
		for(Map.Entry<String, Integer> entry:map.entrySet())
		{
			System.out.println("Key = "+entry.getKey()+" , Value = "+entry.getValue());
		}
		
	}

}
