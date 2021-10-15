package com.company.SoftServe.market;

import java.util.List;
import java.util.TreeMap;

public class ProcessingController {
    private TreeMap<Integer, Integer> bid = new TreeMap();
    private TreeMap<Integer, Integer> ask = new TreeMap();

    StringBuilder requestProcessing(List<String> arrays) {
        StringBuilder sb = new StringBuilder("");
        switch (arrays.get(0)) {
            case "u":
                if (arrays.get(3).equals("bid")) {
                    if (Integer.parseInt(arrays.get(2)) == 0) {
                        bid.remove(Integer.parseInt(arrays.get(1)));
                    } else {
                        bid.put(Integer.parseInt(arrays.get(1)), Integer.parseInt(arrays.get(2)));
                    }
                } else if (arrays.get(3).equals("ask")) {
                    if (Integer.parseInt(arrays.get(2)) == 0) {
                        ask.remove(Integer.parseInt(arrays.get(1)));
                    } else {
                        ask.put(Integer.valueOf(arrays.get(1)), Integer.valueOf(arrays.get(2)));
                    }
                }
                break;
            case "q":
                if (arrays.get(1).equals("best_bid")) {
                    sb.append(bid.lastEntry().getKey());
                    sb.append(",");
                    sb.append(bid.getOrDefault(bid.lastEntry().getKey(), 0));
                    return sb;
                } else if (arrays.get(1).equals("best_ask")) {
                    sb.append(ask.firstEntry().getKey());
                    sb.append(",");
                    sb.append(ask.getOrDefault(ask.firstEntry().getKey(), 0));
                    return sb;
                } else if (arrays.get(1).equals("size")) {
                    int size = Integer.parseInt(arrays.get(2));
                    sb.append(Math.abs(bid.getOrDefault(size, 0) - ask.getOrDefault(size, 0)));
                    return sb;
                }
                break;
            case "o":
                if (arrays.get(1).equals("sell")) {
                    sell(arrays);
                } else if (arrays.get(1).equals("buy")) {
                    buy(arrays);
                }
                break;
        }
        return null;
    }

    private void buy(List<String> arrays) {
        int sharesCount = Integer.parseInt(arrays.get(2));
        while (sharesCount != 0) {
            if (ask.get(ask.firstEntry().getKey()) <= sharesCount) {
                sharesCount -= ask.get(ask.firstEntry().getKey());
                ask.remove(ask.firstEntry().getKey());
            } else {
                ask.put(ask.firstEntry().getKey(), ask.get(ask.firstEntry().getKey()) - sharesCount);
                sharesCount = 0;
            }
        }
    }

    private void sell(List<String> arrays) {
        int sharesCount = Integer.parseInt(arrays.get(2));
        while (sharesCount != 0) {
            if (bid.get(bid.lastEntry().getKey()) <= sharesCount) {
                sharesCount -= bid.get(bid.lastEntry().getKey());
                bid.remove(bid.lastEntry().getKey());
            } else {
                bid.put(bid.lastEntry().getKey(), bid.get(bid.lastEntry().getKey()) - sharesCount);
                sharesCount = 0;
            }
        }
    }
}
