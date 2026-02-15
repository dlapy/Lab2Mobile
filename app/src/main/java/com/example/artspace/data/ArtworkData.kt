package com.example.artspace.data

import com.example.artspace.R
import com.example.artspace.model.Artwork

object ArtworkData {

    val artworks = listOf(
        Artwork(
            imageRes = R.drawable.art1,
            titleRes = R.string.title1,
            authorRes = R.string.author1,
            yearRes = R.string.year1
        ),
        Artwork(
            imageRes = R.drawable.art2,
            titleRes = R.string.title2,
            authorRes = R.string.author2,
            yearRes = R.string.year2
        ),
        Artwork(
            imageRes = R.drawable.art3,
            titleRes = R.string.title3,
            authorRes = R.string.author3,
            yearRes = R.string.year3
        )
    )
}
