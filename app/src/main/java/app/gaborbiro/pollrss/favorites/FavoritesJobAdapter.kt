package app.gaborbiro.pollrss.favorites

import android.view.View
import android.widget.ImageView
import app.gaborbiro.pollrss.BaseJobAdapter
import app.gaborbiro.pollrss.BaseJobAdapterCallback
import app.gaborbiro.pollrss.BaseJobViewHolder
import app.gaborbiro.pollrss.R
import app.gaborbiro.pollrss.jobs.JobUIModel
import app.gaborbiro.pollrss.utils.PUSHBULLET_PACKAGE
import app.gaborbiro.pollrss.utils.isPackageInstalled
import kotlinx.android.synthetic.main.card_favorite_job.view.*

class FavoriteJobAdapter(
    private val callback: FavoritesJobAdapterCallback
) : BaseJobAdapter<FavoriteJobViewHolder>(callback, R.layout.card_favorite_job) {

    interface FavoritesJobAdapterCallback : BaseJobAdapterCallback {
        fun onShare(job: JobUIModel)
        fun onDelete(job: JobUIModel)
    }

    override fun createViewHolder(view: View) = FavoriteJobViewHolder(view)

    override fun onBindViewHolder(holder: FavoriteJobViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val job = getItem(position)
        with(holder) {
            shareBtn.setImageResource(if (shareBtn.context.isPackageInstalled(PUSHBULLET_PACKAGE)) R.drawable.ic_computer else R.drawable.ic_share)
            shareBtn.setOnClickListener {
                callback.onShare(job)
            }
            deleteBtn.setOnClickListener {
                callback.onDelete(job)
            }
        }
    }
}

class FavoriteJobViewHolder(view: View) : BaseJobViewHolder(view) {
    val shareBtn: ImageView = view.btn_share
    val deleteBtn: ImageView = view.btn_delete
}