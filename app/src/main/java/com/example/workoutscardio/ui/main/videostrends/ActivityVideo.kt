package com.example.workoutscardio.ui.main.videostrends

import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.workoutscardio.R
import com.example.workoutscardio.data.model.WorkoutModel
import com.example.workoutscardio.data.viewmodel.WorkoutViewModel
import com.example.workoutscardio.databinding.ActivityVideoBinding
import com.example.workoutscardio.ui.main.adapters.AdapterTags
import com.example.workoutscardio.ui.main.adapters.AdapterTrends
import com.example.workoutscardio.ui.videoplayer.ActivityVideoPlayer
import com.example.workoutscardio.utils.Status
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class ActivityVideo : AppCompatActivity() {
    private var workOutList: MutableList<WorkoutModel> = mutableListOf()
    private lateinit var binding: ActivityVideoBinding
    private var workoutModel: WorkoutModel? = null
    private val tagsList: MutableList<String> = mutableListOf()
    private lateinit var adapter: AdapterTags
    private lateinit var adapterTrends: AdapterTrends


    val viewModel: WorkoutViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVideoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        handleExtrasFromIntent()

        initToolbar()

        getData()

    }

    private fun getData() {
        viewModel.fetchWorkouts().observe(this, {
            when (it.status) {
                Status.SUCCESS -> {
                    Timber.d("data coming from api is ${Gson().toJson(it)}")
                    workOutList.addAll(it.data!!)
                    workOutList.reverse()

                    adapterTrends = AdapterTrends(
                        workOutList,
                        this
                    )
                    binding.trendingRecycler.adapter = adapterTrends


                }
                Status.ERROR -> {
                    Timber.d("data coming from api is ${it.message}")
                    binding.tagsRecycler.hide()
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
                Status.LOADING -> {
                    binding.tagsRecycler.show()
                    Timber.d("Loading Status")

                }
            }
        })
    }

    fun View.show() {
        this.visibility = View.VISIBLE
    }

    fun View.hide() {
        this.visibility = View.GONE
    }


    private fun handleExtrasFromIntent() {
        workoutModel = intent.getParcelableExtra("workoutmodel")

        workoutModel?.let {
            binding.workoutName.text = it.getName()
            binding.workoutDesc.text = it.getDescription()
            binding.tvViews.text = it.getViews()
            binding.tvTime.text = it.getDuration() + "mins"

            val options: RequestOptions = RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.loading)
                .error(R.drawable.warning)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH)
                .dontAnimate()
                .dontTransform()

            Glide.with(this).load(it.getVideoUrl())
                .apply(options)
                .into(binding.videoView)

            binding.shareIcon.setOnClickListener { view ->
                shareWorkout(it)

            }

            binding.videoViewParent.setOnClickListener { view ->
                openVideo(it)
            }



            splitTags(it)
            adapter = AdapterTags(tagsList)
            binding.tagsRecycler.adapter = adapter
        }




    }

    private fun openVideo(workoutModel: WorkoutModel) {
        val intent = Intent(this, ActivityVideoPlayer::class.java)
        intent.putExtra("videourl", workoutModel.getVideoUrl())
        startActivity(intent)
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
            startActivity(Intent.createChooser(i, "Share URL"))
        } catch (exception: ActivityNotFoundException) {
            Toast.makeText(
                this, "no activity found to handle this intent",
                Toast.LENGTH_LONG
            )
                .show()
        }
    }


    private fun splitTags(workoutModel: WorkoutModel) {
        tagsList.clear()
        workoutModel.getTags()?.split(",")?.let { tagsList.addAll(it) }
    }


    private fun initToolbar() {
        binding.toolbar.title = workoutModel?.getName()
        setSupportActionBar(binding.toolbar)

        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            supportActionBar!!.setDisplayShowHomeEnabled(true)
        }

    }

    override fun onNavigateUp(): Boolean {
        finish()
        return true
    }
}