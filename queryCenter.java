package AMR17S2;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.io.*;

public class queryCenter 
{
	private ArrayList<Member> mb;
	private StringBuilder sb;
	
	public queryCenter()
	{
		sb = new StringBuilder();
	}
	// To invoke the member list from memberProcessor
	public void CreateMemberList(ArrayList<Member> mb)
	{
		this.mb = mb;
	}
	// To judge the query type and use the corresponding function
	public void query(String param)
	{
		String temp = param.trim();
		if(temp.indexOf("tier")>=0)
		{
			String queryTier = temp.substring(5).trim();
			queryTier(queryTier);
		}
		if(temp.indexOf("age")>=0)
		{
			queryAgeMileage();
		}
		
	}
	
	public void queryTier(String queryTier)
	{
		List <Member> list = new ArrayList<Member> ();
		for(Member b:mb)
		{	
			if(b.getTier()==null)
			{
				b.fixTier();
			}
			if(b.getTier().equalsIgnoreCase(queryTier))
			{	
				list.add(b);
			}
		}
		 Collections.sort(list, new Comparator<Member>() { 
				@Override
				public int compare(Member o1, Member o2) {
					if(o1.getFirstname().equals(o2.getFirstname())&&o1.getSecondname().equals(o2.getSecondname()))
					{
						return o1.getNumber()-o2.getNumber();
					}
					return (o1.getFirstname()).compareTo(o2.getFirstname());
				}  
	        }); 
		 sb.append("----query tier "+queryTier+" ----");
		 for(Member b:list)
		 {
			 sb.append("\n"+b.toString());
		 }
		 sb.append("-----------------------\n\n");
		 
	}
	
	public void queryAgeMileage()
	{	
		int ageSlopeMileage [] = new int[5];
		int count = 0;
		for(Member b :mb)
		{	
			count ++;
			Calendar cal = Calendar.getInstance();  
	    	int nowYear = cal.get(Calendar.YEAR);
			String dateStr=b.getBirthday();
			try
			{
				if(dateStr!=null)
				{
					String array[] = dateStr.split("\\D");
					int year = Integer.valueOf(array[2]);
					int month = Integer.valueOf(array[1]);
					int day = Integer.valueOf(array[0]);
					int age = nowYear-year;
					if(age>=0&&age<=8)
					{
						ageSlopeMileage[0]+=b.getmileage();
					}
					else if(age>8&&age<=18)
					{
						ageSlopeMileage[1]+=b.getmileage();
					}
					else if(age>18&&age<=65)
					{
						ageSlopeMileage[2]+=b.getmileage();
					}
					else if(age>65)
					{
						ageSlopeMileage[3]+=b.getmileage();
					}
				}
				else
				{
					ageSlopeMileage[4]+=b.getmileage();
				}
				
				
			}
			catch(Exception e)
			{	
				count--;
				continue;
			}
					
		}
		sb.append("----query age mileage----\n");
		sb.append(String.format("Total Airline Members: %d\n", count));
		sb.append("Age based mileage distribution\n");
		sb.append(String.format("(0,8]: %d\n",ageSlopeMileage[0]));
		sb.append(String.format("(8,18]: %d\n",ageSlopeMileage[1]));
		sb.append(String.format("(18,65]: %d\n",ageSlopeMileage[2]));
		sb.append(String.format("(65,-]: %d\n",ageSlopeMileage[3]));
		sb.append(String.format("Unknown: %d\n",ageSlopeMileage[4]));
		sb.append("-------------------------\n\n");
		
	}
	
	// Override the toString
	public String toString()
	{	
		return sb.toString();
	}
}
