package com.example.mobdeveartistics.activities.feed

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mobdeveartistics.R
import com.example.mobdeveartistics.activities.library.LibraryActivity
import com.example.mobdeveartistics.activities.profile.ProfileActivity
import com.example.mobdeveartistics.adapters.FeedAdapter
import com.example.mobdeveartistics.models.DataGenerator
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.Serializable

val supabase = createSupabaseClient(
    supabaseUrl = "https://svqrgqcyrqqqgaofpdpa.supabase.co",
    supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InN2cXJncWN5cnFxcWdhb2ZwZHBhIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MzE2MzYzNTMsImV4cCI6MjA0NzIxMjM1M30.LfCcg_UkJa0FQQ9LceGSFajuOmGG-XhbN_uPiEpFM7o"
) {
    // install(Auth)
    install(Postgrest)
}

class MainActivity : AppCompatActivity() {
    private var recyclerView: RecyclerView? = null
    private var adapter: FeedAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.feed_activity_main)

        recyclerView = findViewById(R.id.recycler_view)
        recyclerView?.layoutManager = LinearLayoutManager(this)

        val dataList = DataGenerator.generateData()
        adapter = FeedAdapter(dataList)
        recyclerView?.adapter = adapter
        adapter?.notifyDataSetChanged()
    }


    fun onClickProfile(v: View?) {
        val i = Intent(applicationContext, ProfileActivity::class.java)
        startActivity(i)
    }

    // Navbar Buttons
    fun nav_home_button(v: View?) {
        val i = Intent(applicationContext, MainActivity::class.java)
        startActivity(i)
    }

    fun nav_library_button(v: View?) {
        val i = Intent(applicationContext, LibraryActivity::class.java)
        startActivity(i)
    }

    fun nav_profile_button(v: View?) {
        val i = Intent(applicationContext, ProfileActivity::class.java)
        startActivity(i)
    }
}

//@Serializable
//data class Post (
//    val id: String,
//    val user_id: String,
//    val caption: String,
//    val like_count: Int,
//    val comment_count: Int
//)
//
//@Composable
//fun PostsList() {
//   val posts = remember { mutableStateListOf<Post>() }
//    LaunchedEffect(Unit) {
//        withContext(Dispatchers.IO) {
//            val results = supabase. from("posts").select().decodeList<Post>()
//            posts.addAll(results)
//            results.forEach { post ->
//                Log.d("PostsList", "Post: $post")
//            }
//        }
//    }
//}