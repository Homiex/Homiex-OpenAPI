package io.homiex.api.test;

import java.util.Date;
import java.util.List;

import io.homiex.api.client.HomiexApiClientFactory;
import io.homiex.api.client.HomiexOptionApiRestClient;
import io.homiex.api.client.domain.account.OrderSide;
import io.homiex.api.client.domain.account.OrderType;
import io.homiex.api.client.domain.account.TimeInForce;
import io.homiex.api.client.domain.option.OptionMatchResult;
import io.homiex.api.client.domain.option.OptionOrderResult;
import io.homiex.api.client.domain.option.PositionResult;
import io.homiex.api.client.domain.option.SettlementResult;
import io.homiex.api.client.domain.option.TokenOptionResult;
import io.homiex.api.client.domain.option.request.OptionHistoryOrderRequest;
import io.homiex.api.client.domain.option.request.OptionOpenOrderRequest;
import io.homiex.api.client.domain.option.request.OptionOrderRequest;
import io.homiex.api.client.domain.option.request.OptionPositionRequest;
import io.homiex.api.client.domain.option.request.OptionSettlementRequest;
import io.homiex.api.client.domain.option.request.OptionTradeRequest;
import io.homiex.api.client.domain.option.request.OptionsRequest;
import io.homiex.api.test.constant.Constants;

public class OptionRestApiTest {

    public static void main(String[] args) {
        HomiexApiClientFactory factory = HomiexApiClientFactory.newInstance(Constants.ACCESS_KEY, Constants.SECRET_KEY);
        HomiexOptionApiRestClient client = factory.newOptionRestClient();

        System.out.println("\n ------get options-----");

        List<TokenOptionResult> results
                = client.getOptions(OptionsRequest.builder().expired(Boolean.FALSE).build());

        System.out.println(results);

        System.out.println("\n ------new option order-----");
        OptionOrderResult optionOrderResult =
                client.newOptionOrder(OptionOrderRequest
                        .builder()
                        .symbol("BTC0226CS4000")
                        .price("10000")
                        .quantity("1")
                        .orderSide(OrderSide.BUY)
                        .orderType(OrderType.MARKET)
                        .clientOrderId(String.valueOf(new Date().getTime()))
                        .timeInForce(TimeInForce.GTC)
                        .timestamp(new Date().getTime())
                        .recvWindow(5_000L)
                        .build());
        System.out.println(optionOrderResult);
//
        System.out.println("\n ------new option order-----");
        List<PositionResult> positionResults
                = client.getOptionPositions(OptionPositionRequest
                .builder()
                .timestamp(new Date().getTime())
                .recvWindow(5_000L)
                .build());
        System.out.println(positionResults);

        System.out.println("\n ------new option settlement-----");
        List<SettlementResult> settlementResults
                = client.getOptionSettlements(OptionSettlementRequest.builder().timestamp(new Date().getTime()).recvWindow(5_000L).build());
        System.out.println(settlementResults);


        System.out.println("\n ------new option historyOrders-----");
        List<OptionOrderResult> optionOrderResults
                = client.getOptionHistoryOrders(OptionHistoryOrderRequest.builder().limit(10).timestamp(new Date().getTime()).recvWindow(5_000L).build());
        System.out.println(optionOrderResults);


        System.out.println("\n ------new option openOrders-----");
        List<OptionOrderResult> optionOpenOrders
                = client.getOptionOpenOrders(OptionOpenOrderRequest.builder().timestamp(new Date().getTime()).recvWindow(5_000L).build());
        System.out.println(optionOpenOrders);


        System.out.println("\n ------new option myTrades-----");
        List<OptionMatchResult> optionMatchResults
                = client.getOptionMyTrades(OptionTradeRequest.builder().symbol("BTC0226CS4000").timestamp(new Date().getTime()).recvWindow(5_000L).build());
        System.out.println(optionMatchResults);
    }
}
