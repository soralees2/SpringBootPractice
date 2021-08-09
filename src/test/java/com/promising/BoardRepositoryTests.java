package com.promising;

import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import com.promising.domain.BoardVO;
import com.promising.repository.BoardRepository;

import lombok.extern.java.Log;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
@Commit
public class BoardRepositoryTests {
	
	@Autowired
	BoardRepository repo;
	
	@Test
	public void insertBoardDummies() {
		IntStream.range(0, 300).forEach(i->{
			BoardVO board = new BoardVO();
			
			board.setTitle("Sample Board" + i);
			board.setContents("Content Sample" + i + " of Board");
			board.setWriter("user" + (i%10));
			
			repo.save(board);
		});
	}

}
