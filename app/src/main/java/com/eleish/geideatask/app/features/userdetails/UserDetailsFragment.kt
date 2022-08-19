package com.eleish.geideatask.app.features.userdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.eleish.geideatask.app.core.BindingFragment
import com.eleish.geideatask.app.core.extensions.setGone
import com.eleish.geideatask.databinding.FragmentUserDetailsBinding
import com.eleish.geideatask.entities.User

class UserDetailsFragment : BindingFragment<FragmentUserDetailsBinding>() {

    private val args: UserDetailsFragmentArgs by navArgs()
    private val viewModel: UserDetailsViewModel by viewModels {
        UserDetailsViewModelFactory(args.userId)
    }

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentUserDetailsBinding
        get() = FragmentUserDetailsBinding::inflate


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.run {
            loading.observe(viewLifecycleOwner) {
                binding.loadingPb.setGone(it.not())
            }
            user.observe(viewLifecycleOwner) {
                populateUserData(it)
            }
        }
    }

    private fun populateUserData(user: User) {
        with(binding) {
            context?.let {
                Glide.with(it).load(user.avatar).into(userAvatarIv)
            } ?: return
            nameTV.text = user.fullName
            emailTV.text = user.email
        }
    }
}