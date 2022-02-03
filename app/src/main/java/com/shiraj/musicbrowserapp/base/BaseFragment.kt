package com.shiraj.musicbrowserapp.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
abstract class BaseFragment : Fragment() {

    private var snackBar: Snackbar? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeUI()
    }

    abstract fun subscribeUI()

    protected fun showError(msg: String, onRetry: () -> Unit) {
        view?.let {
            snackBar = Snackbar.make(it, msg, Snackbar.LENGTH_INDEFINITE)
            snackBar?.setAction("RETRY") {
                snackBar?.dismiss()
                onRetry.invoke()
            }
            snackBar?.show()
        }
    }

    protected fun showMessage(msg: String) {
        view?.let {
            hideError()
            snackBar = Snackbar.make(it, msg, Snackbar.LENGTH_SHORT)
            snackBar?.show()
        }
    }

    protected fun hideError() {
        snackBar?.dismiss()
    }

}