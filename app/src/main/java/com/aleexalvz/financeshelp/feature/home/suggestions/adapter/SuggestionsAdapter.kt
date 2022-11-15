package com.aleexalvz.financeshelp.feature.home.suggestions.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.aleexalvz.financeshelp.R
import com.aleexalvz.financeshelp.commons.extension.setInvisible
import com.aleexalvz.financeshelp.commons.extension.setVisible
import com.aleexalvz.financeshelp.commons.model.Course

class SuggestionsAdapter(
    private val onItemClickListener: ((course: Course) -> Unit)
) : RecyclerView.Adapter<SuggestionsAdapter.SuggestionAdapterViewHolder>() {

    private var courseList: List<Course>? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SuggestionAdapterViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.course_menu, parent, false)
        return SuggestionAdapterViewHolder(view, parent.context)
    }

    override fun onBindViewHolder(holder: SuggestionAdapterViewHolder, position: Int) {
        courseList?.get(position)?.let { course ->
            holder.title.text = course.title
            holder.description.text = course.description
            holder.itemView.setOnClickListener { onItemClickListener.invoke(course) }
            when (course.percentForComplete) {
                0 -> holder.progressView.setInvisible()
                in 1..99 -> setInProgress(holder, course)
                100 -> setDone(holder)
            }
        }
    }

    private fun setDone(holder: SuggestionAdapterViewHolder) {
        holder.progressView.setVisible()
        holder.progressText.text = holder.context.getString(R.string.conclusion_text)
        val colorDrawable = AppCompatResources.getDrawable(holder.context, R.color.green_done)
        holder.progressView.background = colorDrawable
    }

    private fun setInProgress(holder: SuggestionAdapterViewHolder, course: Course) {
        holder.progressView.setVisible()
        val colorDrawable =
            AppCompatResources.getDrawable(holder.context, R.color.yellow_in_progress)
        holder.progressView.background = colorDrawable
        val progressText = "Em progresso (${course.percentForComplete}%)"
        holder.progressText.text = progressText
    }

    override fun getItemCount(): Int = courseList?.size ?: 0

    fun insertCourseList(newCourseList: List<Course>) {
        courseList = newCourseList
    }

    class SuggestionAdapterViewHolder(view: View, val context: Context) :
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
