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
        const val SUSPEND_FUNCTION = "SUSPEND FUNCTION"
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
        onSuspendFunctionClicked()
    }

    private fun onSuspendFunctionClicked() {
        /*
        Coroutines work well with suspend functions.
        When a coroutine calls a suspend function and reaches a suspension point (e.g., delay, network call),
        the thread is released and can run other coroutines until they also reach a suspension point.
        When the first coroutine’s suspension is over, it resumes execution on an available thread (not necessarily the same one).
        This allows coroutines to cooperate, ensuring that threads are not left idle while waiting.
        The logs below illustrate how these coroutines interleave and work together.
        */

        binding?.btnSuspendFunctions?.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch{
                suspendSimulationOne()
            }
            CoroutineScope(Dispatchers.IO).launch{
                suspendSimulationTwo()
            }
        }
    }

    private suspend fun suspendSimulationOne(){
        delay(1000)
        Log.d(SUSPEND_FUNCTION,"SF-1 LOG-1")
        delay(1000)
        Log.d(SUSPEND_FUNCTION,"SF-1 LOG-2")
        delay(1000)
        Log.d(SUSPEND_FUNCTION,"SF-1 LOG-3")
        delay(6000)
        Log.d(SUSPEND_FUNCTION,"SF-1 LOG-4")
    }

    private suspend fun suspendSimulationTwo(){
        delay(500)
        Log.d(SUSPEND_FUNCTION,"SF-2 LOG-1")
        delay(3000)
        Log.d(SUSPEND_FUNCTION,"SF-2 LOG-2")
        delay(1000)
        Log.d(SUSPEND_FUNCTION,"SF-2 LOG-3")
        delay(1000)
        Log.d(SUSPEND_FUNCTION,"SF-2 LOG-4")

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
         THIS IN OUR MAIN THREAD AGAIN OUR APP WOULD BE FREEZED BECAUSE HEAVY TASKS ARE NOT GOOD AT MAIN THREAD
         MAIN -> UI TASKS
         IO -> READING WRITING FROM FILES , API ,DATABASE WORK
         DEFAULT -> CPU PROCESSING LIKE COMPLEX CALCULATIONS
        * */
        CoroutineScope(Dispatchers.Main).launch{
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