package model;


import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import utils.Area;
import utils.Gender;

public class Employee extends Person implements Serializable{

    private double salary;
    private Department department;
    private String startOfWork;
    private String password;
    private String picturePath;

	public Employee(String id, String firstName, String lastName, String phoneNumber, Area area, Gender gender,
			int yearOfBirth,String startOfWork,double salary, Department department,String password) {
		super(id, firstName, lastName, phoneNumber, area, gender,yearOfBirth);
		this.salary = salary;
		this.department = department;
		this.startOfWork = startOfWork;
		this.password = password;
		
	}


	
	public String getStartOfWork() {
		return startOfWork;
	}


	public void setStartOfWork(String startOfWork) {
		this.startOfWork = startOfWork;
	}


	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public Department getDepartment() {
		return department;
	}


	public void setDepartment(Department department) {
		this.department = department;
	}
	
	public String getPassword() {
		return password;
	}


	@Override
	public String toString() {
		return "Employee [id " +getId() +" salary=" + salary + ", department=" + department + ", startOfWork=" + startOfWork + "]"+"\n";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(department, salary, startOfWork);
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		return Objects.equals(department, other.department)
				&& Double.doubleToLongBits(salary) == Double.doubleToLongBits(other.salary)
				&& startOfWork == other.startOfWork;
	}




	public String getPicturePath() {
		return picturePath;
	}




	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}


	


    
}
