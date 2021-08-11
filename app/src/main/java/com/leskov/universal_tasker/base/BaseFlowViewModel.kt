package com.leskov.universal_tasker.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 *  Created by Android Studio on 8/7/2021 1:05 AM
 *  Developer: Sergey Leskov
 */

@ExperimentalCoroutinesApi
abstract class BaseFlowViewModel<S, A, E>: ViewModel() {

    protected val disposables: CompositeDisposable = CompositeDisposable()

    private val TAG = BaseFlowViewModel::class.java.simpleName
    private val _viewStates: MutableStateFlow<S?> = MutableStateFlow(null)
    fun viewStates(): StateFlow<S?> = _viewStates

    protected var viewState: S
        get() = _viewStates.value
            ?: throw UninitializedPropertyAccessException("\"viewState\" was queried before being initialized")
        set(value) {
            /** StateFlow doesn't work with same values */
            if (_viewStates.value == value) {
                _viewStates.value = null
            }

            _viewStates.value = value
        }

    private val _viewActions: MutableStateFlow<A?> = MutableStateFlow(null)
    fun viewActions(): StateFlow<A?> = _viewActions

    protected var viewAction: A
        get() = _viewActions.value ?: throw UninitializedPropertyAccessException("\"viewAction\" was queried before being initialized")
        set(value) {
            /** StateFlow doesn't work with same values */
            if (_viewActions.value == value) {
                _viewActions.value = null
            }

            _viewActions.value = value
        }

    abstract fun obtainEvent(viewEvent: E)

    override fun onCleared() {
        disposables.clear()
    }
}