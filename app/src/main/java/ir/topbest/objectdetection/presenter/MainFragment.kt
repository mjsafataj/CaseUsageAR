package ir.topbest.objectdetection.presenter

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ir.topbest.objectdetection.databinding.FragmentMainBinding
import ir.topbest.objectdetection.presenter.apps.AppsUsageFragment

@AndroidEntryPoint
class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    val getCameraPreview get() = binding.cameraPreviewView

    private val vm: MainFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentMainBinding.inflate(inflater)

        vm.caseDetails.observe(viewLifecycleOwner) { model ->
            if (binding.cardView.visibility == View.GONE)
                binding.cardView.visibility = View.VISIBLE
            binding.caseDetail = model
            setView()
        }
        vm.caseUsage.observe(viewLifecycleOwner) { usage ->
            binding.usage = usage
            setView()
        }

        vm.loading.observe(viewLifecycleOwner) { isLoading ->
//            binding.cardView.visibility = if (isLoading) {
//                View.GONE
//            } else {
//                View.VISIBLE
//            }
        }

        vm.error.observe(viewLifecycleOwner) { error ->
            binding.cardView.visibility = View.GONE
        }

        binding.appsButton.setOnClickListener {
            AppsUsageFragment(vm.identifier).show(childFragmentManager, "AppsUsageDialog")
        }


        Handler().postDelayed({
            onTextFound("case1")
        }, 2000)

        return binding.root
    }

    fun onTextFound(foundText: String) {
        if (foundText != vm.identifier && foundText.contains("case")) {
            binding.text.text = foundText
            vm.identifier = foundText
        }
    }

    fun setView() {
        vm.apply {
            if (caseDetails.value == null || caseUsage.value == null)
                return
            binding.memoryProgressBar.progress =
                (caseUsage.value!!.memoryUsage * 100) / caseDetails.value!!.memoryCapacity
            binding.gpuMemoryProgressBar.progress =
                (caseUsage.value!!.gpuMemoryUsage * 100) / caseDetails.value!!.gpuMemoryCapacity
        }
    }
}