package jiglionero.android.app.putonpompom.view.recycler

import androidx.recyclerview.widget.DiffUtil
import com.example.sweetgram.data.entitys.DatingEvent


class DatingEventDiffUtilItemCallback : DiffUtil.ItemCallback<DatingEvent>() {
    override fun areItemsTheSame(oldItem: DatingEvent, newItem: DatingEvent): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: DatingEvent, newItem: DatingEvent): Boolean {
        return oldItem == newItem
    }
}