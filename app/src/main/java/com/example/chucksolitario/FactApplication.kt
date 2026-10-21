package com.example.chucksolitario

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory

/**
 * Clase Application para la configuración de la app de Facts y la caché de Coil.
 */
class FactApplication : Application(), ImageLoaderFactory {
    override fun newImageLoader(): ImageLoader =
        ImageLoader.Builder(this)
            .respectCacheHeaders(false)
            .build()
}
