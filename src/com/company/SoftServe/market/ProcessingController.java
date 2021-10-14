package com.company.SoftServe.market;

import java.util.List;
import java.util.TreeMap;

public class ProcessingController {
    private TreeMap<Integer, Integer> bid = new TreeMap();
    private TreeMap<Integer, Integer> ask = new TreeMap();
    private int bestBid = 0;
    private int bestAsk = 0;

    StringBuilder requestProcessing(List<String> arrays) {
        StringBuilder sb = new StringBuilder("");
        switch (arrays.get(0)) {
            case "u":
                if (arrays.get(3).equals("bid")) {
                    bid.put(Integer.parseInt(arrays.get(1)), Integer.parseInt(arrays.get(2)));
                    bestBid = bid.lastEntry().getKey();
                    return null;
                } else if (arrays.get(3).equals("ask")) {
                    ask.put(Integer.valueOf(arrays.get(1)), Integer.valueOf(arrays.get(2)));
                    bestAsk = ask.firstEntry().getKey();
                    return null;
                }
                break;
            case "q":
                if (arrays.get(1).equals("best_bid")) {
                    sb.append(bestBid);
                    sb.append(",");
                    sb.append(bid.getOrDefault(bestBid, 0));
                    return sb;
                } else if (arrays.get(1).equals("best_ask")) {
                    sb.append(bestAsk);
                    sb.append(",");
                    sb.append(ask.getOrDefault(bestAsk, 0));
                    return sb;
                } else if (arrays.get(1).equals("size")) {
                    int size = Integer.parseInt(arrays.get(2));
//                    if (size <= bestBid) {
//                        sb.append(bid.getOrDefault(size, 0));
//                    } else if (size >= bestAsk) {
//                        sb.append(ask.getOrDefault(size, 0));
//                    }
                    sb.append(Math.abs(bid.getOrDefault(size, 0)-ask.getOrDefault(size, 0)));
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

//    private boolean checkAndAddAsk(List<String> arrays) {
//        if (bid.getOrDefault(Integer.parseInt(arrays.get(1)), 0) > 0) {
//            int size = bid.get(Integer.parseInt(arrays.get(1)));
//            if (size > Integer.parseInt(arrays.get(2))) {
//                size = bid.get(Integer.parseInt(arrays.get(1))) - Integer.parseInt(arrays.get(2));
//                bid.remove(Integer.parseInt(arrays.get(1)));
//                bid.put(Integer.parseInt(arrays.get(1)), size);
//                return true;
//            } else if (size < Integer.parseInt(arrays.get(2))) {
//                bid.remove(Integer.parseInt(arrays.get(1)));
//                ask.put(Integer.parseInt(arrays.get(1)) - size, Integer.parseInt(arrays.get(2)));
//                bestAsk = ask.firstEntry().getKey();
//                return true;
//            } else if (size == Integer.parseInt(arrays.get(2))) {
//                bid.remove(Integer.parseInt(arrays.get(1)));
//                return true;
//            }
//        } else {
//            ask.put(Integer.valueOf(arrays.get(1)), Integer.valueOf(arrays.get(2)));
//            bestAsk = ask.firstEntry().getKey();
//            return true;
//        }
//        return false;
//    }
//
//    private boolean checkAndAddBid(List<String> arrays) {
//        if (ask.getOrDefault(Integer.parseInt(arrays.get(1)), 0) > 0) {
//            int size = ask.get(Integer.parseInt(arrays.get(1)));
//            if (size > Integer.parseInt(arrays.get(2))) {
//                size = ask.get(Integer.parseInt(arrays.get(1))) - Integer.parseInt(arrays.get(2));
//                ask.remove(Integer.parseInt(arrays.get(1)));
//                ask.put(Integer.parseInt(arrays.get(1)), size);
//                return true;
//            } else if (size < Integer.parseInt(arrays.get(2))) {
//                ask.remove(Integer.parseInt(arrays.get(1)));
//                bid.put(Integer.parseInt(arrays.get(1)) - size, Integer.parseInt(arrays.get(2)));
//                bestBid = bid.lastEntry().getKey();
//                return true;
//            } else if (size == Integer.parseInt(arrays.get(2))) {
//                ask.remove(Integer.parseInt(arrays.get(1)));
//                return true;
//            }
//        } else {
//            bid.put(Integer.parseInt(arrays.get(1)), Integer.parseInt(arrays.get(2)));
//            bestBid = bid.lastEntry().getKey();
//            return true;
//        }
//        return false;
//    }

    private void buy(List<String> arrays) {
        int sharesCount = Integer.parseInt(arrays.get(2));
        while (ask.get(bestAsk) < sharesCount) {
            sharesCount -= ask.get(bestAsk);
            ask.pollFirstEntry();
            bestAsk = ask.firstEntry().getKey();
        }
        int sharesDifference = ask.get(bestAsk) - sharesCount;
        ask.pollFirstEntry();
        ask.put(bestAsk, sharesDifference);
    }

    private void sell(List<String> arrays) {
        int sharesCount = Integer.parseInt(arrays.get(2));
        while (bid.get(bestBid) < sharesCount) {
            sharesCount -= bid.get(bestBid);
            bid.pollLastEntry();
            bestBid = bid.lastEntry().getKey();
        }
        int sharesDifference = bid.get(bestBid) - sharesCount;
        bid.pollLastEntry();
        bid.put(bestBid, sharesDifference);
    }
}
