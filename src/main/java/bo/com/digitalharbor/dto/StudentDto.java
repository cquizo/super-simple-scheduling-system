package bo.com.digitalharbor.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class StudentDto implements Serializable {
	private static final long serialVersionUID = 4913093L;
	private Long studentId;
	private String firstName;
	private String lastName;
	private List<ClassDto> classList;

	public void add(ClassDto value) {
		if (this.classList == null) {
			this.classList = new ArrayList<>();
		}
		this.classList.add(value);
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<ClassDto> getClassList() {
		return classList;
	}

	public void setClassList(List<ClassDto> classList) {
		this.classList = classList;
	}

}
