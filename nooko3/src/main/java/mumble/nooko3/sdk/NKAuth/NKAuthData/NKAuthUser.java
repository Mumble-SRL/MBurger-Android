package mumble.nooko3.sdk.NKAuth.NKAuthData;

import java.io.Serializable;

public class NKAuthUser implements Serializable{

    private long id;
    private String name, surname, email, phone, image, gender, data, auth_mode;

    public NKAuthUser(long id, String name, String surname,
                      String email, String phone, String image,
                      String gender, String data, String auth_mode) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
        this.image = image;
        this.gender = gender;
        this.data = data;
        this.auth_mode = auth_mode;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getAuth_mode() {
        return auth_mode;
    }

    public void setAuth_mode(String auth_mode) {
        this.auth_mode = auth_mode;
    }
}
