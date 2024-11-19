import io.github.jan.supabase.SupabaseClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.Serializable
import android.util.Log
import io.github.jan.supabase.postgrest.from

@Serializable
data class Post (
    val id: String,
    val user_id: String,
    val caption: String,
    val likeCount: Int,
    val commentCount: Int
)

class PostRepository(private val supabaseClient: SupabaseClient) {
    suspend fun fetchPosts(): List<Post> {
        return supabaseClient.from("posts").select().decodeList<Post>()
    }
}