package model;

//IT19040318
//DE SILVA U.S.C
//FUND MANAGEMENT SERVICE

import java.sql.*;

public class Fund {
	
	//A common method to connect to the DB
	private Connection connect() 
	 { 
			Connection con = null;
			
			try
			{ 
				Class.forName("com.mysql.jdbc.Driver"); 
	 
				//Provide the correct details: DBServer/DBName, username, password 
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/fund", "root", ""); 
			} 
			catch (Exception e) 
			{e.printStackTrace();}
			
			return con; 
	 } 

	
	public String insertFund(String name, String price, String desc)
	 {
			String output = "";
			
			try
			{
					Connection con = connect();
					
					if (con == null)
					{return "Error while connecting to the database for inserting."; }
	 
					// create a prepared statement
					String query = "INSERT INTO `funddetails`(`fundId`, `fundName`, `fundPrice`, `fundDesc`)"
									+ " values (?, ?, ?, ?)";
	 
					PreparedStatement preparedStmt = con.prepareStatement(query);
	 
					// binding values
					 preparedStmt.setInt(1, 0); 
					 preparedStmt.setString(2, name);
					 preparedStmt.setDouble(3, Double.parseDouble(price));
					 preparedStmt.setString(4, desc);
	
					 // execute the statement
					 preparedStmt.execute();
					 con.close();
					 
					 output = "Inserted successfully";
	       }
			catch (Exception e)
			{
					 output = "Error while inserting the item.";
					 System.err.println(e.getMessage());
			}
			
			return output;
	 }
	
	
	public String readFunds() {
		
			String output = ""; 
		
			try
			 { 
					Connection con = connect(); 
					
					 if (con == null) 
					 {return "Error while connecting to the database for reading."; }
			 
					 // Prepare the html table to be displayed
					 output = "<table border='1'><tr><th>Fund Name</th>" +
					 "<th>Fund Price</th>" + 
					 "<th>Fund Description</th>" +
					 "<th>Update</th><th>Remove</th></tr>"; 
				
					 String query = "select * from funddetails"; 
					 Statement stmt = con.createStatement(); 
					 ResultSet rs = stmt.executeQuery(query); 
		
		
					// iterate through the rows in the result set
					 while (rs.next()) 
					 { 
							 String fundId = Integer.toString(rs.getInt("fundId")); 
							 String fundName = rs.getString("fundName"); 
							 String fundPrice = Double.toString(rs.getDouble("fundPrice")); 
							 String fundDesc = rs.getString("fundDesc"); 
					 
							 // Add into the html table
							 output += "<tr><td>" + fundName + "</td>"; 
							 output += "<td>" + fundPrice + "</td>"; 
							 output += "<td>" + fundDesc + "</td>"; 
			 
							 //button
							 output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"+ "<td><form method='post' action='items.jsp'>"+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"+ "<input name='fundId' type='hidden' value='" + fundId+ "'>" + "</form></td></tr>";
		
					 }
					 
					 con.close();
			 
					 // Complete the html table
					 output += "</table>";
			  }
			 	catch (Exception e)
			 	{
					 output = "Error while reading the funds.";
					 System.err.println(e.getMessage());
			 	}
			
			 	return output;
			 }
	
	
	public String updateFund(String ID, String name, String price, String desc)
	{
		 String output = "";
		 
		 try
		 {
			 
			 	Connection con = connect();
		 
			 	if (con == null)
			 	{return "Error while connecting to the database for updating."; }
		 
			 	// create a prepared statement
			 	String query = "UPDATE funddetails SET fundName=?,fundPrice=?,fundDesc=?WHERE fundId=?";
		 
			 	PreparedStatement preparedStmt = con.prepareStatement(query);
		 
				 // binding values
				 preparedStmt.setString(1, name);
				 preparedStmt.setDouble(2, Double.parseDouble(price));
				 preparedStmt.setString(3, desc);
				 preparedStmt.setInt(4, Integer.parseInt(ID));
				 
				 // execute the statement
				 preparedStmt.execute();
				 con.close();
				 
				 output = "Updated successfully";
		 }
		 catch (Exception e)
		 {
			 	output = "Error while updating the fund.";
			 	System.err.println(e.getMessage());
		 }
		 
		 return output;
	}
	
	public String deleteFund(String fundId)
		 {
		 		String output = "";
		 		
		 		try
		 		{
					 Connection con = connect();
					 
					 if (con == null)
					 {return "Error while connecting to the database for deleting."; }
					 
					 // create a prepared statement
					 String query = "delete from funddetails where fundId=?";
					 
					 PreparedStatement preparedStmt = con.prepareStatement(query);
					 
					 // binding values
					 preparedStmt.setInt(1, Integer.parseInt(fundId));
					 
					 // execute the statement
					 preparedStmt.execute();
					 con.close();
					 
					 output = "Deleted successfully";
		 		}
		 		catch (Exception e)
		 		{
		 			output = "Error while deleting the fund.";
		 			System.err.println(e.getMessage());
		 		}
		 		
		 		return output;
		 } 

	
}
		
	
	

