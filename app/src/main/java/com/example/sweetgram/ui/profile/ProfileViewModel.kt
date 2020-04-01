package com.example.sweetgram.ui.profile

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.example.sweetgram.SweetgramApplication
import com.example.sweetgram.data.DataNode
import com.example.sweetgram.data.entitys.Lover
import com.example.sweetgram.data.entitys.Relationship
import com.example.sweetgram.ui.activities.MainActivity
import javax.inject.Inject

class ProfileViewModel : ViewModel() {

    @Inject lateinit var dataNode: DataNode
    var relationship: ObservableField<Relationship> = ObservableField()
    var lover1: Lover
    var lover2: Lover

    init {
        SweetgramApplication.instance.injector.inject(this)
        relationship.set(dataNode.getRelationshipByLoversIds(MainActivity.user.id)!!)
        lover1 = relationship.get()?.lover1Id?.let { dataNode.getLoverById(it) }!!
        lover2 = relationship.get()?.lover2Id?.let { dataNode.getLoverById(it) }!!
    }

    fun updateRelationship(){
        dataNode.updateRelationship(relationship.get()!!)
    }
}