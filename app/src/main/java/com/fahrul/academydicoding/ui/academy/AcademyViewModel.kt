package com.fahrul.academydicoding.ui.academy

import androidx.lifecycle.ViewModel
import com.fahrul.academydicoding.data.CourseEntity
import com.fahrul.academydicoding.utils.DataDummy

class AcademyViewModel : ViewModel() {
    fun getCourses(): List<CourseEntity> = DataDummy.generateDummyCourses()
}