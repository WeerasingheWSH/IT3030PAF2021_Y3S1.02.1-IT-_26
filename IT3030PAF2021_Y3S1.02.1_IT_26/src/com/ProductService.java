package com;

import model.Product;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Products")

public class ProductService {
	
	Product itemObj = new Product();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readItems() {
		return itemObj.readItems();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertItem(@FormParam("ProductName") String ProductName,
			@FormParam("ProductPrice") String ProductPrice,
			@FormParam("ManufactureDate") String ManufactureDate, 
			@FormParam("ExpireDate") String ExpireDate, 
	        @FormParam("ProductRatings") String ProductRatings, 
            @FormParam("NoOfUnits") String NoOfUnits) 
	{
		String output = itemObj.insertItem(ProductName, ProductPrice, ManufactureDate, ExpireDate,ProductRatings,NoOfUnits);
		return output;
	}

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateItem(String itemData) {
		// Convert the input string to a JSON object
		JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject();
		// Read the values from the JSON object
		String productID = itemObject.get("productID").getAsString();
		String ProductName = itemObject.get("ProductName").getAsString();
		String ProductPrice = itemObject.get("ProductPrice").getAsString();
		String ManufactureDate = itemObject.get("ManufactureDate").getAsString();
		String ExpireDate = itemObject.get("ExpireDate").getAsString();
		String ProductRatings = itemObject.get("ProductRatings").getAsString();
		String NoOfUnits = itemObject.get("NoOfUnits").getAsString();
		String output = itemObj.updateItem(productID,ProductName, ProductPrice, ManufactureDate, ExpireDate,ProductRatings,NoOfUnits);
		return output;
	}

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteItem(String itemData) {
		// Convert the input string to an XML document
		Document doc = Jsoup.parse(itemData, "", Parser.xmlParser());

		// Read the value from the element <itemID>
		String productID = doc.select("productID").text();
		String output = itemObj.deleteItem(productID);
		return output;
	}
}
