package com.xxx.model;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_course")
public class CourseModel extends PaginationModel implements Serializable{
	
	private static final long serialVersionUID = 1L;

	public CourseModel(){}
	
	public CourseModel(String name, int type, int score){
		this.name = name;
		this.type = type;
		this.score = score;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@Column(name="name")
    private String name;

	@Column(name="type")
    private Integer type;

	@Column(name="score")
    private Integer score;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}
    
    
}
