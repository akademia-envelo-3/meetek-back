package pl.envelo.meetek.model.request;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.envelo.meetek.model.category.Category;
import pl.envelo.meetek.model.comment.RequestComment;
import pl.envelo.meetek.model.user.StandardUser;

import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "requests_category")
public class CategoryRequest extends Request {

    private String name;

    @OneToOne
    @JoinColumn(name = "comment_id")
    private RequestComment comment;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public CategoryRequest(Long requestId, StandardUser requester, RequestStatus status, String name, RequestComment comment, Category category) {
        super(requestId, requester, status);
        this.name = name;
        this.comment = comment;
        this.category = category;
    }

    @Override
    public String toString() {
        return "CategoryRequest{" +
                "name='" + name + '\'' +
                ", comment=" + comment +
                ", category=" + category +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CategoryRequest that = (CategoryRequest) o;
        return Objects.equals(name, that.name) && Objects.equals(comment, that.comment) && Objects.equals(category, that.category) && super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
