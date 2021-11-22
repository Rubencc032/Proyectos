package com.jovian.bodymass.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jovian.bodymass.model.BMICalculator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BMICalculatorViewModel: ViewModel() {

    //creamos un objeto de tipo BMICalculator
    private val bmiCalculator: BMICalculator = BMICalculator()

    //variable que guarda el IMC, es mutable
    val bmi : MutableLiveData<Double> = MutableLiveData()
    val heightError : MutableLiveData<String> = MutableLiveData()
    val weightError : MutableLiveData<String> = MutableLiveData()
    val error : MutableLiveData<String> = MutableLiveData()
    val loading : MutableLiveData<Boolean> = MutableLiveData()

    //choose which of the three methods you want to use
    fun calculate(weight: Double, height: Double){

        calculateBMIFunctions(weight, height)
        //calculateBMICallBack(weight, height)
        //calculateBMISealed(weight, height)

    }

    /**************************************WITH FUNCTIONS*********************************/
    private fun calculateBMIFunctions(weight: Double, height: Double) {
        //using coroutine
        CoroutineScope(Dispatchers.IO).launch {
            //Using callbacks
            bmiCalculator.calculateWithFunctions(BMICalculator.Request(weight, height),
                onSuccess = { mBMI ->
                    bmi.postValue(mBMI)
                    heightError.postValue("")
                    weightError.postValue("")
                    error.postValue("")
                },
                onError = {
                    e -> error.postValue(e)
                },
                onLoading = {
                    isLoading -> loading.postValue(isLoading)
                },
                onWrongWeight = {
                    e -> weightError.postValue(e)
                }, null)
        }
    }
}