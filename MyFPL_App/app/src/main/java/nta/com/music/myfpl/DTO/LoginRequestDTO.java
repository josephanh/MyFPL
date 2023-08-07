package nta.com.music.myfpl.DTO;

public class LoginRequestDTO {
    private String email, name, avatar;

    public LoginRequestDTO() {
    }

    public LoginRequestDTO(String email, String name, String avatar) {
        this.email = email;
        this.name = name;
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
