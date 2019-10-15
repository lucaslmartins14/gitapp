package com.example.gitapp.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gitapp.R
import com.example.gitapp.entity.Repository
import kotlinx.android.synthetic.main.item_repository.view.*

class RepositoryAdapter(private val context: Context, private var repositoryList: List<Repository>) :
    RecyclerView.Adapter<RepositoryAdapter.RepositoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_repository, parent, false)
        return RepositoryViewHolder(view)
    }

    override fun getItemCount() = repositoryList.size

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        holder.bindView(repositoryList.get(position))
    }

    class RepositoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvRepoName = itemView.tv_name
        fun bindView(repository: Repository) {
            tvRepoName.text = repository.name
        }
    }
}