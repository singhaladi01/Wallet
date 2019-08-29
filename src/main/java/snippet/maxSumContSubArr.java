package snippet;

public class maxSumContSubArr {
	public static void main(String[] args) 
	{
		int[] arr= {-2,-3,4,-1,-2,1,5,-3};
		int arrLength = arr.length;
		int max_sum = Integer.MIN_VALUE;
		int max_so_far = arr[0];
		
		for(int i=0;i<arrLength;i++)
		{
			max_so_far = max_so_far + arr[i];
			
			if(max_so_far < 0)
			{
				max_so_far =0;
			}
			else if(max_so_far > max_sum)
			{
				max_sum = max_so_far;
			}
		}
		
		System.out.println(max_sum);
	
	}
}

