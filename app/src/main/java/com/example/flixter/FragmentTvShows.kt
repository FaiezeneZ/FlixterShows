package com.example.flixter

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.ContentLoadingProgressBar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.RequestParams
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.Headers
import org.json.JSONArray

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val API_KEY = "a07e22bc18f5cb106bfe4cc1f83ad8ed"


/**
 * A simple [Fragment] subclass.
 * Use the [FragmentTvShows.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentTvShows : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i("Debug", "Fragment Begin")
        val view = inflater.inflate(R.layout.fragment_tv_shows, container, false)
        val progressBar = view.findViewById<View>(R.id.progress) as ContentLoadingProgressBar
        val recyclerView = view.findViewById<View>(R.id.list) as RecyclerView
        val context = view.context
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        Log.i("Debug", "Fragment End")
        updateAdapter(progressBar, recyclerView)
        return view
    }

    private fun updateAdapter(progressBar: ContentLoadingProgressBar, recyclerView: RecyclerView) {
        Log.i("Debug", "Update Adapter 1")
        progressBar.show()

        // Create and set up an AsyncHTTPClient() here
        val client = AsyncHttpClient()
        val params = RequestParams()
        params["api-key"] = API_KEY
        client["https://api.themoviedb.org/3/trending/tv/day?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed", params, object :
            JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers, json: JSON) {
                Log.i("On Sucess","Successful")
                progressBar.hide()

                val resultsJSON: JSONArray = json.jsonObject.getJSONArray("results") as JSONArray
                Log.i("On Success", "$resultsJSON")
                val showsRawJSON: String = resultsJSON.toString()
                val gson = Gson()

                val arrayShowsType = object : TypeToken<List<show>>() {}.type
                val models: List<show> = gson.fromJson(showsRawJSON, arrayShowsType)

                recyclerView.adapter= showRecycleViewAdapter(models, this@FragmentTvShows)
                    //showRecycleViewAdapter(models, this@FragmentTvShows)
            }

            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                response: String,
                throwable: Throwable?
            ) {
                Log.i("Debug", "Failed")
            }
        }]
    }
}