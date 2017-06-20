package id.uwacoding.apps.movieapp.data.trailer

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Firman on 6/15/2017.
 */
class TrailerRepository : TrailerDS{

    private val localDS = TrailerLocalDS()
    private val remoteDS = TrailerRemoteDS()

    override fun getTrailers(id: Long): Single<List<Trailer>> = localDS.getTrailers(id)
            .onErrorResumeNext {
                remoteDS.getTrailers(id)
                        .doOnSuccess { localDS.saveRepositories(id, it) }
    }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}