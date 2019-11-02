package com.esprit.Iservice;
import java.sql.Timestamp;
import java.util.List;
import javax.ejb.Remote;
import com.esprit.beans.Comment;
import com.esprit.beans.Reaction;
import com.esprit.enums.FILE_TYPE;


@Remote
public interface IFileServiceRemote {
	
 boolean addFile(String path,FILE_TYPE type);
// boolean removeFile(int id);
}
