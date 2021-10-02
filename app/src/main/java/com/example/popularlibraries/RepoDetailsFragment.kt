package com.example.popularlibraries

import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.popularlibraries.databinding.FragmentRepoDetailsBinding
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class RepoDetailsFragment (
    private val networkStatus : AndroidNetworkStatus,
    private val user: GithubUser,
    private val repo: UserRepo
    ) :
    MvpAppCompatFragment(R.layout.fragment_repo_details), RepoView, BackButtonListener {

    private val viewBinding: FragmentRepoDetailsBinding by viewBinding()

    private val presenter by moxyPresenter {
        RepoDetailsPresenter(
            networkStatus,
            repo,
            user,
            CiceroneObject.router,
            AndroidScreens(),
        )
    }

    companion object {
        fun newInstance(networkStatus : AndroidNetworkStatus, user: GithubUser, repo: UserRepo): Fragment
            = RepoDetailsFragment(networkStatus, user, repo)
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