package managesys.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import managesys.repository.AbstractRepository;

@Entity(name = "CATEGORY_MASTER")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String name;

    public Category() {
    }

    public Category(String name) {
        super();
        this.name = name;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static Category findById(AbstractRepository repo, int id) {
		return repo.findById(Category.class, id).orElse(null);
	}

	public static List<Category> findAll(AbstractRepository repo) {
		return repo.findAll(Category.class);
	}

	public void save(AbstractRepository repo) {
		repo.save(Category.class, this);
	}

	public static Category findByName(AbstractRepository repo, String name) {
		Specification<Category> spec = StringUtils.isEmpty(name) ? null : (root, query, cb) -> {
			return cb.equal(root.get("name"), name);
		};
		return repo.findOne(Category.class, spec).orElse(null);
	}
}
