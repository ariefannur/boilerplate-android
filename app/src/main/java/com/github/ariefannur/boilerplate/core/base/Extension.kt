package com.github.ariefannur.boilerplate.core.base

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.ariefannur.boilerplate.R
import com.google.android.material.textfield.TextInputLayout
import com.squareup.picasso.Picasso
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.regex.Pattern

fun Context.inflate(layoutId:Int, viewGroup: ViewGroup?):View{
    return LayoutInflater.from(this).inflate(layoutId, viewGroup, false)
}

fun Context.savePref(key:String, value:String){
    val sharedPref = this.getSharedPreferences(Constants.my_pref, Context.MODE_PRIVATE)
    sharedPref.edit().putString(key, value).apply()

}

fun Context.getPref(key: String):String{
    this.getSharedPreferences(Constants.my_pref, Context.MODE_PRIVATE).apply {
        return getString(key, "") ?: ""
    }

}

fun Context.saveBooleanPref(key:String, value:Boolean){
    val sharedPref = this.getSharedPreferences(Constants.my_pref,Context.MODE_PRIVATE)
    sharedPref.edit().putBoolean(key, value).apply()
}

fun Context.getBooleanPref(key: String):Boolean{
    this.getSharedPreferences(Constants.my_pref,Context.MODE_PRIVATE).apply {
        return getBoolean(key, false)
    }

}

fun Context.showToast( message:String){
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun <T> AppCompatActivity.toActivity(
    bundle: Bundle? = null,
    destination: Class<T>
){
    val intent = Intent(baseContext, destination)
    if(bundle != null)
        intent.putExtras(bundle)
    startActivity(intent)
}

fun AppCompatActivity.getBundle():Bundle?{
    return intent.extras
}

fun <T> AppCompatActivity.toActivityForResult(bundle: Bundle?, destination: Class<T>, intentCode:Int){
    val intent = Intent(baseContext, destination)
    if(bundle != null)
        intent.putExtras(bundle)
    startActivityForResult(intent, intentCode)
}

fun AppCompatActivity.addFragment(fragment: Fragment){
    supportFragmentManager.beginTransaction().add(android.R.id.content, fragment).commit()
}

fun AppCompatActivity.addFragment(layoutId: Int, fragment: Fragment){
    supportFragmentManager.beginTransaction().add(layoutId, fragment).commit()
}

fun AppCompatActivity.replaceFragment(fragment: Fragment){
    supportFragmentManager.beginTransaction().replace(android.R.id.content, fragment).commit()
}

fun AppCompatActivity.replaceFragment(layoutId: Int, bundle: Bundle?, fragment: Fragment){
    if(bundle != null) fragment.arguments = bundle
    supportFragmentManager.beginTransaction().replace(layoutId, fragment).commit()
}

fun AppCompatActivity.removeFragment(fragment: Fragment){
    supportFragmentManager.beginTransaction().remove(fragment).commit()
}

fun String.checkValidEmail():Boolean{
    val regexPattern = Pattern.compile("^(.+)@(.+)$")
    val regMatcher   = regexPattern.matcher(this)
    return regMatcher.matches()
}

fun EditText.checkEmail(textInputLayout:TextInputLayout){
    addTextChangedListener(object :TextWatcher{
        override fun afterTextChanged(p0: Editable?) {
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            if(p0.toString().checkValidEmail()){
                textInputLayout.error = null
            }else{
                textInputLayout.error = context.getString(R.string.invalid_email)
            }
        }

    })
}

fun ImageView.loadImage(url:String){
    Picasso.get().load(url).into(this)
}

fun <T> ViewModel.asyncRxExecutor(heavyFunction: Observable<T>, state: MutableLiveData<State>, response : (response : T?) -> Unit, error: (error : Throwable) -> Unit) {
    state.postValue(State.LOADING)

    val disposable = heavyFunction.subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
            {
                response(it)
                state.postValue(State.COMPLETE)
            },{
                state.postValue(State.COMPLETE)
                error(it)
            }
        )
    Constants.compositeDisposable.add(disposable)
}









