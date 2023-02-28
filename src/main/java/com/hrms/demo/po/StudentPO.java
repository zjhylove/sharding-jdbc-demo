package com.hrms.demo.po;

/**
 * 学生信息
 *
 * @author zhengjun
 */
public class StudentPO extends PersonPO {

    /**
     * 学校名称
     */
    private String schoolName;

    /**
     * 班级名称
     */
    private String className;

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
