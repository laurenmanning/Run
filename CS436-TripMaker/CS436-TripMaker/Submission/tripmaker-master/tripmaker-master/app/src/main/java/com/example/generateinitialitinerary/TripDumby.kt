package com.example.generateinitialitinerary

import com.example.TripMaker.DraftModelClass

data class TripDumby(val name: String? = null, val address: String? = null, val time : Int? = null,
                     val rating: String? = null) {

    fun toDraftModel() = DraftModelClass(name as String, address as String, rating as String,
        "", false, false, List<String>(0) { it -> "" }, time as Int)

}