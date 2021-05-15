package com.fahrul.academydicoding.ui.reader.list

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.fahrul.academydicoding.R
import com.fahrul.academydicoding.data.ModuleEntity
import com.fahrul.academydicoding.databinding.FragmentModuleListBinding
import com.fahrul.academydicoding.ui.reader.CourseReaderActivity
import com.fahrul.academydicoding.ui.reader.CourseReaderCallback
import com.fahrul.academydicoding.ui.reader.CourseReaderViewModel
import com.fahrul.academydicoding.utils.DataDummy

class ModuleListFragment : Fragment(), MyAdapterClickListener {

    companion object{
        val TAG : String = ModuleListFragment::class.java.simpleName
        fun newInstance() : ModuleListFragment = ModuleListFragment()
    }

    private lateinit var fragmentModuleListBinding: FragmentModuleListBinding
    private lateinit var moduleAdapter: ModuleListAdapter
    private lateinit var courseReaderCallback: CourseReaderCallback
    private lateinit var courseReaderViewModel : CourseReaderViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentModuleListBinding = FragmentModuleListBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return fragmentModuleListBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        courseReaderViewModel = ViewModelProvider(requireActivity(), ViewModelProvider.NewInstanceFactory())[CourseReaderViewModel::class.java]
        moduleAdapter = ModuleListAdapter(this)
        populateRecyclerView(courseReaderViewModel.getModules())
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        courseReaderCallback = context as CourseReaderActivity
    }

    override fun onItemClicked(position: Int, moduleId: String) {
        courseReaderCallback.moveTo(position, moduleId)
        courseReaderViewModel.setSelectedModule(moduleId)
    }

    private fun populateRecyclerView(modules: List<ModuleEntity>) {
        with(fragmentModuleListBinding){
            progressBar.visibility = View.GONE
            moduleAdapter.setModules(modules)
            with(rvModule){
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = moduleAdapter
                val dividerItemDecoration = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
                addItemDecoration(dividerItemDecoration)
            }
        }
    }

}