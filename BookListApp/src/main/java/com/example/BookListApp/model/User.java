package com.example.BookListApp.model;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "username is mandatory")
    private String username;

    @NotEmpty(message = "password is mandatory")
    @Size(min = 6, max = 100, message
            = "Password must be between 6 and 100 characters")
    private String password;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "user", cascade = CascadeType.ALL)
    private List<ReadingList> readingLists;

}
