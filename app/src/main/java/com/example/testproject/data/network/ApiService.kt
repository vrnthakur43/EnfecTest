package com.example.testproject.data.network

import com.example.testproject.data.model.postModel.PostDTO
import com.example.testproject.data.model.userModel.UserModelDTO
import retrofit2.http.GET

interface ApiService {

    @GET("/posts")
    suspend fun getPost(): List<PostDTO>

    @GET("/users")
    suspend fun getUser(): List<UserModelDTO>
}