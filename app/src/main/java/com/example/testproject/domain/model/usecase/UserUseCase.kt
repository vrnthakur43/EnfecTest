package com.example.testproject.domain.model.usecase

import com.example.testproject.common.Resource
import com.example.testproject.data.model.UserModel
import com.example.testproject.data.model.userModel.UserModelDTO
import com.example.testproject.data.repository.PostRepository
import com.example.testproject.data.repository.UserRepository
import com.example.testproject.domain.model.Post
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class UserUseCase(val postRepository: PostRepository, val userRepository: UserRepository) {

    operator fun invoke(): Flow<Resource<List<UserModel>>> = flow {
        try {
            emit(Resource.LOADING())
            var userList: List<UserModelDTO> = userRepository.getUser()
            var postList: List<Post> = postRepository.getPost()

            val postMap = postList.associateBy { it.id }
            val useMainList = userList.mapNotNull { user ->
                postMap[user.id]?.let { post ->
                    UserModel(
                        id = user.id,
                        title = post.title,
                        lat = user.address.geo.lat,
                        lng = user.address.geo.lng,
                        companyName = user.company.name
                    )
                }
            }
            emit(Resource.SUCCESS(data = useMainList))
        } catch (e: Exception) {
            emit(Resource.ERROR(message = e.message.toString()))
        }

    }.flowOn(Dispatchers.IO)
}