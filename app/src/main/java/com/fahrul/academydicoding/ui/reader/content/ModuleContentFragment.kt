package com.fahrul.academydicoding.ui.reader.content

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.fahrul.academydicoding.R
import com.fahrul.academydicoding.data.ContentEntity
import com.fahrul.academydicoding.data.ModuleEntity
import com.fahrul.academydicoding.databinding.FragmentModuleContentBinding
import com.fahrul.academydicoding.ui.reader.CourseReaderViewModel

class ModuleContentFragment : Fragment() {

    companion object {
        val TAG : String = ModuleContentFragment::class.java.simpleName
        fun newInstance(): ModuleContentFragment = ModuleContentFragment()
    }

    private lateinit var fragmentModuleContentBinding: FragmentModuleContentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentModuleContentBinding = FragmentModuleContentBinding.inflate(
            inflater,
            container,
            false
        )
        // Inflate the layout for this fragment
        return fragmentModuleContentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null){
            val courseReaderViewModel = ViewModelProvider(requireActivity(), ViewModelProvider.NewInstanceFactory())[CourseReaderViewModel::class.java]
            val module = courseReaderViewModel.getSelectedModule()
            populateWebView(module)
        }
    }

    private fun populateWebView(module: ModuleEntity) {
        fragmentModuleContentBinding.webView.loadData(module.contentEntity?.content ?: "", "text/html", "UTF-8")
    }
}