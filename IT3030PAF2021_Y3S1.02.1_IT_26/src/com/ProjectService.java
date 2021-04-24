package com;

import model.Project;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Projects")
public class ProjectService {
	
	Project projectObj = new Project();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readItems() {
		return projectObj.readItems();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertProject(@FormParam("projectName") String projectName,
			@FormParam("projectEmail") String projectEmail,
			@FormParam("projectTitle") String projectTitle, 
			@FormParam("projectDesc") String projectDesc,
			@FormParam("projectLink") String projectLink,
			@FormParam("projectVideo") String projectVideo)
	{
		String output = projectObj.insertProject(projectName, projectEmail, projectTitle, projectDesc, projectLink, projectVideo);
		return output;
	}

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateProject(String projectData) {
		// Convert the input string to a JSON object
		JsonObject projectObject = new JsonParser().parse(projectData).getAsJsonObject();
		// Read the values from the JSON object
		String projectID = projectObject.get("projectID").getAsString();
		String projectName = projectObject.get("projectName").getAsString();
		String projectEmail = projectObject.get("projectEmail").getAsString();
		String projectTitle = projectObject.get("projectTitle").getAsString();
		String projectDesc = projectObject.get("projectDesc").getAsString();
		String projectLink = projectObject.get("projectLink").getAsString();
		String projectVideo = projectObject.get("projectVideo").getAsString();
		String output = projectObj.updateProject(projectID, projectName, projectEmail, projectTitle, projectDesc, projectLink, projectVideo);
		return output;
	}

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteProject(String projectData) {
		// Convert the input string to an XML document
		Document doc = Jsoup.parse(projectData, "", Parser.xmlParser());

		// Read the value from the element <itemID>
		String projectID = doc.select("projectID").text();
		String output = projectObj.deleteProject(projectID);
		return output;
	}
}
