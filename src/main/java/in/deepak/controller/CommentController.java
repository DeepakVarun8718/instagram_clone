//package in.deepak.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestHeader;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import in.deepak.entity.Comments;
//import in.deepak.entity.User;
//import in.deepak.exception.CommentsException;
//import in.deepak.exception.PostException;
//import in.deepak.exception.UserException;
//import in.deepak.service.CommentService;
//import in.deepak.service.PostService;
//import in.deepak.service.UserService;
//import in.deepak.serviceImpl.CommentServiceImpl;
//
//@RestController
//@RequestMapping("/api/comments")
//public class CommentController {
//
//	@Autowired
//	private CommentServiceImpl commentService;
//	
////	@Autowired
////	private PostService postService;
////	
//	@Autowired
//	private UserService  userService;
//	
//	@PostMapping("/create/{postId}")
//	public ResponseEntity<Comments> createCommentsHandler(@RequestBody Comments commet,@RequestHeader("Authorization") String token,@PathVariable Integer postId) throws UserException, CommentsException, PostException{
//		
//		User user=userService.findByProfile(token);
//		
//		Comments comment=commentService.createComment(commet, postId, user.getId());
//		
//		return new ResponseEntity<Comments>(comment,HttpStatus.ACCEPTED);
//		
//	}
//	
//	@PutMapping("/like/{commentId}")
//	public ResponseEntity<Comments> likeCommentHandler(@RequestHeader("Authorization") String token,@PathVariable Integer commentId) throws UserException, CommentsException{
//		
//		
//		User user=userService.findByProfile(token);
//		
//		Comments comment=commentService.likeComment(commentId, user.getId());
//		
//		return new ResponseEntity<Comments>(comment,HttpStatus.ACCEPTED);
//		
//		
//		
//		
//	}
//	
//}
