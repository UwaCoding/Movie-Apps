package id.uwacoding.apps.movieapp.uimovie

import android.content.Intent
import android.view.View
import android.widget.TextView
import com.facebook.drawee.view.SimpleDraweeView

import id.uwacoding.apps.movieapp.MovinConfig
import id.uwacoding.apps.movieapp.R
import id.uwacoding.apps.movieapp.base.BaseAdapter
import id.uwacoding.apps.movieapp.base.BaseViewHolder
import id.uwacoding.apps.movieapp.data.movie.Movie
import id.uwacoding.apps.movieapp.uidetail.DetailActivity

import java.text.SimpleDateFormat

/**
 * Created by Firman on 6/15/2017.
 */
class MovieAdapter : BaseAdapter<Movie, MovieAdapter.ReposViewHolder>() {

    var movie:Movie?=null

    override fun getItemViewId(): Int = R.layout.movie_item

    override fun instantiateViewHolder(view: View?): ReposViewHolder = ReposViewHolder(view)

    class ReposViewHolder(itemView: View?) : BaseViewHolder<Movie>(itemView) {

        private val readFormat = SimpleDateFormat("yyyy-MM-dd")
        private val writeFormat = SimpleDateFormat("dd MMMM yyyy")

        val movieTitle: TextView by lazy { itemView?.findViewById(R.id.movieTitle) as TextView }
        val movieDate: TextView by lazy { itemView?.findViewById(R.id.movieDate) as TextView }
        val movieOverview: TextView by lazy { itemView?.findViewById(R.id.movieOverview) as TextView }
        val movieRating: TextView by lazy { itemView?.findViewById(R.id.movieRating) as TextView }
        val moviePopularity: TextView by lazy { itemView?.findViewById(R.id.moviePopularity) as TextView }
        val moviePoster: SimpleDraweeView by lazy { itemView?.findViewById(R.id.moviePoster) as SimpleDraweeView }

        override fun onBind(item: Movie) {
            var formatted = item.releaseDate
            if (item.releaseDate != null) {
                formatted = writeFormat.format(readFormat.parse(item.releaseDate))
            }
            movieTitle.text = item.originalTitle
            movieDate.text = "Release $formatted"
            movieOverview.text = item.overview
            movieRating.text = "⭐️ Rating ${item.voteAverage}"
            moviePopularity.text = "🔥 Popular ${item.popularity?.toInt()}"
            moviePoster.setImageURI("${MovinConfig.IMG_BASE_URL}${item.posterPath}")
            moviePoster.setOnClickListener {
                val detail = Intent(it.context, DetailActivity::class.java)
                detail.putExtra("extra", item.id)
                it.context.startActivity(detail)
            }
        }
    }

}