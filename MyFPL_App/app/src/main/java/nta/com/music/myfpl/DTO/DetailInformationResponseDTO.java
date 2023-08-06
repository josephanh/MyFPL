package nta.com.music.myfpl.DTO;

import java.util.List;

public class DetailInformationResponseDTO {
    private boolean status;
    private ListInformationResponseDTO.InformationResponseDTO posts;


    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public ListInformationResponseDTO.InformationResponseDTO getPosts() {
        return posts;
    }

    public void setPosts(ListInformationResponseDTO.InformationResponseDTO posts) {
        this.posts = posts;
    }

    public DetailInformationResponseDTO() {
    }

    public DetailInformationResponseDTO(boolean status, ListInformationResponseDTO.InformationResponseDTO posts) {
        this.status = status;
        this.posts = posts;
    }
}
