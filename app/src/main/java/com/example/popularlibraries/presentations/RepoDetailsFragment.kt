package com.example.popularlibraries.presentations

import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.popularlibraries.*
import com.example.popularlibraries.data.GithubUser
import com.example.popularlibraries.data.UserRepo
import com.example.popularlibraries.databinding.FragmentRepoDetailsBinding
import com.example.popularlibraries.presentations.abs.AbsFragment
import com.example.popularlibraries.presenters.RepoDetailsPresenter
import com.github.terrakok.cicerone.Router
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class RepoDetailsFragment(
    private val user: GithubUser,
    private val repo: UserRepo
    ) :
    AbsFragment(R.layout.fragment_repo_details), RepoView, BackButtonListener {

    private val viewBinding: FragmentRepoDetailsBinding by viewBinding()

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var screens: AndroidScreens

    private val presenter by moxyPresenter {
        RepoDetailsPresenter(
            repo,
            user,
            router,
            screens,
        )
    }

    companion object {
        fun newInstance(user: GithubUser, repo: UserRepo): Fragment
            = RepoDetailsFragment(user, repo)
    }

    override fun setRepoDetails(repo: UserRepo) {
        viewBinding.repoID.text = "Repo ID: " + repo.id.toString()
        viewBinding.repoName.text = "Repo name: " + repo.name
        viewBinding.repoLanguage.text = "Language: " + repo.language
        viewBinding.repoForks.text = "Forks: " + repo.forks.toString()
        viewBinding.repoCreatedAt.text = "Created at: " + repo.createdAt
    }

    override fun backPressed(): Boolean = presenter.backPressed()

}