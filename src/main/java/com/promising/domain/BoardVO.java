package com.promising.domain;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(of="bno")
@SequenceGenerator(name="MYBOARD_SEQ_GENERATOR",sequenceName = "MYBOARD_SEQ" ,initialValue = 1,allocationSize = 1)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="myboard")
public class BoardVO {

	@Id
	@GeneratedValue(strategy = GenerationType. SEQUENCE, generator = "MYBOARD_SEQ_GENERATOR")
	private Long bno;
	private String title;
	@Lob
	private String contents;
	private String writer;
	@CreationTimestamp
	private Timestamp regdate;
	

}