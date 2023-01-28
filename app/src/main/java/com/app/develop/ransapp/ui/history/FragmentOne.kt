package com.app.develop.ransapp.ui.history

import android.app.Dialog
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.AppCompatSpinner
import com.app.develop.ransapp.R
import com.app.develop.ransapp.base.BaseFragment
import com.app.develop.ransapp.base.adapter.SpinnerAdapter
import com.app.develop.ransapp.databinding.FragmentOneBinding
import com.app.develop.ransapp.entity.SpinnerEntity


class FragmentOne: BaseFragment() {

    lateinit var binding: FragmentOneBinding

    private var statusSearch:Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOneBinding.inflate(inflater, container, false)
        return binding.root.apply {
            initView()
        }
    }

    private fun showDialog() {
        val dialog = Dialog(requireActivity())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        //dialog.setCancelable(false)
        dialog.setContentView(R.layout.custom_dialog_search)
        val spinnerEstados = dialog.findViewById(R.id.spinnerEstados) as AppCompatSpinner
        val spinnerUbicaciones = dialog.findViewById(R.id.spinnerUbicaciones) as AppCompatSpinner
        val spinnerTipos = dialog.findViewById(R.id.spinnerTipos) as AppCompatSpinner
        val spinnerCampo = dialog.findViewById(R.id.spinnerCampo) as AppCompatSpinner
        val spinnerAsc = dialog.findViewById(R.id.spinnerAsc) as AppCompatSpinner


        val dataListEstados = mutableListOf<SpinnerEntity>()
        dataListEstados.add(SpinnerEntity(0,"--Estado--"))
        val dataListUbicaciones= mutableListOf<SpinnerEntity>()
        dataListUbicaciones.add(SpinnerEntity(0,"--Ubicaciones--"))
        val dataListTipos = mutableListOf<SpinnerEntity>()
        dataListTipos.add(SpinnerEntity(0,"--Tipos--"))
        val dataListCampos = mutableListOf<SpinnerEntity>()
        dataListCampos.add(SpinnerEntity(0,"--Campo--"))
        val dataListAsc = mutableListOf<SpinnerEntity>()
        dataListAsc.add(SpinnerEntity(0,"--Asc--"))


        val adapterEstados= SpinnerAdapter(context = requireContext(), dataListEstados)
        spinnerEstados.adapter = adapterEstados

        val adapterUbicaciones = SpinnerAdapter(context = requireContext(), dataListUbicaciones)
        spinnerUbicaciones.adapter = adapterUbicaciones

        val adapterTipos = SpinnerAdapter(context = requireContext(), dataListTipos)
        spinnerTipos.adapter = adapterTipos

        val adapterCampo = SpinnerAdapter(context = requireContext(), dataListCampos)
        spinnerCampo.adapter = adapterCampo

        val adapterAsc = SpinnerAdapter(context = requireContext(), dataListAsc)
        spinnerAsc.adapter = adapterAsc


        val window: Window? = dialog.window
        window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        window?.setGravity(Gravity.CENTER)

        dialog.show()

    }

    private fun initView() {
        binding.btnSearch.setOnClickListener {

            showDialog()
            /*if(!statusSearch){
                statusSearch = true
                visibleSearchConst()
            }else{
                statusSearch = false
                goneSearchConst()
            }*/
        }
    }

    private fun visibleSearchConst(){
        binding.constSearch.visibility= View.VISIBLE
    }
    private fun goneSearchConst(){
        binding.constSearch.visibility= View.GONE
    }

}