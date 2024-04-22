package com.bpgurugram.smartdatafetcher.views.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.bpgurugram.smartdatafetcher.R
import com.bpgurugram.smartdatafetcher.databinding.FragmentUserDetailsBinding
import com.bpgurugram.smartdatafetcher.models.parceable.UserListItemParceable
import dagger.hilt.android.AndroidEntryPoint
import java.util.Calendar



@AndroidEntryPoint
class UserDetailsFragment : Fragment() {

    lateinit var binding: FragmentUserDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_user_details, container, false)

        Log.w("Details user view loading end - " ,  Calendar.getInstance().time.toString())
        var userDetails: UserListItemParceable =
            UserDetailsFragmentArgs.fromBundle(requireArguments()).UserDetails
        activity?.setTitle("User details - Id(${userDetails.userId})")

        binding.userTitle.text = userDetails.userTitle
        binding.userDetails.text = userDetails.userBody

        return binding.root
    }


}