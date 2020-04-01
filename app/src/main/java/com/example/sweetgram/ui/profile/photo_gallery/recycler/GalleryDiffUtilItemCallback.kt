package jiglionero.android.app.putonpompom.view.recycler

import androidx.recyclerview.widget.DiffUtil


class GalleryDiffUtilItemCallback : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }
}