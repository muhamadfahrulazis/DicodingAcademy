package com.fahrul.academydicoding.ui.bookmark

import androidx.lifecycle.ViewModel
import com.fahrul.academydicoding.data.CourseEntity
import com.fahrul.academydicoding.utils.DataDummy

class BookmarkViewModel : ViewModel() {
    fun getBookmarks() : List<CourseEntity> = DataDummy.generateDummyCourses()
}