package daveho.co.auntypasty.clearscoredonut.ui.main

import daveho.co.auntypasty.clearscoredonut.entity.ClearScoreRangeData
import daveho.co.auntypasty.clearscoredonut.ui.ClearScoreDataModel
import daveho.co.auntypasty.clearscoredonut.usecase.ClearScoreDataFetcher
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class PojoScoreResultPresenter(
    private val dataFetcher: ClearScoreDataFetcher
) : MainContract.Presenter {

    private lateinit var view: MainContract.View
    private val subscriptions = CompositeDisposable()


    override fun startButtonPressed() {
        val subscribe = dataFetcher.getScore().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ data: ClearScoreRangeData ->
                view.showScore(data.toModel())

            },{ error ->
                view.showScore(ClearScoreRangeData.INVALID.toModel())

            })

        subscriptions.add(subscribe)
    }

    override fun subscribe() {
        // We can use this fetch the data straight away if required.
    }

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: MainContract.View) {
        this.view = view
    }

    private fun ClearScoreRangeData.toModel() =
        ClearScoreDataModel(
            score = score,
            minScore = minScore,
            maxScore = maxScore
        )
}