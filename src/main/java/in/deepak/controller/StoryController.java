//package in.deepak.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestHeader;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import in.deepak.entity.Story;
//import in.deepak.entity.User;
//import in.deepak.exception.StoryException;
//import in.deepak.exception.UserException;
//import in.deepak.service.StoryService;
//import in.deepak.service.UserService;
//
//@RestController
//@RequestMapping("/api/stories")
//public class StoryController {
//
//	@Autowired
//	private UserService userService;
//	
//	@Autowired
//	private StoryService storyService;
//	
//	@PostMapping("/create/{}")
//	public ResponseEntity<Story> createStoryHandler(@RequestBody Story story, @RequestHeader("Authorization") String token)
//	throws StoryException, UserException{
//		
//	User user=userService.findByProfile(token);
//	Story createdStory =storyService.createStory(story, user.getId());
//	
//	return new ResponseEntity<Story>(createdStory,HttpStatus.ACCEPTED);
//	
//		
//	}
//	
//	@PostMapping("")
//	public ResponseEntity<List<Story>> findAllStoryByUserIdHandler(@PathVariable Integer userId)throws UserException, StoryException{
//	
//		User user=userService.findById(userId);
//		
//		List<Story> stories =storyService.findStoryByUserId(userId);
//		
//		return new ResponseEntity<List<Story>>(stories,HttpStatus.OK);
//	}
//	
//	
//	
//	
//}
