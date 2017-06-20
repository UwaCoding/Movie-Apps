package id.uwacoding.apps.movieapp.data.trailer

import id.uwacoding.apps.movieapp.data.DBMaker
import id.uwacoding.apps.movieapp.ext.mapInPlace
import io.reactivex.Single

/**
 * Created by Firman on 6/15/2017.
 */
class TrailerLocalDS : TrailerDS {
    val dao = DBMaker.db.trailerDao()

    override fun getTrailers(id: Long): Single<List<Trailer>> =
            dao.loadTrailers(id).firstOrError().doOnSuccess { if (it.isEmpty()) throw Exception() }


    override fun saveRepositories(id: Long, list: List<Trailer>) {
        val mutableList = list.toMutableList()
        mutableList.mapInPlace { it.apply { movieId = id } }
        dao.insertAll(mutableList)
    }
}