package com.example.androidmasterclass.coroutines

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.androidmasterclass.databinding.CoroutinesFragmentBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CoroutinesFragment : Fragment(){
    private var binding : CoroutinesFragmentBinding? = null
    companion object{
        const val COROUTINE_SCOPE = "COROUTINE SCOPE"
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CoroutinesFragmentBinding.inflate(inflater,container,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onCoroutineScopeClicked()
    }

    private fun onCoroutineScopeClicked() {
        binding?.btnCoroutineScope?.setOnClickListener {
            simulateCoroutineScope()
        }
    }

    private fun simulateCoroutineScope() {
        /*Simulates That Coroutines Does not Block the Code or Thread
         OTHERWISE KOTLIN CODE GOES LINE BY LINE AND DOES NOT CONTINUE
         TO NEXT LINE IF PREVIOUS LINE IS NOT FINISHED . THIS RESULTS IN
         UI FREEZE IF THE CODE IS BLOCKED FOR A FEW SECONDS
         THE NON BLOCKING NATURE CAN BE OBSERVED IN THE LOGS
         MOREOVER OUR APPLICATION HAS ONE MAIN THREAD SO IF WE WOULD HAVE RUN
         THIS IN OUR MAIN THREAD AGAIN OUR APP WOULD BE FREEZED.
         BUT IN OUR CASE BECAUSE WE USE IO THREAD, IT IS LETTING THE MAIN THREAD FREE WHICH CAN PERFORM OTHERS
         TASKS
         MAIN -> UI TASKS
         IO -> READING WRITING FROM FILES , API ,DATABASE WORK
         DEFAULT -> CPU PROCESSING LIKE COMPLEX CALCULATIONS
        * */
        CoroutineScope(Dispatchers.IO).launch{
            var count = 0
            while (count<11){
                for (i in 1..1000000000){

                }
                Log.d(COROUTINE_SCOPE,"Count is $count")
                count++
            }
        }
        Log.d(COROUTINE_SCOPE,"User Clicked A Button")
        Toast.makeText(requireContext(), "You Clicked A Button", Toast.LENGTH_SHORT).show()
    }
}