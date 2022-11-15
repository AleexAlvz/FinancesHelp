package com.aleexalvz.financeshelp.feature.home.suggestions

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aleexalvz.financeshelp.R
import com.aleexalvz.financeshelp.commons.model.Course
import com.aleexalvz.financeshelp.feature.course.CourseActivity
import com.aleexalvz.financeshelp.feature.home.lateststudies.adapter.LatestStudiesAdapter
import com.aleexalvz.financeshelp.feature.home.suggestions.adapter.SuggestionsAdapter
import com.aleexalvz.financeshelp.feature.home.suggestions.adapter.VerticalMarginItemDecoration
import com.aleexalvz.financeshelp.feature.home.suggestions.model.SuggestionsViewState
import com.aleexalvz.financeshelp.feature.home.suggestions.viewmodel.SuggestionsViewModel
import com.aleexalvz.financeshelp.feature.home.suggestions.viewmodel.SuggestionsViewModelFactory

class SuggestionsFragment: Fragment() {

    private val viewModel: SuggestionsViewModel by lazy {
        ViewModelProvider(
            this,
            SuggestionsViewModelFactory()
        )[SuggestionsViewModel::class.java]
    }

    lateinit var recyclerView: RecyclerView
    lateinit var adapter: SuggestionsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_home_suggestions, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        findViews(view)
    }

    private fun findViews(view: View) {
        recyclerView = view.findViewById(R.id.suggestions_recycler_view)
    }

    override fun onResume() {
        super.onResume()

        configureRecyclerView()
        observeViewStates()

        viewModel.getSuggestedCourses()
    }

    private fun configureRecyclerView() {
        val marginDecoration = VerticalMarginItemDecoration(resources.getDimensionPixelSize(R.dimen.card_margin))
        recyclerView.addItemDecoration(marginDecoration)
        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager
        adapter = SuggestionsAdapter{ course ->
            onCourseClickListener(course)
        }
        recyclerView.adapter = adapter
    }

    private fun observeViewStates() {
        viewModel.viewState.observe(viewLifecycleOwner){
            when (it){
                is SuggestionsViewState.FetchingCourses -> {}
                is SuggestionsViewState.FetchedCourses -> { insertCourseList(it.courseList) }
                is SuggestionsViewState.FailureInFetchCourses -> {}
            }
        }
    }

    private fun onCourseClickListener(course: Course){
        Toast.makeText(requireContext(), "ITEM CLICADO: ${course.title}", Toast.LENGTH_LONG).show()
        startActivity(Intent(requireContext(), CourseActivity::class.java))
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun insertCourseList(courseList: List<Course>) {
        adapter.insertCourseList(courseList)
        adapter.notifyDataSetChanged()
    }
}