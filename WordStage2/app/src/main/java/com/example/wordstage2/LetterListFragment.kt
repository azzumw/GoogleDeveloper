package com.example.wordstage2

import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wordstage2.databinding.FragmentLetterListBinding


/**
 * A simple [Fragment] subclass.
 * Use the [LetterListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LetterListFragment : Fragment() {

    private var isLinearLayoutManager = true
    private var _binding : FragmentLetterListBinding? = null
    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentLetterListBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = binding.recyclerView
        chooseLayout()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu,menu)

        val layoutButton = menu.findItem(R.id.action_switch_layout)

        setIcon(layoutButton)
    }

    private fun chooseLayout() {
        if (isLinearLayoutManager) {
            recyclerView.layoutManager = LinearLayoutManager(context)
        } else {
            recyclerView.layoutManager = GridLayoutManager(context, 4)
        }
        recyclerView.adapter = LetterAdapter()
    }

    private fun setIcon(menuItem: MenuItem?) {
        if (menuItem == null)
            return

        // Set the drawable for the menu icon based on which LayoutManager is currently in use

        // An if-clause can be used on the right side of an assignment if all paths return a value.
        // The following code is equivalent to
        // if (isLinearLayoutManager)
        //     menu.icon = ContextCompat.getDrawable(this, R.drawable.ic_grid_layout)
        // else menu.icon = ContextCompat.getDrawable(this, R.drawable.ic_linear_layout)
        menuItem.icon =
            if (isLinearLayoutManager)
                ContextCompat.getDrawable(requireContext(), R.drawable.ic_grid_layout)
            else ContextCompat.getDrawable(requireContext(), R.drawable.ic_linear_layout)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_switch_layout -> {
                // Sets isLinearLayoutManager (a Boolean) to the opposite value
                isLinearLayoutManager = !isLinearLayoutManager
                // Sets layout and icon
                chooseLayout()
                setIcon(item)

                return true
            }
            //  Otherwise, do nothing and use the core event handling

            // when clauses require that all possible paths be accounted for explicitly,
            //  for instance both the true and false cases if the value is a Boolean,
            //  or an else to catch all unhandled cases.
            else -> super.onOptionsItemSelected(item)
        }
    }
}