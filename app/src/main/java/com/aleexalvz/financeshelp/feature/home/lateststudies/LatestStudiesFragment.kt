package com.aleexalvz.financeshelp.feature.home.lateststudies

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Orientation
import com.aleexalvz.financeshelp.R
import com.aleexalvz.financeshelp.commons.model.Course
import com.aleexalvz.financeshelp.commons.model.LatestStudiesViewState
import com.aleexalvz.financeshelp.feature.home.lateststudies.adapter.LatestStudiesAdapter
import com.aleexalvz.financeshelp.feature.home.lateststudies.adapter.MarginItemDecoration
import com.aleexalvz.financeshelp.feature.home.viewmodel.LatestStudiesViewModel
import com.aleexalvz.financeshelp.feature.home.viewmodel.LatestStudiesViewModelFactory


class LatestStudiesFragment : Fragment() {

    private val latestStudiesViewModel: LatestStudiesViewModel by lazy {
        ViewModelProvider(
            ViewModelStore(),
            LatestStudiesViewModelFactory()
        )[LatestStudiesViewModel::class.java]
    }

    lateinit var recyclerView: RecyclerView
    lateinit var adapter: LatestStudiesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = layoutInflater.inflate(R.layout.fragment_latest_studies, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)
        observeViewStates()

        latestStudiesViewModel.getLatestStudies()
    }

    private fun initViews(view: View) {
        recyclerView = view.findViewById(R.id.latest_studies_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        LinearSnapHelper().attachToRecyclerView(recyclerView)
        val itemDecoration = MarginItemDecoration(resources.getDimensionPixelSize(R.dimen.card_margin))
        recyclerView.addItemDecoration(itemDecoration)
        adapter = LatestStudiesAdapter()
        recyclerView.adapter = adapter
    }

    private fun observeViewStates() {
        latestStudiesViewModel.viewState.observe(viewLifecycleOwner){
            when (it){
                is LatestStudiesViewState.FailureInFetchCourses -> {}
                is LatestStudiesViewState.FetchingCourses -> {}
                is LatestStudiesViewState.FetchedCourses -> {configureRecyclerView(it.courseList)}
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun configureRecyclerView(courseList: List<Course>) {
        adapter.insertCourseList(courseList)
        adapter.notifyDataSetChanged()
    }
}