package com.example.learningproject.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.clevertap.android.sdk.displayunits.model.CleverTapDisplayUnit
import com.example.learningproject.FragmentCommunicator
import com.example.learningproject.HomePageActivity
import com.example.learningproject.R
import com.example.learningproject.carousel.CarouselAdapter
import com.example.learningproject.carousel.ImageModel
import com.example.learningproject.databinding.FragmentHomeBinding
import com.google.android.material.carousel.CarouselLayoutManager


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private lateinit var binding: FragmentHomeBinding
private lateinit var carouselAdapter: CarouselAdapter
private lateinit var carouseImageList : List<ImageModel>
private val TAG: String = HomePageActivity::class.java.getSimpleName()



/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment(), FragmentCommunicator {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home,container,false)
        binding.include.toolbarText.text = "Home"

        carouseImageList = emptyList()

        // create  layoutManager
        val layoutManager: RecyclerView.LayoutManager = CarouselLayoutManager()

        // pass it to rvLists layoutManager
        binding.carouselRecyclerView.setLayoutManager(layoutManager)

        // initialize the adapter,
        // and pass the required argument
        carouselAdapter = CarouselAdapter(requireActivity().applicationContext,carouseImageList)

        // attach adapter to the recycler view
        binding.carouselRecyclerView.adapter = carouselAdapter

        carouselAdapter.setOnItemClickListener(object : CarouselAdapter.OnItemClickListener {
            override fun onClick(imageView: ImageView?, url: String?) {
                //Do something like opening the image in new activity or showing it in full screen or something else.
            }
        })

        if (activity is HomePageActivity) {
            (activity as HomePageActivity?)?.setFragmentListener(this)
        }

        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String = "", param2: String ="") =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun loadData(units: ArrayList<CleverTapDisplayUnit>) {
        Log.d(TAG, "loadData() called with: units = [$units]")
        for (cleverTapDisplayUnit in units) {
            //CustomKV
            val customMap = cleverTapDisplayUnit.customExtras ?: return

            if (customMap.containsKey("native_display_type") && customMap["native_display_type"] == "carousel_banner") {
                /*for (Map.Entry<String,String> entry : customMap.entrySet()) {
                    System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
                }*/
                val customImage1 : String = customMap["carousel_image_1"]!!
                val customImage2 : String = customMap["carousel_image_2"]!!
                val customImage3 : String = customMap["carousel_image_3"]!!
                val list = arrayListOf<ImageModel>()
                list.add(ImageModel(customImage1))
                list.add(ImageModel(customImage2))
                list.add(ImageModel(customImage3))

                Log.d(TAG, ("onDisplayUnitsLoaded() called with: carousel models = [" + list.size + "]"))
                carouselAdapter.updateList(list)
                carouselAdapter.notifyDataSetChanged()

            } else {
                //backend
            }
        }
    }

}