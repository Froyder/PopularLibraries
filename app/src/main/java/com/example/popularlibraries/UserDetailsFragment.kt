package com.example.popularlibraries

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import com.example.popularlibraries.databinding.FragmentDetailsBinding
import kotlinx.android.synthetic.main.fragment_details.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserDetailsFragment (val user : GithubUser? = null) :
    MvpAppCompatFragment(), BackButtonListener, UserDetailsView {

    companion object {
        fun newInstance(user: GithubUser?) = UserDetailsFragment(user)
    }

    private val presenter by moxyPresenter {
        UserDetailsPresenter(App.router, user)
    }

    private var vb: FragmentDetailsBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        FragmentDetailsBinding.inflate(inflater, container, false).also {
            vb = it
            vb?.back?.setOnClickListener { backPressed() }
            presenter
        }.root

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }

    override fun backPressed() = presenter.backPressed()

    override fun setUserData (user : GithubUser) {
        val login = user.login
        vb?.detailsName?.text = login
        user.avatarUrl?.let { GlideImageLoader().loadInto(it,details_userImage) }
        Toast.makeText(context, "Name $login set from presenter", Toast.LENGTH_SHORT).show()
    }
}