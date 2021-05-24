package bo.com.digitalharbor.mapper;

import java.util.ArrayList;
import java.util.List;

import bo.com.digitalharbor.dto.RegistrationDto;
import bo.com.digitalharbor.entity.Class;
import bo.com.digitalharbor.entity.Registration;
import bo.com.digitalharbor.entity.Student;

public final class RegistrationMapper {

	public static void to(RegistrationDto from, Registration to) {
		to.setRegistrationId(from.getRegistrationId());
		to.setDate(from.getDate());
		to.setScore(from.getScore());
		if (from.getStudentId() != null) {
			to.setStudent(new Student());
			to.getStudent().setStudentId(from.getStudentId());
		}
		if (from.getClassCode() != null) {
			to.setClazz(new Class());
			to.getClazz().setCode(from.getClassCode());
		}
	}

	public static void to(Registration from, RegistrationDto to) {
		to.setRegistrationId(from.getRegistrationId());
		to.setDate(from.getDate());
		to.setScore(from.getScore());
		if (from.getStudent() != null) {
			to.setStudentId(from.getStudent().getStudentId());
			to.setStudentName(from.getStudent().getFirstName() + " " + from.getStudent().getLastName());
		}
		if (from.getClazz() != null) {
			to.setClassCode(from.getClazz().getCode());
			to.setClassTitle(from.getClazz().getTitle() + " - " + from.getClazz().getDescription());
		}
	}

	public static RegistrationDto to(Registration from) {
		RegistrationDto to = new RegistrationDto();
		to(from, to);
		return to;
	}

	public static Registration to(RegistrationDto from) {
		Registration to = new Registration();
		to(from, to);
		return to;
	}

	public static List<Registration> to(List<RegistrationDto> fromList) {
		List<Registration> toList = new ArrayList<>();
		fromList.forEach(from -> {
			Registration to = to(from);
			toList.add(to);
		});
		return toList;
	}

	public static List<RegistrationDto> toDto(List<Registration> fromList) {
		List<RegistrationDto> toList = new ArrayList<>();
		fromList.forEach(from -> {
			RegistrationDto to = to(from);
			toList.add(to);
		});
		return toList;
	}

}
