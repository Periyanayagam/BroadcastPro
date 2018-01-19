package com.perusudroid.broadcastpro.internetbroad;

import android.net.ConnectivityManager;
import android.net.LinkProperties;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import android.net.ConnectivityManager.NetworkCallback;
/**
 * Created by Intel on 28-12-2017.
 */

public class NetworkRequestBuilderSample {
    public NetworkRequestBuilderSample() {
        NetworkRequest.Builder requestBuilder = new NetworkRequest.Builder();
        requestBuilder.addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET);
        requestBuilder.addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR);
        NetworkRequest networkRequest = requestBuilder.build();
        ConnectivityManager connectivityManager = null;
        connectivityManager.requestNetwork(networkRequest, new NetworkCallback() {
            @Override
            public void onAvailable(Network network) {
                super.onAvailable(network);				// network.openConnection(url)
            }
            @Override
            public void onLosing(Network network, int maxMsToLive) {
                super.onLosing(network, maxMsToLive);
            }
            @Override
            public void onLost(Network network) {
                super.onLost(network);
            }
            @Override
            public void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
                super.onCapabilitiesChanged(network, networkCapabilities);
            }
            @Override
            public void onLinkPropertiesChanged(Network network, LinkProperties linkProperties) {
                super.onLinkPropertiesChanged(network, linkProperties);
            }
        });
    }
}
