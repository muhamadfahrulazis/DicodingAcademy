package com.fahrul.academydicoding.ui.bookmark

import com.fahrul.academydicoding.data.CourseEntity

interface BookmarkFragmentCallback {
    fun onShareClick(course: CourseEntity)
}
