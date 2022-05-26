package com.unfortunatefortunes.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.Query
import com.unfortunatefortunes.R
import com.unfortunatefortunes.model.Fortune

class FortuneAdapter(query: Query) : FirestoreAdapter<FortuneAdapter.FortuneViewHolder>(query) {

    class FortuneViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val fortuneText: TextView = itemView.findViewById(R.id.item_fortune_fortuneText_text_view)
        private val gender: TextView = itemView.findViewById(R.id.item_fortune_gender_text_view)
        private val rating: TextView = itemView.findViewById(R.id.item_fortune_rating_text_view)

        fun bind(snapshot: DocumentSnapshot) {
            val fortunes: Fortune? = snapshot.toObject(Fortune::class.java)
            fortuneText.text = fortunes?.fortuneText
            gender.text = fortunes?.gender
            rating.text = fortunes?.rating
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FortuneViewHolder {
        return FortuneViewHolder(
            LayoutInflater.from(parent.context).inflate(
            R.layout.fragment_fortune, parent, false)
        )
    }

    override fun onBindViewHolder(holder: FortuneViewHolder, position: Int) {
        getSnapshot(position)?.let { snapshot -> holder.bind(snapshot) }
    }
}

