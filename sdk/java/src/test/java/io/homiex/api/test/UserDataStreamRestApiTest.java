package io.homiex.api.test;

import io.homiex.api.client.HomiexApiClientFactory;
import io.homiex.api.client.HomiexApiRestClient;
import io.homiex.api.client.constant.HomiexConstants;
import io.homiex.api.test.constant.Constants;

public class UserDataStreamRestApiTest {

    public static void main(String[] args) {

        HomiexApiClientFactory factory = HomiexApiClientFactory.newInstance(Constants.ACCESS_KEY, Constants.SECRET_KEY);
        HomiexApiRestClient client = factory.newRestClient();

        System.out.println("\n ------start user data stream-----");
        String listenKey = client.startUserDataStream(HomiexConstants.DEFAULT_RECEIVING_WINDOW, System.currentTimeMillis());
        System.out.println(listenKey);

        System.out.println("\n ------keepAlive user data stream-----");
        client.keepAliveUserDataStream(listenKey, HomiexConstants.DEFAULT_RECEIVING_WINDOW, System.currentTimeMillis());

        System.out.println("\n ------close user data stream-----");
        client.closeUserDataStream(listenKey, HomiexConstants.DEFAULT_RECEIVING_WINDOW, System.currentTimeMillis());

    }

}
