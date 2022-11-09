package com.aleexalvz.financeshelp.commons.model

data class Course(
    val title: String,
    val description: String,
    val percentForComplete: Int,
    val isFavorited: Boolean = false
)