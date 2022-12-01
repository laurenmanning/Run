package com.example.generateinitialitinerary

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_draft_itinerary.view.*

var places = ArrayList<Results>()

var arrayList = ArrayList<DraftModelClass>()

class Results{
    var results: List<Locations>? = null
}

class Locations{
    var name: String = "Name Unavailable"
    var vicinity: String = "Not Given"
    var rating : String = "No Ratings Available"
    var types : List<String>? = null
    var visited: Boolean = false
}

class DraftItinerary : Fragment() {

    private val args by navArgs<DraftItineraryArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        places.clear()
        arrayList.clear()

        for (json in args.userName){
            places.add(Gson().fromJson(json, Results::class.java))
        }

        var locationObjects = generateAllPossibleLocations(places)

        for (i in locationObjects){
            var type = i.types?.get(0)
            type?.let { DraftModelClass(i.name,i.vicinity,i.rating, it,false,false) }
                ?.let { arrayList.add(it) }
        }

        var itinerary = createDraftItinerary(arrayList)

        val view = inflater.inflate(R.layout.fragment_draft_itinerary, container, false)
        view.recyclerView.layoutManager = LinearLayoutManager(activity)
        view.recyclerView.adapter = DraftAdapter(this, itinerary)

        return view
    }

    // The trip will be 10 hours long
    // Different types of locations will have weights representing the amount of
    // time a visit to there will consume
    // (ex: aquarium visits are longer than beauty store)
    // different types of destinations shouldn't be too early on in the day
    // (ex: casino, bar, nightclub)
    // Because of the weights and the max amount of time we can tell where we are in the day
    fun createDraftItinerary(arrayList: ArrayList<DraftModelClass>) : ArrayList<DraftModelClass>{
        var draftItineraryArray = ArrayList<DraftModelClass>()
        var hours = 0

        if (arrayList.size < 2){
            return arrayList
        }
        else{
            while (hours < 10){
                //random idx
                var ridx = (0 until (arrayList.size-1)).random()

                // 1 hour Destinations
                if (arrayList[ridx].type == "airport" || arrayList[ridx].type == "bakery" ||
                    arrayList[ridx].type == "bar" || arrayList[ridx].type == "beauty_salon" ||
                    arrayList[ridx].type == "bicycle_store" || arrayList[ridx].type == "book_store" ||
                    arrayList[ridx].type == "cafe" || arrayList[ridx].type == "casino" ||
                    arrayList[ridx].type == "clothing_store" ||
                    arrayList[ridx].type == "department_store" || arrayList[ridx].type == "home_goods_store" ||
                    arrayList[ridx].type == "department_store" || arrayList[ridx].type == "jewelry_store" ||
                    arrayList[ridx].type == "shoe_store" ||  arrayList[ridx].type == "store" ||
                    arrayList[ridx].type == "electronics_store" ||  arrayList[ridx].type == "florist" ||
                    arrayList[ridx].type == "night_club" || arrayList[ridx].type == "pet_store")
                        {
                    draftItineraryArray.add(arrayList[ridx])
                    hours++
                }

                // 2 hour destinations
                if (arrayList[ridx].type == "bowling_alley" || arrayList[ridx].type == "cemetery" ||
                    arrayList[ridx].type == "city_hall" || arrayList[ridx].type == "courthouse" ||
                    arrayList[ridx].type == "embassy" || arrayList[ridx].type == "fire_station" ||
                    arrayList[ridx].type == "local_government_office" || arrayList[ridx].type == "tourist_attraction" ||
                    arrayList[ridx].type == "university" || arrayList[ridx].type == "church" ||
                    arrayList[ridx].type == "hindu_temple" || arrayList[ridx].type == "mosque" ||
                    arrayList[ridx].type == "synagogue" || arrayList[ridx].type == "gym" ||
                    arrayList[ridx].type == "library" || arrayList[ridx].type == "movie_rental" ||
                    arrayList[ridx].type == "movie_theater"
                ){
                    draftItineraryArray.add(arrayList[ridx])
                    hours += 2
                }

                // 3 hour destinations
                if (arrayList[ridx].type == "art_gallery" || arrayList[ridx].type == "museum" ||
                    arrayList[ridx].type == "park" || arrayList[ridx].type == "mall" ||
                    arrayList[ridx].type == "spa" || arrayList[ridx].type == "stadium"){
                    draftItineraryArray.add(arrayList[ridx])
                    hours += 3
                }

                // 4 hour destinations
                if (arrayList[ridx].type == "amusement_park" || arrayList[ridx].type == "aquarium" ||
                    arrayList[ridx].type == "zoo"){
                    draftItineraryArray.add(arrayList[ridx])
                    hours += 4
                }

                //10 hour (fullday)
                if (arrayList[ridx].type == "campground" || arrayList[ridx].type == "rv_park"){
                    draftItineraryArray.add(arrayList[ridx])
                    hours+=10
                }
                else{
                    hours++
                }
            }
        }



        return draftItineraryArray
    }


    fun generateAllPossibleLocations(places: ArrayList<Results>) : ArrayList<Locations> {
        var returnMe = ArrayList<Locations>()

        //Get the results
        var decodePlaces = ArrayList<List<Locations>>()
        for (i in places.indices)
            places[i].results?.let { decodePlaces.add(it) }

        for(i in decodePlaces.indices)
            for (j in decodePlaces[i].indices){
                var location = Locations()
                location.name = decodePlaces[i][j].name
                location.vicinity = decodePlaces[i][j].vicinity
                location.rating = decodePlaces[i][j].rating
                location.types = decodePlaces[i][j].types
                returnMe.add(location)
            }
        return returnMe

    }

}