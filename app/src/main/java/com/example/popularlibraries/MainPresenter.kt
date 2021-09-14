package com.example.popularlibraries

import moxy.MvpPresenter

class MainPresenter(private val model : CountersModel) : MvpPresenter<MainView>() {

    fun buttonOneClicked () {
        model.counters[0]++
        viewState.setCounterOne(model.counters[0].toString())
    }

    fun buttonTwoClicked () {
        model.counters[1]++
        viewState.setCounterTwo(model.counters[1].toString())
    }

    fun buttonThreeClicked () {
        model.counters[2]++
        viewState.setCounterThree(model.counters[2].toString())
    }

}