package es.iessaladillo.yeraymoreno.pr06_new.ui.avatarFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import java.util.Objects;

import es.iessaladillo.yeraymoreno.pr06_new.R;
import es.iessaladillo.yeraymoreno.pr06_new.data.Database;
import es.iessaladillo.yeraymoreno.pr06_new.databinding.ActivityAvatarBinding;
import es.iessaladillo.yeraymoreno.pr06_new.ui.studentFragment.StudentViewModel;
import es.iessaladillo.yeraymoreno.pr06_new.utils.ResourcesUtils;

public class AvatarFragment extends Fragment {

    private AvatarViewModel aViewModel;
    private StudentViewModel sViewmodel;
    private ActivityAvatarBinding avatarBinding;

    private NavController navController;

    public static AvatarFragment newInstance() {
        return new AvatarFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        avatarBinding = ActivityAvatarBinding.inflate(inflater,container,false);
        return avatarBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        aViewModel = ViewModelProviders.of(Objects.requireNonNull(this.getActivity())).get(AvatarViewModel.class);
        sViewmodel = ViewModelProviders.of(this.getActivity()).get(StudentViewModel.class);
        navController = NavHostFragment.findNavController(this);
        setupToolbar(getView());
        setupViews();
    }

    private void setupViews() {
        initListeners();
        selectedAvatar((int) sViewmodel.getStudent().getAvatar().getId());
    }

    //Sets the toolbar to add a menu.
    private void setupToolbar(View view) {
        Toolbar toolbar = ViewCompat.requireViewById(view, R.id.toolbar);
        toolbar.setTitle(R.string.fragment_avatar_select);
        toolbar.inflateMenu(R.menu.fragment_avatar);
        toolbar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.mnuSelect) {
                returnAvatar();
                return true;
            }
            else return AvatarFragment.super.onOptionsItemSelected(item);
        });
    }

    //Changes the actual avatar that is selected.
    private void selectedAvatar(int avatarId) {
        deselectImageView(aViewModel.getAvatarIdSelected());
        aViewModel.setAvatarIdSelected(avatarId);
        selectAvatar(aViewModel.getAvatarIdSelected());
    }

    //Adds alpha to image selected.
    private void selectAvatar(int chosenAvatar) {
        switch (chosenAvatar) {
            case 1:
                avatarBinding.imgAvatar1.setAlpha(ResourcesUtils.getFloat(Objects.requireNonNull(this.getContext()), R.dimen.avatar_selected_image_alpha));
                break;
            case 2:
                avatarBinding.imgAvatar2.setAlpha(ResourcesUtils.getFloat(Objects.requireNonNull(this.getContext()), R.dimen.avatar_selected_image_alpha));
                break;
            case 3:
                avatarBinding.imgAvatar3.setAlpha(ResourcesUtils.getFloat(Objects.requireNonNull(this.getContext()), R.dimen.avatar_selected_image_alpha));
                break;
            case 4:
                avatarBinding.imgAvatar4.setAlpha(ResourcesUtils.getFloat(Objects.requireNonNull(this.getContext()), R.dimen.avatar_selected_image_alpha));
                break;
            case 5:
                avatarBinding.imgAvatar5.setAlpha(ResourcesUtils.getFloat(Objects.requireNonNull(this.getContext()), R.dimen.avatar_selected_image_alpha));
                break;
            case 6:
                avatarBinding.imgAvatar6.setAlpha(ResourcesUtils.getFloat(Objects.requireNonNull(this.getContext()), R.dimen.avatar_selected_image_alpha));
                break;
        }
    }

    //Removes alpha from last avatar.
    private void deselectImageView(int lastAvatar) {
        switch (lastAvatar) {
            case 1:
                avatarBinding.imgAvatar1.setAlpha(ResourcesUtils.getFloat(Objects.requireNonNull(this.getContext()), R.dimen.avatar_not_selected_image_alpha));
                break;
            case 2:
                avatarBinding.imgAvatar2.setAlpha(ResourcesUtils.getFloat(Objects.requireNonNull(this.getContext()), R.dimen.avatar_not_selected_image_alpha));
                break;
            case 3:
                avatarBinding.imgAvatar3.setAlpha(ResourcesUtils.getFloat(Objects.requireNonNull(this.getContext()), R.dimen.avatar_not_selected_image_alpha));
                break;
            case 4:
                avatarBinding.imgAvatar4.setAlpha(ResourcesUtils.getFloat(Objects.requireNonNull(this.getContext()), R.dimen.avatar_not_selected_image_alpha));
                break;
            case 5:
                avatarBinding.imgAvatar5.setAlpha(ResourcesUtils.getFloat(Objects.requireNonNull(this.getContext()), R.dimen.avatar_not_selected_image_alpha));
                break;
            case 6:
                avatarBinding.imgAvatar6.setAlpha(ResourcesUtils.getFloat(Objects.requireNonNull(this.getContext()), R.dimen.avatar_not_selected_image_alpha));
                break;
        }
    }

    //Sets clickListeners
    private void initListeners() {
        avatarBinding.lblAvatar1.setOnClickListener(v -> selectedAvatar(1));
        avatarBinding.imgAvatar1.setOnClickListener(v -> selectedAvatar(1));
        avatarBinding.lblAvatar2.setOnClickListener(v -> selectedAvatar(2));
        avatarBinding.imgAvatar2.setOnClickListener(v -> selectedAvatar(2));
        avatarBinding.lblAvatar3.setOnClickListener(v -> selectedAvatar(3));
        avatarBinding.imgAvatar3.setOnClickListener(v -> selectedAvatar(3));
        avatarBinding.lblAvatar4.setOnClickListener(v -> selectedAvatar(4));
        avatarBinding.imgAvatar4.setOnClickListener(v -> selectedAvatar(4));
        avatarBinding.lblAvatar5.setOnClickListener(v -> selectedAvatar(5));
        avatarBinding.imgAvatar5.setOnClickListener(v -> selectedAvatar(5));
        avatarBinding.lblAvatar6.setOnClickListener(v -> selectedAvatar(6));
        avatarBinding.imgAvatar6.setOnClickListener(v -> selectedAvatar(6));
    }

    //sets new avatar in studentViewmodel and returns to StudentFragment.
    private void returnAvatar() {
        aViewModel.setAvatarSelected(Database.getInstance().queryAvatar(aViewModel.getAvatarIdSelected()));
        sViewmodel.getStudent().setAvatar(aViewModel.getAvatarSelected());
        navController.navigateUp();
    }

}
