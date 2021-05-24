package bo.com.digitalharbor.mapper;

import java.util.ArrayList;
import java.util.List;

import bo.com.digitalharbor.dto.ClassDto;
import bo.com.digitalharbor.entity.Class;

public final class ClassMapper {

	public static void to(ClassDto from, Class to) {
		to.setCode(from.getCode());
		to.setTitle(from.getTitle());
		to.setDescription(from.getDescription());
	}

	public static void to(Class from, ClassDto to) {
		to.setCode(from.getCode());
		to.setTitle(from.getTitle());
		to.setDescription(from.getDescription());
	}

	public static ClassDto to(Class from) {
		ClassDto to = new ClassDto();
		to(from, to);
		return to;
	}

	public static Class to(ClassDto from) {
		Class to = new Class();
		to(from, to);
		return to;
	}

	public static List<Class> to(List<ClassDto> fromList) {
		List<Class> toList = new ArrayList<>();
		fromList.forEach(from -> {
			Class to = to(from);
			toList.add(to);
		});
		return toList;
	}

	public static List<ClassDto> toDto(List<Class> fromList) {
		List<ClassDto> toList = new ArrayList<>();
		fromList.forEach(from -> {
			ClassDto to = to(from);
			toList.add(to);
		});
		return toList;
	}

}
