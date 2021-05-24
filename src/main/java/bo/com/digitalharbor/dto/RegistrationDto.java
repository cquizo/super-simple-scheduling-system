package bo.com.digitalharbor.dto;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class RegistrationDto implements Serializable {
	private static final long serialVersionUID = 4913093L;
	private Long registrationId;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date;
	private Integer score;
	private Long studentId;
	private String studentName;
	private Long classCode;
	private String classTitle;

	public Long getRegistrationId() {
		return registrationId;
	}

	public void setRegistrationId(Long registrationId) {
		this.registrationId = registrationId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public Long getClassCode() {
		return classCode;
	}

	public void setClassCode(Long classCode) {
		this.classCode = classCode;
	}

	public String getClassTitle() {
		return classTitle;
	}

	public void setClassTitle(String classTitle) {
		this.classTitle = classTitle;
	}

}
