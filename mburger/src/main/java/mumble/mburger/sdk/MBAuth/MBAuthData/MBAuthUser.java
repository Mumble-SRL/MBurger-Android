package mumble.mburger.sdk.MBAuth.MBAuthData;

import java.io.Serializable;
import java.util.ArrayList;

import mumble.mburger.sdk.MBPay.MBPayData.MBStripeSubscription;

public class MBAuthUser implements Serializable{

    private long id;
    private String name, surname, email, phone, image, gender, data, auth_mode;
    private ArrayList<MBStripeSubscription> subscriptions;
    private ArrayList<MBContractsAccepted> contracts;

    public MBAuthUser(long id, String name, String surname,
                      String email, String phone, String image,
                      String gender, String data, String auth_mode,
                      ArrayList<MBStripeSubscription> subscriptions,
                      ArrayList<MBContractsAccepted> contracts) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
        this.image = image;
        this.gender = gender;
        this.data = data;
        this.auth_mode = auth_mode;
        this.subscriptions = subscriptions;
        this.contracts = contracts;
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

    public ArrayList<MBStripeSubscription> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(ArrayList<MBStripeSubscription> subscriptions) {
        this.subscriptions = subscriptions;
    }

    public ArrayList<MBContractsAccepted> getContracts() {
        return contracts;
    }

    public void setContracts(ArrayList<MBContractsAccepted> contracts) {
        this.contracts = contracts;
    }
}
