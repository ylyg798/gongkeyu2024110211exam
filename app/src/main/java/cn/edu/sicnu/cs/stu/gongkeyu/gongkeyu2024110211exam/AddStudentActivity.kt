package cn.edu.sicnu.cs.stu.gongkeyu.gongkeyu2024110211exam

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

/**
 * 第 2 个 Activity：新增学生交互界面
 * 点击“确定”校验输入并把学生数据回传给第 1 个 Activity，点击“取消”放弃新增
 */
class AddStudentActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_STUDENT = "extra_student"
        const val EXTRA_STU_ID = "extra_stu_id"
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_GENDER = "extra_gender"
        const val EXTRA_CLASS = "extra_class"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_student)

        val root = findViewById<android.view.View>(R.id.main_add)
        ViewCompat.setOnApplyWindowInsetsListener(root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val toolbar = findViewById<Toolbar>(R.id.toolbar_add)
        setSupportActionBar(toolbar)
        supportActionBar?.title = getString(R.string.add_title)

        val etId = findViewById<EditText>(R.id.et_stu_id)
        val etName = findViewById<EditText>(R.id.et_name)
        val rgGender = findViewById<RadioGroup>(R.id.rg_gender)
        val etClass = findViewById<EditText>(R.id.et_class)

        findViewById<Button>(R.id.btn_ok).setOnClickListener {
            val stuId = etId.text.toString().trim()
            val name = etName.text.toString().trim()
            val className = etClass.text.toString().trim()

            if (stuId.isEmpty() || name.isEmpty() || className.isEmpty()) {
                Toast.makeText(this, R.string.tip_empty, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val gender = when (rgGender.checkedRadioButtonId) {
                R.id.rb_male -> getString(R.string.male)
                R.id.rb_female -> getString(R.string.female)
                else -> getString(R.string.male)
            }

            val data = Intent().apply {
                putExtra(EXTRA_STU_ID, stuId)
                putExtra(EXTRA_NAME, name)
                putExtra(EXTRA_GENDER, gender)
                putExtra(EXTRA_CLASS, className)
            }
            setResult(RESULT_OK, data)
            finish()
        }

        findViewById<Button>(R.id.btn_cancel).setOnClickListener {
            setResult(RESULT_CANCELED)
            finish()
        }
    }
}
