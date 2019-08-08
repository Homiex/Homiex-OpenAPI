package io.homiex.api.client.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.homiex.api.client.HomiexApiCallback;
import io.homiex.api.client.constant.HomiexConstants;
import io.homiex.api.client.exception.HomiexApiException;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

import java.io.IOException;

/**
 * Homiex API WebSocket listener.
 */
public class HomiexApiWebSocketListener<T> extends WebSocketListener {

    private HomiexApiCallback<T> callback;

    private Class<T> eventClass;

    private TypeReference<T> eventTypeReference;

    private boolean closing = false;

    private boolean failure = false;

    public HomiexApiWebSocketListener(HomiexApiCallback<T> callback, Class<T> eventClass) {
        this.callback = callback;
        this.eventClass = eventClass;
    }

    public HomiexApiWebSocketListener(HomiexApiCallback<T> callback, TypeReference<T> eventTypeReference) {
        this.callback = callback;
        this.eventTypeReference = eventTypeReference;
    }

    public boolean getFailure() {
        return failure;
    }

    @Override
    public void onMessage(WebSocket webSocket, String text) {
        this.failure = false;
        if (text.contains(HomiexConstants.PONG_MSG_KEY) || text.contains(HomiexConstants.PING_MSG_KEY)) {
            return;
        }
        ObjectMapper mapper = new ObjectMapper();
        try {
            T event = null;
            if (eventClass == null) {
                event = mapper.readValue(text, eventTypeReference);
            } else {
                event = mapper.readValue(text, eventClass);
            }
            callback.onResponse(event);
        } catch (IOException e) {
            throw new HomiexApiException(e);
        }
    }

    @Override
    public void onClosing(final WebSocket webSocket, final int code, final String reason) {
        closing = true;
    }

    @Override
    public void onFailure(WebSocket webSocket, Throwable t, Response response) {
        this.failure = true;
        if (!closing) {
            callback.onFailure(t);
        }
    }

}
