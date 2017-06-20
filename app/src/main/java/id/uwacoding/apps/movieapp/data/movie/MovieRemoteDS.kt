package id.uwacoding.apps.movieapp.data.movie

import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.rx.rx_object
import id.uwacoding.apps.movieapp.MovinConfig
import id.uwacoding.apps.movieapp.ext.log
import io.reactivex.Single

/**
 * Created by Firman on 6/15/2017.
 */
class MovieRemoteDS : MovieDS {

    init {
        FuelManager.instance.basePath = MovinConfig.API_BASE_URL
        FuelManager.instance.baseParams = listOf("api_key" to MovinConfig.API_KEY)
    }

    override fun getPopular(): Single<List<Movie>> = "/movie/popular"
            .httpGet().log().rx_object(Movie.ListDeserializer())
            .map { it?.component1() ?: throw it?.component2() ?: throw Exception() }

    override fun getTopRated(): Single<List<Movie>> = "/movie/top_rated"
            .httpGet().log().rx_object(Movie.ListDeserializer())
            .map { it?.component1() ?: throw it?.component2() ?: throw Exception() }
}