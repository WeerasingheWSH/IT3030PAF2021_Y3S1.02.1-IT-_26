package com;

//IT19040318

//DE SILVA U.S.C
//FUND MANAGEMENT SERVICE

import model.Fund; 

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;
@Path("/Funds")

public class FundService {
	
	
	Fund fundObj = new Fund();
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	
	public String readFunds()
	 {
		return fundObj.readFunds(); 		
	 }
	
	
	//post request
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)	
	public String insertFund(@FormParam("fundName") String fundName,
	 @FormParam("fundPrice") String fundPrice,
	 @FormParam("fundDesc") String fundDesc)
	{	
		String output = fundObj.insertFund(fundName, fundPrice, fundDesc);
		return output;
	}
	

	//put  request
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateFund(String fundData)
	{
		//Convert the input string to a JSON object
		JsonObject fundObject = new JsonParser().parse(fundData).getAsJsonObject();
	
		//Read the values from the JSON object
		String fundId = fundObject.get("fundId").getAsString();
		String fundName = fundObject.get("fundName").getAsString();
		String fundPrice = fundObject.get("fundPrice").getAsString();
		String fundDesc = fundObject.get("fundDesc").getAsString();
		String output = fundObj.updateFund(fundId, fundName, fundPrice, fundDesc);
	
		return output;
	}
	
	//delete
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteFund(String fundData)
	{
		//Convert the input string to an XML document
		Document doc = Jsoup.parse(fundData, "", Parser.xmlParser());

		//Read the value from the element <itemID>
		String fundId = doc.select("fundId").text();
		String output = fundObj.deleteFund(fundId);
		
		return output;
	}
	
}
