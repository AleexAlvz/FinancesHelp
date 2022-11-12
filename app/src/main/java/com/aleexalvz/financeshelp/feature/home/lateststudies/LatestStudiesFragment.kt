package com.aleexalvz.financeshelp.feature.home.lateststudies

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.viewpager2.widget.ViewPager2
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

    lateinit var viewPager: ViewPager2
    lateinit var adapter: LatestStudiesAdapter
    lateinit var dots: LinearLayout
    lateinit var carouselArrowLeft: ImageView
    lateinit var carouselArrowRight: ImageView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = layoutInflater.inflate(R.layout.fragment_latest_studies, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
    }

    override fun onResume() {
        super.onResume()

        setListeners()
        configureViewPager()
        observeViewStates()

        latestStudiesViewModel.getLatestStudies()
    }

    private fun setListeners() {
        carouselArrowLeft.setOnClickListener {
            if (viewPager.currentItem > 0) {
                viewPager.setCurrentItem(viewPager.currentItem - 1, true)
            }
        }
        carouselArrowRight.setOnClickListener {
            val lastIndex = (viewPager.adapter?.itemCount?.minus(1) ?: 0)
            if (viewPager.currentItem < lastIndex) {
                viewPager.setCurrentItem(viewPager.currentItem + 1, true)
            }
        }
    }

    private fun configureViewPager() {
        val itemDecoration =
            MarginItemDecoration(resources.getDimensionPixelSize(R.dimen.card_margin))
        viewPager.addItemDecoration(itemDecoration)
        adapter = LatestStudiesAdapter()

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                configureCarouselDots(adapter.itemCount, position)
            }
        })

        viewPager.adapter = adapter
    }

    private fun configureCarouselDots(size: Int, position: Int = 0) {
        dots.removeAllViews()
        Array(size) {
            val textView = TextView(requireContext()).apply {
                text = getText(R.string.dotted)
                if (it == position) {
                    setTextColor(ContextCompat.getColor(requireContext(), R.color.button_blue))
                    textSize = 40f
                } else {
                    setTextColor(ContextCompat.getColor(requireContext(), R.color.dark_gray))
                    textSize = 32f
                }
            }
            dots.addView(textView)
        }
    }

    private fun initViews(view: View) {
        viewPager = view.findViewById(R.id.latest_studies_view_pager)
        dots = view.findViewById(R.id.carousel_dots_list)
        carouselArrowLeft = view.findViewById(R.id.carousel_arrow_left)
        carouselArrowRight = view.findViewById(R.id.carousel_arrow_right)
    }

    private fun observeViewStates() {
        latestStudiesViewModel.viewState.observe(viewLifecycleOwner) {
            when (it) {
                is LatestStudiesViewState.FailureInFetchCourses -> {}
                is LatestStudiesViewState.FetchingCourses -> {}
                is LatestStudiesViewState.FetchedCourses -> {
                    configureRecyclerView(it.courseList)
                }
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun configureRecyclerView(courseList: List<Course>) {
        adapter.insertCourseList(courseList)
        adapter.notifyDataSetChanged()
        configureCarouselDots(courseList.size)
    }
}