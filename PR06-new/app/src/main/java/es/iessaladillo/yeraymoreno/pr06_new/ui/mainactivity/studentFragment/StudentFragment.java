package es.iessaladillo.yeraymoreno.pr06_new.ui.mainactivity.studentFragment;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

import es.iessaladillo.yeraymoreno.pr06_new.R;
import es.iessaladillo.yeraymoreno.pr06_new.databinding.FragmentStudentBinding;
import es.iessaladillo.yeraymoreno.pr06_new.ui.mainactivity.mainFragment.MainFragmentViewModel;
import es.iessaladillo.yeraymoreno.pr06_new.utils.KeyboardUtils;
import es.iessaladillo.yeraymoreno.pr06_new.utils.TextChangedListener;
import es.iessaladillo.yeraymoreno.pr06_new.utils.ValidationUtils;

public class StudentFragment extends Fragment {

    public StudentViewModel studentViewModel;
    public MainFragmentViewModel mainFragmentViewModel;
    private FragmentStudentBinding studentBinding;

    private NavController navController;

    private Intent intention;

    public static StudentFragment newInstance() {
        return new StudentFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        studentBinding = FragmentStudentBinding.inflate(inflater,container,false);
        return studentBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //Sets navcontroller, gets viewmodel for current student(if edit was clicked).
        navController = NavHostFragment.findNavController(this);
        studentViewModel = ViewModelProviders.of(Objects.requireNonNull(this.getActivity())).get(StudentViewModel.class);
        mainFragmentViewModel = ViewModelProviders.of(this.getActivity()).get(MainFragmentViewModel.class);
        //Sets the image and form with data from viewmodel.
        setupViews();
        //Sets the click listeners.
        setupListeners();
        //Sets the appbar for studentFragment.
        setupToolbar(getView());
    }

    //Sets the toolbar.
    private void setupToolbar(View view) {
        Toolbar toolbar = ViewCompat.requireViewById(view, R.id.toolbar);
        toolbar.setTitle(R.string.fragment_student_toolbar);
        toolbar.inflateMenu(R.menu.fragment_student);
        toolbar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.mnuSave) {
                save();
                return true;
            }
            else return StudentFragment.super.onOptionsItemSelected(item);
        });
    }


    //Sets all needed listeners.
    private void setupListeners() {
        //Focus Listeners
        focusListeners();

        //Icon press listeners.
        iconListeners();

        //IME Option
        imeListener();

        //When pressing avatar image or name.
        avatarListeners();
    }

    //Sets a listener for ime button.
    private void imeListener() {
        //When pressing IME done button.
        studentBinding.layoutForm.txtWeb.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                save();
                KeyboardUtils.hideSoftKeyboard(Objects.requireNonNull(this.getActivity()));
                return true;
            }
            return false;
        });
    }

    //Sets the avatar with a click listener to start AvatarFragment.
    private void avatarListeners() {
        studentBinding.layoutAvatar.imgAvatar.setOnClickListener(v -> changeAvatar());
        studentBinding.layoutAvatar.lblAvatar.setOnClickListener(v -> changeAvatar());
    }

    //starts avatarFragment.
    private void changeAvatar() {
        setStudent();
        KeyboardUtils.hideSoftKeyboard(Objects.requireNonNull(getActivity()));
        navController.navigate(R.id.action_studentFragment_to_avatarFragment);
    }

    //Sets click listheners for icons to send intent.
    private void iconListeners() {

        //EMAIL
        studentBinding.layoutForm.imgEmail.setOnClickListener(v -> {
            intention = new Intent(Intent.ACTION_SENDTO);
            intention.setData(Uri.parse("mailto:" + studentBinding.layoutForm.txtEmail.getText()));
            startActivity(intention);
        });

        //PHONENUMBER
        studentBinding.layoutForm.imgPhonenumber.setOnClickListener(v -> {
            intention = new Intent(Intent.ACTION_DIAL);
            intention.setData(Uri.parse("tel:" + studentBinding.layoutForm.txtPhonenumber.getText()));
            startActivity(intention);
        });

        //ADDRESS
        studentBinding.layoutForm.imgAddress.setOnClickListener(v -> {
            intention = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=" + studentBinding.layoutForm.txtAddress.getText().toString()));
            intention.setPackage("com.google.android.apps.maps");
            startActivity(intention);
        });

        //WEB
        studentBinding.layoutForm.imgWeb.setOnClickListener(v -> {
            String url = studentBinding.layoutForm.txtWeb.getText().toString();
            if (!url.startsWith("http://") && !url.startsWith("https://")) url = "http://" + url;
            intention = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intention);
        });
    }

    //Sets focus listeners for label emphasis.
    private void focusListeners() {
        studentBinding.layoutForm.txtName.setOnFocusChangeListener((v, hasFocus) -> isFocused(hasFocus, studentBinding.layoutForm.lblName));
        studentBinding.layoutForm.txtEmail.setOnFocusChangeListener((v, hasFocus) -> isFocused(hasFocus, studentBinding.layoutForm.lblEmail));
        studentBinding.layoutForm.txtPhonenumber.setOnFocusChangeListener((v, hasFocus) -> isFocused(hasFocus, studentBinding.layoutForm.lblPhonenumber));
        studentBinding.layoutForm.txtAddress.setOnFocusChangeListener((v, hasFocus) -> isFocused(hasFocus, studentBinding.layoutForm.lblAddress));
        studentBinding.layoutForm.txtWeb.setOnFocusChangeListener((v, hasFocus) -> isFocused(hasFocus, studentBinding.layoutForm.lblWeb));
    }

    //Changes label typeface when focus is true.
    private void isFocused(boolean hasFocus, TextView lbl) {
        if (hasFocus) lbl.setTypeface(Typeface.DEFAULT_BOLD);
        else lbl.setTypeface(Typeface.DEFAULT);
    }

    //Sets the image and the form if viewmodel has a student.
    private void setupViews() {
        //Sets the avatar image and name.
        setAvatar();
        //If we are editing a student it will fill the form with student in viewmodel.
        if (studentViewModel.isEdit) {
            //Fills de form with data obtained from viewmodel
            setForm();
        }
    }

    //Fills de form with data obtained from viewmodel
    private void setForm() {
        studentBinding.layoutForm.txtName.setText(studentViewModel.getStudent().getName());
        studentBinding.layoutForm.txtEmail.setText(studentViewModel.getStudent().getEmail());
        studentBinding.layoutForm.txtPhonenumber.setText(String.valueOf(studentViewModel.getStudent().getPhonenumber()));
        studentBinding.layoutForm.txtAddress.setText(studentViewModel.getStudent().getAddress());
        studentBinding.layoutForm.txtWeb.setText(studentViewModel.getStudent().getWeb());
    }

    //Sets the avatar image and name.
    private void setAvatar() {
        studentBinding.layoutAvatar.imgAvatar.setTag(studentViewModel.getStudent().getAvatar().getImageResId());
        studentBinding.layoutAvatar.imgAvatar.setImageResource(studentViewModel.getStudent().getAvatar().getImageResId());
        studentBinding.layoutAvatar.lblAvatar.setText(studentViewModel.getStudent().getAvatar().getName());
    }

    //Save data from form when orientation changes.
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        setStudent();
    }

    //On orientation change this will force check form texts.
    @Override
    public void onResume() {
        super.onResume();
        //Text change Listeners
        textChangedListeners();
    }

    //Uses the util TextChangedListeners to check if text has changed in form.
    private void textChangedListeners() {
        TextChangedListener.is_changed(studentBinding.layoutForm.txtName, studentBinding.layoutForm.lblName, null, 0);
        TextChangedListener.is_changed(studentBinding.layoutForm.txtEmail, studentBinding.layoutForm.lblEmail, studentBinding.layoutForm.imgEmail, 1);
        TextChangedListener.is_changed(studentBinding.layoutForm.txtPhonenumber, studentBinding.layoutForm.lblPhonenumber, studentBinding.layoutForm.imgPhonenumber, 2);
        TextChangedListener.is_changed(studentBinding.layoutForm.txtAddress, studentBinding.layoutForm.lblAddress, studentBinding.layoutForm.imgAddress, 4);
        TextChangedListener.is_changed(studentBinding.layoutForm.txtWeb, studentBinding.layoutForm.lblWeb, studentBinding.layoutForm.imgWeb, 3);
    }

    //Saves data from form in viewmodel.
    private void setStudent() {
        studentViewModel.getStudent().setName(studentBinding.layoutForm.txtName.getText().toString());
        studentViewModel.getStudent().setEmail(studentBinding.layoutForm.txtEmail.getText().toString());
        if(!studentBinding.layoutForm.txtPhonenumber.getText().toString().equals("")){
            studentViewModel.getStudent().setPhonenumber(Integer.parseInt(studentBinding.layoutForm.txtPhonenumber.getText().toString()));
        }
        studentViewModel.getStudent().setAddress(studentBinding.layoutForm.txtAddress.getText().toString());
        studentViewModel.getStudent().setWeb(studentBinding.layoutForm.txtWeb.getText().toString());
    }


    //Checks form and, if form is valid, saves the student in database.
    private void save() {
        if (!ValidationUtils.isValidForm(studentBinding.layoutForm.txtEmail.getText().toString(), studentBinding.layoutForm.txtPhonenumber.getText().toString(), studentBinding.layoutForm.txtWeb.getText().toString(),
                studentBinding.layoutForm.txtName.getText().toString(), studentBinding.layoutForm.txtAddress.getText().toString())) {
            Snackbar.make(studentBinding.layoutForm.txtWeb, getString(R.string.main_saved_succesfully), Snackbar.LENGTH_SHORT).show();
            //Sends the student to the database.
            setStudent();
            sendStudentToDataBase();
            KeyboardUtils.hideSoftKeyboard(Objects.requireNonNull(getActivity()));
            close();
        } else {
            showErrors();
            Snackbar.make(studentBinding.layoutForm.txtWeb, getString(R.string.main_error_saving), Snackbar.LENGTH_SHORT).show();
        }

    }

    //Returns to mainFragment
    private void close() {
        navController.navigateUp();
    }

    //Sends the student to database.
    private void sendStudentToDataBase() {
        setStudent();
        if (studentViewModel.isEdit) {
            mainFragmentViewModel.editStudent(studentViewModel.getOldStudent(), studentViewModel.getStudent());
        } else {
            mainFragmentViewModel.addStudent(studentViewModel.getStudent());
        }
    }

    //Shows errors in form if trying to save with blank areas.
    private void showErrors() {
        if (!ValidationUtils.isValidText(studentBinding.layoutForm.txtName.getText().toString()))
            studentBinding.layoutForm.txtName.setText("");
        if (!ValidationUtils.isValidEmail(studentBinding.layoutForm.txtEmail.getText().toString()))
            studentBinding.layoutForm.txtEmail.setText("");
        if (!ValidationUtils.isValidPhone(studentBinding.layoutForm.txtPhonenumber.getText().toString()))
            studentBinding.layoutForm.txtPhonenumber.setText("");
        if (!ValidationUtils.isValidText(studentBinding.layoutForm.txtAddress.getText().toString()))
            studentBinding.layoutForm.txtAddress.setText("");
        if (!ValidationUtils.isValidUrl(studentBinding.layoutForm.txtWeb.getText().toString()))
            studentBinding.layoutForm.txtWeb.setText("");
    }
}
