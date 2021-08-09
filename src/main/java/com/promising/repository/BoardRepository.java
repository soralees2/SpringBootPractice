package com.promising.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.promising.domain.BoardVO;
import com.promising.domain.QBoardVO;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;


public interface BoardRepository extends JpaRepository<BoardVO, Long>, QuerydslPredicateExecutor<BoardVO>{
	
	public default Predicate makePredicate(String type, String keyword) {
		
		BooleanBuilder builder = new BooleanBuilder();
		
		QBoardVO board = QBoardVO.boardVO;
		
		builder.and(board.bno.gt(0));
		
		if(type==null) {
			return builder;
		}

		switch(type) {
		case "t" :
			builder.and(board.title.like("%"+keyword+"%"));
			break;
		case "c":
			builder.and(board.contents.like("%"+keyword+"%"));
			break;
		case "w" :
			builder.and(board.writer.like("%"+keyword+"%"));
			break;
		}
		
		return builder;
	}
	
}
