package io.homiex.api.test;

import io.homiex.api.client.HomiexApiClientFactory;
import io.homiex.api.client.HomiexApiRestClient;
import io.homiex.api.client.domain.general.BrokerInfo;
import io.homiex.api.test.constant.Constants;

public class GeneralRestApiTest {

    public static void main(String[] args) {

        HomiexApiClientFactory factory = HomiexApiClientFactory.newInstance();
        HomiexApiRestClient client = factory.newRestClient();

        System.out.println("\n ------BrokerInfo-----");
        BrokerInfo brokerInfo = client.getBrokerInfo();
        System.out.println(brokerInfo);

    }


}
