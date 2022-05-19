package com.ubaya.advweek11.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.ubaya.advweek11.R
import com.ubaya.advweek11.databinding.FragmentStudentDetailBinding
import com.ubaya.advweek11.viewmodel.DetailViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_student_detail.*
import kotlinx.android.synthetic.main.fragment_student_detail.view.*
import kotlinx.android.synthetic.main.fragment_student_list.*
import kotlinx.android.synthetic.main.student_list_item.*
import kotlinx.android.synthetic.main.student_list_item.view.*
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * A simple [Fragment] subclass.
 * Use the [StudentDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StudentDetailFragment : Fragment(), StudentUpdateClickListener, StudentNotifClickListener {
    private lateinit var viewModel: DetailViewModel
    private lateinit var dataBinding: FragmentStudentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_student_detail, container, false)
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_student_detail, container,false)
//        dataBinding = FragmentStudentDetailBinding.inflate(inflater,container,false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        arguments?.let{
            val studentID = StudentDetailFragmentArgs.fromBundle(requireArguments()).studentID
            Log.d("tes",studentID)
            viewModel.fetch(studentID)
        }

        observeViewModel()

        dataBinding.updateClickListener = this
        dataBinding.notifClickListener = this
    }

    private fun observeViewModel(){
        viewModel.studentLiveData.observe(viewLifecycleOwner) {
            dataBinding.student = it

//            student?.let {
////                editID.setText(it.id)
////                editName.setText(it.name)
////                editDOB.setText(it.dob)
////                editPhone.setText(it.phone)
////                imageDetailStudent.loadImage(it.photoURL,progressBarDetailStudent)
////                buttonNotif.setOnClickListener {
////                    Observable.timer(5, TimeUnit.SECONDS)
////                        .subscribeOn(Schedulers.io())
////                        .observeOn(AndroidSchedulers.mainThread())
////                        .subscribe{
////                            Log.d("mynotif","Notification delayed after 5 seconds")
////                            student.name?.let { studentName ->
////                                MainActivity.showNotification(
////                                    studentName, "Notification created", R.drawable.ic_baseline_error_24)
////                            }
////                        }
////                }
//            }
        }
    }

    override fun onStudentUpdateClickListener(view: View) {
        Toast.makeText(view.context,"data updated",Toast.LENGTH_SHORT).show()
        Navigation.findNavController(view).popBackStack()
    }

    override fun onStudentNotifClickListener(view: View) {
        Observable.timer(5, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe( {
                Log.d("mynotif", "Notification delayed after 5 seconds")
                MainActivity.showNotification(
                    view.tag.toString(), "Notification created", R.drawable.ic_baseline_error_24
                )
            },{
                Log.e("error", it.message.toString())
            })
    }

}