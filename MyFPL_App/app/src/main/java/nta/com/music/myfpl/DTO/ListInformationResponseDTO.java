package nta.com.music.myfpl.DTO;

import java.io.Serializable;
import java.util.List;

public class ListInformationResponseDTO implements Serializable{

    private boolean status;
    private List<ListInformationResponseDTO.InformationResponseDTO> posts;

    public ListInformationResponseDTO() {
    }

    public ListInformationResponseDTO(boolean status, List<ListInformationResponseDTO.InformationResponseDTO> posts) {
        this.status = status;
        this.posts = posts;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<InformationResponseDTO> getPosts() {
        return posts;
    }

    public void setPosts(List<InformationResponseDTO> posts) {
        this.posts = posts;
    }

    public static class InformationResponseDTO implements Serializable {
        private int id;
        private String title, content, department, created_at, author, type;

        public InformationResponseDTO() {

        }

        public InformationResponseDTO(int id, String title, String content, String department, String created_at, String author, String type) {
            this.id = id;
            this.title = title;
            this.content = content;
            this.department = department;
            this.created_at = created_at;
            this.author = author;
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getDepartment() {
            return department;
        }

        public void setDepartment(String department) {
            this.department = department;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
