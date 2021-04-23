package model;



	import java.sql.*;

	public class payment {
		//A common method to connect to the DB
		private Connection connect() {
			Connection con = null;
			try 
			{
			Class.forName("com.mysql.cj.jdbc.Driver");

				//Provide the correct details: DBServer/DBName, username, password
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/payment", "root", "1234");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return con;
		}

		public String insertPayment(String productID, String name, String address, String phoneNO, String email, String paymentMethod, String orderDate) {

			String output = "";
			try {
				Connection con = connect();
				if (con == null) {
					return "Error while connecting to the database for inserting.";
					// create a prepared statement
				}

				String query = " insert into payments(`orderNo`, `productID`,`name`,`address`, `phoneNO`, `email`, `paymentMethod`, `orderDate`)"
					+ " values (?, ?, ?, ?, ?, ?,?,?)";


				PreparedStatement preparedStmt = con.prepareStatement(query);
				// binding values
				preparedStmt.setInt(1, 0);
				preparedStmt.setString(2, productID);
				preparedStmt.setString(3, name);
				preparedStmt.setString(4, address);
				preparedStmt.setString(5, phoneNO);
				preparedStmt.setString(6, email);
				preparedStmt.setString(7, paymentMethod);
				preparedStmt.setString(8, orderDate);
				// execute the statement

				preparedStmt.execute();
				con.close();

				output = "Inserted successfully";		
			    }
			    catch (Exception e) {
				output = "Error while inserting the item.";
				System.err.println(e.getMessage());
			}
			return output;
			}

		public String readPayment()
		{
			String output = "";
			try
			{
				Connection con = connect();
				if (con == null){
					return "Error while connecting to the database for reading."; 
					 // Prepare the html table to be displayed
			}

			output = "<table border='1'></tr><th>productID</th><th>name</th><th>address</th><th>phoneNO</th><th>email</th><th>paymentMethod</th><th>orderDate</th></th>"+
					 "<th>Update</th><th>Remove</th></tr>";
			String query = "select * from payments";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set

			while (rs.next()) {

				String orderNo = rs.getString("orderNo");
				String productID = rs.getString("productID");
				String name = rs.getString("name");
				String address = rs.getString("address");
				String phoneNO =  rs.getString("phoneNO");
				String email = rs.getString("email");
				String paymentMethod = rs.getString("paymentMethod");
				String orderDate = rs.getString("orderDate");
				 // Add into the html table

				output += "<td>" + productID + "</td>";
				output += "<td>" + name + "</td>";
				output += "<td>" + address + "</td>";
				output += "<td>" + phoneNO  + "</td>";
				output += "<td>" + email + "</td>";
				output += "<td>" + paymentMethod + "</td>";
				output += "<td>" + orderDate + "</td>";
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"+ "<td><form method='post' action='items.jsp'>"+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"+ "<input name='orderNo' type='hidden' value='" + orderNo+ "'>" + "</form></td></tr>"; 
			}
				con.close();
				// Complete the html table
				output += "</table>";
			} 
			catch (Exception e){
				output = "Error while reading the payments.";
				System.err.println(e.getMessage());
			}
			return output;
		}



		public String updatePayment(String orderNo, String productID, String name, String address, String phoneNO, String email, String paymentMethod, String orderDate ) {

			String output = "";

			try {

				Connection con = connect();
				if (con == null) {
					return "Error while connecting to the database for updating.";
					// create a prepared statement
				}

				String query = "UPDATE payments SET productID= ?, name= ?, address= ?, phoneNO= ?, email= ?, paymentMethod= ?, orderDate= ? WHERE orderNo= ?";

				PreparedStatement preparedStmt = con.prepareStatement(query);
				// binding values
				preparedStmt.setString(1, productID);
				preparedStmt.setString(2, name);
				preparedStmt.setString(3, address);
				preparedStmt.setString(4, phoneNO);
				preparedStmt.setString(5, email);
				preparedStmt.setString(6, paymentMethod);
				preparedStmt.setString(7, orderDate);
				preparedStmt.setInt(8, Integer.parseInt(orderNo));


				preparedStmt.execute();

				con.close();
				output = "Updated successfully";

			} 
			catch (Exception e) {
				output = "Error while updating the payment.";
				System.err.println(e.getMessage());
			}
			return output;
		}

		public String deletePayment(String orderNo)
		{
			String output = "";
			try
			{
				Connection con = connect();
				if (con == null)
			{
			return "Error while connecting to the database for deleting."; }
				// create a prepared statement
			String query = "delete from payments where orderNo=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(orderNo));

			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";

			}
			catch (Exception e)
			{
				output = "Error while deleting the payment."; 
				System.err.println(e.getMessage()); 
			}
			return output;
		} 

	
	
}
