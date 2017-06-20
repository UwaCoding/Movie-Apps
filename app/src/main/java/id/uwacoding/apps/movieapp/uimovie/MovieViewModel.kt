package id.uwacoding.apps.movieapp.uimovie

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData
import id.uwacoding.apps.movieapp.data.movie.Movie
import id.uwacoding.apps.movieapp.data.movie.MovieRepository

/**
 * Created by Firman on 6/15/2017.
 */
class MovieViewModel(application: Application?) : AndroidViewModel(application) {

    private val repository = MovieRepository()

    private val filterLiveData = MutableLiveData<String>()

    val resultLiveData = MovieLiveData(repository)
    val isLoadingLiveData = MediatorLiveData<Boolean>()
    val throwableLiveData = MediatorLiveData<Throwable>()
    val reposLiveData = MediatorLiveData<List<Movie>>()

    init {
        resultLiveData.addSource(filterLiveData) {
            it?.let { resultLiveData.filter = it }
        }
        isLoadingLiveData.addSource(resultLiveData) {
            isLoadingLiveData.value = false
        }
        throwableLiveData.addSource(resultLiveData) {
            it?.second?.let { throwableLiveData.value = it }
        }
        reposLiveData.addSource(resultLiveData) {
            it?.first?.let { reposLiveData.value = it }
        }
    }

    fun setFilter(filterName: String) {
        filterLiveData.value = filterName
        isLoadingLiveData.value = true
    }

    companion object {
        val FILTER_POPULAR = "Popular"
        val FILTER_TOP_RATED = "Top Rated"
        val FILTER_FAVORITE = "Favorites"
    }

}