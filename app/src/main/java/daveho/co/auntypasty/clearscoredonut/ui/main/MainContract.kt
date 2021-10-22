package daveho.co.auntypasty.clearscoredonut.ui.main

import daveho.co.auntypasty.clearscoredonut.base.BaseContract
import daveho.co.auntypasty.clearscoredonut.entity.ScoreState
import daveho.co.auntypasty.clearscoredonut.ui.ClearScoreDataModel

class MainContract {

    interface View: BaseContract.View {

        fun setState(state: ScoreState)
        fun showScore(score: ClearScoreDataModel)
        fun showError()

    }

    interface Presenter: BaseContract.Presenter<View> {
        fun startButtonPressed()
    }
}


