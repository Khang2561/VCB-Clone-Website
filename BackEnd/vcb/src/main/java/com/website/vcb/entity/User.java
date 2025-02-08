package com.website.vcb.entity;

import com.website.vcb.enums.Role;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;


@Entity
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID) // Chuỗi mã được random ngẫu nhiên và không được lặp
    String id;
    String username;
    String password;
    String firstName;
    String lastName;
    LocalDate dob;
    String roles;
}
