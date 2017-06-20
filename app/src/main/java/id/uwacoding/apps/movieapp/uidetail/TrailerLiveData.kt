package id.uwacoding.apps.movieapp.uidetail

import android.arch.lifecycle.MediatorLiveData
import id.uwacoding.apps.movieapp.data.trailer.Trailer
import id.uwacoding.apps.movieapp.data.trailer.TrailerRepository
import io.reactivex.disposables.Disposable

/**
 * Created by Firman on 6/15/2017.
 */
class TrailerLiveData : MediatorLiveData<Pair<List<Trailer>?, Throwable?>>(){

    val repo = TrailerRepository()

    var movieId: Long? = null
    set(value) {
        value?.let {
            disposable = repo.getTrailers(it).subscribe{data, error -> this@TrailerLiveData.value = Pair(data, error)}

        }
    }

    private var disposable : Disposable? = null
    override fun onActive() {
        super.onActive()
        if (disposable?.isDisposed?.not() ?: false) {
            disposable?.dispose()
        }
    }
}