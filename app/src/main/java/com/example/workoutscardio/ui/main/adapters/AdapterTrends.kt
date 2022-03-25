package com.example.workoutscardio.ui.main.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.workoutscardio.R
import com.example.workoutscardio.data.model.WorkoutModel
import com.example.workoutscardio.databinding.ItemTagsBinding
import com.example.workoutscardio.databinding.ItemTrendingBinding
import com.example.workoutscardio.ui.main.videostrends.ActivityVideo
import com.example.workoutscardio.ui.main.viewholders.TagsViewHolder
import com.example.workoutscardio.ui.main.viewholders.TrendsViewHolder
import com.example.workoutscardio.ui.videoplayer.ActivityVideoPlayer

class AdapterTrends(
    private val trendingList: MutableList<WorkoutModel>,
    private val context: Context,
) : RecyclerView.Adapter<TrendsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendsViewHolder {
        return TrendsViewHolder(
            ItemTrendingBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TrendsViewHolder, position: Int) {
        val trendsModel = trendingList[position]

        holder.binding.workoutName.text = trendsModel.getName()
        holder.binding.tvTime.text = trendsModel.getDuration() + "mins"


        val options: RequestOptions = RequestOptions()
            .centerCrop()
            .placeholder(R.drawable.loading)
            .error(R.drawable.warning)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .priority(Priority.HIGH)
            .dontAnimate()
            .dontTransform()

        Glide.with(context).load(trendsModel.getVideoUrl())
            .apply(options)
            .into(holder.binding.videoView)

        holder.binding.parentlayout.setOnClickListener {
            openVideo(trendsModel)
        }
    }

    private fun openVideo(workoutModel: WorkoutModel) {
        val intent = Intent(context, ActivityVideoPlayer::class.java)
        intent.putExtra("videourl", workoutModel.getVideoUrl())
        context.startActivity(intent)
    }

    override fun getItemCount(): Int {
        return trendingList.size
    }
}