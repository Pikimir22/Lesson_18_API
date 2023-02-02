package models;

public class LoginResponseModel {

    private String token;

//    @Override
//    public String toString() {
//        return "{\"token=\"" + token + "\"}";
//    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
