package com.example.sweetgram.ui.profile

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.sweetgram.R
import com.example.sweetgram.databinding.FragmentProfileBinding
import com.example.sweetgram.ui.activities.ImagePickerActivity
import de.hdodenhof.circleimageview.CircleImageView

class ProfileFragment : Fragment() {

    private lateinit var profileViewModel: ProfileViewModel
    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentProfileBinding>(inflater, R.layout.fragment_profile, container, false)
        return binding.root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        //super.onActivityResult(requestCode, resultCode, data)
        data?.extras?.getString(ImagePickerActivity.IMAGE_PATH_KEY)?.let {
            val path = data.extras!!.getString(ImagePickerActivity.IMAGE_PATH_KEY)!!
            Log.e("onActivityResult", "Path = $path")
            profileViewModel.relationship.get()?.relIconId = path
            profileViewModel.relationship.notifyChange()
            profileViewModel.updateRelationship()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        profileViewModel =
            ViewModelProviders.of(this).get(ProfileViewModel::class.java)
        binding.profileVM = profileViewModel

        view?.let {
            /*val viewPager = it.findViewById<ViewPager>(R.id.gallery_view_pager)
            viewPager.adapter =*/

            val profileIcon = it.findViewById<CircleImageView>(R.id.relationship_icon)
            profileIcon.setOnClickListener {
                val intent = Intent(context, ImagePickerActivity::class.java)
                startActivityForResult(intent, 1)
            }
        }
    }
}
