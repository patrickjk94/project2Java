package com.revature.Project2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.Project2.beans.Story;
import com.revature.Project2.dto.TaskDTO;
import com.revature.Project2.repository.StoryRepo;

@Service
public class StoryService {
	
	@Autowired
	StoryRepo storyRepo;
	
	public Story getStory(int id) {
		
		return storyRepo.findOne(id);
		
	}
	
	public Story createStory(Story story) {
		
		return storyRepo.save(story);
	}
	
	public Story addTask(TaskDTO dto) {
		Story story = storyRepo.findOne(dto.getStoryId());
		story.getTasks().add(dto.getTask());
		return storyRepo.save(story);
	}
	
	public boolean deleteStory(Story story) {
		storyRepo.delete(story.getStId());
		return true;
	}
}
