package com.example.learningproject.carousel

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.learningproject.databinding.CarouselHolderLayoutBinding


class CarouselAdapter(private var context: Context, private var mList: List<ImageModel>) :
    RecyclerView.Adapter<CarouselAdapter.ViewHolder>() {

    private var onItemClickListener: OnItemClickListener? = null

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val binding =
            CarouselHolderLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(mList[position]) {
                Glide
                    .with(context)
                    .load(this.imageUrl)
                    .centerCrop()
                    .into(binding.carouselImage);

//                binding.carouselImage.tooltipText = this.imageUrl
                binding.root.setOnClickListener { view ->
                    onItemClickListener!!.onClick(
                        binding.carouselImage, mList.get(position).imageUrl
                    )
                }
            }
        }
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(val binding: CarouselHolderLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener?) {
        this.onItemClickListener = onItemClickListener
    }


    interface OnItemClickListener {
        fun onClick(imageView: ImageView?, url: String?)
    }

    fun updateList(list : List<ImageModel>) {
        this.mList = list
    }

}