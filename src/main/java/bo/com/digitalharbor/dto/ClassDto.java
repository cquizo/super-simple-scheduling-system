package bo.com.digitalharbor.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ClassDto implements Serializable {
	private static final long serialVersionUID = 4913093L;
	private Long code;
	private String title;
	private String description;
	private List<StudentDto> studentList;

	public void add(StudentDto value) {
		if (this.studentList == null) {
			this.studentList = new ArrayList<>();
		}
		this.studentList.add(value);
	}

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

	public List<StudentDto> getStudentList() {
		return studentList;
	}

	public void setStudentList(List<StudentDto> studentList) {
		this.studentList = studentList;
	}

}
