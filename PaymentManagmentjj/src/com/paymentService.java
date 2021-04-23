package com;

	import model.payment;
	//For REST Service
	import javax.ws.rs.*;
	import javax.ws.rs.core.MediaType;
	//For JSON
	import com.google.gson.*;
	//For XML
	import org.jsoup.*;
	import org.jsoup.parser.*;
	import org.jsoup.nodes.Document;
	@Path("/Payments")



	public class paymentService {


	payment paymentobj = new payment();


		@GET
		@Path("/")
		@Produces(MediaType.TEXT_HTML)
		public String readPayments()
		{
			return paymentobj.readPayment();
		}

		@POST
		@Path("/")
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		@Produces(MediaType.TEXT_PLAIN)
		public String insertPayment(@FormParam("productID") String productID,

		 @FormParam("name") String name,
		 @FormParam("address") String address,
		 @FormParam("phoneNO") String phoneNO,
		 @FormParam("email") String email,
		 @FormParam("paymentMethod") String paymentMethod,
		 @FormParam("orderDate") String orderDate)
		{
		 String output = paymentobj.insertPayment(productID, name, address, phoneNO,email,paymentMethod,orderDate );
		return output;
		}

		@PUT
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String updatePayment(String paymentData)
		{
		//Convert the input string to a JSON object
		 JsonObject paymentObject = new JsonParser().parse(paymentData).getAsJsonObject();
		//Read the values from the JSON object
		 String orderNo = paymentObject.get("orderNo").getAsString();
		 String productID = paymentObject.get("productID").getAsString();
		 String name = paymentObject.get("name").getAsString();
		 String address = paymentObject.get("address").getAsString();
		 String phoneNO = paymentObject.get("phoneNO").getAsString();
		 String email = paymentObject.get("email").getAsString();
		 String paymentMethod = paymentObject.get("paymentMethod").getAsString();
		 String orderDate = paymentObject.get("orderDate").getAsString();
		 String output = paymentobj.updatePayment(orderNo, productID, name, address, phoneNO,email,paymentMethod,orderDate);
		return output;
		}

		@DELETE
		@Path("/")
		@Consumes(MediaType.APPLICATION_XML)
		@Produces(MediaType.TEXT_PLAIN)
		public String deletePayment(String paymentData)
		{
		//Convert the input string to an XML document
		 Document doc = Jsoup.parse(paymentData, "", Parser.xmlParser());

		//Read the value from the element <orderNo>
		 String orderNo = doc.select("orderNo").text();
		 String output = paymentobj.deletePayment(orderNo);
		return output;
		}




}
