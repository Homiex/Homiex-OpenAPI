package io.homiex.api.client.impl;

import java.util.List;

import io.homiex.api.client.HomiexOptionApiRestClient;
import io.homiex.api.client.constant.HomiexConstants;
import io.homiex.api.client.domain.account.request.CancelOrderRequest;
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
import io.homiex.api.client.service.HomiexOptionApiService;

import static io.homiex.api.client.impl.HomiexApiServiceGenerator.createService;
import static io.homiex.api.client.impl.HomiexApiServiceGenerator.executeSync;

/**
 * Implementation of Homiex's Option REST API using Retrofit with synchronous/blocking method calls.
 */
public class HomiexOptionApiRestClientImpl implements HomiexOptionApiRestClient {

    private final HomiexOptionApiService bHexOptionApiService;

    public HomiexOptionApiRestClientImpl(String baseUrl, String apiKey, String secret) {
        bHexOptionApiService = createService(baseUrl, HomiexOptionApiService.class, apiKey, secret);
    }

    @Override
    public List<TokenOptionResult> getOptions(OptionsRequest request) {
        return executeSync(bHexOptionApiService.getOptions(request.getExpired()));
    }

    @Override
    public OptionOrderResult newOptionOrder(OptionOrderRequest request) {
        return executeSync(bHexOptionApiService.newOptionOrder(
                request.getSymbol(),
                request.getOrderSide() == null ? "" : request.getOrderSide().name(),
                request.getOrderType() == null ? "" : request.getOrderType().name(),
                request.getTimeInForce().name(),
                request.getQuantity(),
                request.getPrice(),
                request.getClientOrderId(),
                request.getRecvWindow(),
                request.getTimestamp()
        ));
    }

    @Override
    public OptionOrderResult cancelOptionOrder(CancelOrderRequest cancelOrderRequest) {
        return executeSync(bHexOptionApiService.cancelOptionOrder(
                cancelOrderRequest.getOrderId(),
                cancelOrderRequest.getClientOrderId(),
                cancelOrderRequest.getRecvWindow(),
                cancelOrderRequest.getTimestamp()
        ));
    }

    @Override
    public List<OptionOrderResult> getOptionOpenOrders(OptionOpenOrderRequest orderRequest) {
        return executeSync(bHexOptionApiService.getOptionOpenOrders(
                orderRequest.getSymbol(),
                orderRequest.getOrderId(),
                orderRequest.getLimit(),
                orderRequest.getOrderSide() == null ? "" : orderRequest.getOrderSide().name(),
                orderRequest.getOrderType() == null ? "" : orderRequest.getOrderType().name(),
                orderRequest.getRecvWindow(),
                orderRequest.getTimestamp()
        ));
    }

    @Override
    public List<OptionOrderResult> getOptionHistoryOrders(OptionHistoryOrderRequest orderRequest) {
        return executeSync(bHexOptionApiService.getOptionHistoryOrders(
                orderRequest.getSymbol(),
                orderRequest.getOrderSide() == null ? "" : orderRequest.getOrderSide().name(),
                orderRequest.getOrderType() == null ? "" : orderRequest.getOrderType().name(),
                orderRequest.getLimit(),
                orderRequest.getOrderStatus() == null ? "" : orderRequest.getOrderStatus().name(),
                orderRequest.getRecvWindow(),
                orderRequest.getTimestamp()
        ));
    }

    @Override
    public List<OptionMatchResult> getOptionMyTrades(OptionTradeRequest request) {
        return executeSync(bHexOptionApiService.getOptionMyTrades(
                request.getSymbol(),
                request.getFromId(),
                request.getToId(),
                request.getLimit(),
                request.getOrderSide() == null ? "" : request.getOrderSide().name(),
                request.getRecvWindow(),
                request.getTimestamp()
        ));
    }

    @Override
    public List<PositionResult> getOptionPositions(OptionPositionRequest request) {
        return executeSync(bHexOptionApiService.getOptionPositions(
                request.getSymbol(),
                request.getRecvWindow(),
                request.getTimestamp()
        ));
    }

    @Override
    public List<SettlementResult> getOptionSettlements(OptionSettlementRequest request) {
        return executeSync(bHexOptionApiService.getOptionSettlements(
                request.getSymbol(),
                request.getRecvWindow(),
                request.getTimestamp()
        ));
    }
}
