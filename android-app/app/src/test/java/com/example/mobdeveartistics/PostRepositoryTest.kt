package com.example.mobdeveartistics

import PostRepository
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.from
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.whenever
import kotlinx.coroutines.runBlocking

data class Post(
    val id: String,
    val user_id: String,
    val caption: String,
    val like_count: Int,
    val comment_count: Int
)

class PostRepositoryTest() {

    private val supabaseClient: SupabaseClient = Mockito.mock(SupabaseClient::class.java)
    private val postRepository = PostRepository(supabaseClient)

    @Test
    fun `fetchPosts returns list of posts`() = runBlocking {
        // Given
        val expectedPosts = listOf(
            Post("1", "user1", "Caption 1", 10, 5),
            Post("2", "user2", "Caption 2", 20, 10)
        )
        whenever(supabaseClient.from("posts").select().decodeList<Post>()).thenReturn(expectedPosts)

        // When
        val posts = postRepository.fetchPosts()

        // Then
        assertEquals(expectedPosts, posts)
    }

    @Test
    fun `fetchPosts returns empty list on error`() = runBlocking {
        // Given
        whenever(supabaseClient.from("posts").select().decodeList<Post>()).thenThrow(RuntimeException("Network error"))

        // When
        val posts = postRepository.fetchPosts()

        // Then
        assertTrue(posts.isEmpty())
    }
}