package com.esprit.Iservice;
import java.sql.Timestamp;
import java.util.List;
import javax.ejb.Local;
import com.esprit.beans.Comment;
import com.esprit.beans.FileUpload;
import com.esprit.beans.Reaction;
import com.esprit.enums.FILE_TYPE;


@Local
public interface IFileServiceLocal {
	
	
 boolean addFile(FileUpload file);
 //boolean removeFile(int id);
}
