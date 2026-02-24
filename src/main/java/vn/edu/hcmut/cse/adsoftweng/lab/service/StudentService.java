package vn.edu.hcmut.cse.adsoftweng.lab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.hcmut.cse.adsoftweng.lab.entity.Student;
import vn.edu.hcmut.cse.adsoftweng.lab.repository.StudentRepository;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository repository;

    public List<Student> getAll() {
        return repository.findAll();
    }

    public Student getById(String id) {
        return repository.findById(id).orElse(null);
    }

    public List<Student> searchByName(String keyword) {
        return repository.findByNameContainingIgnoreCase(keyword);
    }

    public void create(Student student) {
        if(repository.existsById(student.getId())) {
            throw new IllegalStateException("Student with id " + student.getId() + " already exists");
        }
        repository.save(student);
    }

    public void update(Student student) {
        if(repository.existsById(student.getId())) {
            repository.save(student);
        } else
            throw new IllegalStateException("Student with id " + student.getId() + " does not exist");
    }

    public void delete(String id) {
        repository.deleteById(id);
    }

}
