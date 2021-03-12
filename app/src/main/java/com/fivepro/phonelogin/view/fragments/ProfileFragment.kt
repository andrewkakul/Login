package com.fivepro.phonelogin.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.fivepro.phonelogin.viewmodel.database.UsersRoomDatabase
import com.fivepro.phonelogin.databinding.FragmentProfileBinding
import com.fivepro.phonelogin.viewmodel.ProfileViewModel
import java.lang.StringBuilder

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var profileViewModel: ProfileViewModel
    private lateinit var db: UsersRoomDatabase

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        db = UsersRoomDatabase.getDatabase(requireActivity().applicationContext)
        initViewModel()
        profileViewModel.getUserData(db, 0)
    }

    private fun initViewModel(){
        profileViewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        profileViewModel.userProfile.observe(viewLifecycleOwner, {
            binding.nameTextview.text = it.name
            binding.phoneNumberTextview.text = StringBuilder(it.phoneCode + it.phoneNumber)
            binding.surnameTextview.text = it.secondName
        })
    }

}