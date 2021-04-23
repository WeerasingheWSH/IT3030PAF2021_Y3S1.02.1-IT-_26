package model;
import java.sql.*;
public class User
{ 		//A common method to connect to the DB
	private Connection connect()
	{
		Connection con = null;
		
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			//Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test", "root", "");
		}
		catch (Exception e)
		{e.printStackTrace();}
		return con;
	}
	
	public String insertUser(String username, String Email, String Age, String PhoneNumber, String Password)
	{
		String output = "";
		
		try
		{
			Connection con = connect();
			
			if (con == null)
			{return "Error while connecting to the database for inserting."; }
			// create a prepared statement
			 String query = " INSERT INTO test.user(`UserID`,`UserName`,`Email`,`Age`,`PhoneNumber`,`Password`)"
					 + " values (?, ?, ?, ?, ?,?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, username);
			preparedStmt.setString(3, Email);
			preparedStmt.setString(4, Age);
			preparedStmt.setString(5, PhoneNumber );
			preparedStmt.setString(6, Password);
			
			
			// execute the statement
			 preparedStmt.execute(); 
			 con.close(); 
			 output = "Inserted successfully"; 
			 } 
		catch (Exception e) 
		{ 
			 output = "Error while inserting the User."; 
			 System.err.println(e.getMessage()); 
		} 
		return output; 
	} 
			
	public String readUser() 
	{ 
		String output = ""; 
		
		try
		{ 
			 Connection con = connect(); 
			 
			 if (con == null) 
			 {
				 return "Error while connecting to the database for reading."; 
			 } 
			 
			 // Prepare the html table to be displayed
			 output = "<table border='1'><tr><th>username</th><th>Email</th>" +
			 "<th>Age</th>" + 
			 "<th>PhoneNumber</th>" + "<th>Password</th>" +
			 "<th>Update</th><th>Remove</th></tr>"; 
			 
			 String query = "select * from User"; 
			 Statement stmt = con.createStatement(); 
			 ResultSet rs = stmt.executeQuery(query); 
			 
			 // iterate through the rows in the result set
			 while (rs.next()) 
			 { 
				 String userID = Integer.toString(rs.getInt("userID")); 
				 String username = rs.getString("username"); 
				 String Email = rs.getString("Email"); 
				 String Age = rs.getString("Age"); 
				 String PhoneNumber = rs.getString("PhoneNumber"); 
				 String Password = rs.getString("Password");
			 
				 // Add into the html table
				 output += "<tr><td>" + username + "</td>"; 
				 output += "<td>" + Email + "</td>"; 
				 output += "<td>" + Age + "</td>"; 
				 output += "<td>" + PhoneNumber + "</td>"; 
				 output += "<td>" + Password + "</td>"; 
				 
				 // buttons
				 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
						 + "<td><form method='post' action='Users.jsp'>" + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
						 + "<input name='userID' type='hidden' value='" + userID 
			 + "'>" + "</form></td></tr>"; 
			 } 
			 con.close(); 
			 // Complete the html table
			 output += "</table>"; 
		} 
		catch (Exception e) 
		{ 
			 output = "Error while reading the user."; 
			 System.err.println(e.getMessage()); 
		} 
		return output; 
	} 
			
	public String updateUser(String ID, String UserName, String Email, String Age, String PhoneNumber, String Password)
	{ 
		String output = ""; 
		
		try
		{ 
			Connection con = connect(); 
		
			if (con == null) 
			{
				return "Error while connecting to the database for updating."; 
			} 
			
			 // create a prepared statement
			 String query = "UPDATE User SET UserName=?,Email=?,Age=?,PhoneNumber=?,Password=? WHERE UserID=?"; 
			 
			 PreparedStatement preparedStmt = con.prepareStatement(query); 
			 
			 // binding values
			 preparedStmt.setString(1, UserName); 
			 preparedStmt.setString(2, Email); 
			 preparedStmt.setString(3, Age);
			 preparedStmt.setString(4, PhoneNumber); 
			 preparedStmt.setString(5, Password);
			 preparedStmt.setInt(6, Integer.parseInt(ID)); 
			 
			 // execute the statement
			 preparedStmt.execute(); 
			 con.close(); 
			 
			 output = "Updated successfully"; 
		} 
		catch (Exception e) 
		{ 
			 output = "Error while updating the user."; 
			 System.err.println(e.getMessage()); 
		}
		
		return output; 
	} 
			
	public String deleteUser(String id) 
	{ 
		String output = ""; 
		
		try
		{ 
			 Connection con = connect(); 
			 
			 if (con == null) 
			 {
				 return "Error while connecting to the database for deleting."; 
			 } 
			 
			 // create a prepared statement
			 String query = "delete from User where userID=?"; 
			 
			 PreparedStatement preparedStmt = con.prepareStatement(query); 
			 
			 // binding values
			 preparedStmt.setInt(1, Integer.parseInt(id)); 
			 
			 // execute the statement
			 preparedStmt.execute(); 
			 con.close(); 
			 output = "Deleted successfully"; 
		} 
		catch (Exception e) 
		{ 
			 output = "Error while deleting the user."; 
			 System.err.println(e.getMessage()); 
		} 
		
		return output; 
	} 
} 