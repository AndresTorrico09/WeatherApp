package com.distillery.interview.main

import com.distillery.interview.basemvp.BasePresenter
import com.distillery.interview.basemvp.BaseView

interface MainContract {

    interface View : BaseView

    interface Presenter : BasePresenter<View>
}
