package id.uwacoding.apps.movieapp.data.movie

import io.reactivex.Single

/**
 * Created by Firman on 6/15/2017.
 */
interface MovieDS {

    fun getPopular(): Single<List<Movie>>
    fun getTopRated(): Single<List<Movie>>

    fun saveRepositories(list: List<Movie>) : Unit = Unit

}