package com.amonteiro.a2023_12_kampus

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.amonteiro.a2023_12_kampus.databinding.FragmentScreen11Binding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Screen11Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class Screen11Fragment : Fragment(), MenuProvider {

    private var _binding: FragmentScreen11Binding? = null
    private val binding get() = _binding!!

    //val model by lazy { ViewModelProvider(this)[ScreenViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("Screen11Fragment.onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        println("Screen11Fragment.onCreateView")

        _binding = FragmentScreen11Binding.inflate(inflater, container, false)

        binding.goto12.setOnClickListener {
            findNavController().navigate(R.id.action_screen11Fragment_to_screen12Fragment)
        }

        //Menu
        requireActivity().addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

        println("Screen11Fragment.onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()

        println("Screen11Fragment.onDestroy")
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menu.add(0,11,0, "Goto 12")
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        if(menuItem.itemId == 11){
            findNavController().navigate(R.id.action_screen11Fragment_to_screen12Fragment)
            return true
        }

        return false
    }
}