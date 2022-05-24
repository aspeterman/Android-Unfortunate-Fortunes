package com.unfortunatefortunes.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.R
import com.unfortunatefortunes.model.Fortune

class FortuneAdapter(query: Query) : FirestoreAdapter<FortuneAdapter.FortunesViewHolder>(query) {

    class FortunesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val title: TextView = itemView.findViewById(R.id.item_sport_title_text_view)
        private val originated: TextView = itemView.findViewById(R.id.item_sport_originated_text_view)

        fun bind(snapshot: DocumentSnapshot) {
            val fortunes: Fortune? = snapshot.toObject(Fortune::class.java)
            title.text = fortunes?.fortuneText
            originated.text = fortunes?.fortuneText
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FortunesViewHolder {
        return FortunesViewHolder(
            LayoutInflater.from(parent.context).inflate(
            R.layout.item_sport, parent, false)
        )
    }

    override fun onBindViewHolder(holder: FortunesViewHolder, position: Int) {
        getSnapshot(position)?.let { snapshot -> holder.bind(snapshot) }
    }
}