package snippet;

import java.util.Arrays;

public class revStrNumAlphaIntact {

	public static void main(String[] args) {
		String str = "d4c3b2A1";
		
		//String str = "abcd";
		char[] arr = str.toCharArray();
		
		Arrays.sort(arr);
		System.out.println(arr);
		
		int firstIndexofChar = 0;
		int i=0;
		
		while(arr[i]<65)
		{
			i++;
		}
		firstIndexofChar = i;
		System.out.println(firstIndexofChar);
		
		int firstIndexofNum =0;
		
		if(firstIndexofChar==0)
		{
			System.out.println("there are no numbers, all chars!");
			
			for(int k=str.length()-1;k>=0;k--)
			{
				System.out.print(str.charAt(k));
				
			}
		}
		
		for(int j=0;j<str.length();j++)
		{
			
			if(str.charAt(j)<65)
			{
				System.out.print(arr[firstIndexofNum]); 
				firstIndexofNum++;
			}
			else
			{
				System.out.print(arr[firstIndexofChar]); 
				firstIndexofChar++;
			}
		}
		
		

	}

}
