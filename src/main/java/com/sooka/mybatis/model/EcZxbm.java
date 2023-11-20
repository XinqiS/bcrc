package com.sooka.mybatis.model;

import com.sooka.common.annotation.ExcelField;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author limeng
 * @date 2021年12月17日 13:45
 */
@Table(name = "tb_ec_zxbm")
public class EcZxbm implements Serializable {
    @ExcelField("编号")
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private String id;

    /**
     * 企业名称
     */
    @ExcelField("公司名称")
    private String qy_qymc;

    /**
     * 姓名
     */
    @ExcelField("姓名")
    private String name;

    /**
     * 性别
     */
    @ExcelField("性别")
    private String sex;

    /**
     * 民族
     */
    @ExcelField("民族")
    private String nation;

    /**
     * 出生年月
     */
    @ExcelField("出生年月")
    private String birthday;

    /**
     * 联系电话
     */
    @ExcelField("联系电话")
    private String tel;

    /**
     * 身份证号
     */
    @ExcelField("身份证号")
    private String idNumber;

    /**
     * 学历
     */
    @ExcelField("学历")
    private String education;

    /**
     * 毕业时间
     */
    @ExcelField("毕业时间")
    private String graduationTime;

    /**
     * 毕业院校
     */
    @ExcelField("毕业院校")
    private String wentToSchool;

    /**
     * 文章ID
     */
    private String contentId;

    /**
     * 用户ID
     */
    private String zhId;




    public String getQy_qymc() {
        return qy_qymc;
    }

    public void setQy_qymc(String qy_qymc) {
        this.qy_qymc = qy_qymc;
    }

    public String getZhId() {
        return zhId;
    }

    public void setZhId(String zhId) {
        this.zhId = zhId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getGraduationTime() {
        return graduationTime;
    }

    public void setGraduationTime(String graduationTime) {
        this.graduationTime = graduationTime;
    }

    public String getWentToSchool() {
        return wentToSchool;
    }

    public void setWentToSchool(String wentToSchool) {
        this.wentToSchool = wentToSchool;
    }

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

}
