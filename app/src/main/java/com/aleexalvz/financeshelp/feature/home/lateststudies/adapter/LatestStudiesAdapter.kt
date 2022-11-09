package com.aleexalvz.financeshelp.feature.home.lateststudies.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.aleexalvz.financeshelp.R
import com.aleexalvz.financeshelp.commons.extension.setGone
import com.aleexalvz.financeshelp.commons.extension.setInvisible
import com.aleexalvz.financeshelp.commons.extension.setVisible
import com.aleexalvz.financeshelp.commons.model.Course

class LatestStudiesAdapter : RecyclerView.Adapter<LatestStudiesAdapter.LatestStudiesAdapterViewHolder>() {

    private var courseList: List<Course>? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LatestStudiesAdapterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.course_menu, parent, false)
        return LatestStudiesAdapterViewHolder(view, parent.context)
    }

    override fun onBindViewHolder(holder: LatestStudiesAdapterViewHolder, position: Int) {
        courseList?.get(position)?.let {
            holder.title.text = it.title
            holder.description.text = it.description

            when (it.percentForComplete) {
                0 -> holder.progressView.setInvisible()
                in 1..99 -> setInProgress(holder, it)
                100 -> setDone(holder)
            }
        }
    }

    private fun setDone(holder: LatestStudiesAdapterViewHolder) {
        holder.progressView.setVisible()
        holder.progressText.text = holder.context.getString(R.string.conclusion_text)
        val colorDrawable = AppCompatResources.getDrawable(holder.context, R.color.green_done)
        holder.progressView.background = colorDrawable
    }

    private fun setInProgress(holder: LatestStudiesAdapterViewHolder, course: Course) {
        holder.progressView.setVisible()
        val colorDrawable = AppCompatResources.getDrawable(holder.context, R.color.yellow_in_progress)
        holder.progressView.background = colorDrawable
        val progressText = "Em progresso (${course.percentForComplete}%)"
        holder.progressText.text = progressText
    }

    override fun getItemCount(): Int = courseList?.size ?: 0

    fun insertCourseList(newCourseList: List<Course>){
        courseList = newCourseList
    }

    class LatestStudiesAdapterViewHolder(view: View, val context: Context) :
        RecyclerView.ViewHolder(view) {
        val title: TextView
        val description: TextView
        val progressView: LinearLayout
        val progressText: TextView

        init {
            title = view.findViewById(R.id.course_menu_title)
            description = view.findViewById(R.id.course_menu_description)
            progressView = view.findViewById(R.id.course_menu_progress_view)
            progressText = view.findViewById(R.id.course_menu_progress_text)
        }
    }
}