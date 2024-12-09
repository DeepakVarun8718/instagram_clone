//package in.deepak.serviceImpl;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import in.deepak.entity.Story;
//import in.deepak.entity.User;
//import in.deepak.entity.UserDto;
//import in.deepak.exception.StoryException;
//import in.deepak.exception.UserException;
//import in.deepak.repository.StoryRepository;
//import in.deepak.repository.UserRepository;
//import in.deepak.service.StoryService;
//import in.deepak.service.UserService;
//
//@Service
//public class StoryServiceImpl implements StoryService {
//
//	@Autowired
//	private StoryRepository storyRepository;
//	
//	
//	@Autowired
//	private UserService userService;
//	
//
////	@Autowired
////	private  UserRepository userRepository;
//	
//	@Override
//	public Story createStory(Story story, Integer userId) throws UserException {
//	
//		User user=userService.findById(userId);
//		
//		UserDto userDto=new UserDto();
//		
//		userDto.setId(user.getId());
//		userDto.setName(user.getName());
//		userDto.setEmail(user.getEmail());
//		userDto.setUserImage(user.getImage());
//		userDto.setUsername(user.getUsername());
//		
//		story.setUser(userDto);
//		
//		story.setTimestamp(LocalDateTime.now());
//		
//		user.getStories().add(story);
//		
////		return storyRepository.save(story);
//		return null;
//	
//	}
//	
//	@Override
//	public List<Story> findStoryByUserId(Integer userId) throws UserException, StoryException {
//		
//		User user=userService.findById(userId);
//	List<Story> stories=user.getStories();
//	
//	if(stories.size()==0) {
//		throw new StoryException("this user doesn't have any story");
//	}
//	
//	return stories;
//	}
//
//}
