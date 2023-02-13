package pl.envelo.meetek.domain.request.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.envelo.meetek.domain.category.Category;
import pl.envelo.meetek.domain.comment.model.RequestComment;
import pl.envelo.meetek.domain.user.model.StandardUser;

import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "requests_category")
public class CategoryRequest extends Request {
    @NotNull(message = "Field must not be null")
    @NotBlank(message = "Field must not be blank")
    @Size(min = 2, max = 50, message = "Field must be between {min} and {max} characters")
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
        return super.toString() + " CategoryRequest{" +
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
