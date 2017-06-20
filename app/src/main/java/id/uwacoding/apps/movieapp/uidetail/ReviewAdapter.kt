package id.uwacoding.apps.movieapp.uidetail


import android.view.View
import android.widget.TextView
import id.uwacoding.apps.movieapp.R
import id.uwacoding.apps.movieapp.base.BaseAdapter
import id.uwacoding.apps.movieapp.base.BaseViewHolder
import id.uwacoding.apps.movieapp.data.review.Review

/**
 * Created by Firman on 6/15/2017.
 */
class ReviewAdapter : BaseAdapter<Review, ReviewAdapter.ReviewViewHolder>() {


    override fun getItemViewId(): Int = R.layout.review_item

    override fun instantiateViewHolder(view: View?): ReviewViewHolder = ReviewViewHolder(view)


    class ReviewViewHolder(itemView: View?) : BaseViewHolder<Review>(itemView){

        val author: TextView by lazy { itemView?.findViewById(R.id.author) as TextView}
        val content: TextView by lazy { itemView?.findViewById(R.id.content) as TextView}

        override fun onBind(item: Review) {
            author.text = item.author
            content.text = item.content
        }
    }
}