package hashMap;

import java.util.Objects;

public class CarOwner {
	private int id;
	private String name;
	private String lastName;
	
	public CarOwner(int id, String name, String lastName) {
		this.id = id;
		this.name = name;
		this.lastName = lastName;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@Override 
	public int hashCode() {
		return Objects.hash(name,lastName,id);
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}
		if(obj ==null || getClass() != obj.getClass()) {
			return false;
		}
		CarOwner owner = (CarOwner) obj;
		return id == owner.id && name.equals(owner.name) && lastName.equals(owner.lastName);
	}
	
	
}
