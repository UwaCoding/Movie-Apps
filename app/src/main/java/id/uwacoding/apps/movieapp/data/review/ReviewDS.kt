package id.uwacoding.apps.movieapp.data.review

import io.reactivex.Single

/**
 * Created by Firman on 6/15/2017.
 */
interface ReviewDS {
    fun getReviews(id: Long): Single<List<Review>>
    fun saveRepositories(id:Long, list: List<Review>): Unit = Unit
}