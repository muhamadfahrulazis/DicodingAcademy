package com.fahrul.academydicoding.ui.reader.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fahrul.academydicoding.data.ModuleEntity
import com.fahrul.academydicoding.databinding.ItemsModuleListBinding

class ModuleListAdapter internal constructor(private val listener: MyAdapterClickListener)
    : RecyclerView.Adapter<ModuleListAdapter.ModuleViewHolder>(){

    private val listModules = ArrayList<ModuleEntity>()

    fun setModules(modules : List<ModuleEntity>?){
        if (modules == null) return
        this.listModules.clear()
        this.listModules.addAll(modules)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ModuleViewHolder {
        val binding = ItemsModuleListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ModuleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ModuleViewHolder, position: Int) {
        val modules = listModules[position]
        holder.bind(modules)
        holder.itemView.setOnClickListener {
            listener.onItemClicked(holder.adapterPosition, listModules[holder.adapterPosition].moduleId)
        }
    }

    override fun getItemCount(): Int = listModules.size

    class ModuleViewHolder(private val binding : ItemsModuleListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(modules: ModuleEntity) {
            with(binding){
                textModuleTitle.text = modules.title
            }
        }

    }
}

internal interface MyAdapterClickListener {
    fun onItemClicked(position: Int, moduleId: String)
}
