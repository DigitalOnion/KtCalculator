package com.outerspace.ktcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var recycler: RecyclerView
    private lateinit var display: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        display = findViewById(R.id.display) as TextView
        recycler = findViewById(R.id.recycler) as RecyclerView
        recycler.layoutManager = GridLayoutManager(this, 4, RecyclerView.VERTICAL, false)
        recycler.adapter = MyAdapter(resources.getStringArray(R.array.calculator_keys)) {key : String ->  processKey(key) };
    }

    fun processKey(key : String) {
        display.text = key
    }
}

private class MyAdapter(
    private val keyFaces: Array<String>,
    private val listener : (key: String) -> Unit)
    : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    val BUTTON_CLASS_NAME = "android.widget.Button"

    class MyViewHolder(val button: Button) : RecyclerView.ViewHolder(button)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.MyViewHolder {
        val button = LayoutInflater
            .from(parent.context).createView(BUTTON_CLASS_NAME, null, null) as Button;
        return MyViewHolder(button)
    }

    override fun getItemCount() = keyFaces.size

    override fun onBindViewHolder(holder: MyAdapter.MyViewHolder, position: Int) {
        holder.button.text = keyFaces[position]
        holder.button.setOnClickListener { listener(keyFaces[position])}
    }
}