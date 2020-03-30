package com.example.sweetgram.ui.activities.login

import android.content.Intent
import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.example.sweetgram.SweetgramApplication
import com.example.sweetgram.data.DataNode
import com.example.sweetgram.data.entitys.Lover
import com.example.sweetgram.data.entitys.LoverCredentials
import com.example.sweetgram.data.entitys.Relationship
import com.example.sweetgram.ui.activities.MainActivity
import java.text.SimpleDateFormat
import javax.inject.Inject


class LoginViewModel: ViewModel() {
    @Inject
    lateinit var dataNode: DataNode

    lateinit var loginActivity: LoginActivity

    val username =  ObservableField<String>("")
    val loversUsername =  ObservableField<String>("")
    val relationshipStartDate =  ObservableField<String>("")

    val dateFormatter = SimpleDateFormat("dd.MM.yyyy")

    init {
        SweetgramApplication.instance.injector.inject(this)
    }

    fun tryToLoginByCredentials(credentials: LoverCredentials){
        val lover1 = dataNode.getLoverByCredentials(credentials) ?: return
        //TODO FIX THIS
        val relationship = dataNode.getRelationshipByLoversIds(lover1.id) ?: return

        loginActivity.sharedPreferences.edit().putString("username", lover1.getCredentials().username).apply()

        val intent = Intent(loginActivity, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
        loginActivity.startActivity(intent)
    }

    fun tryToLoginByInput(){
        Log.d("Login", "tryToLoginByInput")
        if (
            username.get() != null &&
            loversUsername.get() != null &&
            relationshipStartDate.get() != null &&
            username.get()!!.length > 3 &&
            loversUsername.get()!!.length > 3
        ) {
            try {
            val loverTry1 = Lover(username = username.get()!!)
            val loverTry2 = Lover(username = loversUsername.get()!!)

            val cred1 = loverTry1.getCredentials()
            val cred2 = loverTry2.getCredentials()

            val lover1 = dataNode.getLoverByCredentials(cred1)
            val lover2 = dataNode.getLoverByCredentials(cred2)

            var id1: Long = -1
            var id2: Long = -1
            var relationshipId: Long = -1

                id1 = lover1?.id ?: dataNode.saveLover(loverTry1)

                id2 = lover2?.id ?: dataNode.saveLover(loverTry2)

                var relationship = dataNode.getRelationshipByLoversIds(id1, id2)

                relationshipId = relationship?.id
                    ?: dataNode.saveRelationship(Relationship(lover1Id = id1, lover2Id = id2, dt = dateFormatter.parse(relationshipStartDate.get()!!)!!.time))

            if (id1 > 0 && id2 > 0 && relationshipId > 0){
                loginActivity.sharedPreferences.edit().putString("username", cred1.username).apply()

                val intent = Intent(loginActivity, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
                loginActivity.startActivity(intent)
            }
            }
            catch (e: Exception){
                e.printStackTrace()
                return
            }
        }
        return
    }
}