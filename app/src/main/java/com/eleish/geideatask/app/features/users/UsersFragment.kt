package com.eleish.geideatask.app.features.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.eleish.geideatask.app.core.BindingFragment
import com.eleish.geideatask.app.core.extensions.setGone
import com.eleish.geideatask.databinding.FragmentUsersBinding

class UsersFragment : BindingFragment<FragmentUsersBinding>() {

    private var usersAdapter: UsersAdapter? = null

    private val viewModel: UsersViewModel by viewModels()

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentUsersBinding
        get() = FragmentUsersBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUsersRV()

        viewModel.run {
            loading.observe(viewLifecycleOwner) {
                binding.loadingPb.setGone(it.not())
            }
            users.observe(viewLifecycleOwner) {
                usersAdapter?.submitList(it)
            }
        }
    }

    private fun setupUsersRV() {
        with(binding.usersRv) {
            layoutManager = LinearLayoutManager(context)
            adapter = UsersAdapter {
                findNavController().navigate(UsersFragmentDirections.actionUsersFragmentToUserDetailsFragment(
                    it.id))
            }.also {
                usersAdapter = it
            }
        }
    }
}