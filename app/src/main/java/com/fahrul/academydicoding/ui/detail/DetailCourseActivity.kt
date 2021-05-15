package com.fahrul.academydicoding.ui.detail

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.fahrul.academydicoding.R
import com.fahrul.academydicoding.data.CourseEntity
import com.fahrul.academydicoding.databinding.ActivityDetailCourseBinding
import com.fahrul.academydicoding.databinding.ContentDetailCourseBinding
import com.fahrul.academydicoding.ui.reader.CourseReaderActivity
import com.fahrul.academydicoding.utils.DataDummy
import kotlinx.android.synthetic.main.activity_detail_course.*

class DetailCourseActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_COURSE = "extra_course"
    }

    private lateinit var detailContentBinding: ContentDetailCourseBinding
    private val detailCourseViewModel: DetailCourseViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_course)
        setSupportActionBar(findViewById(R.id.toolbar))

        val activityDetailCourseBinding = ActivityDetailCourseBinding.inflate(layoutInflater)
        detailContentBinding = activityDetailCourseBinding.detailContent

        setContentView(activityDetailCourseBinding.root)

        setSupportActionBar(activityDetailCourseBinding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val adapter = DetailCourseAdapter()

        val extras = intent.extras
        if (extras != null){
            val courseId = extras.getString(EXTRA_COURSE)
            if (courseId != null){
                detailCourseViewModel.setSelectedCourse(courseId)
                val modules = detailCourseViewModel.getModules()
                adapter.setModules(modules)
                populateCourse(detailCourseViewModel.getCourse())
            }
        }

        with(detailContentBinding.rvModule){
            isNestedScrollingEnabled = false
            layoutManager = LinearLayoutManager(this@DetailCourseActivity)
            setHasFixedSize(true)
            this.adapter = adapter
            val dividerItemDecoration = DividerItemDecoration(
                this@DetailCourseActivity,
                DividerItemDecoration.VERTICAL
            )
            addItemDecoration(dividerItemDecoration)
        }

    }

    private fun populateCourse(course: CourseEntity) {
        detailContentBinding.textTitle.text = course.title
        detailContentBinding.textDescription.text = course.description
        detailContentBinding.textDate.text = resources.getString(R.string.deadline_date, course.deadline)

        Glide.with(this)
            .load(course.imagePath)
            .transform(RoundedCorners(20))
            .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
            .error(R.drawable.ic_broken_image_black)
            .into(detailContentBinding.imagePoster)

        detailContentBinding.btnStart.setOnClickListener {
            val intent = Intent(this, CourseReaderActivity::class.java)
            intent.putExtra(CourseReaderActivity.EXTRA_COURSE_ID, course.courseId)
            startActivity(intent)
        }
    }
}