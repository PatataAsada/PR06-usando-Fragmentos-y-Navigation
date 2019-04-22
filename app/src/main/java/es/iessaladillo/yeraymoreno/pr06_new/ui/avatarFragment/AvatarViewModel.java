package es.iessaladillo.yeraymoreno.pr06_new.ui.avatarFragment;

import androidx.lifecycle.ViewModel;

public class AvatarViewModel extends ViewModel {
    private int avatarSelected;

    public AvatarViewModel(int avatarSelected) {
        this.avatarSelected = avatarSelected;
    }

    public void setAvatarSelected(int avatarSelected) {
        this.avatarSelected = avatarSelected;
    }

    public int getAvatarSelected() {
        return avatarSelected;
    }
}
