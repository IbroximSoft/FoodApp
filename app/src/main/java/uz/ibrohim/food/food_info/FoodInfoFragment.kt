package uz.ibrohim.food.food_info

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import uz.ibrohim.food.databinding.FragmentFoodInfoBinding
import uz.ibrohim.food.utils.errorToast
import uz.ibrohim.food.utils.warningToast

class FoodInfoFragment : Fragment() {

    private var _binding: FragmentFoodInfoBinding? = null
    private val binding get() = _binding!!
    private val viewModel: InfoModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFoodInfoBinding.inflate(inflater, container, false)

        binding.apply {
            infoBack.setOnClickListener {
                findNavController().popBackStack()
            }

            infoName.text = viewModel.name
            infoDescription.text = viewModel.description
            infoAbout.text = viewModel.info
            if (viewModel.image.isNotEmpty()){
                Glide.with(requireContext()).load(viewModel.image).into(infoImage)
            }
        }

        return binding.root
    }
}