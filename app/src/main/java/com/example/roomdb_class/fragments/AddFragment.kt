package com.example.roomdb_class.fragments

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.roomdb_class.R
import com.example.roomdb_class.domain.models.User
import com.example.roomdb_class.viewmodels.UserViewModel
import com.example.roomdb_class.databinding.FragmentAddBinding

class AddFragment : Fragment() {

    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!

    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddBinding.inflate(inflater, container, false)
        val view = binding.root

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        binding.addBtn.setOnClickListener{
            addToDatabase()
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    
    private fun addToDatabase() {
        val firstName = binding.editTextFirstname.text.toString()
        val secondName = binding.editTextLastname.text.toString()
        val age = binding.editTextAge.text
        if(checkInputs(firstName, secondName, age)) {
            Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT).show()
            return
        }

        val user = User(0, firstName, secondName, Integer.parseInt(age.toString()))
        mUserViewModel.addUser(user)
        Toast.makeText(requireContext(), "User added", Toast.LENGTH_SHORT).show()

        findNavController().navigate(R.id.action_addFragment_to_listFragment)
    }

    private fun checkInputs(firstName: String, secondName: String, age: Editable): Boolean {
        return (firstName.isEmpty() || secondName.isEmpty() || age.isEmpty())
    }
}