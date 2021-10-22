package daveho.co.auntypasty.clearscoredonut.base

/**
 * Defines the base contract between presenter and the view
 */
class BaseContract {

    interface Presenter<in T> {
        fun subscribe()
        fun unsubscribe()
        fun attach(view: T)
    }

    interface View {

    }
}