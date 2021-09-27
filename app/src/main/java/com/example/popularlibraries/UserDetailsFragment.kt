package com.example.popularlibraries

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.popularlibraries.databinding.FragmentDetailsBinding
import by.kirich1409.viewbindingdelegate.viewBinding
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserDetailsFragment (private val user : GithubUser = GithubUser("UserLogin")) :
    MvpAppCompatFragment(R.layout.fragment_details), BackButtonListener, UserDetailsView  {

    companion object {
        fun newInstance(user: GithubUser) = UserDetailsFragment(user)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.back.setOnClickListener { backPressed() }
    }

    private val presenter by moxyPresenter { UserDetailsPresenter(App.router, user) }

    private val viewBinding: FragmentDetailsBinding by viewBinding()

    override fun backPressed() = presenter.backPressed()

    override fun setUserName (name : String) {
        viewBinding.detailsName.text = name
        Toast.makeText(context, "Name $name set from presenter", Toast.LENGTH_SHORT).show()
    }
}