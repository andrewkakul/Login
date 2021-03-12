package com.fivepro.phonelogin.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.fivepro.phonelogin.R
import com.fivepro.phonelogin.databinding.FragmentLoginBinding
import com.fivepro.phonelogin.viewmodel.database.UsersRoomDatabase
import com.fivepro.phonelogin.model.UserLogin
import com.fivepro.phonelogin.viewmodel.LoginViewModel

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var db: UsersRoomDatabase

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        db = UsersRoomDatabase.getDatabase(requireActivity().applicationContext)
        initViewModel()

        binding.loginContinueBtn.setOnClickListener {
            val userLogin = UserLogin(("+"+ binding.ccp.selectedCountryCode),
                binding.phoneNumberEditText.text.toString(),
                binding.passwordEditText.text.toString())

            loginViewModel.loginUser(db, requireContext(), userLogin)
        }
    }

    private fun initViewModel(){
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        loginViewModel.responseAnswer.observe(viewLifecycleOwner,{
            if(it){
                findNavController().navigate(R.id.action_loginFragment_to_profileFragment)
                loginViewModel.responseAnswer.postValue(false)
            }
        })
    }
}