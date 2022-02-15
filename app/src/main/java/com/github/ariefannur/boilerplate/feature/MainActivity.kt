package com.github.ariefannur.boilerplate.feature

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.ariefannur.boilerplate.core.base.BaseActivity
import com.github.ariefannur.boilerplate.R
import com.github.ariefannur.boilerplate.core.base.State
import com.github.ariefannur.boilerplate.core.base.inflate
import com.github.ariefannur.boilerplate.core.base.loadImage
import com.github.ariefannur.boilerplate.core.model.Contributor
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

class MainActivity : BaseActivity() {

    override fun setLayout(): Int {
       return R.layout.activity_main
    }

    override fun setUpVariable() {
        rv_main.layoutManager = LinearLayoutManager(this)
        mainViewModel.getData()
        mainViewModel.state.observe(this, Observer {
            progress.visibility = if (it == State.LOADING) View.VISIBLE else View.GONE
        })

        mainViewModel.data.observe(this, Observer {
            val (data, error) = it
            if(data != null){
                rv_main.adapter = Adapter(data)
                Timber.d("AF data : ${data[0].login}")
            }else{
                Timber.d("AF error $it")
            }
        })

    }

    class Adapter(val list: List<Contributor>) : RecyclerView.Adapter<ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(parent.context.inflate(R.layout.item_list, parent))
        }

        override fun getItemCount(): Int {
            return list.size
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.tvUser.text = list[position].login
            holder.imgUser.loadImage(list[position].avatar_url)
        }

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val tvUser by lazy {
            itemView.findViewById(R.id.tv_user) as TextView
        }

        val imgUser by lazy {
            itemView.findViewById(R.id.img_user) as ImageView
        }

    }




}
