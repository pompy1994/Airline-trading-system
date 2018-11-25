package AMR17S2;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;

public class memberProcessor 
{
	private File memberFile;
	private File instruFile;
	private File resultFile;
	private File reportFile;
	private ArrayList<Member> mb;
	private memberCenter memberCenter;
	private queryCenter queryCenter;
	
	
	public memberProcessor()
	{
	}
	// Initialization
	public memberProcessor(String []s)
	{		
		 memberFile = new File(s[0]+".txt");
		 instruFile = new File(s[1]+".txt");
		 resultFile = new File(s[2]+".txt");
		 reportFile = new File(s[3]+".txt");
		 memberCenter = new memberCenter();
		 queryCenter = new queryCenter();
		 mb = new ArrayList<Member>();
	}
	
	// Save the result
	public void saveResult() throws FileNotFoundException
	{
		PrintWriter out = new PrintWriter(resultFile);
		out.println(memberCenter.toString());
		out.close();
	}
	// Read the instruction file and use the corresponding function
	public void readIns() throws FileNotFoundException
	{
		Scanner scan = new Scanner (instruFile);
		while (scan.hasNextLine())
		{
			String instruction = scan.nextLine();
			Scanner sc = new Scanner(instruction);
			String keyword, param;
			
			if(sc.hasNext())
			{	
				keyword = sc.next();
				if(sc.hasNextLine())
				{
					param = sc.nextLine();
					if(keyword.equalsIgnoreCase("add"))
					{
						try
						{
							memberCenter.addMember(param);
						}
						catch(Exception e)
						{
							continue;
						}
					}
					else if(keyword.equalsIgnoreCase("delete"))
					{
						try
						{
							memberCenter.deleteMember(param);
						}
						catch(Exception e)
						{
							continue;
						}
					}
					else if(keyword.equalsIgnoreCase("earn"))
					{
						try
						{
							memberCenter.earn(param);
						}
						catch(Exception e)
						{
							continue;
						}
					}
					else if (keyword.equals("redeem"))
					{	
						try
						{
							memberCenter.redeem(param);
						}
						catch(Exception e)
						{
							
						}
						
					}
					else if (keyword.equalsIgnoreCase("query"))
					{
						try
						{
							mb = memberCenter.getArrayList();
							queryCenter.CreateMemberList(mb);
							queryCenter.query(param);
						}
						catch(Exception e)
						{
							continue;
						}					
					}
					
				}
			}
		}
	}
	
	// Read the member file and write it into the arraylist
	public void readMem() throws FileNotFoundException
	{	
		
		int memberLc = 0;
		Scanner sc = new Scanner(memberFile);
		Member c = new Member();
		mb.add(c);
		String key = null;
		while(sc.hasNextLine())
		{	
			String temp = sc.nextLine();
			Scanner scn = new Scanner(temp);
			if(scn.hasNext())
			{
				key = scn.next();
			}
			if(temp.isEmpty())
			{
				memberLc ++;
				mb.add(new Member());
			}
			else
			{
					String [] tempAr = temp.split("\\s");
					if(key.equalsIgnoreCase("number"))
					{
						try
						{
							int n =Integer.parseInt(tempAr[1]);
							mb.get(memberLc).setNumber(n);
							if(!mb.get(memberLc).validNumber(tempAr[1]))
							{	
								mb.get(memberLc).setNumber(0);
								mb.get(memberLc).setFlag(-1);
							}
							
						}
						catch(Exception e)
						{
							mb.get(memberLc).setNumber(0);
							mb.get(memberLc).setFlag(-1);
						}
						
					}
					else if(key.equalsIgnoreCase("name"))
					{	
						try
						{
							mb.get(memberLc).setFirstName(tempAr[1]);
							mb.get(memberLc).setSecondName(tempAr[2]);
							if(!mb.get(memberLc).validName())
							{								
									mb.get(memberLc).setFirstName(null);
									mb.get(memberLc).setSecondName(null);
									mb.get(memberLc).setFlag(-1);							
							}
						}
						catch(Exception e)
						{
							mb.get(memberLc).setFirstName(null);
							mb.get(memberLc).setSecondName(null);
							mb.get(memberLc).setFlag(-1);
						}
					
					}
					else if(key.equalsIgnoreCase("birthday"))
					{
						try
						{
							mb.get(memberLc).setBirthday(tempAr[1]);
							if(!mb.get(memberLc).validBirthday())
							{
								mb.get(memberLc).setBirthday(null);
							}
							else
							{
								String dateStr = tempAr[1];
								String array[] = dateStr.split("[-|/]");
								int year = Integer.valueOf(array[2]);
								int month = Integer.valueOf(array[1]);
								int day = Integer.valueOf(array[0]);
								dateStr = array[0]+"/"+array[1]+"/"+array[2];
								mb.get(memberLc).setBirthday(dateStr);
							}
						}
						catch(Exception e)
						{
							mb.get(memberLc).setBirthday(null);
						}
						
					}
					else if(key.equalsIgnoreCase("tier"))
					{
						
						try
						{
							mb.get(memberLc).setTier(tempAr[1]);
							if(!mb.get(memberLc).validTier())
							{	
								mb.get(memberLc).setTier(null);
							}
						}
						catch(Exception e)
						{
							mb.get(memberLc).setTier(null);
						}	
					}
					else if(key.equalsIgnoreCase("address"))
					{	
						try
						{
							String address = temp.substring(8);
							mb.get(memberLc).setAdress(address);
							if(!mb.get(memberLc).validAddress())
							{
								mb.get(memberLc).setAdress(null);
							}
						}
						catch(Exception e)
						{
							mb.get(memberLc).setAdress(null);
						}
						
					}
					else if(key.equalsIgnoreCase("points"))
					{
						try
						{
							String pointsS = temp.substring(7).trim();
							double points  =Double.parseDouble(pointsS);
							mb.get(memberLc).setPoints(points);
							if(!mb.get(memberLc).validPoints())
							{	
								
								mb.get(memberLc).setPoints(0.0);
							}
							
						}
						catch(Exception e)
						{
							mb.get(memberLc).setPoints(0.0);
						}
						
					}
					else if(key.equalsIgnoreCase("mileage"))
					{	
						try
						{
							String str = tempAr[1];
							String str2 = "";
							for(int i=0;i<str.length()-2;i++)
							{
								str2+=str.charAt(i);
							}
							double d =Double.parseDouble(str2);
							mb.get(memberLc).setMileage(d);
							if(!mb.get(memberLc).validMileage())
							{
								mb.get(memberLc).setMileage(0.0);
							}
						}
						catch(Exception e)
						{
							mb.get(memberLc).setMileage(0.0);
						}
					}
					else
					{		
		
						continue;
					}
			}
			scn.close();
		}
		sc.close();
		for(int i=0;i<mb.size();i++)
		{
			if(mb.get(i).getFlag()==-1||mb.get(i).getNumber()==0||mb.get(i).getFirstname()==null||mb.get(i).getSecondname()==null)
			{
				mb.remove(i);
				i--;
			}	
		}
		for(int j=0;j<mb.size();j++)
		{
			for(int k=j+1;k<mb.size();k++)
			{
				if(mb.get(k).getNumber()==mb.get(j).getNumber())
				{
					mb.remove(k);
					k--;
				}
			}	
		}
		
		memberCenter.CreateMemberList(mb);
	}

	// Get the output report
	public void outReport() throws FileNotFoundException
	{
		PrintWriter out = new PrintWriter(reportFile);
		out.println(queryCenter.toString());
		out.close();
	}
	
}
