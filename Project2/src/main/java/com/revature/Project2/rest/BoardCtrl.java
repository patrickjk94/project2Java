package com.revature.Project2.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.Project2.beans.Board;
import com.revature.Project2.service.BoardService;

@RestController
public class BoardCtrl {
	
	@Autowired
	private BoardService boardService;
	
	@GetMapping("/board/{id}")
	public Board getBoard(@PathVariable int id) {
		
		System.out.println("REMOVE board control get");
		return boardService.getBoard(id);
	}
	
	@PostMapping("/createBoard")
	public ResponseEntity<Board> createBoard(@RequestBody Board board){
		
		System.out.println("REMOVE board control create");
		board = boardService.createBoard(board);
		return new ResponseEntity<Board>(board, HttpStatus.CREATED);
	}
	
}