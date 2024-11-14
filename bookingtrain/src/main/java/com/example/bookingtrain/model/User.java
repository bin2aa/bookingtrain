package com.example.bookingtrain.model;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.persistence.*;
@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private Integer roleId;

    @ManyToOne
    @JoinColumn(name = "roleId", insertable = false, updatable = false)
    private Role role;

    @Transient // Thuộc tính này không cần ánh xạ với cột trong cơ sở dữ liệu
    private String repeatPassword; // Thêm thuộc tính này để xác nhận mật khẩu
}
