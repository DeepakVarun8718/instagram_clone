//package in.deepak.serviceImpl;
//
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import in.deepak.entity.Posts;
//import in.deepak.entity.User;
//import in.deepak.entity.UserDto;
//import in.deepak.exception.PostException;
//import in.deepak.exception.UserException;
//import in.deepak.repository.PostsRepository;
//import in.deepak.repository.UserRepository;
//import in.deepak.service.PostService;
//import in.deepak.service.UserService;
//
//@Service
//public class PostServiceImpl implements PostService {
//
//	
//	@Autowired
//	private PostsRepository postsRepo;
//	
//	@Autowired
//	private UserService userService;
//	
//	@Autowired
//	private UserRepository userRepo;
//	
//	
//	
//	@Override
//	public Posts createPost(Posts post,Integer userId) throws UserException {
//		
//		User user=userService.findById(userId);
//		
//		UserDto userdto=new UserDto();
//		userdto.setEmail(userdto.getEmail());
//		userdto.setName(user.getName());
//		userdto.setId(user.getId());
//		userdto.setUsername(user.getUsername());
//		userdto.setUserImage(user.getImage());
//		
//		
//		
//		post.setUser(userdto);
//		
//		Posts createPost=postsRepo.save(post);
//		
//		
//		return createPost;
//	}
//
//	@Override
//	public String deletePost(Integer userId, Integer postId) throws UserException, PostException {
//		Posts post=findPostById(postId);
//		
//          User user=userService.findById(userId);
//          
//          if(post.getUser().getId().equals(user.getId())) {
//        	  postsRepo.deleteById(post.getId());
//          }
//          throw new PostException("Your post deleted SuccessFully");
//	}
//
//	@Override
//	public List<Posts> findPostByUserId(Integer userId) throws UserException {
//		
//		List<Posts> posts=postsRepo.findByUserId(userId);
//		
//		if(posts.size()==0) {
//			throw new UserException("this user does not have any posts");
//		}
//		return posts;
//	}
//
//	@Override
//	public Posts findPostById(Integer postId) throws PostException{
//		
//		Optional<Posts> post=postsRepo.findById(postId);
//		
//		if(post.isPresent()) {
//			
//			return post.get();
//		}
//	        throw new PostException("No posts Avaliable");
//	}
//
//	@Override
//	public List<Posts> findAllPostByUserIds(List<Integer> userIds) throws PostException {
//		
//        List<Posts> posts=postsRepo.findAllPostsByUserId(userIds);
//		
//		if(posts.size()==0) {
//			throw new PostException("No posts Avaliable");
//		}
//		return posts;
//	}
//	
//
//	@Override
//	public String savedPost(Integer userId, Integer postId) throws UserException, PostException {
//		
//		Posts post=findPostById(postId);
//	
//          User user=userService.findById(userId);
//		
//		if(!user.getSavedPost().contains(post)) {
//			user.getSavedPost().add(post);
//			userRepo.save(user);
//			
//		}
// 		return "your post is successfully saved";
//	}
//
//	@Override
//	public String unSavedPost(Integer userId, Integer postId) throws UserException, PostException {
//		Posts post=findPostById(postId);
//		
//          User user=userService.findById(userId);
//		
//		if(!user.getSavedPost().contains(post)) {
//			user.getSavedPost().remove(post);
//			userRepo.save(user);
//			
//		}
// 		return "your post is successfully unsaved";
//	}
//
//	@Override
//	public Posts likePost(Integer userId, Integer postId) throws UserException, PostException {
//		
//		Posts post=findPostById(postId);
//		
//        User user=userService.findById(userId);
//        
//        UserDto userDto=new UserDto();
//        
//        userDto.setName(user.getName());
//        userDto.setEmail(user.getEmail());
//        userDto.setUsername(user.getUsername());
//        userDto.setUserImage(user.getImage());
//        userDto.setId(user.getId());
//        
//        post.getLikedByUsers().add(userDto);
//        
//		return postsRepo.save(post);
//	}
//
//	@Override
//	public Posts unlikePost(Integer userId, Integer postId) throws UserException, PostException {
//	
//		
//        Posts post=findPostById(postId);
//		
//        User user=userService.findById(userId);
//        
//        UserDto userDto=new UserDto();
//        
//        userDto.setName(user.getName());
//        userDto.setEmail(user.getEmail());
//        userDto.setUsername(user.getUsername());
//        userDto.setUserImage(user.getImage());
//        userDto.setId(user.getId());
//        
//        post.getLikedByUsers().remove(userDto);
//        
//		return postsRepo.save(post);
//	}
//
//}
