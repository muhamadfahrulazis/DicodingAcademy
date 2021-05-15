package com.fahrul.academydicoding.ui.academy

import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class AcademyViewModelTest {
    private lateinit var viewModel: AcademyViewModel

    @Before
    fun setUp() {
        viewModel = AcademyViewModel()
    }

    @Test
    fun getCourses() {
        val courseEntities = viewModel.getCourses()
        assertNotNull(courseEntities)
        assertEquals(5, courseEntities.size)
    }
}