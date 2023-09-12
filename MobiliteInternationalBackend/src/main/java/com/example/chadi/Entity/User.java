package com.example.chadi.Entity;

import com.example.chadi.token.Token;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@ToString
@Data
@Builder
@Table(name = "user")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUser;

    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String email;
    @NotBlank
    @Size(min = 4,max = 8)
    private String password;
//    private Integer phone;

    @OneToMany( cascade = CascadeType.ALL, mappedBy = "userCandidature")
    @JsonIgnore
    private Set<Candidature> candidatures;

    private Boolean locked = false;
    private Boolean enabled = false;

    @NotBlank
    @Enumerated(EnumType.STRING)
    private  Role role;




    @OneToMany(mappedBy = "UserAuth" ,fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Autority> autority;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Token> tokens;






    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<GrantedAuthority> authorities = new ArrayList<>() ;
        for (Autority authority : autority) {
            if (authority !=null)
                authorities.add(new SimpleGrantedAuthority(role.name()));
            else
                System.out.println("----- U have no AUtority Bro ----");
        }
        return authorities;
 }

    public User(String firstname,
                String lastname,
                String email,
                String password,
                Role role
                ){
        this.firstName = firstname;
        this.lastName = lastname;
        this.email = email;
        this.password = password;
        this.role = role;


    }
    @Override
    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @Override
    @JsonIgnore
    public String getUsername() {
        return email;
    }


    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return enabled;
    }


}
