package managesys.model;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

import managesys.repository.AbstractRepository;

@Entity(name = "ACCOUNT_MASTER")
public class Account {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int id;

   @NotNull
   private String name;

   @NotNull
   @JsonIgnore
   private String password;

   @OneToMany(fetch = FetchType.EAGER)
   private Collection<AccountAuthority> authorities;

   public Account() {}

   public Account(String name, String password, Collection<AccountAuthority> authorities) {
       this.name = name;
       this.password = password;
       this.authorities = authorities;
   }

   public int getId() {
       return id;
   }

   public Collection<AccountAuthority> getAuthorities() {
       return authorities;
   }

   public void setAuthorities(Collection<AccountAuthority> authorities) {
       this.authorities = authorities;
   }

   public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
         return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public static Account findByUsername(AbstractRepository repo, String name) {
    	Specification<Account> spec = StringUtils.isEmpty(name) ? null : (root, query, cb) -> {
			return cb.equal(root.get("name"), name);
		};
		return repo.findOne(Account.class, spec).orElse(null);
    }

    public void save(AbstractRepository repo) {
    	repo.save(Account.class, this);
    }

}
