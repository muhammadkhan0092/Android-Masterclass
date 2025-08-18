package com.example.androidmasterclass.coroutines.views

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.androidmasterclass.databinding.CoroutinesFragmentBinding
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class CoroutinesFragment : Fragment(){
    private var binding : CoroutinesFragmentBinding? = null
    companion object{
        const val COROUTINE_SCOPE = "COROUTINE SCOPE"
        const val SUSPEND_FUNCTION = "SUSPEND FUNCTION"
        const val ASYNC_AWAIT = "ASYNC_AWAIT"
        const val JOB = "JOB"
        const val RUN_BLOCKING_AND_RUN_WITH = "RUN_BLOCKING_AND_RUN_WITH"
        const val LIFECYCLE_SCOPE = "LIFECYCLE SCOPE"
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
        onAsyncAwaitClicked()
        onCoroutineJobsClicked()
        onRunWithAndRunBlockingClicked()
        onLifeCycleClickedListener()
    }

    private fun onLifeCycleClickedListener() {
        binding?.btnLifeCycleScope?.setOnClickListener {
            simulateLifeCycleScope()
        }
    }
    private fun simulateLifeCycleScope() {
        //lifecycle scope is tied to component (fragment,activity) and viewmodel scope is tied to lifecycle of view mode
        // which means when the viewmodel is destroyed ,the coroutine is also destroyed
        //In this Example When the fragment is destroyed the logs of coroutine scope are visible but lifecycle scope logs are not visible as
        // lifecycle scope is tied to the lifecycle of the fragment here
        CoroutineScope(Dispatchers.IO).launch {
            delay(5000)
            Log.d(LIFECYCLE_SCOPE,"IN COROUTINE SCOPE")
        }

        lifecycleScope.launch {
            delay(1000)
            findNavController().navigateUp()
            delay(2000)
            Log.d(LIFECYCLE_SCOPE,"IN LIFECYCLE SCOPE")
        }
    }



    private fun onRunWithAndRunBlockingClicked() {
        binding?.btnWithContextAndRunBlocking?.setOnClickListener {
            lifecycleScope.launch {
                simulateRunBlockingAndWithContext()
            }
        }
    }
    private suspend fun simulateRunBlockingAndWithContext() {
        //Run With is used when we Are in A coroutine and want to switch thread to perform some operation in another thread
        //Run With is Also Blocking in nature which means the last log of this coroutine wont execute until with context is finished
        val job = CoroutineScope(Dispatchers.IO).launch{
            Log.d(RUN_BLOCKING_AND_RUN_WITH,"RUNNING HEAVY IO OPERATIONS ON ${coroutineContext}")
            delay(1000)
            withContext(Dispatchers.Main) {
                Log.d(RUN_BLOCKING_AND_RUN_WITH, "UPADTING UI ON ${coroutineContext}")
            }
            Log.d(RUN_BLOCKING_AND_RUN_WITH,"COROUTINE COMPLETED")
        }
        job.join()

        //It Blocks the thread. So The Coroutine is Guranteed to Complete other wise if we use coroutine here and write
        //cancel just after it only hello will be executed and world will not be executed
        runBlocking {
            launch {
                delay(2000)
                Log.d(RUN_BLOCKING_AND_RUN_WITH, "WORLD")
            }
            Log.d(RUN_BLOCKING_AND_RUN_WITH, "HELLO")
        }

    }

    private fun onCoroutineJobsClicked() {
        binding?.btnJobs?.setOnClickListener {
            lifecycleScope.launch {
                simulateJobAndCancel()
            }
        }
    }
    private suspend fun simulateJobAndCancel(){
        //cancellation is required to avoid resource wastage and leaks
        val parentJob = CoroutineScope(Dispatchers.IO).launch{
            //even if we cancel the coroutine it will run for some time if the thread is busy and do not check. so we have to manually check it
            if(isActive) {
                Log.d(JOB, "PARENT JOB STARTED")
                //if normal code is present in coroutine, it will go line by line execution , if coroutine within coroutine ,the child may start in another thread and parent coroutine will continue
                //we can change the childs context also
                launch(Dispatchers.Default) {
                    try {
                        Log.d(JOB, "CHILD JOB STARTED")
                        delay(5000)
                        Log.d(JOB, "CHILD JOB ENDED")
                    } catch (e: CancellationException) {
                        Log.d(JOB, "CHILD JOB CANCELLED")
                    }
                }
                delay(1000)
                Log.d(JOB, "PARENT JOB ENDED")
            }
        }
        delay(2000)
        //if parent is cancelled , its child automatically gets cancelled
        parentJob.cancel()
        parentJob.join()
        Log.d(JOB,"PARENT JOB COMPLETED")
    }


    private fun onAsyncAwaitClicked() {
        binding?.btnAwaitAndAsync?.setOnClickListener {
            // We Used Coroutine here also because Suspend Functions Can Only be Called from Suspend functions or coroutine
            //lifecyclescope gives us a coroutine which is tied to our activity/fragments life cycle, the coroutine gets cancelled when the component
            //(activity/fragment) is destroyed
            lifecycleScope.launch {
                asyncAwaitSimulation()
            }
        }
    }
    private suspend fun asyncAwaitSimulation(){
        //we use async and await when we are expecting a result from the coroutine . It returns us a deffered object which means this is the object
        //which will have the data you want , but it is deferred/late. we use await which helps to hold the program untill the data is received
        val jobOne = CoroutineScope(Dispatchers.IO).async {
            getComputerScienceStudent()
        }

        val jobTwo = CoroutineScope(Dispatchers.IO).async {
            getMechanicalStudent()
        }
        //The Program Waits untill both jobs are done
        Log.d(ASYNC_AWAIT,"Computer Science ${jobOne.await()}  Mechanical -> ${jobTwo.await()}")
        Log.d(ASYNC_AWAIT,"Async Task Completed")


        //Same Result As Above, if we do not use async in below function , the second function would only be executed once the first function got over
//        CoroutineScope(Dispatchers.IO).launch {
//            val cs = async{getComputerScienceStudent()}
//            val me = async {getMechanicalStudent()}
//            Log.d(ASYNC_AWAIT,"Computer Science ${cs.await()}  Mechanical -> ${me.await()}")
//            Log.d(ASYNC_AWAIT,"Async Task Completed")
//        }
    }
    private suspend fun getMechanicalStudent() : Int{
        delay(1000)
        return 132
    }
    private suspend fun getComputerScienceStudent(): Int {
        delay(1000)
        return 100
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

    override fun onDestroy() {
        binding = null
        Log.d(LIFECYCLE_SCOPE,"ON DESTROY")
        super.onDestroy()
    }
}