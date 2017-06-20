package id.uwacoding.apps.movieapp.uidetail


import android.view.View
import android.widget.TextView
import id.uwacoding.apps.movieapp.R
import id.uwacoding.apps.movieapp.base.BaseAdapter
import id.uwacoding.apps.movieapp.base.BaseViewHolder
import id.uwacoding.apps.movieapp.data.movie.Movie
import java.text.SimpleDateFormat

/**
 * Created by Firman on 6/15/2017.
 */
class DetailAdapter : BaseAdapter<Movie, DetailAdapter.DetailViewHolder>(){

    override fun getItemViewId(): Int = R.layout.movie_detail_item

    override fun instantiateViewHolder(view: View?): DetailViewHolder = DetailViewHolder(view)


    class DetailViewHolder(itemView: View?) : BaseViewHolder<Movie>(itemView){

        private val readFormat = SimpleDateFormat("yyyy-MM-dd")
        private val writeFormat = SimpleDateFormat("dd MMMM yyyy")

        val movieTitle: TextView by lazy { itemView?.findViewById(R.id.movieTitle) as TextView }
        val movieDate: TextView by lazy { itemView?.findViewById(R.id.movieDate) as TextView }
        val movieOverview: TextView by lazy { itemView?.findViewById(R.id.movieOverview) as TextView }
        val movieRating: TextView by lazy { itemView?.findViewById(R.id.movieRating) as TextView }
        val moviePopularity: TextView by lazy { itemView?.findViewById(R.id.moviePopularity) as TextView }

        override fun onBind(item: Movie) {
            var formatted = item.releaseDate
            if (item.releaseDate != null) {
                formatted = writeFormat.format(readFormat.parse(item.releaseDate))
            }
            movieTitle.text = item.originalTitle
            movieDate.text = "Release date $formatted"
            movieOverview.text = item.overview
            movieRating.text = "⭐️ Average cote rating ${item.voteAverage}"
            moviePopularity.text = "🔥 Popularity by community ${item.popularity?.toInt()}"
        }
    }

}