package com.example.flixter

import android.app.Activity
import android.app.PendingIntent.getActivity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import java.security.AccessController.getContext

const val SHOWS_EXTRA = "SHOW_EXTRA"
private const val TAG = "ShowAdapter"

class showRecycleViewAdapter(private val shows: List<show>,
                             private val mainFragment: FragmentTvShows,
) : RecyclerView.Adapter<showRecycleViewAdapter.ShowViewHolder>()
{
    override fun onCreateViewHolder(parent : ViewGroup, viewType : Int) : ShowViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.show, parent, false)
        return ShowViewHolder(view)
    }

    inner class ShowViewHolder(val mView: View) : RecyclerView.ViewHolder(mView),
    View.OnClickListener {
        var mItem: show? = null
        val mShowTitle: TextView = mView.findViewById<View>(R.id.tv_show_name) as TextView
        val mShowCover: ImageView = mView.findViewById(R.id.show_pic) as ImageView
        //val activity : Activity = requireActivity()

        override fun toString(): String {
            return mShowTitle.toString()
        }

        init {
            mView.setOnClickListener(this)
        }

        //val activity : Activity = getContext()
        override fun onClick(v: View?) {

            // TODO: Get selected article
            val show = shows[absoluteAdapterPosition]
            // TODO: Navigate to Details screen and pass selected article
            val intent = Intent(mainFragment.context, DetailActivity::class.java)
            intent.putExtra(SHOWS_EXTRA, show)
            mainFragment.context?.startActivity(intent)
        }

        /*fun bind(Show: show) {
            mShowTitle.text = Show.title
            Log.i("In Bind()", "${mShowTitle.text}")

            Glide.with(mainFragment.requireContext())
                .load(Show.showCoverUrl)
                .into(mShowCover)
        }*/
    }

    override fun onBindViewHolder(holder: ShowViewHolder, position: Int) {
        val show = shows[position]
        //holder.bind(show)

        holder.mItem = show
        holder.mShowTitle.text = show.title.toString()

        Log.i("in OnBindViewHolder()", "${holder.mShowTitle.text}")

        Glide.with(holder.mView)
            .load("https://image.tmdb.org/t/p/w500/" + show.showCoverUrl)
            .centerInside()
            .into(holder.mShowCover)


    }

    override fun getItemCount(): Int {
        return shows.size
    }

}
