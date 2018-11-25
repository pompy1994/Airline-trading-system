package AMR17S2;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Member {
	private String firstName;
	private String secondName;
	private String birthday;
	private String tier;
	private double mileage;
	private double points;
	private int number;
	private String address;
	private int flag;
	
	
	public Member()
	{
		this.firstName = null;
		this.secondName = null;
		this.birthday = null;
		this.tier = null;
		this.address = null;
		this.mileage = 0.0;
		this.points = 0.0;
		this.number = 0;
		this.flag = 1;
		 
	}
	//Functions to return the private variable	
	public int getNumber(){
		return number;
	}
	public String getBirthday()
	{
		return birthday;
	}
	public double getmileage()
	{
		return mileage;
	}
	public String getFirstname()
	{
		return firstName;
	}
	public String getSecondname()
	{
		return secondName;
	}
	public String getAddress()
	{
		return address;
	}
	public String getTier()
	{
		return tier;
	}
	public double getPoints()
	{
		return points;
	}
	public int getFlag()
	{
		return flag;
	}

	//Function to set the private variable
	public void setFirstName(String s)
	{
		firstName = s;
	}
	public void setSecondName(String s)
	{
		secondName = s;
	}
	public void setBirthday(String s)
	{
		birthday = s;
	}
	public void setTier(String s)
	{
		tier = s;
	}
	public void setPoints(double d)
	{
		points = d;
	}
	public void setMileage(double d)
	{
		mileage = d;
	}
	public void setNumber(int i)
	{
		number = i;
	}
	public void setAdress(String s)
	{
		address = s;
	}
	
	public void setFlag(int i)
	{
		flag = i;
	}
	//Function to fix the tier
	public String fixTier()
	{
		if(points<5000)
		{
			tier = "Silver";
		}
		else if(5000<=points&&points<=10000)
		{
			tier = "Gold";
		}
		else if(points>=10000)
		{
			tier = "Platinum";
		}
		return tier;
	}
	//Function to valid the variable
	public boolean validNumber(String s)
	{
		Pattern p = Pattern.compile("^[0-9]{5}$");
		Matcher m = p.matcher(s);
		if(!m.matches())
		{
			return false;
		}
		return true;
	}
	
	public boolean validName()
	{	
		if(firstName==null||secondName==null)
		{
			return false;
		}
		Pattern p = Pattern.compile("[a-zA-Z]+");
		Matcher m1 = p.matcher(firstName);
		Matcher m2= p.matcher(secondName);
		if(!m1.matches()||!m2.matches())
		{
			return false;
		}
		return true;
	}
	
	public boolean validTier()
	{	
		if(tier!=null)
		{
			if(!(tier.equalsIgnoreCase("silver")||tier.equalsIgnoreCase("Platinum")||tier.equalsIgnoreCase("gold")))
			{
				return false;
			}
		}
		return true;
	}
	
	public boolean validMileage()
	{
		Pattern p = Pattern.compile("^\\d+(\\.\\d+)?$");
		String mileageS = String.valueOf(mileage);
		Matcher m = p.matcher(mileageS);
		if(!m.matches())
		{
			return false;
		}
		return true;
	}
	public boolean validPoints()
	{
		Pattern p = Pattern.compile("^\\d+(\\.\\d+)?$");
		String pointsS = String.valueOf(points);
		Matcher m = p.matcher(pointsS);
		if(!m.matches())
		{
			return false;
		}
		return true;
	}
	public boolean validAddress()
	{
		if(address!=null)
		{
			String ss [] = address.trim().split("[,]");
			String rgx0 = "^[0-9]+\\s+[a-zA-Z]+\\s*[a-zA-Z]*$";
			String rgx1 = "^[a-zA-Z]+\\s*[a-zA-Z]*$";
			String rgx2 = "^[a-zA-Z]+$";
			String rgx3 = "^[0-9]{4}$";
			
			if(!(ss[0].trim().matches(rgx0)&&ss[1].trim().matches(rgx1)&&ss[2].trim().matches(rgx2)&&ss[3].trim().matches(rgx3)))
			{
				return false;	
			}		
		}
		return true;
	}
	
	public boolean validBirthday()
	{	
		if(birthday!=null)
		{
			boolean isLeapYear = false;
			String dateStr = birthday.trim();
			Pattern p = Pattern.compile("\\d{1,2}+[-|/]\\d{1,2}+[-|/]\\d{4}+");
			Matcher m = p.matcher(dateStr);
			if(!m.matches())
			{
				return false;
			}
			String array[] = dateStr.split("[-|/]");
			int year = Integer.valueOf(array[2]);
			int month = Integer.valueOf(array[1]);
			int day = Integer.valueOf(array[0]);
			Calendar cal = Calendar.getInstance();  
	    	int nowYear = cal.get(Calendar.YEAR);
	    	if(year>=nowYear)
	    	{
	    		return false;
	    	}
			if(month<1 || month>12)
			{
				return false;
			}
			int[] monthLengths = new int[]{0, 31, -1, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
			if((year % 4 == 0 && year % 100 != 0) || year % 400 == 0)
			{
				isLeapYear = true;
			}
			if(isLeapYear){
			monthLengths[2] = 29;
			}
			else
			{
			monthLengths[2] = 28;
			}
			int monthLength = monthLengths[month];
			if(day<1 || day>monthLength){
			return false;
			}
		}
		return true;	
	}
	

	// Override toString
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("number %05d\n",getNumber()));
		sb.append(String.format("name %s %s\n",getFirstname(),getSecondname()));
		if(getBirthday()!=null)
		{
			sb.append(String.format("birthday %s\n",getBirthday()));
		}
		if(getTier()!=null)
		{
			sb.append(String.format("tier %s\n",getTier()));
		}
		else
		{
			fixTier();
			sb.append(String.format("tier %s\n",getTier()));
		}
		if(getmileage()>0)
		{
			sb.append(String.format("mileage %.2fkm\n",getmileage()));
		}
		if(getPoints()>0)
		{
			sb.append(String.format("points %.2f\n",getPoints()));
		}
		if(getAddress()!=null)
		{
			sb.append(String.format("address %s\n",getAddress()));
		}
		return sb.toString();
	}
}
