package citics.sharing.adapter.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseLoadMoreAdapter<VB : ViewBinding, T : Any>(
    diffCallback: DiffUtil.ItemCallback<T>
) :
    PagingDataAdapter<T, BaseLoadMoreAdapter<VB, T>.RecyclerViewHolder>(diffCallback) {

    var onClickRoot: ((T) -> Unit)? = null
    var layoutManager: LinearLayoutManager? = null
    open val isVertical: Boolean
        get() = true
    abstract val bindingInflater: (LayoutInflater, ViewGroup, Boolean) -> VB
    private var _bi: VB? = null
    private val bi: VB get() = _bi!!

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.run {
            val item = getItem(position)
            item?.handleItem(holder.convertView, position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        _bi = bindingInflater.invoke(layoutInflater, parent, false)
        return RecyclerViewHolder(bi)
    }

    inner class RecyclerViewHolder(var itemViewd: VB) :
        RecyclerView.ViewHolder(itemViewd.root) {
        val convertView: VB
            get() = itemViewd
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        setLayoutManager(recyclerView)
    }


    open fun setLayoutManager(recyclerView: RecyclerView) {
        recyclerView.layoutManager = LinearLayoutManager(
            recyclerView.context,
            if (isVertical) RecyclerView.VERTICAL else RecyclerView.HORIZONTAL,
            false
        ).apply {
            layoutManager = this
        }
    }

    protected abstract fun T.handleItem(binding: VB?, position: Int)

//    class BaseDiffUtil<T : Any> : DiffUtil.ItemCallback<T>() {
//        //        override fun areContentsTheSame(
////            oldItem: T, newItem: T
////        ): Boolean {
////            return oldItem == newItem
////        }
////
////        override fun areItemsTheSame(
////            oldItem: T, newItem: T
////        ): Boolean {
////            return oldItem.record_id == newItem.record_id
////        }
//        override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
//            return areItemsTheSamea(oldItem, newItem)
//        }
//
//        override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
//
//        }
//    }


}

