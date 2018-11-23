package com.xxx.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="t_user")
public class UserModel extends PaginationModel implements Serializable {
	
	private static final long serialVersionUID = -4429434734978388953L;
	
	public UserModel(){
	}
	
	public UserModel(String name, int age, String job){
		this.name = name;
		this.age = age;
		this.job = job;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@Column(name="name")
    private String name;

	@Column(name="age")
    private Integer age;

	@Column(name="job")
    private String job;
    
	@Column(name="password")
    private String password;
    
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

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
    
}
