package com.marlonmafra.android.widget.utils;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

public final class FontCache {

    private final String TAG = "FontCache";

    private static FontCache instance;

    private final Map<String, Typeface> fontMap = new HashMap<>();

    private FontCache() {
    }

    public static FontCache getInstance() {
        if (instance == null) {
            instance = new FontCache();
        }
        return instance;
    }

    private Typeface get(final String path) {
        return fontMap.get(path);
    }

    public Typeface put(final String path, final AssetManager assetManager) {

        if (assetManager == null) {
            Log.e(TAG, "The assetManager cannot be null.");
            throw new IllegalArgumentException("The assetManager cannot be null.");
        }

        if (fontMap.containsKey(path)) {
            return get(path);
        }

        try {
            final Typeface typeface = Typeface.createFromAsset(assetManager, path);
            fontMap.put(path, typeface);
            return typeface;
        } catch (Exception ex) {
            Log.e(TAG, "Can not load the font");
        }

        return null;
    }
}