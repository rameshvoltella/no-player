package com.novoda.noplayer.internal.exoplayer.drm;

import android.os.Handler;

import com.google.android.exoplayer2.drm.DefaultDrmSessionManager;
import com.google.android.exoplayer2.drm.DrmSessionManager;
import com.google.android.exoplayer2.drm.FrameworkMediaCrypto;
import com.google.android.exoplayer2.drm.FrameworkMediaDrm;
import com.google.android.exoplayer2.drm.MediaDrmCallback;

import java.util.HashMap;

class StreamingDrmSessionCreator implements DrmSessionCreator {

    private static final HashMap<String, String> NO_OPTIONAL_PARAMETERS = null;

    private final MediaDrmCallback mediaDrmCallback;
    private final FrameworkMediaDrmCreator frameworkMediaDrmCreator;
    private final Handler handler;

    StreamingDrmSessionCreator(MediaDrmCallback mediaDrmCallback, FrameworkMediaDrmCreator frameworkMediaDrmCreator, Handler handler) {
        this.mediaDrmCallback = mediaDrmCallback;
        this.frameworkMediaDrmCreator = frameworkMediaDrmCreator;
        this.handler = handler;
    }

    @Override
    public DrmSessionManager<FrameworkMediaCrypto> create(DefaultDrmSessionManager.EventListener eventListener) {
        FrameworkMediaDrm frameworkMediaDrm = frameworkMediaDrmCreator.create(WIDEVINE_MODULAR_UUID);

        return new DefaultDrmSessionManager<>(
                WIDEVINE_MODULAR_UUID,
                frameworkMediaDrm,
                mediaDrmCallback,
                NO_OPTIONAL_PARAMETERS,
                handler,
                eventListener
        );
    }
}
