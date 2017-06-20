package id.uwacoding.apps.movieapp.uidetail

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData
import id.uwacoding.apps.movieapp.data.movie.Movie
import id.uwacoding.apps.movieapp.data.review.Review
import id.uwacoding.apps.movieapp.data.trailer.Trailer

/**
 * Created by Firman on 6/15/2017.
 */
class DetailViewModel(application: Application?) : AndroidViewModel(application){

    private val filterLiveData = MutableLiveData<Long>()

    val detailLD = DetailLiveData()
    val reviewLD = ReviewLiveData()
    val trailerLD = TrailerLiveData()

    val isLoadingLiveData = MediatorLiveData<Boolean>()
    val throwableLiveData = MediatorLiveData<Throwable>()
    val detailRepoLD = MediatorLiveData<List<Movie>>()
    val reviewRepoLD = MediatorLiveData<List<Review>>()
    val trailerRepoLD = MediatorLiveData<List<Trailer>>()

    init {
        detailLD.addSource(filterLiveData) { it?.let{
            detailLD.movieId = it
            reviewLD.movieId = it
            trailerLD.movieId = it
        }}

        isLoadingLiveData.addSource(detailLD) {
            isLoadingLiveData.value = false
        }
        throwableLiveData.addSource(detailLD) {
            it?.second?.let { throwableLiveData.value = it }
        }
        detailRepoLD.addSource(detailLD) {
            it?.first?.let { detailRepoLD.value = it }
        }

        isLoadingLiveData.addSource(reviewLD) {
            isLoadingLiveData.value = false
        }
        throwableLiveData.addSource(reviewLD) {
            it?.second?.let { throwableLiveData.value = it }
        }
        reviewRepoLD.addSource(reviewLD) {
            it?.first?.let { reviewRepoLD.value = it }
        }
        isLoadingLiveData.addSource(trailerLD) {
            isLoadingLiveData.value = false
        }
        throwableLiveData.addSource(trailerLD) {
            it?.second?.let { throwableLiveData.value = it }
        }
        trailerRepoLD.addSource(trailerLD) {
            it?.first?.let { trailerRepoLD.value = it }
        }
    }

    fun setFilter(filterMovie: Long) {
        filterLiveData.value = filterMovie
        isLoadingLiveData.value = true
    }
}