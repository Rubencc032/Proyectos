package com.jovian.bodymass.model

import java.lang.Exception
import kotlin.math.pow

typealias OnWrongHeight = (error:String)->Unit
typealias OnWrongWeight = (error:String)->Unit
typealias OnError = (error:String)->Unit
typealias OnSuccess = (bmi:Double)->Unit
typealias OnLoading = (loading:Boolean)->Unit

class BMICalculator {

    data class Request(
        val weight:Double,
        val height:Double
    )

    private fun calcMBI(weight: Double, height: Double):Double = weight/ (height/100).pow(2)

    /**********************************WITH FUNCTIONS****************************************/

    //Long calculating function
    fun calculateWithFunctions(request: Request,onSuccess: OnSuccess,onError: OnError,onLoading: OnLoading,onWrongWeight: OnWrongWeight?,onWrongHeight: OnWrongHeight?){

        //llamada a la funcion, pasando el valor true, ya que estamos realizando el calculo
        onLoading(true)

        //declaramos las variables con un minimo de peso y altura
        val minHeight = 50
        val minWeight = 10

        //partimos de que los datos van a ser correctos
        var error = false

        //le añadimos un retraso para simular un calculo largo
        Thread.sleep(5000)

        //imprimimos por consola el nombre del hilo
        println(Thread.currentThread().name)

        //si la altura es menor que la altura minima, llamamos al callback
        if(minHeight > request.height){
            onWrongHeight?.let {
                it.invoke("La altura debe ser mayor")
                error = true
            }
        }

        //si el peso es menor que el peso minimo, llamamos al callback
        if(minWeight > request.weight){
            onWrongWeight?.let {
                it.invoke("La altura debe ser mayor")
                error = true
            }
        }

        //si los datos introducidos son correctos, no hay error e inciamos el calculo
        if(!error){
            try{
                val bmi = calcMBI(request.weight, request.height)
                onSuccess(bmi)
            }catch (e:Exception){
                onError(e.toString())
            }
        }

        onLoading(false)

    }
}