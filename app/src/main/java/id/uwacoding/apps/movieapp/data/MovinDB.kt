package id.uwacoding.apps.movieapp.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import id.uwacoding.apps.movieapp.data.movie.Movie
import id.uwacoding.apps.movieapp.data.movie.MovieDao
import id.uwacoding.apps.movieapp.data.review.Review
import id.uwacoding.apps.movieapp.data.review.ReviewDao
import id.uwacoding.apps.movieapp.data.trailer.Trailer
import id.uwacoding.apps.movieapp.data.trailer.TrailerDao

/**
 * Created by Firman on 6/21/2017.
 */
@Database(entities = arrayOf(Movie::class, Review::class, Trailer::class), version = 1, exportSchema = false)
abstract class MovinDB : RoomDatabase() {

    abstract fun movieDao(): MovieDao
    abstract fun reviewDao(): ReviewDao
    abstract fun trailerDao(): TrailerDao

    companion object {
        const val DB_NAME = "movin_db"
    }

}