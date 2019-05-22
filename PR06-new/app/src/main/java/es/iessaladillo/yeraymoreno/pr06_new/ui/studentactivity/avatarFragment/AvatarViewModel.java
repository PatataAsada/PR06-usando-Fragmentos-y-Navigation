package es.iessaladillo.yeraymoreno.pr06_new.ui.mainactivity.avatarFragment;

import androidx.lifecycle.ViewModel;

import es.iessaladillo.yeraymoreno.pr06_new.data.Database;
import es.iessaladillo.yeraymoreno.pr06_new.data.model.Avatar;

public class AvatarViewModel extends ViewModel {
    private Avatar avatarSelected;
    private int avatarIdSelected;

    public AvatarViewModel() {
        this.avatarSelected = Database.getInstance().getDefaultAvatar();
    }

    public void setAvatarSelected(Avatar avatarSelected) {
        this.avatarSelected = avatarSelected;
    }

    public Avatar getAvatarSelected() {
        return avatarSelected;
    }

    public int getAvatarIdSelected() {
        return avatarIdSelected;
    }

    public void setAvatarIdSelected(int avatarIdSelected) {
        this.avatarIdSelected = avatarIdSelected;
    }
}
