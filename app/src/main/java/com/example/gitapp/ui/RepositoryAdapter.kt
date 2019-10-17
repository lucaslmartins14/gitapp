package com.example.gitapp.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gitapp.R
import com.example.gitapp.model.entity.GR
import kotlinx.android.synthetic.main.item_repository.view.*

class RepositoryAdapter(private val context: Context) :
    RecyclerView.Adapter<RepositoryAdapter.RepositoryViewHolder>() {
    private var GRList: List<GR> = emptyList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_repository, parent, false)
        return RepositoryViewHolder(view)
    }

    fun setGrs(GRList: List<GR>){
        this.GRList = GRList
        notifyDataSetChanged()
    }
    override fun getItemCount() = GRList.size

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        holder.bindView(GRList.get(position))
    }

    class RepositoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvRepoName = itemView.tv_name
        val ivAuthor = itemView.iv_author
        val tvStars = itemView.tv_stars
        val tvAuthor = itemView.tv_author
        val tvForks = itemView.tv_forks
        fun bindView(gr: GR) {
            tvRepoName.text = gr.name
            Glide.with(itemView).load(gr.owner.avatar_url).into(ivAuthor)
            tvStars.text = gr.stargazers_count.toString()
            tvAuthor.text = gr.owner.login
            tvForks.text = "Forks: " + gr.forks_count.toString()
        }
    }
}