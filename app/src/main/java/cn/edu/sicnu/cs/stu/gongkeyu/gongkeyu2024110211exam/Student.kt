package cn.edu.sicnu.cs.stu.gongkeyu.gongkeyu2024110211exam

import java.io.Serializable

/**
 * 学生实体类
 * 属性：学号、姓名、性别、班级
 */
data class Student(
    val stuId: String,
    val name: String,
    val gender: String,
    val className: String
) : Serializable
