package com.bpgurugram.smartdatafetcher.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bpgurugram.smartdatafetcher.MainActivity
import com.bpgurugram.smartdatafetcher.R
import com.bpgurugram.smartdatafetcher.api.ApiClient
import com.bpgurugram.smartdatafetcher.api.UserApi
import com.bpgurugram.smartdatafetcher.models.UserList
import com.bpgurugram.smartdatafetcher.models.UserListItem
import com.bpgurugram.smartdatafetcher.models.parceable.UserListItemParceable
import com.bpgurugram.smartdatafetcher.repository.UserRepository

import com.bpgurugram.smartdatafetcher.utility.IUserClickItem
import com.bpgurugram.smartdatafetcher.viewmodel.MainViewModel
import com.bpgurugram.smartdatafetcher.viewmodel.MainViewModelFactory
import com.bpgurugram.smartdatafetcher.views.adapters.MyUsersRecyclerViewAdapter
import dagger.hilt.android.AndroidEntryPoint

/**
 * A fragment representing a list of Items.
 */

@AndroidEntryPoint
class UsersListFragment : Fragment() {

    lateinit var mainViewModel: MainViewModel
    lateinit var repository: UserRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_users_list_list, container, false)

        (activity as MainActivity).showLoading()
        var userApi = ApiClient().getApi().create(UserApi::class.java)
        repository = UserRepository(userApi)
        mainViewModel =
            ViewModelProvider(this, MainViewModelFactory(repository)).get(MainViewModel::class.java)
        mainViewModel.userLiveData.observe(viewLifecycleOwner, Observer {
            if (view is RecyclerView) {

                loadRecyclerView(view, it)
            }
        })


        return view
    }

    fun loadRecyclerView(view: RecyclerView, userList: UserList) {
        with(view) {
            view.setHasFixedSize(true)
            view.itemAnimator = null

            if (adapter == null) {
                layoutManager = LinearLayoutManager(context)
                (activity as MainActivity).hideLoading()
                adapter = MyUsersRecyclerViewAdapter(userList, object : IUserClickItem {
                    override fun itemSelected(item: UserListItem) {
                        openUserDetailsFragment(item)
                    }

                    override fun onScrollComplete(scrollStatus: Boolean) {

                        (activity as MainActivity).showLoading()

                        if (scrollStatus) {
                            mainViewModel.loadMoreItems()
                        }

                    }
                })
            } else {
                var pos: Int = adapter?.itemCount!!
                val userAdpater = (adapter as MyUsersRecyclerViewAdapter)
                pos = userAdpater.getItemCount()
                userAdpater.addMoreItems(userList)
                userAdpater.notifyItemInserted(pos)
                (activity as MainActivity).hideLoading()
                userAdpater.enableScroll()
            }
        }
    }

    fun openUserDetailsFragment(item: UserListItem) {
        var action = UsersListFragmentDirections.actionUsersListFragmentToUserDetailsFragment(
            UserListItemParceable(
                userId = item.userId.toString(),
                id = item.id.toString(),
                userTitle = item.title,
                userBody = item.body
            )
        )
        NavHostFragment.findNavController(this).navigate(action)
    }

    override fun onResume() {
        super.onResume()
        activity?.setTitle("Users")
    }

}