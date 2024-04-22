package com.bpgurugram.smartdatafetcher.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.bpgurugram.smartdatafetcher.R
import com.bpgurugram.smartdatafetcher.databinding.FragmentUserDetailsBinding
import com.bpgurugram.smartdatafetcher.models.parceable.UserListItemParceable
import dagger.hilt.android.AndroidEntryPoint

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "userid"
private const val ARG_PARAM2 = "userDetails"

/**
 * A simple [Fragment] subclass.
 * Use the [UserDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class UserDetailsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
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


        var userDetails: UserListItemParceable =
            UserDetailsFragmentArgs.fromBundle(requireArguments()).UserDetails
        activity?.setTitle("User details - Id(${userDetails.userId})")

        binding.userTitle.text = userDetails.userTitle
        binding.userDetails.text = userDetails.userBody

        return binding.root
    }


}