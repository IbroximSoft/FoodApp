package uz.ibrohim.food.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.launch
import uz.ibrohim.food.R
import uz.ibrohim.food.databinding.FragmentHomeBinding
import uz.ibrohim.food.food_info.InfoModel
import uz.ibrohim.food.home.response.HomeResponse
import uz.ibrohim.food.home.response.Meal
import uz.ibrohim.food.home.services.HomeViewModel
import uz.ibrohim.food.retrofit.ApiClient
import uz.ibrohim.food.retrofit.NetworkHelper
import uz.ibrohim.food.retrofit.Resource
import uz.ibrohim.food.utils.errorToast

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: HomeAdapter
    private lateinit var foodList: ArrayList<Meal>
    private val viewModels: InfoModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        foodList = arrayListOf()

        adapter = HomeAdapter(foodList, object :
            HomeAdapter.OnItemClickListener {
            override fun onItemClick(meal: Meal) {
            }
        })
        binding.apply {
            homeSearch.queryHint = "Izlash..."

            homeSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(p0: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(p0: String): Boolean {
                    searchProduct(p0)
                    return true
                }

            })
        }

        getRequestProducts()
        return binding.root
    }

    private fun getRequestProducts() {
        val networkHelper = NetworkHelper(requireContext())
        val viewModel = HomeViewModel(ApiClient.apiServices, networkHelper)
        viewModel.getList("a")
        lifecycleScope.launch {
            viewModel.flow.collect {
                when (it) {
                    is Resource.Error -> {
                        binding.apply {
                            errorToast("Internet bilan aloqa yo'q")
                            homeProgress.isVisible = false
                            homeRv.isVisible = true
                        }
                    }

                    Resource.Loading -> {
                        binding.apply {
                            homeRv.isVisible = false
                            homeProgress.isVisible = true
                        }
                    }

                    is Resource.Success -> {
                        binding.apply {
                            homeProgress.isVisible = false
                            homeRv.isVisible = true
                        }

                        successData(it.data as HomeResponse)
                    }
                }
            }

        }
    }

    private fun successData(productResponse: HomeResponse) {
        adapter = HomeAdapter(productResponse.meals, object :
            HomeAdapter.OnItemClickListener {
            override fun onItemClick(meal: Meal) {
//                viewModels.list!!.add(meal)
                viewModels.name = meal.strMeal
                viewModels.description = meal.strCategory
                viewModels.info = meal.strInstructions
                viewModels.image = meal.strMealThumb
                findNavController().navigate(R.id.foodInfoFragment)
            }
        })

        binding.homeRv.adapter = adapter

    }

    private fun searchProduct(p0: String?) {
        adapter.filter.filter(p0)
    }
}