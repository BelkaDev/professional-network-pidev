package com.esprit.resource;

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


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.io.IOUtils;






@Path("/files")
@RequestScoped
public class FileUploadWS {
	private static final String UPLOAD_FOLDER = System.getProperty("user.dir")+"/files/";
	@Context
	private UriInfo context;
	
	@POST
	@Path("upload") 
	@Consumes("*/*")
	public Response uploadFile(MultipartFormDataInput input) throws IOException {
		
		CreateFolderIfNotExist(UPLOAD_FOLDER);
		String fileName = uploadFile(input.getFormDataMap());
	    return Response.status(200).entity("uploaded file to "+ fileName).build();

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
