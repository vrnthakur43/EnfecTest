package com.example.testproject.data.repositoryImpl

import com.example.testproject.data.network.ApiService
import com.example.testproject.data.repository.PostRepository
import com.example.testproject.domain.model.Post

class PostImpl(val apiService: ApiService):PostRepository {
   override suspend fun getPost(): List<Post> {
      return  apiService.getPost().map { it.toMapIntoPost() }
    }
}