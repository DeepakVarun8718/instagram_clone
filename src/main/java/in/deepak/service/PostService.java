package in.deepak.service;
import java.util.List;

import org.springframework.stereotype.Service;

import in.deepak.entity.Post;
import in.deepak.exception.PostException;
import in.deepak.exception.UserException;

@Service
public interface PostService {

	
	public Post createPost(Post post,Integer userId)throws UserException;
	
	public String deletePost(Integer userId , Integer postId)throws UserException,PostException;
	
	public List<Post> findPostByUserId(Integer userId) throws UserException;
	
	public Post findPostById(Integer postId)throws PostException;
	
	public List<Post> findAllPostByUserIds(List<Integer> userIds) throws PostException;
	
	public String savedPost(Integer userId,Integer postId)throws UserException,PostException;
	
	public String unSavedPost(Integer userId,Integer postId)throws UserException,PostException;
	
	public  Post likePost(Integer userId,Integer postId)throws UserException,PostException;
	
	public Post unlikePost(Integer userId,Integer postId)throws UserException,PostException;
	
	
	
	
	
	
}
