package com.carlostorres.pruebatecnicagonet.utils

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory

fun decodeSampledBitmapFromResource(
    resources: Resources, // Proporciona el objeto Resources de tu contexto
    resId: Int,           // El ID del recurso drawable
    reqWidth: Int,        // Ancho deseado
    reqHeight: Int        // Alto deseado
): Bitmap {
    // Decodifica con inJustDecodeBounds=true para obtener las dimensiones
    val options = BitmapFactory.Options().apply {
        inJustDecodeBounds = true
    }
    BitmapFactory.decodeResource(resources, resId, options)

    // Calcula inSampleSize
    options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight)

    // Decodifica con inSampleSize configurado
    options.inJustDecodeBounds = false
    return BitmapFactory.decodeResource(resources, resId, options)
}

fun calculateInSampleSize(options: BitmapFactory.Options, reqWidth: Int, reqHeight: Int): Int {
    val (height: Int, width: Int) = options.run { outHeight to outWidth }
    var inSampleSize = 1

    if (height > reqHeight || width > reqWidth) {
        val halfHeight: Int = height / 2
        val halfWidth: Int = width / 2

        while (halfHeight / inSampleSize >= reqHeight && halfWidth / inSampleSize >= reqWidth) {
            inSampleSize *= 2
        }
    }

    return inSampleSize
}