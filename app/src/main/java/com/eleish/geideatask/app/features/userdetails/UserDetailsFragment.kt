package com.eleish.geideatask.app.features.userdetails

import android.content.ComponentName
import android.content.Context.BIND_AUTO_CREATE
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
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

    private val connection: ServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(className: ComponentName, service: IBinder) {
            val binder = service as TimerService.TimerServiceBinder

            binder.service.setTimerListener(
                onTimerTick = { remaining ->
                    binding.timerTv.text = remaining.toString()
                },
                onTimerCompleted = {
                    findNavController().popBackStack()
                }
            )

            context?.startService(Intent(context, TimerService::class.java))
        }

        override fun onServiceDisconnected(arg0: ComponentName) {
            // Do nothing
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindTimerService()

        viewModel.run {
            loading.observe(viewLifecycleOwner) {
                binding.loadingPb.setGone(it.not())
            }
            error.observe(viewLifecycleOwner) {
                Toast.makeText(context, it, Toast.LENGTH_LONG).show()
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

    private fun bindTimerService() {
        val intent = Intent(context, TimerService::class.java)
        context?.bindService(intent, connection, BIND_AUTO_CREATE)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        context?.unbindService(connection)
        context?.stopService(Intent(context, TimerService::class.java))
    }
}