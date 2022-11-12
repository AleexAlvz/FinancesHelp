package com.aleexalvz.financeshelp.feature.home.suggestions.model

import com.aleexalvz.financeshelp.commons.model.Course

sealed class SuggestionsViewState {
    data class FetchedCourses(val courseList: List<Course>) : SuggestionsViewState()
    object FailureInFetchCourses : SuggestionsViewState()
    object FetchingCourses : SuggestionsViewState()
}