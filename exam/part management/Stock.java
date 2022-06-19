// Copyright 2022 Julius Schmidt
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public abstract class Stock {
  private Map<Part, Integer> parts = new HashMap<Part, Integer>();
  private List<StockObserver> observers = new ArrayList<StockObserver>();

  public int getCount(Part part) {
    Objects.requireNonNull(part, "part must be non-null");
    Integer amountOfPart = parts.get(part);
    return amountOfPart == null ? -1 : amountOfPart;
  }

  public boolean insert(Part part, int amount) {
    Objects.requireNonNull(part, "part must be non-null");
    if (amount <= 0)
      throw new IllegalArgumentException("amount must be positive");

    Integer amountOfPart = parts.get(part);
    amountOfPart = (amountOfPart == null) ? amount : amountOfPart + amount;

    parts.put(part, amountOfPart);
    return true; // NOTE: weird.
  }

  public boolean remove(Part part, int amount) {
    Objects.requireNonNull(part, "part must be non-null");
    if (amount <= 0)
      throw new IllegalArgumentException("amount must be positive");

    Integer amountOfPart = parts.get(part);
    if (amountOfPart == null || (amountOfPart - amount) < 0)
      return false;
    parts.put(part, amountOfPart - amount);
    notifyPartCountChanged(part);
    return true;
  }

  public void addObserver(StockObserver observer) {
    Objects.requireNonNull(observer, "observer must be non-null");
    if (!observers.contains(observer))
      observers.add(observer);
  }

  private void notifyPartCountChanged(Part part) {
    Objects.requireNonNull(part, "part must be non-null");
    Integer partCount = parts.get(part);
    for (StockObserver observer : observers)
      observer.onPartCountChanged(part, partCount);
  }
}