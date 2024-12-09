//package in.deepak.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestHeader;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import in.deepak.dto.MessageReponse;
//import in.deepak.entity.Posts;
//import in.deepak.entity.User;
//import in.deepak.exception.PostException;
//import in.deepak.exception.UserException;
//import in.deepak.service.PostService;
//import in.deepak.service.UserService;
//import in.deepak.serviceImpl.PostServiceImpl;
//
//@RestController
//@RequestMapping("/api/posts")
//public class PostController {
//
//	
//	@Autowired
//	private PostServiceImpl postService;
//	
//	@Autowired
//	private UserService userService;
//	
//	@PostMapping("/create")
//	public ResponseEntity<Posts> createPostHandler(@RequestBody Posts post , @RequestHeader("Authorization") 
//	String token) throws PostException,UserException{
//		try {
//		User user=userService.findByProfile(token);
//		Posts createPost=postService.createPost(post, user.getId());
//		return new ResponseEntity<Posts>(createPost,HttpStatus.OK);
//		}
//		catch(Exception e) {
//			throw e;
//		}
//		
//	}
//	
//	@GetMapping("/all/{ids}")
//	public ResponseEntity<List<Posts>> findPostsByUserIdHandler(@PathVariable("ids") List<Integer> userId) throws UserException, PostException{
//	
//		List<Posts> posts=postService.findAllPostByUserIds(userId);
//		
//		return new ResponseEntity<List<Posts>>(posts,HttpStatus.OK);
//	}
//	
//	@GetMapping("/{postId}")
//	public ResponseEntity<Posts> findPostByIdHandler(@PathVariable Integer postId) throws PostException{
//		
//		Posts post=postService.findPostById(postId);
//		
//		return new ResponseEntity<Posts>(post,HttpStatus.OK);
//	}
//	
//	@PutMapping("/like/{postId")
//	public ResponseEntity<Posts> likePostHandler(@PathVariable Integer postId,@RequestHeader("Authorization") String token) throws UserException, PostException{
//		
//		
//		User user=userService.findByProfile(token);
//		
//		Posts likedPost=postService.likePost(postId, user.getId());
//		
//		return new ResponseEntity<Posts>(likedPost,HttpStatus.OK);
//	}
//	
//	@PutMapping("/unlike/{postId")
//	public ResponseEntity<Posts> unLikePostHandler(@PathVariable Integer postId,@RequestHeader("Authorization") String token) throws UserException, PostException{
//		
//		
//		User user=userService.findByProfile(token);
//		
//		Posts unlikedPost=postService.unlikePost(postId, user.getId());
//		
//		return new ResponseEntity<Posts>(unlikedPost,HttpStatus.OK);
//	}
//	
//	
//	@DeleteMapping("/delete/{postId")
//	public ResponseEntity<MessageReponse> deletePostHandler(@PathVariable Integer postId,@RequestHeader("Authorization") String token) throws UserException, PostException{
//		
//		
//		User user=userService.findByProfile(token);
//		
//
//		String msg=postService.deletePost(postId, user.getId());
//		MessageReponse msgreponse=new MessageReponse(msg);
//		
//		return new ResponseEntity<MessageReponse>(msgreponse,HttpStatus.OK);
//	}
//	
//	@PutMapping("/save_post/{postId")
//	public ResponseEntity<MessageReponse> savedPostHandler(@PathVariable Integer postId,@RequestHeader("Authorization") String token) throws UserException, PostException{
//		
//		
//		User user=userService.findByProfile(token);
//		
//
//		String msg=postService.savedPost(postId, user.getId());
//		MessageReponse msgreponse=new MessageReponse(msg);
//		
//		return new ResponseEntity<MessageReponse>(msgreponse,HttpStatus.OK);
//	}
//	
//
//	@PutMapping("/unsave_post/{postId")
//	public ResponseEntity<MessageReponse> unsavedPostHandler(@PathVariable Integer postId,@RequestHeader("Authorization") String token) throws UserException, PostException{
//		
//		
//		User user=userService.findByProfile(token);
//		
//
//		String msg=postService.unSavedPost(postId, user.getId());
//		MessageReponse msgreponse=new MessageReponse(msg);
//		
//		return new ResponseEntity<MessageReponse>(msgreponse,HttpStatus.OK);
//	}
//	
//	
//	//4;29
//	
//}
