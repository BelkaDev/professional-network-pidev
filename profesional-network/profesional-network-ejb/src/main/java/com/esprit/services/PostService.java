package com.esprit.services;


import java.io.File;
import java.net.URLConnection;
import java.nio.file.Files;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.activation.MimetypesFileTypeMap;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.esprit.Iservice.IPostServiceLocal;
import com.esprit.Iservice.IPostServiceRemote;
import com.esprit.beans.FileUpload;
import com.esprit.beans.Post;
import com.esprit.beans.User;
import com.esprit.enums.FILE_TYPE;
import com.esprit.enums.NOTIFICATION_TYPE;
import com.esprit.enums.POST_TYPE;
import com.esprit.utils.MimeTypeToEnums;
import com.esprit.utils.UserSession;

@Stateless
@LocalBean
public class PostService implements IPostServiceLocal,IPostServiceRemote {
	

	@PersistenceContext(unitName = "pidevtwin-ejb")
	EntityManager em;
	
	@EJB
	FollowingService followingservice = new FollowingService();
	@EJB
	NotificationService notificationservice = new NotificationService();
	@EJB
	FileService fileService;
	@EJB
	MimeTypeToEnums mimetypetoenums ;

	@Override
	public void addPost(int idUser,String content, FileUpload file) {


		User poster = em.find(User.class,idUser);
		Post post = new Post();
		Timestamp date = new Timestamp(System.currentTimeMillis());


		String mimeType="text";
		if(file != null)
		{
			
		String fileName = file.getPath();
		MimetypesFileTypeMap mimeTypesMap = new MimetypesFileTypeMap();
		File newfile = new File(fileName);
		mimeType = mimeTypesMap.getContentType(newfile).split("/")[0];
		//String mimeType = URLConnection.guessContentTypeFromName(file.getPath()).split("/")[0];
		file.setType(mimetypetoenums.toFileType(mimeType));
		}
		
		post.setDate(date);
		post.setContent(content);
		post.setUser(poster);
		post.setAuthor(poster.getId());
		post.setFile(file);
		post.setType(mimetypetoenums.toPostType(mimeType));	
		if ((post.getUser() == null )){
			System.out.println("The user doesn't exist.");
			return ;
			}
		else {
				em.persist(post);
				em.flush();
			 }
		int idPost = post.getId();
		
		//Automatically add poster to following the post
		followingservice.followPost(idUser, idPost);
		
		// notifying followers about the new Post

		List<User> followers = followingservice.UserFollowers(idUser);
		String notif_message = poster.getFirstName()+" "+poster.getLastName()+
				" shared a new Post.";

		for (User usr : followers) {
		NOTIFICATION_TYPE type = NOTIFICATION_TYPE.Follow;
		notificationservice.CreateNotification(usr.getId(),notif_message,type ,idPost);
		}
		
		}

	@Override
	public boolean updatePost(int id,String content, FileUpload file) {


		Timestamp date = new Timestamp(System.currentTimeMillis());
		Post post = em.find(Post.class,id);
		if (post == null || post.getUser().getId() != UserSession.getInstance().getId() ) {
			return false;
		}

		String mimeType="text";
		if(file != null)
		{
			
		String fileName = file.getPath();
		MimetypesFileTypeMap mimeTypesMap = new MimetypesFileTypeMap();
		File newfile = new File(fileName);
		mimeType = mimeTypesMap.getContentType(newfile).split("/")[0];
		//String mimeType = URLConnection.guessContentTypeFromName(file.getPath()).split("/")[0];
		file.setType(mimetypetoenums.toFileType(mimeType));
		}
		
		post.setContent(content +" [edited on "+" "+new SimpleDateFormat("dd/MM/YY - HH:mm").format(date)+"]");
		post.setType(mimetypetoenums.toPostType(mimeType));	
		em.merge(post);
		em.flush();
		
		// other posts that share this
		List<Post> posts = sharingPost(id);
		for (Post p : posts) {
			p.setType(post.getType());
			p.setContent(content);
			em.merge(p);
		}
		return true;
	}

	@Override
	public boolean deletePost(int id) {
		Post post = em.find(Post.class, id);
		if (post == null || post.getUser().getId() != UserSession.getInstance().getId())
		{
			return false;
		}
		em.remove(post);
		
		// other posts that share this
		List<Post> posts = sharingPost(id);
		for (Post p : posts) {
			p.setType(POST_TYPE.REMOVED);
			p.setContent("The original post wasn't retrieved.");
			em.merge(p);
		}
		return true;
	}



	@Override
	public boolean sharePost(int idPost,int idUser) {
		Post sharedPost = em.find(Post.class, idPost);
		User user = em.find(User.class, idUser);
		if (sharedPost == null) {
			return false;
		}
		Post post = new Post();
		post.setUser(user);
		post.setAuthor(sharedPost.getAuthor());
		Timestamp date = new Timestamp(System.currentTimeMillis());
		post.setContent(sharedPost.getContent()+" [shared on "+new SimpleDateFormat("dd/MM/YY - HH:mm").format(date)+"]");
		post.setType(sharedPost.getType());
		post.setDate(date);
		em.persist(post);
		
		// notify author of post
		String notif_message =user.getFirstName()+" "+user.getLastName()+" has shared your post.";
		NOTIFICATION_TYPE type = NOTIFICATION_TYPE.Share;
		notificationservice.CreateNotification(sharedPost.getAuthor(),notif_message,type ,idPost);
		
		//Automatically add poster to following list
		followingservice.followPost(idUser, idPost);
		
		// notifying followers about the new Post

		List<User> followers = followingservice.UserFollowers(idUser);
		notif_message = user.getFirstName()+" "+user.getLastName()+
				" shared a new Post.";

		for (User usr : followers) {
		type = NOTIFICATION_TYPE.Follow;
		notificationservice.CreateNotification(usr.getId(),notif_message,type ,idPost);
		}
		
		
		return true;
	}

	
	@Override
	public Post findPost(int id) {
		return em.find(Post.class, id);
	}


	@Override
	public List<Post> findAllPosts() {
		return em
				.createQuery("select post from Post post", Post.class)
				.getResultList();
	}


	@Override
	public List<Post> findAllUserPosts(int id) {
		List<Post> posts = em.createQuery("select post from Post post where post.user.id=:Id",Post.class)
				.setParameter("Id", id).getResultList();
		return posts;
	}



	@Override
	public List<Post> findFriendsPosts(int Userid) {
		return null;
	}

	
	@Override
	public List<Post> sharingPost(int id) {
		List<Post> posts = em.createQuery("select post from Post post where post.author=:Id AND post.user.id<>:Id",Post.class)
				.setParameter("Id", id).getResultList();
		return posts;
	}
	
	@Override
	public boolean checkPostType(POST_TYPE postType) {
	       POST_TYPE[] types = POST_TYPE.values();
	       for (POST_TYPE type : types)
	           if (type.equals(postType))
	               return true;
	       return false;
	}
}
	

