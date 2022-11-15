package com.aleexalvz.financeshelp.commons.model

data class Course(
    val id: Int,
    val title: String,
    val description: String,
    val percentForComplete: Int,
    val isFavorited: Boolean = false
)