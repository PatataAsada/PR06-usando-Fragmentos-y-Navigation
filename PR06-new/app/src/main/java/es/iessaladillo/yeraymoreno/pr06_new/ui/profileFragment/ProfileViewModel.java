package es.iessaladillo.yeraymoreno.pr06_new.ui.profileFragment;

import androidx.lifecycle.ViewModel;
import es.iessaladillo.yeraymoreno.pr06_new.data.Database;
import es.iessaladillo.yeraymoreno.pr06_new.data.model.Student;

public class ProfileViewModel extends ViewModel {
    // TODO: Implement the ViewModel

    private Student student;
    private Student oldStudent;
    public boolean isEdit;
    public boolean success;

    public ProfileViewModel(Student student){
        this.student = new Student();
        if(student!=null){
            this.student.setName(student.getName());
            this.student.setAvatar(student.getAvatar());
            this.student.setEmail(student.getEmail());
            this.student.setPhonenumber(student.getPhonenumber());
            this.student.setAddress(student.getAddress());
            this.student.setWeb(student.getWeb());
            isEdit = true;
        }else{
            this.student.setAvatar(Database.getInstance().getDefaultAvatar());
            isEdit = false;
        }
        success = false;
        oldStudent = this.student;
    }

    public void saveStudent(Student student){
        this.student = student;
        success = true;
    }
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Student getOldStudent() {
        return oldStudent;
    }

    public void setOldStudent(Student oldStudent) {
        this.oldStudent = oldStudent;
    }
}
