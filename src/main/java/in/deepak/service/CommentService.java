package in.deepak.service;

import org.springframework.stereotype.Service;

import in.deepak.entity.Comments;
import in.deepak.exception.CommentsException;
import in.deepak.exception.PostException;
import in.deepak.exception.UserException;

@Service
public interface CommentService {

	public Comments createComment(Comments comment,Integer postId,Integer userId)throws CommentsException,UserException,PostException;
	
	public Comments findCommentById(Integer commentId)throws CommentsException;
	
	public Comments likeComment(Integer commentId,Integer userId )throws CommentsException,UserException;
	
	public Comments unlikeComment(Integer commentId,Integer userId )throws CommentsException,UserException;
	
}
