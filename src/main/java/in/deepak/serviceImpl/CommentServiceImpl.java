//package in.deepak.serviceImpl;
//
//import java.time.LocalDateTime;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import in.deepak.entity.Comments;
//import in.deepak.entity.Posts;
//import in.deepak.entity.User;
//import in.deepak.entity.UserDto;
//import in.deepak.exception.CommentsException;
//import in.deepak.exception.PostException;
//import in.deepak.exception.UserException;
//import in.deepak.repository.CommentRepository;
//import in.deepak.repository.PostsRepository;
//import in.deepak.service.CommentService;
//import in.deepak.service.PostService;
//import in.deepak.service.UserService;
//
//@Service
//public class CommentServiceImpl implements CommentService {
//	
//	@Autowired
//	private CommentRepository commentRepo;
//	
//	@Autowired
//	private PostService postService;
//	
//	@Autowired
//	private UserService userService;
//	
//	@Autowired
//	private PostsRepository postRepo;
//
//	@Override
//	public Comments createComment(Comments comment, Integer postId, Integer userId) throws CommentsException, UserException, PostException {
//		
//		User user=userService.findById(userId);
//		
//		Posts post=postService.findPostById(postId);
//		
//		UserDto userDto=new UserDto();
//		
//		userDto.setId(user.getId());
//		userDto.setName(user.getName());
//        userDto.setUsername(user.getUsername());
//        userDto.setUserImage(user.getImage());
//        userDto.setEmail(user.getEmail());
//        
//        comment.setUser(userDto);
//        comment.setCreateAt(LocalDateTime.now());
//       
//        Comments createComment=commentRepo.save(comment);
//        
//        post.getList().add(createComment);
//        postRepo.save(post);
//        
//        
//		return createComment;
//	}
//
//	@Override
//	public Comments findCommentById(Integer commentId) throws CommentsException {
//		Optional<Comments> comment=commentRepo.findById(commentId);
//		
//		if(comment.isPresent()) {
//			return comment.get();
//		}
//		throw  new CommentsException("Comment is not Exist with Id : "+commentId);
//	}
//
//	@Override
//	public Comments likeComment(Integer commentId, Integer userId) throws CommentsException, UserException {
//		
//		User user=userService.findById(userId);
//		Comments comment=findCommentById(commentId);
//		
//UserDto userDto=new UserDto();
//		
//		userDto.setId(user.getId());
//		userDto.setName(user.getName());
//        userDto.setUsername(user.getUsername());
//        userDto.setUserImage(user.getImage());
//        userDto.setEmail(user.getEmail());
//        
//		comment.getLikedByUsers().add(userDto);
//		
//		return commentRepo.save(comment);
//		
//	}
//
//	@Override
//	public Comments unlikeComment(Integer commentId, Integer userId) throws CommentsException, UserException {
//
//		User user=userService.findById(userId);
//		Comments comment=findCommentById(commentId);
//		
//        UserDto userDto=new UserDto();
//		
//		userDto.setId(user.getId());
//		userDto.setName(user.getName());
//        userDto.setUsername(user.getUsername());
//        userDto.setUserImage(user.getImage());
//        userDto.setEmail(user.getEmail());
//        
//		comment.getLikedByUsers().remove(userDto);
//		
//		return commentRepo.save(comment);
//	}
//
//}
