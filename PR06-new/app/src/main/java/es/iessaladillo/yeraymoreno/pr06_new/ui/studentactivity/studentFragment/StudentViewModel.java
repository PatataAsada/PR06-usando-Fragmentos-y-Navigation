package es.iessaladillo.yeraymoreno.pr06_new.ui.studentactivity.studentFragment;

import androidx.lifecycle.ViewModel;

import es.iessaladillo.yeraymoreno.pr06_new.data.Database;
import es.iessaladillo.yeraymoreno.pr06_new.data.model.Student;

public class StudentViewModel extends ViewModel {
    private Student student;
    private Student oldStudent;
    public boolean isEdit;
    public boolean success;

    public StudentViewModel() {
        student = new Student();
        student.setAvatar(Database.getInstance().getDefaultAvatar());
    }

    public void saveStudent(Student student) {
        this.student = student;
        success = true;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = new Student();
        if (student!=null){
            this.student.setAvatar(student.getAvatar());
            this.student.setName(student.getName());
            this.student.setAddress(student.getAddress());
            this.student.setEmail(student.getEmail());
            this.student.setPhonenumber(student.getPhonenumber());
            this.student.setWeb(student.getWeb());
            this.oldStudent = student;
        }else{
            this.student.setAvatar(Database.getInstance().getDefaultAvatar());
        }
    }

    public Student getOldStudent() {
        return oldStudent;
    }

    public void setOldStudent(Student oldStudent) {
        this.oldStudent = oldStudent;
    }
}
