package com.esprit.utils;


import com.esprit.enums.POST_TYPE;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.esprit.enums.FILE_TYPE;

@Stateless
@LocalBean
public class MimeTypeToEnums {

	public POST_TYPE toPostType(String mimetype)
	{
		 switch (mimetype) {
		case "image":
			return POST_TYPE.Image;
		case "application":
			return POST_TYPE.Video;
		case "video":
			return POST_TYPE.Video;
		case "audio":
			return POST_TYPE.Audio;
		case "text":
			return POST_TYPE.Text;
		default:
			return POST_TYPE.Text;		 
		 }
	}
	
	public FILE_TYPE toFileType(String mimetype)
	{
		 switch (mimetype) {
		case "image":
			return FILE_TYPE.Image;
		case "application":
			return FILE_TYPE.Video;
		case "video":
			return FILE_TYPE.Video;
		case "audio":
			return FILE_TYPE.Audio;
		case "text":
			return FILE_TYPE.None;
		default:
			return FILE_TYPE.Other;
		 }		
	}
}
