package id.uwacoding.apps.movieapp.base

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by Firman on 6/15/2017.
 */
abstract class BaseAdapter<D, VH : BaseViewHolder<D>> : RecyclerView.Adapter<VH>() {

    var dataSource: List<D> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = dataSource.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): VH {
        val inflater = LayoutInflater.from(parent?.context)
        val view = inflater.inflate(getItemViewId(), parent, false)
        return instantiateViewHolder(view)
    }

    abstract fun getItemViewId(): Int

    abstract fun instantiateViewHolder(view: View?): VH

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBind(getItem(position))
    }

    fun getItem(position: Int) = dataSource[position]

}