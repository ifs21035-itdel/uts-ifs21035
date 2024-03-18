package com.ifs21035.dinopedia

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize
data class Family(
    var name: String,
    var icon: Int,
    var description: String,
    var period: String,
    var physics: String,
    var habitat: String,
    var behaviour: String,
    var classification: String,
) :Parcelable