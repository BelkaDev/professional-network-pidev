package com.esprit.resource;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.io.IOUtils;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import com.esprit.beans.FileUpload;
import com.esprit.beans.Post;
import com.esprit.services.PostService;
import com.esprit.utils.UserSession;



@Path("post")
public class PostWS {
	
	@EJB
	PostService PostService;

	private static final String UPLOAD_FOLDER = System.getProperty("user.dir")+"/files/";
	@Context
	private UriInfo context;
	private final String out = "success" ;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("add")
	public Response addPost(@QueryParam("content") String content
	) {
		int idUser = UserSession.getInstance().getId();
		PostService.addPost(idUser,content,null);
	    return Response.status(200).entity("shared post with no files").build();
		
	}
	
	
	@POST
	@Consumes("*/*")
	@Path("addWithFile")
	public Response addPost(@QueryParam("content") String content,
			MultipartFormDataInput input
	) {
			int idUser = UserSession.getInstance().getId();
		   if (input == null || input.getParts() == null || input.getParts().isEmpty()) {
		        throw new IllegalArgumentException("Multipart request is empty");
		   }
		
		CreateFolderIfNotExist(UPLOAD_FOLDER);
		String fileName = uploadFile(input.getFormDataMap());
	    FileUpload file = new FileUpload();
	    file.setPath(fileName);
		PostService.addPost(idUser,content,file);
	    return Response.status(200).entity("uploaded file to "+ fileName).build();
		
	}
	

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("share")
	public Response sharePost(@QueryParam("idPost") int idPost) {
		int idUser = UserSession.getInstance().getId();
		if (!PostService.sharePost(idPost,idUser))
		{
		return Response.status(Status.NOT_FOUND).entity("post doesn't exist").build();
		}
		return Response.status(Response.Status.CREATED).entity(out).build();
		
	}
	

	@DELETE
	@Path("delete")
	@Produces(MediaType.APPLICATION_JSON)
	public Response removePost(@QueryParam("id") int id) {

		if(PostService.deletePost(id))
		{
		return Response.status(Status.OK).entity("the Post has been deleted").build();
		}
		return Response.status(Status.NOT_FOUND).entity("Error").build();
		
	}
	

	@PUT
	@Path("update")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updatePost(@QueryParam("id") int idPost,
			@QueryParam("content") String content
	) {

		if(PostService.updatePost(idPost,content,null))
		{
		return Response.status(Status.OK).entity("post updated").build();
		}
		return Response.status(Status.NOT_FOUND).entity("Error").build();
	}
	
	@PUT
	@Path("updateWithFile")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updatePost(@QueryParam("idPost") int idPost,
			@QueryParam("content") String content,
			MultipartFormDataInput input
	) {
		
		CreateFolderIfNotExist(UPLOAD_FOLDER);
		String fileName = uploadFile(input.getFormDataMap());
	    FileUpload file = new FileUpload();
	    file.setPath(fileName);
		if(PostService.updatePost(idPost,content,file))
		{
		return Response.status(Status.OK).entity("post updated").build();
		}
		return Response.status(Status.NOT_FOUND).entity("Error").build();
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response displayQusetion(@PathParam("id")int id
			) {
		Post p = PostService.findPost(id);
		if (p!= null)
		{
		return Response.status(Status.OK).entity(p).build();
		}
		return Response.status(Status.NOT_FOUND).entity("Post not found").build();
	}
	

	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("all")
	public Response findAllPosts() {

		List<Post> Posts = PostService.findAllPosts();

		if (Posts == null)
			return Response.status(Status.NOT_FOUND).entity("No Posts Found").build();
		else
			return Response.ok(Posts).build();

	}
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("user")
	public Response findUserPosts(@QueryParam("id") int idUser) {
		List<Post> Posts = PostService.findAllUserPosts(idUser);

		if (Posts == null)
			return Response.status(Status.NOT_FOUND).entity("No Posts Found").build();
		else
			return Response.ok(Posts).build();

	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("shared")
	public Response sharingPost(@QueryParam("idPost") int idPost) {
		List<Post> Posts = PostService.sharingPost(idPost);
		if (Posts == null)
			return Response.status(Status.NOT_FOUND).entity("No Posts Found").build();
		else
			return Response.ok(Posts).build();

	}
	
	private void CreateFolderIfNotExist(String directory) {
	    File dir = new File(directory);
	    if (!dir.exists()) dir.mkdirs();
	}
	
	private String uploadFile( Map<String, List<InputPart>> uploadForm)
	{
    String fileName = "";
	List<InputPart> inputParts = uploadForm.get("file");
	for (InputPart inputPart : inputParts) {
	 try {
			MultivaluedMap<String, String> header = inputPart.getHeaders();
			fileName = getFileName(header);
			InputStream inputStream = inputPart.getBody(InputStream.class,null);
			byte [] bytes = IOUtils.toByteArray(inputStream);
			fileName = UPLOAD_FOLDER + fileName;
			writeFile(bytes,fileName);
			System.out.println("Done");

		  } catch (IOException e) {
			e.printStackTrace();
		  }
	}
			return fileName;
	}
	
	private String getFileName(MultivaluedMap<String, String> header) {

		String[] contentDisposition = header.getFirst("Content-Disposition").split(";");
		
		for (String filename : contentDisposition) {
			if ((filename.trim().startsWith("filename"))) {
				String[] name = filename.split("=");
				String finalFileName = name[1].trim().replaceAll("\"", "");
				return finalFileName;
			}
		}
		return "unknown";
	}

	//save to somewhere
	private void writeFile(byte[] content, String filename) throws IOException {
		File file = new File(filename);
		if (!file.exists()) {
			file.createNewFile();
		}
		FileOutputStream fop = new FileOutputStream(file);
		fop.write(content);
		fop.flush();
		fop.close();
	}
	
}
