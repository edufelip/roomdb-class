package com.example.roomdb_class.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.roomdb_class.R
import com.example.roomdb_class.viewmodels.UserViewModel
import com.example.roomdb_class.databinding.FragmentUpdateBinding
import com.example.roomdb_class.models.User

class UpdateFragment : Fragment() {
    private var _binding: FragmentUpdateBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<UpdateFragmentArgs>()

    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUpdateBinding.inflate(inflater, container, false)
        val view = binding.root

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        binding.editTextFirstname.setText(args.currentUser.firstName)
        binding.editTextLastname.setText(args.currentUser.lastName)
        binding.editTextAge.setText(args.currentUser.age.toString())

        binding.addBtn.setOnClickListener {
            updateUser()
        }

        setHasOptionsMenu(true)
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun updateUser() {
        val firstName = binding.editTextFirstname.text.toString()
        val lastName = binding.editTextLastname.text.toString()
        val age = binding.editTextAge.text
        if (checkInputs(firstName, lastName, age)) {
            Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT).show()
            return
        }

        val updatedUser = User(args.currentUser.id, firstName, lastName, Integer.parseInt(age.toString()))
        mUserViewModel.updateUser(updatedUser)
        Toast.makeText(requireContext(), "Successfully Updated", Toast.LENGTH_SHORT).show()
        findNavController().navigate(R.id.action_updateFragment_to_listFragment)
    }

    private fun checkInputs(firstName: String, secondName: String, age: Editable): Boolean {
        return (firstName.isEmpty() || secondName.isEmpty() || age.isEmpty())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.item_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete) {
            deleteUser()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteUser() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            mUserViewModel.deleteUser(args.currentUser)
            Toast.makeText(requireContext(), "User Successfully Deleted", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("No") { _, _ ->

        }
        builder.setTitle("Delete ${args.currentUser.firstName}?")
        builder.setMessage("Are you sure?")
        builder.create().show()
    }
}