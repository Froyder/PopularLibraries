package com.example.popularlibraries

class MainPresenter(val view: MainView, val model : CountersModel) {

    fun buttonOneClicked () {
        model.counters[0]++
        view.setCounterOne(model.counters[0].toString())
    }

    fun buttonTwoClicked () {
        model.counters[1]++
        view.setCounterTwo(model.counters[1].toString())
    }

    fun buttonThreeClicked () {
        model.counters[2]++
        view.setCounterThree(model.counters[2].toString())
    }

}