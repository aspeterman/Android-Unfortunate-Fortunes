package com.unfortunatefortunes.adapter
//
//import android.app.DatePickerDialog
//import android.text.Editable
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.Button
//import android.widget.EditText
//import android.widget.Spinner
//import android.widget.TextView
//import androidx.recyclerview.widget.RecyclerView
//import com.bumptech.glide.util.Util.getSnapshot
//import com.google.firebase.firestore.DocumentSnapshot
//import com.google.firebase.firestore.Query
//import com.unfortunatefortunes.R
//import com.unfortunatefortunes.model.User
//import java.text.DateFormat
//import java.text.SimpleDateFormat
//import java.util.*
//import java.util.Calendar.*
//
//
//
//class UserAdapter(query: Query) : FirestoreAdapter<UserAdapter.UserViewHolder>(query) {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.UserViewHolder {
//        return UserAdapter.UserViewHolder(
//            LayoutInflater.from(parent.context).inflate(
//                R.layout.fragment_login, parent, false
//            )
//        )
//    }
//
//    override fun onBindViewHolder(holder: UserAdapter.UserViewHolder, position: Int) {
//        getSnapshot(position)?.let { snapshot -> holder.bind(snapshot) }
//    }
//
//    val button = findViewById<Button>(R.id.button1)
//    button.setOnClickListener {view->
//        printAge(view)
//    }
//}
//
//private fun printAge(view:View){
//    var myCalendar = Calendar.getInstance()
//    var year=myCalendar.get(Calendar.YEAR)
//    var month=myCalendar.get(Calendar.MONTH)
//    var day=myCalendar.get(Calendar.DAY_OF_MONTH)
//
//    DatePickerDialog(this
//        , DatePickerDialog.OnDateSetListener{
//                view,year,month,day->
//
//            val selectedDate="$day/${month+1}/$year"
//
//            var textView1=findViewById<TextView>(R.id.textView1)
//            textView1.text= selectedDate
//
//
//
//            var dob=Calendar.getInstance()
//            dob.set(year,month,day)
//
//            var age= myCalendar.get(Calendar.YEAR) - dob.get(Calendar.YEAR)
//            if (myCalendar.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR))
//            {
//                age--
//            }
//
//            var textView2 = findViewById<TextView>(R.id.textView2)
//            textView2.text="You are $age year old"
//
//        }
//        ,year
//        ,month
//        ,day).show()
//}
//
//    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//
//        private val userEmailText: TextView = itemView.findViewById(R.id.fieldEmail)
//        private val userPasswordText: TextView = itemView.findViewById(R.id.fieldPassword)
//        private val userDOB: Editable = itemView.findViewById(R.id.textView2)
//        private val userGender: Spinner= itemView.findViewById(R.id.userGender)
//        fun bind(snapshot: DocumentSnapshot) {
//            val users: User? = snapshot.toObject(User::class.java)
//            userEmailText.text = users?.email
//            userPasswordText.text = users?.gender
//            userDOB = users?.userDOB
//            userGender.text = users?.userGender
//        }
//    }
//
//
//}
//
