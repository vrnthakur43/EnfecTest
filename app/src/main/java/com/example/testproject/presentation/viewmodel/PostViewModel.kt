package com.example.testproject.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testproject.common.Resource
import com.example.testproject.data.model.UserModel
import com.example.testproject.domain.model.Post
import com.example.testproject.domain.model.usecase.UserUseCase
import com.example.testproject.presentation.adapter.UserAdapter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(val userUseCase: UserUseCase) : ViewModel() {

    private var _userMutableStatFlow: MutableStateFlow<Resource<List<UserModel>>> =
        MutableStateFlow<Resource<List<UserModel>>>(Resource.LOADING())

    val userStateFlow: StateFlow<Resource<List<UserModel>>> get() = _userMutableStatFlow

    val userAdapter = UserAdapter()

    fun getUserData() {
        userUseCase().onEach {
            _userMutableStatFlow.value = it
        }.launchIn(viewModelScope)
    }
}