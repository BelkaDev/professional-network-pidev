package com.esprit.resource;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import java.util.*;

import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import com.esprit.beans.FileUpload;
import com.esprit.services.FileService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.commons.io.IOUtils;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;






@Path("/files")
@RequestScoped
public class FileUploadWS {
	@EJB
	FileService fileService;
	private final String status = "{\"status\":\"ok\"}" ;
	private static final String UPLOAD_FOLDER = System.getProperty("user.dir")+"/files/";
	@Context
	private UriInfo context;
	
	@POST
	@Path("upload") 
	@Consumes("*/*")
	public Response getFile(String filename) throws IOException {
		
	    FileUpload file = new FileUpload();
	    file.setPath(filename);
//	    fileService.addFile("C:/Users/Zied/Images/"+filename, null);
	    return Response.status(200).entity( "{\"file\":\""+filename+"\"}").build();

	}

	private void CreateFolderIfNotExist(String directory) {
	    File dir = new File(directory);
	    if (!dir.exists()) dir.mkdirs();
	}
	
	private String uploadFile( String filename ) throws IOException
	{
		CreateFolderIfNotExist(UPLOAD_FOLDER);
	    String path = UPLOAD_FOLDER + filename;
	    return path;
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
