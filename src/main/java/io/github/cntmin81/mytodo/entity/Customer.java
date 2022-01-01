package io.github.cntmin81.mytodo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String firstName;
	private String lastName;
	
	public Customer() {
	}

	public Customer(String firstName, String lastname) {
		super();
		this.firstName = firstName;
		this.lastName = lastname;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", firstName=" + firstName + ", lastname=" + lastName + "]";
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastname() {
		return lastName;
	}

}
