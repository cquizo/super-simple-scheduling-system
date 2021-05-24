package bo.com.digitalharbor.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "classes")
public class Class implements Serializable {
	private static final long serialVersionUID = 4913093L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long code;
	private String title;
	private String description;
	@OneToMany(targetEntity = Registration.class, mappedBy = "clazz")
	private List<Registration> registrationList;

	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Registration> getRegistrationList() {
		return registrationList;
	}

	public void setRegistrationList(List<Registration> registrationList) {
		this.registrationList = registrationList;
	}
}
