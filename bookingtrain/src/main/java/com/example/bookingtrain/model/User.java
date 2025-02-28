package com.example.bookingtrain.model;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

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

    @Column(name = "oauth_provider")
    private String oauthProvider; // "GOOGLE" or "FACEBOOK"

    @Column(name = "oauth_id")
    private String oauthId; // ID từ OAuth provider

    @ManyToOne
    @JoinColumn(name = "roleId", insertable = false, updatable = false)
    private Role role;

    @Transient // Thuộc tính này không cần ánh xạ với cột trong cơ sở dữ liệu
    private String repeatPassword; // Thêm thuộc tính này để xác nhận mật khẩu

}
