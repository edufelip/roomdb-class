package com.example.roomdb_class.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdb_class.databinding.RecyclerItemBinding
import com.example.roomdb_class.domain.models.User
import com.example.roomdb_class.fragments.ListFragmentDirections

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {
    private var userList = listOf<User>()

    class MyViewHolder(private val itemBinding: RecyclerItemBinding): RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(user: User) {
            itemBinding.rcId.text = user.id.toString()
            itemBinding.rcFirstName.text = user.firstName
            itemBinding.rcLastName.text = user.lastName
            itemBinding.rcAge.text = user.age.toString()

            itemBinding.recyclerUser.setOnClickListener {
                val action = ListFragmentDirections.actionListFragmentToUpdateFragment(user)
                Navigation.findNavController(itemBinding.root).navigate(action)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = RecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val user: User = userList[position]
        holder.bind(user)
    }

    fun setUserList(users: List<User>) {
        this.userList = users
        notifyDataSetChanged()
    }
}
