package com.fahrul.academydicoding.ui.bookmark

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.fahrul.academydicoding.R
import com.fahrul.academydicoding.data.CourseEntity
import com.fahrul.academydicoding.databinding.ItemsBookmarkBinding
import com.fahrul.academydicoding.ui.detail.DetailCourseActivity
import java.util.*

class BookmarkAdapter(private val callback: BookmarkFragmentCallback)
    : RecyclerView.Adapter<BookmarkAdapter.BookmarkViewHolder>() {

    private val listCourses = ArrayList<CourseEntity>()

    fun setCourses(courses: List<CourseEntity>?){
        if (courses == null) return
        this.listCourses.clear()
        this.listCourses.addAll(courses)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarkViewHolder {
        val itemsBookmarkBinding = ItemsBookmarkBinding.inflate(LayoutInflater.from(
            parent.context), parent, false)

        return BookmarkViewHolder(itemsBookmarkBinding)
    }

    override fun onBindViewHolder(holder: BookmarkViewHolder, position: Int) {
        val course = listCourses[position]
        holder.bind(course, callback)
    }

    override fun getItemCount(): Int = listCourses.size

    class BookmarkViewHolder(private val binding: ItemsBookmarkBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(course: CourseEntity, callback: BookmarkFragmentCallback) {
            with(binding) {
                tvItemTitle.text = course.title
                tvItemDate.text = itemView.resources.getString(R.string.deadline_date, course.deadline)
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailCourseActivity::class.java)
                    intent.putExtra(DetailCourseActivity.EXTRA_COURSE, course.courseId)
                    itemView.context.startActivity(intent)
                }
                imgShare.setOnClickListener {
                    callback.onShareClick(course)
                }
                Glide.with(itemView.context)
                    .load(course.imagePath)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                    .error(R.drawable.ic_broken_image_black)
                    .into(imgPoster)
            }
        }

    }
}