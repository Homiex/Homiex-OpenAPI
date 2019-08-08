package io.homiex.api.client;

import io.homiex.api.client.constant.HomiexConstants;
import io.homiex.api.client.impl.HomiexApiRestClientImpl;
import io.homiex.api.client.impl.HomiexApiWebSocketClientImpl;
import io.homiex.api.client.impl.HomiexOptionApiRestClientImpl;

import static io.homiex.api.client.impl.HomiexApiServiceGenerator.getSharedClient;

/**
 * A factory for creating HomiexApi client objects.
 */
public final class HomiexApiClientFactory {

    /**
     * API Key
     */
    private String apiKey;

    /**
     * Secret.
     */
    private String secret;

    private String baseUrl = HomiexConstants.API_BASE_URL;

    /**
     * Instantiates a new Homiex api client factory.
     *
     * @param apiKey the API key
     * @param secret the Secret
     */
    private HomiexApiClientFactory(String apiKey, String secret) {
        this.apiKey = apiKey;
        this.secret = secret;
    }

    private HomiexApiClientFactory(String baseUrl, String apiKey, String secret) {
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
        this.secret = secret;
    }

    /**
     * New instance.
     *
     * @param apiKey the API key
     * @param secret the Secret
     * @return the Homiex api client factory
     */
    public static HomiexApiClientFactory newInstance(String apiKey, String secret) {
        return new HomiexApiClientFactory(apiKey, secret);
    }

    /**
     * for bhop.cloud client and inner test only
     *
     * @param baseUrl
     * @param apiKey
     * @param secret
     * @return
     */
    public static HomiexApiClientFactory newInstance(String baseUrl, String apiKey, String secret) {
        return new HomiexApiClientFactory(baseUrl, apiKey, secret);
    }

    /**
     * New instance without authentication.
     *
     * @return the Homiex api client factory
     */
    public static HomiexApiClientFactory newInstance() {
        return new HomiexApiClientFactory(null, null);
    }

    /**
     * Creates a new synchronous/blocking REST client.
     */
    public HomiexApiRestClient newRestClient() {
        return new HomiexApiRestClientImpl(baseUrl, apiKey, secret);
    }


    public HomiexApiWebSocketClient newWebSocketClient() {
        return new HomiexApiWebSocketClientImpl(getSharedClient(), HomiexConstants.WS_API_BASE_URL, HomiexConstants.WS_API_USER_URL);
    }

    /**
     * for bhop.cloud client and inner test only
     *
     * @param wsApiBaseUrl
     * @param wsApiUserUrl
     * @return
     */
    public HomiexApiWebSocketClient newWebSocketClient(String wsApiBaseUrl, String wsApiUserUrl) {
        return new HomiexApiWebSocketClientImpl(getSharedClient(), wsApiBaseUrl, wsApiUserUrl);
    }

    /**
     * Creates a new synchronous/blocking Option REST client.
     */
    public HomiexOptionApiRestClient newOptionRestClient() {
        return new HomiexOptionApiRestClientImpl(baseUrl, apiKey, secret);
    }

}
