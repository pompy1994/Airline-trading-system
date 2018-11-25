package AMR17S2;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class memberCenter {
	private ArrayList<Member> mb;
	
	public memberCenter()
	{
	}
	
	//To invoke the member list from memberProcessor
	public void CreateMemberList(ArrayList<Member> mb)
	{
		this.mb = mb;
	}
	
	//Override toString
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		for(Member m : mb)
		{	
			sb.append(m.toString());
			sb.append("\n");
		}
		return sb.toString();
	}
	
	public ArrayList<Member> getArrayList()
	{
		return mb;
	};
	// Function to add member
	public void addMember(String param)
	{	int indexNumber = 0; 
		String NumberString = null;
		String firstName = null;
		String secondName = null;	
		String address = null;
		String tier = null;
		String birthday = null;
		double mileage = 0.0;
		double points = 0.0;
		String temp = param;
		boolean numberExist = false;
		
		String [] ss=temp.split(";");
		
		for(int i=0;i<ss.length;i++)
		{	
			try
			{
				if(ss[i].indexOf("number")>=0)
				{
					String Number = ss[i].substring(7);
					Number = Number.trim();
					NumberString = Number;
					indexNumber = Integer.parseInt(Number);
				}
				
				if(ss[i].indexOf("name")>=0)
				{
					String name = ss[i].substring(5);
					name = name.trim();
					String [] nameAr = name.split("\\s");
					firstName = nameAr[0];
					secondName = nameAr[1];
				}
				
				if(ss[i].indexOf("address")>=0)
				{
					address = ss[i].substring(8);
					address = address.trim();
				}
				if(ss[i].indexOf("birthday")>=0)
				{	
					
					String array[] = ss[i].substring(9).trim().split("[-|/]");
					birthday = array[0]+"/"+array[1]+"/"+array[2];
					
				}
				if(ss[i].indexOf("mileage")>=0)
				{
					String mileageS = ss[i].substring(8);
					mileageS = mileageS.trim();
					String S = "";
					for(int j=0;j<mileageS.length()-2;j++)
					{
						S+=mileageS.charAt(j);
					}
					mileage =Double.parseDouble(S);
				}
				if(ss[i].indexOf("tier")>=0)
				{
					tier = ss[i].substring(5);
					tier = tier.trim();
				}
				if(ss[i].indexOf("points")>=0)
				{
					String pointsS = ss[i].substring(7);
					pointsS= pointsS.trim();
					points = Double.parseDouble(pointsS);
				}	
			}
			catch(Exception e)
			{
				continue;
			}
	
		}
	
			for(Member m: mb)
			{
				if(m.getNumber()==indexNumber)
				{	
					
					if(secondName!=null&&firstName!=null)
					{
						String tempFirstName=m.getFirstname();
						String tempSecondNmae=m.getSecondname();
						m.setFirstName(firstName);
						m.setSecondName(secondName);
						if(!m.validName())
						{
							m.setFirstName(tempFirstName);
							m.setSecondName(tempSecondNmae);	
						}
					}
					if(address!=null)
					{
						String tempAddress = m.getAddress();
						m.setAdress(address);
						if(!m.validAddress())
						{
							if(!m.validAddress())
							{
								m.setAdress(tempAddress);
							}
						}
					}
					if(birthday!=null)
					{
						String tempBirthday = m.getBirthday();
						m.setBirthday(birthday);
						if(!m.validBirthday())
						{
							m.setBirthday(tempBirthday);	
						}
						
					}
					if(tier!=null)
					{	
						String tempTier = m.getTier();
						m.setTier(tier);
						if(!m.validTier())
						{
							m.setTier(tempTier);
						}
						
					}
					
					if(points>=0.0)
					{	
						double originalPoints = m.getPoints();
						m.setPoints(points+originalPoints);
					}
					if(mileage>=0.0)
					{	
						double originaMileage = m.getmileage();
						m.setMileage(mileage+originaMileage);
					}
					numberExist = true;
				}
			}
			
			if(numberExist==false)
			{	
				Member m = new Member();
				m.setNumber(indexNumber);
				m.setFirstName(firstName);
				m.setSecondName(secondName);
				m.setAdress(address);
				m.setBirthday(birthday);
				m.setMileage(mileage);
				m.setTier(tier);
				m.setPoints(points);
				
				if(!m.validAddress())
				{
					m.setAdress(null);
				}
				
				if(!m.validMileage())
				{
					m.setMileage(0.0);
				}
				
				if(!m.validTier())
				{
					m.setTier(null);
				}	
				
				if(!m.validPoints())
				{
					m.setPoints(0.0);
				}
				
				if(!m.validBirthday())
				{
					m.setBirthday(null);
				}

				if(m.validNumber(NumberString)&&m.validName())
				{
					mb.add(m);
				}
			}
	}
	//Function to delete member
	public void deleteMember(String param)
	{
		String temp = param.trim();
		
		if(temp.indexOf("number")>=0)
		{	
			temp=temp.substring(7);
			int numberInt = Integer.parseInt(temp);
			for(int i=0;i<mb.size();i++)
			{
				if(mb.get(i).getNumber()==numberInt){
					mb.remove(i);
					i--;
				}
			}
		}
		
		if(temp.indexOf("name")>=0)
		{	
			String name = temp.substring(5);
			String [] nameAr = name.split("\\s");
			String firstName = nameAr[0];
			String secondName = nameAr[1];
			for(int j=0;j<mb.size();j++)
			{
				if(mb.get(j).getFirstname()!=null&&mb.get(j).getSecondname()!=null&&mb.get(j).getFirstname().equalsIgnoreCase(firstName)&&(mb.get(j).getSecondname().equalsIgnoreCase(secondName))){
					mb.remove(j);
					j--;
				}
			}
		}
	}
	//Function to earn points
	public void  earn(String param)
	{
		int number = 0;
		String tier = null;
		double mileage = 0.0;
		double points = 0.0;
		
		String [] temp = param.split(";");
		if(temp[0].indexOf("number")>=0)
		{
			String numberS = temp[0].substring(7).trim();
			number = Integer.parseInt(numberS);
		}
		if(temp[1].indexOf("mileage")>=0)
		{
			String mileageS = temp[1].substring(8).trim();
			String S = "";
			for(int j=0;j<mileageS.length()-2;j++)
			{
				S+=mileageS.charAt(j);
			}
			mileage =Double.parseDouble(S);
		}
		
		for(Member m:mb)
		{
			if(m.getNumber()==number)
			{
				tier = m.getTier();
				if(tier==null)
				{
					tier = m.fixTier();
					if(tier.equalsIgnoreCase("silver"))
					{	
						points = m.getPoints()+(0.25*(mileage));
					}
					if(tier.equalsIgnoreCase("gold"))
					{
						
						points = m.getPoints()+(0.5*(mileage));
					}
					if(tier.equalsIgnoreCase("platinum"))
					{
						points = m.getPoints()+(1.0*(mileage));
					}
					m.setPoints(points);
					m.setMileage(mileage+m.getmileage());
					m.fixTier();
				}
				else if(tier!=null)
				{	
					if(tier.equalsIgnoreCase("silver"))
					{
						points = m.getPoints()+(0.25*(mileage));
					}
					else if(tier.equalsIgnoreCase("gold"))
					{	
						points = m.getPoints()+(0.5*(mileage));
					}
					else if(tier.equalsIgnoreCase("platinum"))
					{
						points = m.getPoints()+(1.0*(mileage));
					}
					m.setPoints(points);
					m.setMileage(mileage+m.getmileage());
					m.fixTier();
				}
			}
		}
		
	}
	// Function to redeem points
	public void redeem(String param)
	{
		int number = 0;
		String tier = null;
		double mileage = 0.0;
		double points = 0.0;
		
		String [] temp = param.split(";");
		if(temp[0].indexOf("number")>=0)
		{
			String numberS = temp[0].substring(7).trim();
			number = Integer.parseInt(numberS);
		}
		if(temp[1].indexOf("points")>=0)
		{
			String pointsS = temp[1].substring(7).trim();
			points =Double.parseDouble(pointsS);
		}
		
		for(Member m:mb)
		{
			if(m.getNumber()==number)
			{
				if((m.getPoints()-points)>=0)
				{
					points = m.getPoints()-points;
					m.setPoints(points);
					m.fixTier();
				}
			}
		}
		
	}
	
	
	
}
