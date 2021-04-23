package com;


import model.User;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Users")
public class UserService
{
		User UserObj = new User();
		@GET
		@Path("/")
		@Produces(MediaType.TEXT_HTML)
		public String readUsers()
		{
			return UserObj.readUser();
		}
		
		
		@POST
		@Path("/")
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		@Produces(MediaType.TEXT_PLAIN)
		public String insertItem(@FormParam("UserName") String username,
							@FormParam("Email") String Email,
							@FormParam("Age") String Age,
							@FormParam("PhoneNumber") String PhoneNumber,
							@FormParam("Password") String Password)
		{
				String output = UserObj.insertUser(username, Email, Age, PhoneNumber, Password);
				return output;
		}

		
		@PUT
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String updateUser(String userData)
		{
			//Convert the input string to a JSON object
			JsonObject UserObject = new JsonParser().parse(userData).getAsJsonObject();
			//Read the values from the JSON object
			String UserID = UserObject.get("UserID").getAsString();
			String UserName = UserObject.get("UserName").getAsString();
			String Email = UserObject.get("Email").getAsString();
			String Age = UserObject.get("Age").getAsString();
			String PhoneNumber = UserObject.get("PhoneNumber").getAsString();
			String Password = UserObject.get("Password").getAsString();
			String output = UserObj.updateUser(UserID, UserName, Email, Age, PhoneNumber,Password );
			return output;
		}
		
		
		@DELETE
		@Path("/")
		@Consumes(MediaType.APPLICATION_XML)
		@Produces(MediaType.TEXT_PLAIN)
		public String deleteUser(String userData)
		{
		//Convert the input string to an XML document
		 Document doc = Jsoup.parse(userData, "", Parser.xmlParser());

		//Read the value from the element <itemID>
		 String userID = doc.select("userID").text();
		 String output = UserObj.deleteUser(userID);
		return output;
		}
		
}


