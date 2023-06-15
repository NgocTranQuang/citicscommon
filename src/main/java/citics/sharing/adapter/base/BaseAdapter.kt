package citics.sharing.adapter.base

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.sharing.R

abstract class BaseAdapter<VB : ViewBinding, T>(
    context: Context,
    list: MutableList<T>? = mutableListOf()
) :
    RecyclerView.Adapter<BaseAdapter<VB, T>.RecyclerViewHolder>() {
    protected var mContext = context
    var layoutManager: LinearLayoutManager? = null

    open val isVertical: Boolean
        get() = true

    abstract val bindingInflater: (LayoutInflater, ViewGroup, Boolean) -> VB

    private var _bi: VB? = null
    private val bi: VB get() = _bi!!

    open var mainList = list
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        _bi = bindingInflater.invoke(layoutInflater, parent, false)
        return RecyclerViewHolder(bi)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.run {
            mainList?.getOrNull(position)?.handleItem(holder.convertView, position)
        }
    }

    protected abstract fun T.handleItem(binding: VB?, position: Int)

    override fun getItemCount() = mainList?.size ?: 0


    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        setLayoutManager(recyclerView)
    }

    open fun setLayoutManager(recyclerView: RecyclerView) {
        recyclerView.layoutManager = LinearLayoutManager(
            mContext,
            if (isVertical) RecyclerView.VERTICAL else RecyclerView.HORIZONTAL,
            false
        ).apply {
            layoutManager = this
        }
    }

    open fun addItem(list: T) {
        mainList?.add(list)
        notifyItemChanged((mainList?.size ?: 0) - 1)
    }

    open fun addItem(list: MutableList<T>) {
        mainList?.addAll(list)
        notifyDataSetChanged()
    }

    inner class RecyclerViewHolder(var itemViewd: VB) :
        RecyclerView.ViewHolder(itemViewd.root) {
        val convertView: VB
            get() = itemViewd
    }


    open fun handleExpanedCollapse(viewClick: ImageView?, viewShowHide: View?) {
        viewClick?.setOnClickListener {
            if (viewShowHide?.visibility == View.VISIBLE) {
                viewShowHide.visibility = View.GONE
                viewClick.setImageResource(R.drawable.ic_collapsed)
            } else {
                viewShowHide?.visibility = View.VISIBLE
                viewClick.setImageResource(R.drawable.ic_expaned)

            }
        }
    }

}
