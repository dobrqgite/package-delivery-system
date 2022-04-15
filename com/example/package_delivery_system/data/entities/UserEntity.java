package com.example.package_delivery_system.data.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class UserEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
//    @NotEmpty
    private String username;

    @Column(name = "full_name")
//    @NotEmpty
//    @Pattern(regexp="^[a-zA-Z]",message="Your name should only contain letters.")
    private String fullName;

    @Column(name = "phone_number")
//    @Pattern(regexp="^[0-9]",message="Your phone number can only contain numbers and should be at least 8 characters long.")
//    @Size(min = 8)
    private String phone;

    @Column(name = "UCN")
//    @NotEmpty
//    @Pattern(regexp= "^[0-9]", message = "Your UCN should be exactly 10 characters long and should only contain numbers.")
    private String UCN;

    @Column(name = "email", nullable = false)
//    @NotEmpty
//    @Email
    private String email;


    //TODO: Change min password chars to 6. Only 3 for testing purposes!
    @Column(name = "password", nullable = false)
//    @NotEmpty
//    @Size(min = 3, message = "Your password should be at least 3 characters long.")
    private String password;

    @OneToOne
    private Address address;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @ManyToMany
    @JoinTable(
            name = "users_vehicles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "vehicle_id"))
    private Set<Vehicle> vehicles;

    @OneToMany(mappedBy = "receiver")
    private List<Package> packagesToReceive;

    @OneToMany(mappedBy = "sender")
    private List<Package> packagesToSend;

    @Column(name = "is_active", columnDefinition = "tinyint(1)")
    private boolean isActive = true;

    public UserEntity(Long id, String username, String fullName,
                      String phone, String UCN, String email,
                      String password, Address address, Set<Role> roles,
                      Set<Vehicle> vehicles, List<Package> packagesToReceive,
                      List<Package> packagesToSend) {
        this.id = id;
        this.username = username;
        this.fullName = fullName;
        this.phone = phone;
        this.UCN = UCN;
        this.email = email;
        this.password = password;
        this.address = address;
        this.roles = roles;
        this.vehicles = vehicles;
        this.packagesToReceive = packagesToReceive;
        this.packagesToSend = packagesToSend;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
