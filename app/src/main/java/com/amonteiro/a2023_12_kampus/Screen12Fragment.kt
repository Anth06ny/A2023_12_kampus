package com.amonteiro.a2023_12_kampus

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import com.amonteiro.a2023_12_kampus.databinding.FragmentScreen12Binding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Screen12Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class Screen12Fragment : Fragment(), MenuProvider {

    private var _binding: FragmentScreen12Binding? = null
    private val binding get() = _binding!!

    //val model by lazy { ViewModelProvider(this)[Screen12ViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("Screen12Fragment.onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        println("Screen12Fragment.onCreateView")

        _binding = FragmentScreen12Binding.inflate(inflater, container, false)

        //Menu
        requireActivity().addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)

        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

        println("Screen12Fragment.onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()

        println("Screen12Fragment.onDestroy")
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menu.add(0, 12, 0, "Back")
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        if (menuItem.itemId == 12) {
            findNavController().navigateUp()
            return true
        }

        return false
    }
}