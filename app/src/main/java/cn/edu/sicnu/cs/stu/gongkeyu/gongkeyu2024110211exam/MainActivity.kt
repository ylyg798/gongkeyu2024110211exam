package cn.edu.sicnu.cs.stu.gongkeyu.gongkeyu2024110211exam

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

/**
 * 第 1 个 Activity：以列表形式显示学生信息
 * 点击“添加学生”启动第 2 个 Activity，返回后获取学生数据并刷新列表
 */
class MainActivity : AppCompatActivity() {

    private val studentList = mutableListOf<Student>()
    private lateinit var adapter: StudentAdapter
    private lateinit var listView: ListView

    // 接收第 2 个 Activity 返回的学生数据
    private val addLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data = result.data ?: return@registerForActivityResult
            val student = Student(
                data.getStringExtra(AddStudentActivity.EXTRA_STU_ID).orEmpty(),
                data.getStringExtra(AddStudentActivity.EXTRA_NAME).orEmpty(),
                data.getStringExtra(AddStudentActivity.EXTRA_GENDER).orEmpty(),
                data.getStringExtra(AddStudentActivity.EXTRA_CLASS).orEmpty()
            )
            if (student.stuId.isNotEmpty()) {
                studentList.add(student)
                adapter.notifyDataSetChanged()
                refreshCount()
                listView.smoothScrollToPosition(studentList.size - 1)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val root = findViewById<View>(R.id.main)
        ViewCompat.setOnApplyWindowInsetsListener(root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 标题：姓名 + 学号
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = getString(R.string.main_title)

        initData()
        listView = findViewById(R.id.lv_students)
        adapter = StudentAdapter(this, studentList)
        listView.adapter = adapter
        refreshCount()

        findViewById<Button>(R.id.btn_add).setOnClickListener {
            addLauncher.launch(Intent(this, AddStudentActivity::class.java))
        }
    }

    /** 更新列表上方的条数提示 */
    private fun refreshCount() {
        findViewById<android.widget.TextView>(R.id.tv_count).text =
            getString(R.string.student_count, studentList.size)
    }

    /** 初始化几条示例数据，便于打开应用即可看到列表效果 */
    private fun initData() {
        studentList.add(Student("2024110101", "李明", "男", "计科2401"))
        studentList.add(Student("2024110102", "王雨欣", "女", "计科2402"))
        studentList.add(Student("2024110211", "龚科宇", "男", "计科2403"))
    }
}
