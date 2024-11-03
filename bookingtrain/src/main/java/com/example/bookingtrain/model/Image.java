package com.example.bookingtrain.model;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(name ="images")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer Id;

    @Column(name = "filePath", nullable = false)
    public String filePath;

}
