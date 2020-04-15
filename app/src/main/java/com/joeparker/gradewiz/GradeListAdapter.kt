package com.joeparker.gradewiz

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.joeparker.gradewiz.database.entity.Grade

class GradeListAdapter internal constructor(context: Context, owner: ViewModelStoreOwner) : RecyclerView.Adapter<GradeListAdapter.GradeViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var grades = emptyList<Grade>()

    private var gradeViewModel: GradeViewModel = ViewModelProvider(owner).get(GradeViewModel::class.java)

    inner class GradeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val gradeItemView: TextView = itemView.findViewById(R.id.textView)
        val gradeDeleteView: FloatingActionButton = itemView.findViewById(R.id.delete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GradeViewHolder =
        GradeViewHolder(inflater.inflate(R.layout.recyclerview_item, parent, false))

    override fun onBindViewHolder(holder: GradeViewHolder, position: Int) {
        val current = grades[position]

        var noteText = current.note
        val gradeNumber = position + 1
        if (noteText.isNullOrEmpty()) noteText = "Grade $gradeNumber"

        val gradeText = noteText + ":  " + current.mark + "%"
        holder.gradeItemView.text = gradeText

        holder.gradeDeleteView.setOnClickListener {
            gradeViewModel.deleteGrade(current)
        }
    }

    internal fun setGrades(grades: List<Grade>) {
        this.grades = grades
        notifyDataSetChanged()
    }

    override fun getItemCount() = grades.size
}