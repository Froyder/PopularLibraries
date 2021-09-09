package com.example.popularlibraries

class MainPresenter(val view: MainView) {
    val model = CountersModel()

    fun buttonOneClicked () {
        val nextValue = model.next(0)
        view.setButtonOneText(nextValue.toString())
    }

    fun buttonTwoClicked () {
        val nextValue = model.next(1)
        view.setButtonTwoText(nextValue.toString())
    }

    fun buttonThreeClicked () {
        val nextValue = model.next(2)
        view.setButtonThreeText(nextValue.toString())
    }

}