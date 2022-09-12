package com.example.studentbackend.Controllers;

import com.example.studentbackend.models.Student;
import com.example.studentbackend.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class MyController {

    @Autowired
    StudentRepository studentRepository;

    @GetMapping("/listStudents")
    public List<Student> getListOfStudents(){
        return studentRepository.findAll();
    }

    @PostMapping("/student")
    public String addStudent(@RequestBody Student student) {
        Boolean studentExists = studentRepository.existsById(student.getId());

       if(!studentExists){
            studentRepository.save(student); //insert into student values(-,-,-)
            return "Record Saved successfully!!";
        } else{
           return "Student Already Exists!!!";
        }

    }

    @DeleteMapping("/student/{id}")
    public String deleteStudent(@PathVariable Integer id){
        Boolean studentExists = studentRepository.existsById(id);
        if(studentExists) {
            studentRepository.deleteById(id);
            return "Record Deleted Successfully!!";
        } else{
            return "Record Does Not Exist!!";
        }
    }

    @PutMapping("/student/{id}")
    public String updateStudent(@RequestBody Student student, @PathVariable Integer id){
        Student existingStudent = studentRepository.findById(id).get();
        existingStudent.setName(student.getName());
        existingStudent.setAddress(student.getAddress());
        studentRepository.save(existingStudent);
        return "Record Is Updated!!";
    }

    @GetMapping("/student/{id}")
    public Student getStudent(@PathVariable Integer id){

        return studentRepository.findById(id).get();
    }

}
