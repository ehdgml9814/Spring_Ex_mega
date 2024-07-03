package com.example.lesson04.bo;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lesson04.domain.Student;
import com.example.lesson04.mapper.StudentMapper;
import com.example.lesson07.entity.StudentEntity;
import com.example.lesson07.repository.StudentRepository;

@Service
public class StudentBO {

	@Autowired
	private StudentMapper studentMapper;
	
	@Autowired
	private StudentRepository studentRepository;

	// input: 파라미터 4개
	// output: StudentEntity
	public StudentEntity addStudent(String name, String phoneNumber, 
			String email, String dreamJob) {
		StudentEntity student = StudentEntity.builder()
				.name(name)
				.phoneNumber(phoneNumber)
				.email(email)
				.dreamJob(dreamJob)
				.createdAt(LocalDateTime.now()) // @CreationTimestamp 있으면 생략 가능
				.build();
		
		return studentRepository.save(student);
	}
	
	// update
	// input: id, dreamJob
	// output: StudentEntity or null
	public StudentEntity updateStudentDreamJobById(int id, String dreamJob) {
		// 기존 데이터 조회 => StudentEntity
		// Optional: null 검사 가능한 객체
		// orElse(): 조회 결과가 없으면 ()로 바꿈
		StudentEntity student = studentRepository.findById(id).orElse(null);
		
		// 엔티티의 데이터 값 변경
		if (student != null) {
			student = student.toBuilder() // 일부 필드값만 변경
				.dreamJob(dreamJob)
				.build(); // 반드시 객체에 다시 저장
			
			// update(save 요청)
			student = studentRepository.save(student);
		}
		return student;
	}
	
	// input: id
	// output: X
	public void deleteStudentById(int id) {
		// 방법1)
//		StudentEntity student = studentRepository.findById(id).orElse(null);
//		if (student != null) {
//			studentRepository.delete(student);
//		}
		// 방법2)
		Optional<StudentEntity> studentOptional = studentRepository.findById(id);
		studentOptional.ifPresent(s -> studentRepository.delete(s)); // Optional이 가리키는 것 - s
	}
	
	
	// MyBatis
	
	// input: Student 객체
	// output: X
	public void addStudent(Student student) {
		studentMapper.insertStudent(student);
	}
	
	public Student getStudentById(int id) {
		return studentMapper.selectStudentById(id);
	}
}
