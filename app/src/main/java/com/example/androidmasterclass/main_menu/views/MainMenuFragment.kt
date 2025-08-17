package com.example.androidmasterclass.main_menu.views

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidmasterclass.R
import com.example.androidmasterclass.databinding.MainMenuFragmentBinding
import com.example.androidmasterclass.main_menu.adapters.MainMenuAdapter
import com.example.androidmasterclass.main_menu.model.DataMainMenu
import com.example.androidmasterclass.modules.room_db.views.RoomMainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainMenuFragment : Fragment() {
    private var binding : MainMenuFragmentBinding? = null
    private val mainMenuAdapter by lazy { MainMenuAdapter() }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MainMenuFragmentBinding.inflate(inflater,container,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupMainMenuRecyclerView()
        onMenuItemClickListener()
    }

    private fun onMenuItemClickListener() {
        mainMenuAdapter.onClick = {
            when(it){
                getString(R.string.coroutines)-> navigate(R.id.action_mainMenuFragment_to_coroutinesFragment)
                getString(R.string.room_db)-> startRoomActivity()
                else -> navigate(R.id.action_mainMenuFragment_to_coroutinesFragment)
            }
        }
    }

    private fun startRoomActivity() {
        val intent = Intent(requireContext(), RoomMainActivity::class.java)
        startActivity(intent)
    }

    private fun navigate(navigationId: Int) {
        findNavController().navigate(navigationId)
    }

    private fun setupMainMenuRecyclerView() {
        binding?.mainMenuRv?.apply {
            adapter = mainMenuAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false)
        }
        val mainMenuData = returnMainMenuData()
        mainMenuAdapter.differ.submitList(mainMenuData)
    }

    private fun returnMainMenuData(): List<DataMainMenu> {
        return listOf(
            DataMainMenu(getString(R.string.coroutines)),
            DataMainMenu(getString(R.string.room_db))
        )
    }


    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }
}