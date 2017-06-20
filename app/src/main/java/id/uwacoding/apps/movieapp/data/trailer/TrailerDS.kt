package id.uwacoding.apps.movieapp.data.trailer

import io.reactivex.Single

/**
 * Created by Firman on 6/15/2017.
 */
interface TrailerDS {

    fun getTrailers(id: Long): Single<List<Trailer>>
    fun saveRepositories(id:Long, list: List<Trailer>): Unit = Unit

}