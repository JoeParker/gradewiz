package com.joeparker.gradewiz

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.joeparker.gradewiz.database.entity.Grade

class GradeListAdapter internal constructor(context: Context) : RecyclerView.Adapter<GradeListAdapter.GradeViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var grades = emptyList<Grade>()

    inner class GradeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val gradeItemView: TextView = itemView.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GradeViewHolder =
        GradeViewHolder(inflater.inflate(R.layout.recyclerview_item, parent, false))

    override fun onBindViewHolder(holder: GradeViewHolder, position: Int) {
        val current = grades[position]
        holder.gradeItemView.text = current.name
    }

    internal fun setGrades(grades: List<Grade>) {
        this.grades = grades
        notifyDataSetChanged()
    }

    override fun getItemCount() = grades.size
}