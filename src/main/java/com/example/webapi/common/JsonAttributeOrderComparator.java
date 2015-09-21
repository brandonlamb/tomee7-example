package com.example.webapi.common;

import java.util.Comparator;

public class JsonAttributeOrderComparator implements Comparator<String> {

  @Override
  public int compare(final String o1, final String o2) {
    return o1.compareTo(o2);
  }

  @Override
  public Comparator<String> reversed() {
    return Comparator.<String>reverseOrder();
  }
}
