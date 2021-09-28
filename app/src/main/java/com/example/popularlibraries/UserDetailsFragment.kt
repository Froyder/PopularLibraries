package com.example.popularlibraries

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.popularlibraries.databinding.FragmentDetailsBinding
import com.example.popularlibraries.databinding.FragmentUsersBinding
import kotlinx.android.synthetic.main.fragment_details.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserDetailsFragment (private val user : GithubUser? = null) :
    MvpAppCompatFragment(R.layout.fragment_details), BackButtonListener, UserDetailsView {

    companion object {
        fun newInstance(user: GithubUser?) = UserDetailsFragment(user)
    }

    private val presenter by moxyPresenter {
        UserDetailsPresenter(App.router, user)
    }

    private val viewBinding: FragmentDetailsBinding by viewBinding()

    override fun backPressed() = presenter.backPressed()

    override fun setUserData (user : GithubUser) {
        val login = user.login
        viewBinding.detailsName.text = user.login
        user.avatarUrl?.let { GlideImageLoader().loadInto(it,details_userImage) }
        Toast.makeText(context, "User $login set from presenter", Toast.LENGTH_SHORT).show()
    }
}