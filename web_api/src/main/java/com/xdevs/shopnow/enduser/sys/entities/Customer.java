package com.xdevs.shopnow.enduser.sys.entities;

import lombok.Data;
import lombok.ToString;
import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;

@Entity
@Table(name = "customer", schema = "shopnow", catalog = "")
@Data
@ToString
public class Customer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Basic
    @Column(name = "user_name")
    private String userName;

    @Basic
    @Column(name = "password")
    private String password;

    @Basic
    @Column(name = "full_name")
    private String fullName;

    @Basic
    @Column(name = "email")
    private String email;

    @Basic
    @Column(name = "phone")
    private String phone;

    @Basic
    @Column(name = "address")
    private String address;

    @Basic
    @Column(name = "avatar")
    private String avatar;

    @Basic
    @Column(name = "birthday")
    private Time birthday;

    @Basic
    @Column(name = "gender")//1-male, 2-female
    private String gender;

    @Basic
    @Column(name = "status")//0-not active, 1-active, 2-locked
    private Integer status;

    @Basic
    @Column(name = "firebase_fcm_key")
    private String firebaseFcmKey;

    @Basic
    @Column(name = "last_otp")
    private String lastOtp;

    @Basic
    @Column(name = "otp_expire_time")
    private Time otpExpireTime;

    @Basic
    @Column(name = "create_time")
    private Time createTime;

    @Basic
    @Column(name = "create_by")
    private Integer createBy;

    @Basic
    @Column(name = "update_time")
    private Time updateTime;

    @Basic
    @Column(name = "update_by")
    private Integer updateBy;

    @Basic
    @Column(name = "is_deleted") //0-no, 1-yes
    private Integer isDeleted;

}
