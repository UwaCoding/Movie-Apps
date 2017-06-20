package id.uwacoding.apps.movieapp.uimovie

import android.arch.lifecycle.MediatorLiveData
import id.uwacoding.apps.movieapp.data.movie.Movie
import id.uwacoding.apps.movieapp.data.movie.MovieRepository
import id.uwacoding.apps.movieapp.uimovie.MovieViewModel.Companion.FILTER_FAVORITE
import id.uwacoding.apps.movieapp.uimovie.MovieViewModel.Companion.FILTER_POPULAR
import id.uwacoding.apps.movieapp.uimovie.MovieViewModel.Companion.FILTER_TOP_RATED
import io.reactivex.disposables.Disposable

/**
 * Created by Firman on 6/15/2017.
 */
class MovieLiveData(val repository: MovieRepository)
    : MediatorLiveData<Pair<List<Movie>?, Throwable?>>() {


    private var disposable: Disposable? = null

    var filter: String? = null
        set(value) {
            value?.let {
                when (it) {
                    FILTER_POPULAR -> disposable = repository
                            .getPopular()
                            .subscribe { data, error -> this@MovieLiveData.value = Pair(data, error) }
                    FILTER_TOP_RATED -> disposable = repository
                            .getTopRated()
                            .subscribe { data, error -> this@MovieLiveData.value = Pair(data, error) }
                    FILTER_FAVORITE -> disposable = repository
                            .getFavorite()
                            .subscribe { data, error -> this@MovieLiveData.value = Pair(data, error) }
                }
            }
        }

    override fun onInactive() {
        super.onInactive()
        if (disposable?.isDisposed?.not() ?: false) {
            disposable?.dispose()
        }
    }

}