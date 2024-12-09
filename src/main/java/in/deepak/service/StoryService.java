package in.deepak.service;

import java.util.List;

import org.springframework.stereotype.Service;

import in.deepak.entity.Story;
import in.deepak.exception.PostException;
import in.deepak.exception.StoryException;
import in.deepak.exception.UserException;

@Service
public interface StoryService {

	public Story createStory(Story story,Integer userId) throws UserException;
	
	public List<Story> findStoryByUserId(Integer userId) throws UserException,StoryException;
	
	
	
	
	
}
