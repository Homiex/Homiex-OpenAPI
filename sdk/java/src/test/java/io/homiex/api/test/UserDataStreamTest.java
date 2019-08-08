package io.homiex.api.test;

import io.homiex.api.client.HomiexApiClientFactory;
import io.homiex.api.client.HomiexApiRestClient;
import io.homiex.api.client.HomiexApiWebSocketClient;
import io.homiex.api.client.constant.HomiexConstants;
import io.homiex.api.test.constant.Constants;

//@Slf4j
public class UserDataStreamTest {

    public static void main(String[] args) {
//
        HomiexApiWebSocketClient client = HomiexApiClientFactory.newInstance().newWebSocketClient();
        HomiexApiRestClient restClient = HomiexApiClientFactory.newInstance(Constants.ACCESS_KEY, Constants.SECRET_KEY).newRestClient();

        System.out.println("\n ------Get Listen Key -----");
        System.out.println();
        String listenKey = restClient.startUserDataStream(HomiexConstants.DEFAULT_RECEIVING_WINDOW, System.currentTimeMillis());
        System.out.println("listenKey:" + listenKey);
        // order
        client.onUserEvent(listenKey, response -> System.out.println(response), true);

    }
}
