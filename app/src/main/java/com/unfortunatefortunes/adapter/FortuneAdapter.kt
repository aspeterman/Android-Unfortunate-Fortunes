package com.unfortunatefortunes.adapter

//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.TextView
//import androidx.recyclerview.widget.RecyclerView
//import com.google.firebase.firestore.DocumentSnapshot
//import com.google.firebase.firestore.FirebaseFirestore
//import com.google.firebase.firestore.Query
//import com.unfortunatefortunes.FirebaseUtils.mFortunesCollection
//import com.unfortunatefortunes.R
//import com.unfortunatefortunes.State
//import com.unfortunatefortunes.model.Fortune
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.flow.catch
//import kotlinx.coroutines.flow.flow
//import kotlinx.coroutines.flow.flowOn
//import kotlinx.coroutines.tasks.await
//
//
//class FortuneAdapter(query: Query) : FirestoreAdapter<FortuneAdapter.FortuneViewHolder>(query) {
//
//    class FortuneViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//
//        var query = FirebaseFirestore.getInstance()
//            .collection("fortunes")
//            .limit(50)
//
//        private val fortuneText: TextView = itemView.findViewById(R.id.item_fortune_fortuneText_text_view)
//        private val gender: TextView = itemView.findViewById(R.id.item_fortune_gender_text_view)
//        private val rating: TextView = itemView.findViewById(R.id.item_fortune_rating_text_view)
//
//        fun bind(snapshot: DocumentSnapshot) {
//            val fortunes: Fortune? = snapshot.toObject(Fortune::class.java)
//            fortuneText.text = fortunes?.fortuneText
//            gender.text = fortunes?.gender
//            rating.text = fortunes?.rating.toString()
//        }
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FortuneViewHolder {
//        return FortuneViewHolder(
//            LayoutInflater.from(parent.context).inflate(
//            R.layout.fragment_fortune, parent, false)
//        )
//    }
//
//    override fun onBindViewHolder(holder: FortuneViewHolder, position: Int) {
//        getSnapshot(position)?.let { snapshot -> holder.bind(snapshot) }
//    }
//
//    fun getAllFortunes() = flow<State<List<Fortune>>> {
//
//        // Emit loading state
//        emit(State.loading())
//
//        val snapshot = mFortunesCollection.get().await()
//        val fortunes = snapshot.toObjects(Fortune::class.java)
//
//        // Emit success state with data
//        emit(State.success(fortunes))
//
//    }.catch {
//        // If exception is thrown, emit failed state along with message.
//        emit(State.failed(it.message.toString()))
//    }.flowOn(Dispatchers.IO)
//}

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.unfortunatefortunes.R
import com.unfortunatefortunes.model.Fortune
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.item_fortune.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class FortuneAdapter(
    private val fortunes: MutableList<Fortune>
) : RecyclerView.Adapter<FortuneAdapter.FortuneViewHolder>() {

    //an inner class that inherits from recyclerView.ViewHolder
    class FortuneViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    private val fortunesCollectionReference = Firebase.firestore.collection("fortunes")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FortuneViewHolder {
        return FortuneViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_fortune,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return fortunes.size
    }

    override fun onBindViewHolder(holder: FortuneViewHolder, position: Int) {
        val curFortune = fortunes[position]
        holder.itemView.apply {
            tvFortuneText.text = curFortune.fortuneText
//            cbDone.isChecked = curFortune.checked
//            toggleStrikeThrough(tvTodoTitle, curFortune.checked)
//            cbDone.setOnCheckedChangeListener { _, checked ->
//                toggleStrikeThrough(tvFortuneText, checked)
//                updateCheckedDatabase(curFortune)
////                curFortune.checked = !curFortune.checked
//            }
        }
    }

    fun addFortune(fortune: Fortune) {
        fortunes.add(fortune)
        notifyItemInserted(fortunes.size - 1)
    }

//    fun deleteDoneFortunes() {
//        fortunes.removeAll{fortune ->
//            fortune.fortuneText
//        }
//        notifyDataSetChanged()
//    }

    private fun toggleStrikeThrough(tvTodoTitle : TextView, checked: Boolean) {
        if (checked) {
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        } else {
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

//    private fun setCheckedChangedListener() {
//        getFortune.setOnCheckedChangeListener { buttonView, isChecked ->
//            val msg = "Toggle Button is " + if (isChecked) "ON" else "OFF"
//            Toast.makeText(this@HomeActivity, msg, Toast.LENGTH_SHORT).show()
//        }
//    }

    //needed in the fortuneAdapter since it needs to be called in the cbDone.setOnCheckedChangeListener
    private fun updateCheckedDatabase(fortune : Fortune) = CoroutineScope(Dispatchers.IO).launch {
        val tasksQuery = fortunesCollectionReference
            .whereEqualTo("title", fortune.fortuneText)
            .whereEqualTo("id", fortune.rating)
            .get().await()
        //this code will only run after the add function is finished because of the await()
        if (tasksQuery.documents.isNotEmpty()) {
            //it should just return one, unless we are very unlucky with two of the same id
//            try {
//                fortunesCollectionReference.document(tasksQuery.documents[0].id).update("checked", fortune.checked)
//            } catch (e : Exception) {
//                @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
//                Log.d("Updating a task error:", e.message)
//            }
//        } else {
//            Log.d("Updating a task error:", "No task matched the query")
        }
    }
}
