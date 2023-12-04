package com.amonteiro.a2023_12_kampus

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.amonteiro.a2023_12_kampus.databinding.FragmentScreen21Binding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Screen21Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class Screen21Fragment : Fragment() {

    private var _binding: FragmentScreen21Binding? = null
    private val binding get() = _binding!!

    //val model by lazy { ViewModelProvider(this)[Screen21ViewModel::class.java] }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentScreen21Binding.inflate(inflater, container, false)

        binding.goto22.setOnClickListener {
            findNavController().navigate(R.id.action_screen21Fragment_to_screen22Fragment)
        }

        //Menu
        //requireActivity().addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}