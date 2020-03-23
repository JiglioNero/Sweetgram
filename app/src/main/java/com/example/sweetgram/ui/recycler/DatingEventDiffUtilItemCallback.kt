package jiglionero.android.app.putonpompom.view.recycler

import androidx.recyclerview.widget.DiffUtil
import com.example.sweetgram.entitys.DatingEvent


class DatingEventDiffUtilItemCallback : DiffUtil.ItemCallback<DatingEvent>() {
    override fun areItemsTheSame(oldItem: DatingEvent, newItem: DatingEvent): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: DatingEvent, newItem: DatingEvent): Boolean {
        return oldItem == newItem
    }
}