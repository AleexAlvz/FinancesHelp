package com.aleexalvz.financeshelp.feature.home

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.aleexalvz.financeshelp.R
import com.aleexalvz.financeshelp.feature.home.lateststudies.LatestStudiesFragment
import com.aleexalvz.financeshelp.feature.home.suggestions.SuggestionsFragment

class HomeActivity : FragmentActivity() {

    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        findViews()
        initViews()
    }

    private fun findViews() {
        toolbar = findViewById(R.id.home_top_app_bar)
    }

    private fun initViews() {
        configureToolBar()
        addFragment(R.id.latest_studies_container, LatestStudiesFragment())
        addFragment(R.id.next_course_suggestions_container, SuggestionsFragment())
    }

    private fun configureToolBar() {
        toolbar.setNavigationOnClickListener {
            Toast.makeText(this, "Abrir Menu", Toast.LENGTH_LONG).show()
        }
        toolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.top_app_bar_search -> {
                    Toast.makeText(this, "Direcionar para tela de busca de cursos", Toast.LENGTH_LONG).show()
                    true
                }
                else -> false
            }
        }
    }

    private fun addFragment(containerView: Int, fragment: Fragment) =
        supportFragmentManager
            .beginTransaction()
            .replace(containerView, fragment)
            .commit()
}