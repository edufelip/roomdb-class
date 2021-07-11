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
import com.example.roomdb_class.data.User
import com.example.roomdb_class.data.UserViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*

class AddFragment : Fragment() {
    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_add, container, false)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        view.add_btn.setOnClickListener {
            addToDatabase()
        }

        return view
    }

    private fun addToDatabase() {
        val firstName = edit_text_firstname.text.toString()
        val secondName = edit_text_secondname.text.toString()
        val age = edit_text_age.text
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