package com.fahrul.academydicoding.ui.bookmark

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ShareCompat
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.fahrul.academydicoding.R
import com.fahrul.academydicoding.data.CourseEntity
import com.fahrul.academydicoding.databinding.FragmentBookmarkBinding
import com.fahrul.academydicoding.utils.DataDummy

class BookmarkFragment : Fragment(), BookmarkFragmentCallback {

    private lateinit var fragmentBookmarkBinding : FragmentBookmarkBinding
    private val bookmarkViewHolder : BookmarkViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentBookmarkBinding = FragmentBookmarkBinding.inflate(
            inflater,
            container,
            false
        )
        return fragmentBookmarkBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null){
            val courses = bookmarkViewHolder.getBookmarks()
            val adapter = BookmarkAdapter(this)
            adapter.setCourses(courses)

            with(fragmentBookmarkBinding.rvBookmark){
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                this.adapter = adapter
            }
        }
    }

    override fun onShareClick(course: CourseEntity) {
        if (activity != null){
            val mimeType = "text/plain"
            ShareCompat.IntentBuilder
                .from(requireActivity())
                .setType(mimeType)
                .setChooserTitle("Bagikan aplikasi ini sekarang.")
                .setText(resources.getString(R.string.share_text, course.title))
                .startChooser()
        }
    }

}