package id.uwacoding.apps.movieapp.data.review

import id.uwacoding.apps.movieapp.data.DBMaker
import id.uwacoding.apps.movieapp.ext.mapInPlace
import io.reactivex.Single

/**
 * Created by Firman on 6/15/2017.
 */
class ReviewLocalDS : ReviewDS {

    val dao = DBMaker.db.reviewDao()
    override fun getReviews(id: Long): Single<List<Review>> =
            dao.loadReviews(id).firstOrError().doOnSuccess { if(it.isEmpty()) throw Exception()
    }

    override fun saveRepositories(id: Long, list: List<Review>) {
        val mutableList = list.toMutableList()
        mutableList.mapInPlace { it.apply { movieId = id}}
        dao.insertAll(mutableList)
    }
}