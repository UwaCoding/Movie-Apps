package id.uwacoding.apps.movieapp.base

import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by Firman on 6/15/2017.
 */
abstract class BaseViewHolder<in D>(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    abstract fun onBind(item: D)
}