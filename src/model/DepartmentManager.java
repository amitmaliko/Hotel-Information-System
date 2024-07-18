package model;



import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import utils.Area;
import utils.Gender;

public class DepartmentManager extends Employee implements Serializable {
	
	private Double bonus;

	

	public DepartmentManager(String id, String firstName, String lastName, String phoneNumber, Area area, Gender gender,
			int yearOfBirth,String startOfWork,double salary, Department department,String password ,Double bonus) {
		super(id, firstName, lastName, phoneNumber, area, gender, yearOfBirth,startOfWork,salary, department,password);
		this.bonus = bonus;

	}



	public Double getBonus() {
		return bonus;
	}

	public void setBonus(Double bonus) {
		this.bonus = bonus;
	}



	@Override
	public String toString() {
		return  super.toString() + " DepartmentManager [bonus=" + bonus + "]"+"\n";
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(bonus);
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
		DepartmentManager other = (DepartmentManager) obj;
		return Objects.equals(bonus, other.bonus);
	}

	
	
	

}
