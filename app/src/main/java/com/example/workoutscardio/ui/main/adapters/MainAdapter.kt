package com.example.workoutscardio.ui.main.adapters

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.workoutscardio.R
import com.example.workoutscardio.data.model.WorkoutModel
import com.example.workoutscardio.databinding.ItemWorkoutsBinding
import com.example.workoutscardio.room.entity.FavouriteEntity
import com.example.workoutscardio.ui.main.videostrends.ActivityVideo
import com.example.workoutscardio.ui.main.viewholders.MainViewHolder
import com.example.workoutscardio.ui.videoplayer.ActivityVideoPlayer
import com.google.gson.Gson
import timber.log.Timber


class MainAdapter(
    private val workoutList: MutableList<WorkoutModel>,
    private val context: Context,
    private val favList: MutableList<FavouriteEntity>,
    val callBack: (position: Int) -> Unit

) : RecyclerView.Adapter<MainViewHolder>() {
    private var favourite_items: SparseBooleanArray = SparseBooleanArray()
    private var tagsList: MutableList<String> = mutableListOf()
    private lateinit var adapter: AdapterTags

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            ItemWorkoutsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val workoutModel = workoutList[position]


        splitTags(position)
        Timber.d("splitted string are ${Gson().toJson(tagsList)}")

        adapter = AdapterTags(tagsList)
        holder.binding.tagsRecycler.adapter = adapter

        holder.binding.tvTime.text = workoutModel.getDuration() + "mins"
        holder.binding.workoutName.text = workoutModel.getName()
        holder.binding.workoutDesc.text = workoutModel.getDescription()
        holder.binding.tvViews.text = workoutModel.getViews()

        favList.forEach {
            if (it.id == workoutModel.getId()?.toInt()) {
                favourite_items.put(position, true)
            }

        }

        Timber.d("url of video is " + workoutModel.getVideoUrl())

        val options: RequestOptions = RequestOptions()
            .centerCrop()
            .placeholder(R.drawable.loading)
            .error(R.drawable.warning)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .priority(Priority.HIGH)
            .dontAnimate()
            .dontTransform()

        Glide.with(context).load(workoutModel.getVideoUrl())
            .apply(options)
            .into(holder.binding.videoView)

        holder.binding.parentLayout.setOnClickListener {
            openVideo(workoutModel)
        }

        holder.binding.iconFav.setOnClickListener {
            callBack(position)
        }

        toggalSelector(position, holder, holder.itemView.context)

        holder.binding.shareIcon.setOnClickListener {
            shareWorkout(workoutModel)

        }

    }

    private fun splitTags(position: Int) {
        tagsList.clear()
        workoutList[position].getTags()?.split(",")?.let { tagsList.addAll(it) }
    }

    private fun toggalSelector(
        position: Int,
        holder: MainViewHolder,
        context: Context
    ) {
        if (favourite_items.get(position, false)) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                holder.binding.iconFav.setImageDrawable(
                    ContextCompat.getDrawable(
                        context,
                        R.drawable.ic_baseline_favorite_24
                    )
                )

            } else

                holder.binding.iconFav.setImageDrawable(
                    ContextCompat.getDrawable(
                        context,
                        R.drawable.ic_baseline_favorite_24
                    )
                )

        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                holder.binding.iconFav.setImageDrawable(
                    ContextCompat.getDrawable(
                        context,
                        R.drawable.ic_fav
                    )
                )
            } else {
                holder.binding.iconFav.setImageDrawable(
                    ContextCompat.getDrawable(
                        context,
                        R.drawable.ic_fav
                    )
                )

            }
        }
    }


    private fun openVideo(workoutModel: WorkoutModel) {
        val intent = Intent(context, ActivityVideo::class.java)
        intent.putExtra("workoutmodel", workoutModel)
        context.startActivity(intent)
    }

    private fun shareWorkout(workoutModel: WorkoutModel) {
        try {
            val i = Intent(Intent.ACTION_SEND)
            i.type = "text/plain"
            i.putExtra(Intent.EXTRA_SUBJECT, workoutModel.getName())
            i.putExtra(
                Intent.EXTRA_TEXT,
                workoutModel.getDescription() + "\n" + workoutModel.getVideoUrl()
            )
            context.startActivity(Intent.createChooser(i, "Share URL"))
        } catch (exception: ActivityNotFoundException) {
            Toast.makeText(
                context, "no activity found to handle this intent",
                Toast.LENGTH_LONG
            )
                .show()
        }
    }

    override fun getItemCount(): Int {
        return workoutList.size
    }


    fun toggleSelection(pos: Int) {
        if (favourite_items[pos, false]) {
            favourite_items.delete(pos)

        } else {
            favourite_items.put(pos, true)


        }
        notifyDataSetChanged()
    }


    fun getSelectedItemCount(): Int {

        return favourite_items.size()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

}