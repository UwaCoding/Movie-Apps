package id.uwacoding.apps.movieapp.uidetail

import android.arch.lifecycle.MediatorLiveData
import id.uwacoding.apps.movieapp.data.review.Review
import id.uwacoding.apps.movieapp.data.review.ReviewRepository
import io.reactivex.disposables.Disposable

/**
 * Created by Firman on 6/15/2017.
 */
class ReviewLiveData : MediatorLiveData<Pair<List<Review>?,  Throwable?>>() {


    val movieRepo = ReviewRepository()

    var movieId: Long? = null
    set(value) {
        value?.let {
            disposable = movieRepo.getReviews(it).subscribe { data, error -> this@ReviewLiveData.value = Pair(data, error) }
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