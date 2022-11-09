package com.aleexalvz.financeshelp.feature.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentContainerView
import com.aleexalvz.financeshelp.R
import com.aleexalvz.financeshelp.feature.home.lateststudies.LatestStudiesFragment

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
        addFragment(R.id.latest_studies_container, LatestStudiesFragment() )
    }

    private fun addFragment(containerView: Int, fragment: Fragment) =
        supportFragmentManager
            .beginTransaction()
            .add(containerView, fragment)
            .commit()
}