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

import java.util.Objects;

public class Purchasing implements StockObserver {
  private ReceivingStock receivingStock;

  public Purchasing(ReceivingStock receivingStock) {
    this.receivingStock = Objects.requireNonNull(receivingStock, "receivingStock must be non-null");
  }

  public void buy(Part part, int count) {
    Objects.requireNonNull(part, "part must be non-null");
    if (count <= 0)
      throw new IllegalArgumentException("count must be positive");
    receivingStock.insert(part, count);
  }

  public ReceivingStock getStock() {
    return receivingStock;
  }

  @Override
  public void onPartCountChanged(Part part, int count) {
    Objects.requireNonNull(part, "part must be non-null");
    if (count <= 0)
      throw new IllegalArgumentException("count must be positive");

    if (count < receivingStock.getMinStockItems()) // if count is below threshold minStockItems
      buy(part, receivingStock.getMaxStockItems() - count); // ...stock up until maxStockItems again.
  }
}