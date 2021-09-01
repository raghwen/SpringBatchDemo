package com.poc.springBatch.batch.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class User {

    @Id
    private Integer id;
    private String name;
    private String department;
   
    private String career;
    public String getCareer() {
		return career;
	}

	public void setCareer(String career) {
		this.career = career;
	}

	

    public User(Integer id, String name, String department, String career) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.career = career;
       
    }

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

   

    

    public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	@Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("User{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", dept='").append(department).append('\'');
       
        sb.append('}');
        return sb.toString();
    }

   
}
