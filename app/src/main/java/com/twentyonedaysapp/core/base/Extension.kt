package com.twentyonedaysapp.core.base

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

fun Context.inflate(layoutId:Int, viewGroup: ViewGroup?):View{
    return LayoutInflater.from(this).inflate(layoutId, viewGroup, false)
}

fun <T> AppCompatActivity.toActivity(
    bundle: Bundle?,
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

fun AppCompatActivity.removeFragment(fragment: Fragment){
    supportFragmentManager.beginTransaction().remove(fragment).commit()
}

