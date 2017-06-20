package id.uwacoding.apps.movieapp.uidetail

import android.arch.lifecycle.MediatorLiveData
import id.uwacoding.apps.movieapp.data.movie.Movie
import id.uwacoding.apps.movieapp.data.movie.MovieRepository
import io.reactivex.disposables.Disposable

/**
 * Created by Firman on 6/15/2017.
 */
class DetailLiveData : MediatorLiveData<Pair<List<Movie>?, Throwable?>>(){

    val movieRepo = MovieRepository()
    var movieId: Long? = null
        set(value) {
        value?.let {
            disposable = movieRepo.getById(it).subscribe { data, error -> this@DetailLiveData.value = Pair(data, error) }
        }
    }

    private var disposable: Disposable? = null

    override fun onInactive() {
        super.onInactive()
        if (disposable?.isDisposed?.not() ?: false) {
            disposable?.dispose()
        }
    }
}