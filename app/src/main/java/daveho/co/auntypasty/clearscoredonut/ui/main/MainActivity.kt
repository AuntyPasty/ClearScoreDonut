package daveho.co.auntypasty.clearscoredonut.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import daveho.co.auntypasty.clearscoredonut.R
import daveho.co.auntypasty.clearscoredonut.di.component.DaggerActivityComponent
import daveho.co.auntypasty.clearscoredonut.di.module.ActivityModule
import daveho.co.auntypasty.clearscoredonut.entity.ScoreState
import daveho.co.auntypasty.clearscoredonut.ui.ClearScoreDataModel
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainContract.View {

    @Inject
    lateinit var presenter: MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        injectDependency()

        presenter.attach(this)

        start_button.setOnClickListener {
            presenter.startButtonPressed()
        }
    }

    override fun onDestroy() {
        presenter.unsubscribe()
        super.onDestroy()
    }

    override fun showScore(score: ClearScoreDataModel) {
        if (score.maxScore > 0 && score.score >= 0) {
            credit_score_value.text = score.score.toString()
            credit_score_value.setTextColor( getColor(R.color.score_circle))
            donutGraph.setData(score)
            credit_score_suffix.visibility = View.VISIBLE
            credit_score_suffix.text =  resources.getString(R.string.out_of_a_max_score, score.maxScore)
        } else {
            showError()
        }
    }

    override fun showError() {
        credit_score_value.text = resources.getString(R.string.score_unavailable)
        credit_score_value.setTextColor( getColor(R.color.score_unavailable))
        credit_score_suffix.visibility = View.GONE
    }

    override fun setState(state: ScoreState) {
        // TODO Do something useful.
    }

    private fun injectDependency() {
        val activityComponent = DaggerActivityComponent.builder()
            .activityModule(ActivityModule(this))
            .build()

        activityComponent.inject(this)
    }

}