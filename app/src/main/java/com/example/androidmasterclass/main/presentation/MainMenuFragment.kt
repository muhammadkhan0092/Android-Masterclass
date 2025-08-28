package com.example.androidmasterclass.main.presentation

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidmasterclass.R
import com.example.androidmasterclass.common.presentation.adapters.MainMenuAdapter
import com.example.androidmasterclass.common.presentation.models.DataMainMenu
import com.example.androidmasterclass.databinding.MainMenuFragmentBinding
import com.example.androidmasterclass.modules.api_integration.presentation.views.ProductsActivity
import com.example.androidmasterclass.modules.firebase_realtime.presentation.views.activity.FirebaseMainActivity
import com.example.androidmasterclass.modules.firestore.presentation.views.activity.FirestoreMainActivity
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
                getString(R.string.retrofit)-> startProductsActivity()
                getString(R.string.firestore)-> startFirestoreActivity()
                else -> startFirebaseActivity()
            }
        }
    }

    private fun startFirebaseActivity() {
        val intent = Intent(requireContext(), FirebaseMainActivity::class.java)
        startActivity(intent)
    }

    private fun startFirestoreActivity() {
        val intent = Intent(requireContext(), FirestoreMainActivity::class.java)
        startActivity(intent)
    }

    private fun startProductsActivity() {
        val intent = Intent(requireContext(), ProductsActivity::class.java)
        startActivity(intent)
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
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
        val mainMenuData = returnMainMenuData()
        mainMenuAdapter.differ.submitList(mainMenuData)
    }

    private fun returnMainMenuData(): List<DataMainMenu> {
        return listOf(
            DataMainMenu(getString(R.string.coroutines)),
            DataMainMenu(getString(R.string.room_db)),
            DataMainMenu(getString(R.string.retrofit)),
            DataMainMenu(getString(R.string.firestore)),
            DataMainMenu(getString(R.string.firebase))
        )
    }


    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }
}