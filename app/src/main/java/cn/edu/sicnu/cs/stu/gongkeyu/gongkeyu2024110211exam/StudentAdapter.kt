package cn.edu.sicnu.cs.stu.gongkeyu.gongkeyu2024110211exam

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

/**
 * 学生列表适配器：把 Student 集合渲染到 ListView 的每一行
 */
class StudentAdapter(
    private val context: Context,
    private val students: List<Student>
) : BaseAdapter() {

    override fun getCount(): Int = students.size

    override fun getItem(position: Int): Student = students[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = convertView
            ?: LayoutInflater.from(context).inflate(R.layout.item_student, parent, false)

        val student = students[position]
        val tvName = view.findViewById<TextView>(R.id.tv_name)
        val tvInfo = view.findViewById<TextView>(R.id.tv_info)

        tvName.text = student.name
        tvInfo.text = context.getString(
            R.string.student_info,
            student.stuId,
            student.gender,
            student.className
        )
        return view
    }
}
