package es.iessaladillo.yeraymoreno.pr06_new.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import es.iessaladillo.yeraymoreno.pr06_new.data.Database;

@SuppressWarnings("ALL")
@Entity(tableName = "student")
public class Student implements Parcelable {


    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private long id;

    @ColumnInfo(name = "avatar")
    private int avatarId;
    @Ignore
    private Avatar avatar;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "email")
    private String email;
    @ColumnInfo(name = "phonenumber")
    private int phonenumber;
    @ColumnInfo(name = "address")
    private String address;
    @ColumnInfo(name = "web")
    private String web;

    public Student(long id, int avatarId, String name, String email, int phonenumber, String address, String web) {
        this.id = id;
        this.avatarId = avatarId-1;
        this.avatar = Database.getInstance().queryAvatars().get(this.avatarId);
        this.name = name;
        this.email = email;
        this.phonenumber = phonenumber;
        this.address = address;
        this.web = web;
    }

    @Ignore
    protected Student(Parcel in) {
        id = in.readLong();
        avatar = in.readParcelable(getClass().getClassLoader());
        name = in.readString();
        email = in.readString();
        phonenumber = in.readInt();
        address = in.readString();
        web = in.readString();
    }

    public static final Creator<Student> CREATOR = new Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };

    @Ignore
    public Student() {
    }

    @Ignore
    public Student(int avatarResId, String name, String email, int phonenumber, String address, String web) {
        this.avatarId = avatarResId;
        this.avatar = Database.getInstance().queryAvatar(avatarResId);
        this.name = name;
        this.email = email;
        this.phonenumber = phonenumber;
        this.address = address;
        this.web = web;
    }

    public int getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(int phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }

    public int getAvatarId() {
        return avatarId;
    }

    public void setAvatarId(int avatarId) {
        avatar = Database.getInstance().queryAvatar(avatarId);
        this.avatarId = avatarId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeParcelable(avatar, flags);
        dest.writeString(name);
        dest.writeString(email);
        dest.writeInt(phonenumber);
        dest.writeString(address);
        dest.writeString(web);
    }
}
