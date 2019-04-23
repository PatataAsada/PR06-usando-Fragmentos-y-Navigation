package es.iessaladillo.yeraymoreno.pr06_new.ui.mainFragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import es.iessaladillo.yeraymoreno.pr06_new.R;
import es.iessaladillo.yeraymoreno.pr06_new.data.model.Student;


public class MainFragmentAdapter extends ListAdapter<Student, MainFragmentAdapter.ViewHolder> {
    private final OnStudentClickListener onStudentClickListener;

    public MainFragmentAdapter(OnStudentClickListener onStudentClickListener) {
        super(new DiffUtil.ItemCallback<Student>(){
            @Override
            public boolean areItemsTheSame(@NonNull Student oldItem, @NonNull Student newItem) {
                return oldItem.getId() == newItem.getId();
            }

            @Override
            public boolean areContentsTheSame(@NonNull Student oldItem, @NonNull Student newItem) {
                return oldItem.getAvatar().equals(newItem.getAvatar()) &&
                        TextUtils.equals(oldItem.getName(), newItem.getName()) &&
                        TextUtils.equals(oldItem.getEmail(), newItem.getEmail()) &&
                        oldItem.getPhonenumber() == newItem.getPhonenumber() &&
                        TextUtils.equals(oldItem.getAddress(), newItem.getAddress()) &&
                        TextUtils.equals(oldItem.getWeb(), newItem.getWeb());
            }
        });
        this.onStudentClickListener = onStudentClickListener;
    }

    @NonNull
    @Override
    public MainFragmentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MainFragmentAdapter.ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_main_item, parent, false), onStudentClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MainFragmentAdapter.ViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getId();
    }

    @Override
    public Student getItem(int position) {
        return super.getItem(position);
    }

    @SuppressWarnings("WeakerAccess")
    static class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imgAvatar;
        private final TextView lblName;
        private final TextView lblEmail;
        private final TextView lblPhonenumber;
        private final Button btnEdit;
        private final Button btnDelete;

        public ViewHolder(View itemView, final OnStudentClickListener onStudentClickListener) {
            super(itemView);
            imgAvatar = ViewCompat.requireViewById(itemView, R.id.imgAvatar);
            lblName = ViewCompat.requireViewById(itemView, R.id.lblName);
            lblPhonenumber = ViewCompat.requireViewById(itemView, R.id.lblPhonenumber);
            lblEmail = ViewCompat.requireViewById(itemView, R.id.lblEmail);
            btnDelete = ViewCompat.requireViewById(itemView, R.id.btnDelete);
            btnEdit = ViewCompat.requireViewById(itemView, R.id.btnEdit);
            btnEdit.setOnClickListener(v -> onStudentClickListener.OnEditClickListener(getAdapterPosition()));
            btnDelete.setOnClickListener(v -> onStudentClickListener.OnDeleteClickListener(getAdapterPosition()));
        }

        public void bind(Student student) {
            imgAvatar.setImageResource(student.getAvatar().getImageResId());
            lblName.setText(student.getName());
            lblEmail.setText(student.getEmail());
            lblPhonenumber.setText(String.valueOf(student.getPhonenumber()));
        }
    }
}
