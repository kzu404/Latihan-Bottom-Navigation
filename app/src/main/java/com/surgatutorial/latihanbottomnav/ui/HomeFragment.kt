package com.surgatutorial.latihanbottomnav.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.surgatutorial.latihanbottomnav.Food
import com.surgatutorial.latihanbottomnav.FoodAdapter
import com.surgatutorial.latihanbottomnav.R
import com.surgatutorial.latihanbottomnav.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!
    lateinit var foodsAdapter: FoodAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listFoods = listOf(
            Food(name = "Ayam Cabe Bumbu Kemangi","40 menit", image = R.drawable.ayam_cabe),
            Food(name = "Getuk Gulung Keju","45 menit", image = R.drawable.getuk),
            Food(name = "Ikan Krispi dengan Saus Santan Pedas","30 menit", image = R.drawable.ikan_krispy),
            Food(name = "Kare Ayam Krispi","50 menit", image = R.drawable.kare_ayam),
            Food(name = "Es Krim Kelapa dengan Cherry & Almon","25 menit", image = R.drawable.es_krim),
            Food(name = "Plecing Ayam","45 menit", image = R.drawable.plecing),
            Food(name = "Sayur Labu Udang","40 menit", image = R.drawable.sayur_labu),
            Food(name = "Udang Paprika Sambal","30 menit", image = R.drawable.udang),
            Food(name = "Udang Panggang Saus Peri-Peri","30 menit", image = R.drawable.udang_panggang),
            Food(name = "Daging Pedas Ala Thailand","30 menit", image = R.drawable.daging_pedas)
        )

        foodsAdapter = FoodAdapter(listFoods)

        binding.rvMain.setHasFixedSize(true)
        binding.rvMain.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = foodsAdapter
        }
        foodsAdapter.setOnItemClickCallback(object : FoodAdapter.OnItemClickCallback{
            override fun onItemClicked(food: Food) {
                Toast.makeText(activity, "${food.name} di pilih", Toast.LENGTH_SHORT).show()
            }
        })

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                foodsAdapter.filter.filter(query)
                return true
            }

            override fun onQueryTextChange(query: String?): Boolean {
                foodsAdapter.filter.filter(query)
                return true
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}