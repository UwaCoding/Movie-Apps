package id.uwacoding.apps.movieapp.data.review

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import id.uwacoding.apps.movieapp.ext.getResultJson

/**
 * Created by Firman on 6/15/2017.
 */
@Entity(tableName = "reviews")
class Review {
    @PrimaryKey
    var id: String? = null
    var movieId: Long? = null
    var author: String? = null
    var content: String? = null

    class ListDeserializer : ResponseDeserializable<List<Review>> {
        override fun deserialize(content: String): List<Review> =
                Gson().fromJson<List<Review>>(content.getResultJson(), object : TypeToken<List<Review>>() {}.type)
        }
    }
