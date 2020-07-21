package eshop.entity;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="Categories")
public class Category {
	@Id
	@GeneratedValue
	Integer id;
	@NotEmpty
	String nameVN;
	String name;
		
	@OneToMany(mappedBy="category")
	Collection<Product> products;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public String getNameVN() {
		return nameVN;
	}

	public void setNameVN(String nameVN) {
		this.nameVN = nameVN;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Collection<Product> getProducts() {
		return products;
	}

	public void setProducts(Collection<Product> products) {
		this.products = products;
	}
	
}
