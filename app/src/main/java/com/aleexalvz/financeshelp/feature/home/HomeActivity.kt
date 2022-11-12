package com.aleexalvz.financeshelp.feature.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.aleexalvz.financeshelp.R
import com.aleexalvz.financeshelp.feature.appbar.AppBarFragment
import com.aleexalvz.financeshelp.feature.home.lateststudies.LatestStudiesFragment
import com.aleexalvz.financeshelp.feature.home.suggestions.SuggestionsFragment

class HomeActivity: FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        findViews()
        initViews()
    }

    private fun findViews() {

    }

    private fun initViews() {
        addFragment(R.id.home_app_bar_container, AppBarFragment())
        addFragment(R.id.latest_studies_container, LatestStudiesFragment())
        addFragment(R.id.next_course_suggestions_container, SuggestionsFragment())
    }

    private fun addFragment(containerView: Int, fragment: Fragment) =
        supportFragmentManager
            .beginTransaction()
            .replace(containerView, fragment)
            .commit()
}