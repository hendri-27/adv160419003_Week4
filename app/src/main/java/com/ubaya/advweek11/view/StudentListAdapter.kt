package com.ubaya.advweek11.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.advweek11.databinding.StudentListItemBinding
import com.ubaya.advweek11.model.Student
import kotlinx.android.synthetic.main.student_list_item.view.*

class StudentListAdapter(val studentList:ArrayList<Student>) :RecyclerView.Adapter<StudentListAdapter.StudentViewHolder>(),
    StudentDetailClickListener {
    class StudentViewHolder(var view: StudentListItemBinding):RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = StudentListItemBinding.inflate(inflater,parent,false)

        return StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        with(holder.view){
            student = studentList[position]
            detailListener = this@StudentListAdapter
        }
//        val student = studentList[position]
//        with(holder.view){
//            textID.text = student.id
//            textName.text = student.name
//            buttonDetail.setOnClickListener {
//                val action = StudentListFragmentDirections.actionStudentDetail(student.id.toString())
//                Navigation.findNavController(it).navigate(action)
//            }
//            imageStudentPhoto.loadImage(student.photoURL,progressLoadingStudentPhoto)
//        }
    }

    override fun getItemCount() = studentList.size

    fun updateStudentList(newStudentList: ArrayList<Student>){
        studentList.clear()
        studentList.addAll(newStudentList)
        notifyDataSetChanged()
    }

    override fun onStudentDetailClick(view: View) {
        val action = StudentListFragmentDirections.actionStudentDetail(view.tag.toString())
        Navigation.findNavController(view).navigate(action)
    }
}