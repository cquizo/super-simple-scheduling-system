package bo.com.digitalharbor.mapper;

import java.util.ArrayList;
import java.util.List;

import bo.com.digitalharbor.dto.StudentDto;
import bo.com.digitalharbor.entity.Student;

public final class StudentMapper {

	public static void to(StudentDto from, Student to) {
		to.setStudentId(from.getStudentId());
		to.setFirstName(from.getFirstName());
		to.setLastName(from.getLastName());
	}

	public static void to(Student from, StudentDto to) {
		to.setStudentId(from.getStudentId());
		to.setFirstName(from.getFirstName());
		to.setLastName(from.getLastName());
	}

	public static StudentDto to(Student from) {
		StudentDto to = new StudentDto();
		to(from, to);
		return to;
	}

	public static Student to(StudentDto from) {
		Student to = new Student();
		to(from, to);
		return to;
	}

	public static List<Student> to(List<StudentDto> fromList) {
		List<Student> toList = new ArrayList<>();
		fromList.forEach(from -> {
			Student to = to(from);
			toList.add(to);
		});
		return toList;
	}

	public static List<StudentDto> toDto(List<Student> fromList) {
		List<StudentDto> toList = new ArrayList<>();
		fromList.forEach(from -> {
			StudentDto to = to(from);
			toList.add(to);
		});
		return toList;
	}

}
