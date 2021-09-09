package com.example.popularlibraries

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainView {

    val presenter = MainPresenter(this, CountersModel)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnOne.setOnClickListener { presenter.buttonOneClicked() }

        btnTwo.setOnClickListener { presenter.buttonTwoClicked() }

        btnThree.setOnClickListener { presenter.buttonThreeClicked() }
    }

    override fun setCounterOne(text: String) {
        btnOne.text = text
    }

    override fun setCounterTwo(text: String) {
        btnTwo.text = text
    }

    override fun setCounterThree(text: String) {
        btnThree.text = text
    }
}