package ir.topbest.objectdetection.presenter.apps

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import ir.topbest.objectdetection.databinding.FragmentAppsUsageBinding

@AndroidEntryPoint
class AppsUsageFragment(private val identifier:String) : BottomSheetDialogFragment() {

    val vm : AppsUsageViewModel by viewModels()

    private lateinit var binding:FragmentAppsUsageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding= FragmentAppsUsageBinding.inflate(inflater)

        vm.getData(identifier)

        val adapter = AppsUsageAdapter()
        binding.recyclerView.adapter=adapter

        vm.liveData.observe(viewLifecycleOwner){
            adapter.items=it
        }

        return binding.root
    }
}