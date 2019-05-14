package com.example.xiaoqian1.user.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Setter
@Getter
@ToString
@Entity
@Table(name = "person_information")
public class PersonInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Integer id;
    private String userID;
    private String userName;
    private String nickName;
    private String phoneNumber;
    private String eMail;
    private String iconPath;
}
